package com.censoGenerador.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.censoGenerador.controls.dao.services.CensoServices;
import com.censoGenerador.controls.dao.services.FamiliaServices;
import com.censoGenerador.controls.dao.services.GeneradorServices;
import com.censoGenerador.list.LinkedList;
import com.censoGenerador.models.Familia;

import java.util.HashMap;

@Path("censoAPI")
public class CensoAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamiliesWithGenerador() throws Exception {
        HashMap map = new HashMap<>();
        CensoServices cs = new CensoServices();
        FamiliaServices fs = new FamiliaServices();

        LinkedList<Familia> familias = fs.getListAll();
        cs.getCenso().setFamilias(familias);
        cs.getCenso().determinarFamiliasConGenerador();
        cs.saveFamiliasConGenerador();

        LinkedList<Familia> familiasConGenerador = cs.getCenso().getFamiliasConGenerador();

        if (familiasConGenerador == null || familiasConGenerador.isEmpty() || familias == null || familias.isEmpty()) {
            map.put("msg", "OK");
            map.put("data", new Object[]{});
        } else {
            map.put("msg", "OK");
            map.put("data", familiasConGenerador.toArray());
        }

        return Response.ok(map).build();
    }

    @Path("/familias")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFamilies() {
        HashMap map = new HashMap<>();
        FamiliaServices fs = new FamiliaServices();

        map.put("msg", "OK");
        map.put("data", fs.getListAll().toArray());

        if (fs.getListAll().isEmpty()) {
            map.put("data", new Object[]{});
        }

        return Response.ok(map).build();
    }

    @Path("/familias/save")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveFamilies(HashMap map) {
        HashMap res = new HashMap<>();

        try {
            FamiliaServices fs = new FamiliaServices();
            fs.getFamilia().setApellido(map.get("apellido").toString());
            fs.getFamilia().setNroIntegrantes(Integer.parseInt(map.get("nroIntegrantes").toString()));
            fs.save();

            res.put("msg", "OK");
            res.put("data", "Famila registrada correctamente");

            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/familias/get/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamily(@PathParam("id") Integer id) throws Exception {
        HashMap map = new HashMap<>();
        FamiliaServices fs = new FamiliaServices();
        Familia familia = fs.get(id);

        if (familia == null || familia.getId() == null) {
            map.put("msg", "Error");
            map.put("data", "No existe esa persona");
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }

        map.put("msg", "OK");
        map.put("data", familia);

        return Response.ok(map).build();
    }

    @Path("/familias/update")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateFamilies(HashMap map) {
        HashMap res = new HashMap<>();

        try {
            FamiliaServices fs = new FamiliaServices();
            fs.setFamilia(fs.get(Integer.parseInt(map.get("id").toString())));
            fs.getFamilia().setApellido(map.get("apellido").toString());
            fs.getFamilia().setNroIntegrantes(Integer.parseInt(map.get("nroIntegrantes").toString()));
            fs.update();

            res.put("msg", "OK");
            res.put("data", "Famila actualizada correctamente");

            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/familias/delete")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFamilies(HashMap map) {
        HashMap res = new HashMap<>();

        try {
            FamiliaServices fs = new FamiliaServices();
            Integer id = Integer.parseInt(map.get("id").toString());
            Familia familia = fs.get(id);

            if (familia == null || familia.getId() == null) {
                res.put("msg", "Error");
                res.put("data", "No existe esa persona");
                return Response.status(Response.Status.BAD_REQUEST).entity(res).build();
            }

            fs.setFamilia(familia);
            fs.delete();

            res.put("msg", "OK");
            res.put("data", "Famila eliminada correctamente");

            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "Error");
            res.put("data", e.toString());

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

    @Path("/generadores")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGeneradores() {
        HashMap map = new HashMap<>();
        GeneradorServices gs = new GeneradorServices();

        map.put("msg", "OK");
        map.put("data", gs.getListAll().toArray());

        if (gs.getListAll().isEmpty()) {
            map.put("data", new Object[]{});
        }

        return Response.ok(map).build();
    }
}
