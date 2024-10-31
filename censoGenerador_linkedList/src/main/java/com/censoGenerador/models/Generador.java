package com.censoGenerador.models;

public class Generador {
    private Integer id;
    private String marca;
    private String modelo;
    private Double consumoPorHora;
    private Double energiaGenerada;
    private Float precio;
    private String uso;

    public Generador() {
        this.id = 0;
        this.marca = "";
        this.modelo = "";
        this.consumoPorHora = 0.0;
        this.energiaGenerada = 0.0;
        this.precio = 0.0F;
        this.uso = "";
    }

    //SETTERS Y GETTERS
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getConsumoPorHora() {
        return this.consumoPorHora;
    }

    public void setConsumoPorHora(Double consumoPorHora) {
        this.consumoPorHora = consumoPorHora;
    }

    public Double getEnergiaGenerada() {
        return this.energiaGenerada;
    }

    public void setEnergiaGenerada(Double energiaGenerada) {
        this.energiaGenerada = energiaGenerada;
    }

    public Float getPrecio() {
        return this.precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public String getUso() {
        return this.uso;
    }

    public void setUso(String uso) {
		this.uso = uso;
	}

    @Override
    public String toString() {
        return "Generador{" + "marca=" + marca + '\'' + ", modelo=" + modelo + '}';
    }
}
