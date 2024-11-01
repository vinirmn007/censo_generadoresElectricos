package com.censoGenerador.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.censoGenerador.controls.dao.FamiliaDao;
import com.censoGenerador.controls.dao.services.FamiliaServices;
import com.censoGenerador.controls.dao.services.GeneradorServices;
import com.censoGenerador.list.LinkedList;
import com.censoGenerador.models.Familia;

import java.util.HashMap;

@Path("censoAPI")
public class CensoAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamiliesWithGenerador() {
        HashMap map = new HashMap<>();
        FamiliaServices gs = new FamiliaServices();

        try {

            
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("msg", "OK");
        map.put("data", gs.getListAll().toArray());

        return Response.ok(map).build();
    }

    @Path("/familias")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFamilies() {
        HashMap map = new HashMap<>();
        FamiliaServices gs = new FamiliaServices();

        try {
            gs.getFamilia().setApellido("Roman");
            gs.getFamilia().setNroIntegrantes(3);
            gs.save();
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("msg", "OK");
        map.put("data", gs.getListAll().toArray());

        return Response.ok(map).build();
    }

    @Path("/generadores")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGeneradores() {
        HashMap map = new HashMap<>();
        GeneradorServices gs = new GeneradorServices();

        try {
            gs.getGenerador().setMarca("Yamaha");
            gs.getGenerador().setModelo("X1");
            gs.getGenerador().setPrecio(159.99F);
            gs.getGenerador().setConsumoPorHora(1352.0);
            gs.getGenerador().setEnergiaGenerada(1431.8);
            gs.getGenerador().setUso("Imprenta");
            gs.save();
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("msg", "OK");
        map.put("data", gs.getListAll().toArray());

        return Response.ok(map).build();
    }
}
