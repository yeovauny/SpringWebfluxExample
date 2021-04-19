package com.example.bff.example.bff.example.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductDTO {
   private  long id;
   private String nombre;
   private String descripcion;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
