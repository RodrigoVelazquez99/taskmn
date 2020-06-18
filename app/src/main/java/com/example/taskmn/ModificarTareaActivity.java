package com.example.taskmn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ModificarTareaActivity extends AppCompatActivity {

    private final static String TAREA_ACTUAL = "TAREA_ACTUAL";
    private final static String TAREA_MODIFICADA = "TAREA_MODIFICADA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizar_tarea);
        Intent intent = getIntent();
        final Tarea actual = intent.getParcelableExtra(TAREA_ACTUAL);
        final EditText nombreTarea = (EditText) findViewById(R.id.act_nombre);
        final EditText asignaturaTarea = (EditText) findViewById(R.id.act_asignatura);
        final EditText descripcionTarea = (EditText) findViewById(R.id.act_descripcion);
        final CalendarView calendar = (CalendarView) findViewById(R.id.act_vencimiento);
        nombreTarea.setText(actual.getNombre());
        asignaturaTarea.setText(actual.getAsignatura());
        descripcionTarea.setText(actual.getDescripcion());
        Button actualizar = findViewById(R.id.actualizar);
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nuevoNombre = nombreTarea.getText().toString();
                String nuevaAsignatura = asignaturaTarea.getText().toString();
                String nuevaDescripcion = descripcionTarea.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date fechaActual = new Date();
                Date fechaVencimiento = new Date(calendar.getDate());
                String vencimiento = sdf.format(fechaVencimiento);
                String fechaA = sdf.format(fechaActual);
                if (TextUtils.isEmpty(nuevoNombre) || TextUtils.isEmpty(nuevaAsignatura) || TextUtils.isEmpty(nuevaDescripcion)
                || TextUtils.isEmpty(vencimiento)) {
                    Toast incompletos = Toast.makeText(getApplicationContext(), "Ingresa todos los campos", Toast.LENGTH_LONG);
                    incompletos.show();
                } else if (fechaVencimiento.after(fechaActual) || vencimiento.equals(fechaA)) {
                    Tarea nuevaTarea = new Tarea (vencimiento, nuevoNombre, nuevaAsignatura, nuevaDescripcion);
                    nuevaTarea.setId(actual.getId());
                    Intent intent = new Intent();
                    intent.putExtra(TAREA_MODIFICADA, nuevaTarea);
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
