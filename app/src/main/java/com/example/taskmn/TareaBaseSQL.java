package com.example.taskmn;

import android.content.ContentValues;
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

        ContentValues contentValues = new ContentValues();
        contentValues.put (COL_NOMBRE, "Entrega del proyecto");
        contentValues.put (COL_VENCIMIENTO, "19/06/2020");
        contentValues.put (COL_ASIGNATURA, "Dispositivos mobiles");
        contentValues.put (COL_DESCRIPCION, "Integrar la persistencia de datos en la tarea.");
        db.insert(TABLA_TAREA, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put (COL_NOMBRE, "Examen");
        contentValues.put (COL_VENCIMIENTO, "19/06/2020");
        contentValues.put (COL_ASIGNATURA, "Dispositivos mobiles");
        contentValues.put (COL_DESCRIPCION, "El tercer examen de la asigntura.");
        db.insert(TABLA_TAREA, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put (COL_NOMBRE, "Práctica");
        contentValues.put (COL_VENCIMIENTO, "18/06/2020");
        contentValues.put (COL_ASIGNATURA, "Inteligencia Artificial");
        contentValues.put (COL_DESCRIPCION, "Ultima práctica sobre redes de bayes.");
        db.insert(TABLA_TAREA, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put (COL_NOMBRE, "Tarea de riesgos");
        contentValues.put (COL_VENCIMIENTO, "20/06/2020");
        contentValues.put (COL_ASIGNATURA, "Riesgo tecnologico");
        contentValues.put (COL_DESCRIPCION, "Tarea sobre el analisis de riesgos del proyecto final.");
        db.insert(TABLA_TAREA, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put (COL_NOMBRE, "Tarea de Ingenieria de software");
        contentValues.put (COL_VENCIMIENTO, "22/06/2020");
        contentValues.put (COL_ASIGNATURA, "Ingenieria de software");
        contentValues.put (COL_DESCRIPCION, "Tarea sobre los modelos de calidad de software.");
        db.insert(TABLA_TAREA, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put (COL_NOMBRE, "Examen de Inteligencia Artificial");
        contentValues.put (COL_VENCIMIENTO, "19/06/2020");
        contentValues.put (COL_ASIGNATURA, "Inteligencia Artificial");
        contentValues.put (COL_DESCRIPCION, "El utlimo examen acerca de probabilidad condicional.");
        db.insert(TABLA_TAREA, null, contentValues);

    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLA_TAREA);
        onCreate(db);
    }
}
