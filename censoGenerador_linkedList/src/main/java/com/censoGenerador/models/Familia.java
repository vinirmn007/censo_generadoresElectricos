package com.censoGenerador.models;

import com.censoGenerador.list.LinkedList;

public class Familia {
    private Integer id;
    private String apellido;
    private Integer nroIntegrantes;
    private LinkedList<Generador> generadores;

    public Familia(String apellido) {
        this.id = 0;
        this.apellido = apellido;
        this.nroIntegrantes = 0;
        this.generadores = new LinkedList();
    }

    //SETTERS Y GETTERS
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getNroIntegrantes() {
        return this.nroIntegrantes;
    }

    public void setNroIntegrantes(Integer nroIntegrantes) {
        this.nroIntegrantes = nroIntegrantes;
    }

    public LinkedList<Generador> getGeneradores() {
        return this.generadores;
    }

    public void setGenerador(Generador generador) {
        this.generadores.add(generador);
    }

    @Override
    public String toString() {
        return "Familia{" + "apellido='" + apellido + '\'' + ", generadores=" + generadores + '}';
    }
}
