package com.example.taskmn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class TareaBDD {

    private static final int VERSION = 1;
    private static final String NOM_BDD = "taskmn.db";
    private static final String TABLA_TAREA = "TABLA_TAREA";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NOMBRE = "NOMBRE";
    private static final int NUM_COL_NOMBRE = 1;
    private static final String COL_VENCIMIENTO = "VENCIMIENTO";
    private static final int NUM_COL_VENCIMIENTO = 2;
    private static final String COL_ASIGNATURA = "ASIGNATURA";
    private static final int NUM_COL_ASIGNATURA = 3;
    private static final String COL_DESCRIPCION = "DESCRIPCION";
    private static final int NUM_COL_DESCRIPCION = 4;

    private SQLiteDatabase bdd;

    private TareaBaseSQL tareas;

    public TareaBDD (Context context){
        tareas = new TareaBaseSQL(context, NOM_BDD, null, VERSION );
    }

    public void openForWrite () {
        bdd = tareas.getWritableDatabase();
    }

    public void openForRead () {
        bdd = tareas.getReadableDatabase();
    }

    public void close () {
        bdd.close();
    }

    public SQLiteDatabase getBdd() {
        return bdd;
    }

    public Tarea insertaTarea (Tarea tarea) {
        ContentValues contentValues = new ContentValues();
        contentValues.put (COL_NOMBRE, tarea.getNombre());
        contentValues.put (COL_VENCIMIENTO, tarea.getVencimiento());
        contentValues.put (COL_ASIGNATURA, tarea.getAsignatura());
        contentValues.put (COL_DESCRIPCION, tarea.getDescripcion());
        bdd.insert(TABLA_TAREA, null, contentValues);
        return getTarea(tarea);
    }

    public Tarea actualizaTarea (Tarea actual) {
        ContentValues contentValues = new ContentValues();
        contentValues.put (COL_NOMBRE, actual.getNombre());
        contentValues.put (COL_VENCIMIENTO, actual.getVencimiento());
        contentValues.put (COL_ASIGNATURA, actual.getAsignatura());
        contentValues.put (COL_DESCRIPCION, actual.getDescripcion());
        bdd.update(TABLA_TAREA, contentValues, COL_ID + " = ?", new String[]{Integer.toString(actual.getId())});
        return getTarea(actual);
    }


    public int eliminaTarea (Tarea tarea) {
        return bdd.delete(TABLA_TAREA,COL_ID + " = " + tarea.getId(),null);
    }


    public Tarea getTarea (Tarea tarea) {
        String where = COL_NOMBRE + " = '" + tarea.getNombre() + "' AND " + COL_VENCIMIENTO + " = '" + tarea.getVencimiento() + "' AND " +
                COL_ASIGNATURA + " = '" + tarea.getAsignatura() + "' AND " + COL_DESCRIPCION + " = '" + tarea.getDescripcion() + "'";
        Cursor c = bdd.query(TABLA_TAREA, new String[] {COL_ID, COL_NOMBRE, COL_VENCIMIENTO, COL_ASIGNATURA, COL_DESCRIPCION },
                where , null, null , null, COL_ID);
        Tarea tmp = cursorTarea (c);
        return tmp;
    }

    private Tarea cursorTarea (Cursor c) {
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        Tarea tarea = new Tarea();
        if (c.moveToFirst()) {
            tarea.setId(c.getInt(NUM_COL_ID));
            tarea.setNombre(c.getString(NUM_COL_NOMBRE));
            tarea.setVencimiento(c.getString(NUM_COL_VENCIMIENTO));
            tarea.setAsignatura(c.getString(NUM_COL_ASIGNATURA));
            tarea.setDescripcion(c.getString(NUM_COL_DESCRIPCION));
        }
        c.close();
        return tarea;
    }

    public ArrayList<Tarea> obtenerTareas () {
        Cursor c = bdd.query(TABLA_TAREA, new String[] {COL_ID, COL_NOMBRE, COL_VENCIMIENTO, COL_ASIGNATURA, COL_DESCRIPCION},
                            null, null, null, null, COL_NOMBRE);
        if (c.getCount() == 0) {
            c.close();
            return new ArrayList<Tarea>();
        }
        ArrayList<Tarea> tareas = new ArrayList<Tarea>();
        while (c.moveToNext()) {
            Tarea tarea = new Tarea();
            tarea.setId(c.getInt(NUM_COL_ID));
            tarea.setNombre(c.getString(NUM_COL_NOMBRE));
            tarea.setVencimiento(c.getString(NUM_COL_VENCIMIENTO));
            tarea.setAsignatura(c.getString(NUM_COL_ASIGNATURA));
            tarea.setDescripcion(c.getString(NUM_COL_DESCRIPCION));
            tareas.add(tarea);
        }
        c.close();
        return tareas;
    }


}
