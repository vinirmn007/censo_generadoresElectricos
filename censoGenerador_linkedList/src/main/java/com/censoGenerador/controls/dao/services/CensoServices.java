package com.censoGenerador.controls.dao.services;

import com.censoGenerador.controls.dao.CensoDao;
import com.censoGenerador.list.LinkedList;
import com.censoGenerador.models.Censo;

public class CensoServices {
    private CensoDao censo;

    public CensoServices() {
        this.censo = new CensoDao();
    }

    public Censo getcenso() {
        return this.censo.getCenso();
    }

    public void setcenso(Censo censo) {
        this.censo.setCenso(censo);
    }

    public LinkedList getListAll() {
        return this.censo.getListAll();
    }

    public Boolean saveFamiliasConGenerador() throws Exception {
        return this.censo.saveFamiliasConGenerador();
    }
}
