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
    private final static String TAREA_ACTUAL = "TAREA_ACTUAL";
    private final static int MODIFICAR_TAREA = 0;
    private final static String TAREA_MODIFICADA = "TAREA_MODIFICADA";
    private final static String TAREA_ANTERIOR = "TAREA_ANTERIOR";

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
                Intent intent = new Intent(getApplicationContext(), ModificarTareaActivity.class);
                intent.putExtra(TAREA_ACTUAL, actual);
                startActivityForResult(intent, MODIFICAR_TAREA);
            }
        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MODIFICAR_TAREA) {
            if (resultCode == RESULT_OK) {
                Tarea modificada = data.getParcelableExtra(TAREA_MODIFICADA);
                Intent intent = new Intent ();
                intent.putExtra(TAREA_MODIFICADA, modificada);
                intent.putExtra(TAREA_ANTERIOR, actual);
                setResult(RESULT_OK,intent);
                finish();
            }
        }
    }
}
