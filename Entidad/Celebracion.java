/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

import java.time.LocalDate;

/**
 * Uned Alaljuela
 * Entidad general para la recoleccion de datos
 * incluye metodo to string general
 * Celebracion.java fecha 9/2/2026
 */
public class Celebracion {
    
    private int id;
    private LocalDate fecha;
    private String descripcion;
    private String pais;
    
public Celebracion(int id, LocalDate fecha, String descripcion, String pais){
    
    this.id = id;
    this.fecha = fecha;
    this.descripcion = descripcion;
    this.pais = pais;
    
}

//metodos Getter

    public int getId() {
        return id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPais() {
        return pais;
    }

// Metodos Setter

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
//Metodo to string

    @Override
    public String toString() {
        return "Celebracion{" + "id=" + id + ", fecha=" + fecha + ", descripcion=" + descripcion + ", pais=" + pais + '}';
    }

    
    
}
