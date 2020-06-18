package com.example.taskmn;

import android.os.Parcel;
import android.os.Parcelable;

public class Tarea implements Parcelable {

    private int id;
    private String vencimiento;
    private String nombre;
    private String asignatura;
    private String descripcion;

    public Tarea(){
    }


    public Tarea(String vencimiento, String nombre, String asignatura, String descripcion) {
        super();
        this.vencimiento = vencimiento;
        this.nombre = nombre;
        this.asignatura = asignatura;
        this.descripcion = descripcion;
    }

    public Tarea (Parcel source) {
        this.vencimiento = source.readString();
        this.nombre = source.readString();
        this.asignatura = source.readString();
        this.descripcion = source.readString();
        this.id = source.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.vencimiento);
        dest.writeString(this.nombre);
        dest.writeString(this.asignatura);
        dest.writeString(this.descripcion);
        dest.writeInt(this.id);
    }

    public static final Parcelable.Creator<Tarea> CREATOR = new Parcelable.Creator<Tarea>() {
        @Override
        public Tarea createFromParcel (Parcel source) {
            return new Tarea(source);
        }
        @Override
        public Tarea[] newArray(int size) {
            return new Tarea[size];
        }
    };

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

    public int getId () {
        return this.id;
    }

    public void setId (int id){
        this.id = id;
    }

    @Override
    public boolean equals (Object obj) {
        Tarea other = (Tarea) obj;
        if (this.nombre.equals(other.getNombre()) &&
            this.vencimiento.equals(other.getVencimiento()) &&
            this.descripcion.equals(other.getDescripcion()) &&
            this.asignatura.equals(other.getAsignatura())) {
            return true;
        }
        return false;
    }
}


