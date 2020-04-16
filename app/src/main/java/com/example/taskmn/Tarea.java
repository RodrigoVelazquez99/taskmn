package com.example.taskmn;

public class Tarea {

    String vencimiento;
    String nombre;
    String asignatura;
    String descripcion;

    public Tarea(String vencimiento, String nombre, String asignatura, String descripcion) {
        this.vencimiento = vencimiento;
        this.nombre = nombre;
        this.asignatura = asignatura;
        this.descripcion = descripcion;
    }

    public String getVencimiento() {
        return this.vencimiento;

    }

    public void setVencimiento (String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAsignatura() {
        return this.asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getDescripcion () {
        return this.descripcion;
    }

    public void setDescripcion (String descripcion) {
        this.descripcion = descripcion;
    }

}


