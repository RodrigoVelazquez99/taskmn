package com.example.taskmn;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/* Adaptador para la lista de tareas*/

public class TareaAdapter extends ArrayAdapter {

    private ArrayList<Tarea> tareas;
    private Context context;
    private int viewRes;
    private Resources res;

    public TareaAdapter(Context context, int textViewResourceId, ArrayList<Tarea> tareas) {
        super (context, textViewResourceId, tareas);
        this.tareas = tareas;
        this.context = context;
        this.viewRes = textViewResourceId;
        this.res = context.getResources();
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes, parent, false);
        }
        final Tarea tarea = tareas.get(position);
        if (tarea != null) {
            final TextView nombre = (TextView) view.findViewById(R.id.nombre);
            final TextView asignatura = (TextView) view.findViewById(R.id.asignatura);
            final TextView vencimiento = (TextView) view.findViewById(R.id.vencimiento);
            final String tareaNombre = String.format(res.getString(R.string.nombre_tarea), tarea.getNombre());
            nombre.setText(tareaNombre);
            final String tareaAsignatura = String.format(res.getString(R.string.asignatura_tarea), tarea.getAsignatura());
            asignatura.setText(tareaAsignatura);
            final String tareaVencimiento = String.format(res.getString(R.string.vencimiento_tarea), tarea.getVencimiento());
            vencimiento.setText(tareaVencimiento);
        }
        return view;
    }

    @Override
    public int getCount() {
        return tareas.size();
    }

}
