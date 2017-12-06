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
 * Created by loris on 05.12.2017.
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

    public void deleteItem(int position){
        values.get(position).delete();
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(values.get(position).getName());
        label.setPadding(20,20,40,20);
        label.setTextSize(20);

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

}
