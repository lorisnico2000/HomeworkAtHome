package com.home.homework.homeworkhome.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.home.homework.homeworkhome.Model.Homework;

import java.util.ArrayList;

/**
 * Created by loris on 15.11.2017.
 *
 * Der HomeworkAdapter wird benutzt, um die Aufgaben in einer ListView anzuzeigen.
 * Die Klasse dient dazu, den Namen in der Liste anzuzeigen und im Hintergrund mit der ID verknüpft zu haben.
 */

public class HomeworkAdapter extends ArrayAdapter<Homework> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private ArrayList<Homework> values;


    public HomeworkAdapter(Context context, int textViewResourceId,
                          ArrayList<Homework> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount(){
        return values.size();
    }

    @Override
    public Homework getItem(int position){
        return values.get(position);
    }

    /**
     * Löscht Aufgabe aus der Datenbank.
     * @param position Gibt an, welche Position die Aufgabe im Array hat.
     */
    public void deleteItem(int position){
        values.get(position).delete();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    /**
     * Gibt das View Objekt zurück, dass als Item in der ListView angezeit wird.
     *
     * @param position Gibt an, welche Position die Aufgabe im Array hat.
     * @param convertView Ungenutzter Parameter.
     * @param parent Parent Objekt (ListView)
     * @return View Objekt, dass als Item in der ListView angezeit wird.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(values.get(position).getName());
        label.setPadding(20,20,40,20);
        label.setTextSize(20);

        return label;

    }

}
