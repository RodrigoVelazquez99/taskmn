package com.example.taskmn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgregarTareaActivity extends AppCompatActivity {


    private final static String NUEVA_TAREA = "NUEVA_TAREA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_tarea);
        final EditText nombreTarea = (EditText) findViewById(R.id.nombre_agregar);
        final EditText asignaturaTarea = (EditText) findViewById(R.id.asignatura_agregar);
        final EditText descripcionTarea = (EditText) findViewById(R.id.descripcion_agregar);
        final CalendarView calendar = (CalendarView) findViewById(R.id.vencimiento_agregar);
        Button guardar = (Button) findViewById(R.id.boton_agregar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                String nombre = nombreTarea.getText().toString();
                String asignatura = asignaturaTarea.getText().toString();
                String descripcion = descripcionTarea.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaActual = new Date();
                Date fechaVencimiento = new Date(calendar.getDate());
                String vencimiento = sdf.format(fechaVencimiento);
                String actual = sdf.format(fechaActual);
                if (TextUtils.isEmpty(nombre) || TextUtils.isEmpty(asignatura) || TextUtils.isEmpty(descripcion)
                    || TextUtils.isEmpty(vencimiento)){
                    Toast incompletos = Toast.makeText(getApplicationContext(), "Ingresa todos los campos", Toast.LENGTH_LONG);
                    incompletos.show();
                } else if (fechaVencimiento.after(fechaActual) || vencimiento.equals(actual)) {
                    Tarea nuevaTarea = new Tarea(vencimiento, nombre, asignatura, descripcion);
                    Intent intent = new Intent();
                    intent.putExtra(NUEVA_TAREA, nuevaTarea);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Toast fechaInvalida = Toast.makeText(getApplicationContext(), "Ingresa una fecha valida", Toast.LENGTH_LONG);
                    fechaInvalida.show();
                }
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = Integer.toString(dayOfMonth) + "/" + Integer.toString(month + 1) + "/" + Integer.toString(year);
                Date calendarDate = null;
                try {
                    calendarDate = sdf.parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                calendar.setDate(calendarDate.getTime());
            }
        });

    }
}
