package com.example.taskmn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VerTareaActivity extends AppCompatActivity {

    private final static String VER_TAREA = "VER_TAREA";
    private Tarea actual;
    private TextView tareaNombre;
    private TextView tareaAsignatura;
    private TextView tareaDescripcion;
    private TextView tareaVencimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tarea);
        Intent intent = getIntent();
        actual = intent.getParcelableExtra(VER_TAREA);
        Toast.makeText(getApplicationContext(), actual.getNombre(), Toast.LENGTH_LONG).show();
        Button modificar = findViewById(R.id.modificar);
        Button eliminar = findViewById(R.id.eliminar);
        tareaNombre = findViewById(R.id.ver_tarea_nombre);
        tareaAsignatura = findViewById(R.id.ver_asignatura);
        tareaDescripcion = findViewById(R.id.ver_descripcion);
        tareaVencimiento = findViewById(R.id.ver_vencimiento);
        tareaNombre.setText(actual.getNombre());
        tareaAsignatura.setText(actual.getAsignatura());
        tareaDescripcion.setText(actual.getDescripcion());
        tareaVencimiento.setText(actual.getVencimiento());
        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
