package com.example.taskmn;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TareaBaseSQL extends SQLiteOpenHelper {

    private static final String TABLA_TAREA = "TABLA_TAREA";
    private static final String COL_ID = "ID";
    private static final String COL_NOMBRE = "NOMBRE";
    private static final String COL_VENCIMIENTO = "VENCIMIENTO";
    private static final String COL_ASIGNATURA = "ASIGNATURA";
    private static final String COL_DESCRIPCION = "DESCRIPCION";
    private static final String CREATE_DB = "CREATE TABLE " + TABLA_TAREA + " (" +
                                                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                                    COL_VENCIMIENTO + " VARCHAR(10) NOT NULL, " +
                                                    COL_NOMBRE + " TEXT NOT NULL, " +
                                                    COL_ASIGNATURA + " TEXT NOT NULL, " +
                                                    COL_DESCRIPCION + " TEXT NOT NULL);";


    public TareaBaseSQL (Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL(CREATE_DB);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLA_TAREA);
        onCreate(db);
    }
}
