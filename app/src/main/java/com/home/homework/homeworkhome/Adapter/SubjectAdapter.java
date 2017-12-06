package com.home.homework.homeworkhome.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.home.homework.homeworkhome.Model.Subject;

import java.util.ArrayList;

/**
 * Created by loris on 25.11.2017.
 *
 * Der SubjectAdapter wird benutzt, um die Fächer in einem Spinner (Dropdown Selection) anzuzeigen.
 * Die Klasse dient dazu, den Namen in dem Spinner anzuzeigen und im Hintergrund mit der ID verknüpft zu haben.
 */

public class SubjectAdapter extends ArrayAdapter<Subject> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private ArrayList<Subject> values;

    public SubjectAdapter(Context context, int textViewResourceId,
                          ArrayList<Subject> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount(){
        return values.size();
    }

    @Override
    public Subject getItem(int position){
        return values.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    /**
     * Gibt das View Objekt zurück, dass im Spinner angezeit wird.
     *
     * @param position Gibt an, welche Position die Aufgabe im Array hat.
     * @param convertView Ungenutzter Parameter.
     * @param parent Parent Objekt (Spinner)
     * @return View Objekt, dass als Item in dem Spinner angezeit wird.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setPadding(10,10,10,10);
        label.setTextSize(16);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(values.get(position).getName());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    /**
     * Gibt das View Objekt zurück, dass im Spinner angezeit wird, WENN er ausgeklappt ist.
     *
     * @param position Gibt an, welche Position die Aufgabe im Array hat.
     * @param convertView Ungenutzter Parameter.
     * @param parent Parent Objekt (Spinner)
     * @return View Objekt, dass als Item in dem Spinner angezeit wird.
     */
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setPadding(20,20,20,20);
        label.setTextSize(17);
        label.setText(values.get(position).getName());

        return label;
    }

}
