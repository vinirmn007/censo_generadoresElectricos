package com.censoGenerador.models;

import com.censoGenerador.list.LinkedList;

public class Censo {
    private LinkedList<Familia> familias;
    private LinkedList<Familia> familiasConGenerador;

    public Censo() {
        this.familias = new LinkedList<>();
        this.familiasConGenerador = new LinkedList<>();
    }

    //GETTERS Y SETTERS

    public LinkedList<Familia> getFamilias() {
        return this.familias;
    }

    public void setFamilias(LinkedList<Familia> familias) {
        this.familias = familias;
    }

    public LinkedList<Familia> getFamiliasConGenerador() {
        determinarFamiliasConGenerador();
        return this.familiasConGenerador;
    }

    public void determinarFamiliasConGenerador() {
        this.familiasConGenerador.reset();

        Familia familiaArr[] = this.familias.toArray();

        for (Familia familia : familiaArr) {
            if (familia.getGeneradores().isEmpty() == false) {
                this.familiasConGenerador.add(familia);
            }
        }
    }
}
