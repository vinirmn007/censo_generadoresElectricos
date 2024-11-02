package com.censoGenerador.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.censoGenerador.controls.dao.services.CensoServices;
import com.censoGenerador.controls.dao.services.FamiliaServices;
import com.censoGenerador.controls.dao.services.GeneradorServices;

import java.util.HashMap;

@Path("censoAPI")
public class CensoAPI {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFamiliesWithGenerador() throws Exception {
        HashMap map = new HashMap<>();
        FamiliaServices gs = new FamiliaServices();
        CensoServices cs = new CensoServices();
        
        cs.getcenso().setFamilias(gs.getListAll());
        cs.getcenso().determinarFamiliasConGenerador();
        cs.saveFamiliasConGenerador();

        map.put("msg", "OK");
        map.put("data", cs.getcenso().getFamiliasConGenerador().toArray());

        return Response.ok(map).build();
    }

    @Path("/familias")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFamilies() {
        HashMap map = new HashMap<>();
        FamiliaServices fs = new FamiliaServices();
        GeneradorServices gs = new GeneradorServices();

        try {
            fs.getFamilia().setApellido("Roman");
            fs.getFamilia().setNroIntegrantes(3);
            fs.getFamilia().setGenerador(gs.getGenerador());
            fs.save();
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("msg", "OK");
        map.put("data", fs.getListAll().toArray());

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
