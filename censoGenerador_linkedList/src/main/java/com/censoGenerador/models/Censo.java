package com.censoGenerador.models;

import com.censoGenerador.list.LinkedList;

public class Censo {
    private Integer id;
    private LinkedList<Familia> familias;

    public Censo() {
        this.id = 0;
        this.familias = new LinkedList<>();
    }

    //GETTERS Y SETTERS
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LinkedList<Familia> getFamilias() {
        return this.familias;
    }

    //OPERACIONES
    public void addFamilias(Familia familia) {
        this.familias.add(familia);
    }

    public LinkedList<Familia> getFamiliasConGenerador() {
        Familia familiaArr[] = this.familias.toArray();
        LinkedList<Familia> familiasConGenerador = new LinkedList<>();

        for (int i = 0; i < familiaArr.length; i++) {
            if (familiaArr[i].getGeneradores().isEmpty() == false) {
                familiasConGenerador.add(familiaArr[i]);
            }
        }

        return familiasConGenerador;
    }
}
