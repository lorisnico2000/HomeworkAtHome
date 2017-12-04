package com.home.homework.homeworkhome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Work extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;
    private Spinner subjectSpinner;
    private Subject selectedSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        subjectSpinner = (Spinner) findViewById(R.id.workSpinner);
        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                selectedSubject = Home.db.getSubject(position -1);
                Toast.makeText(getApplicationContext(), selectedSubject.name, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
        loadSubjects();

        /*
        listView = (ExpandableListView)findViewById(R.id.list);
        initData();
        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);
        */

    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add("Erinnerungen");

        List<String> notifications = new ArrayList<>();
        notifications.add("30.11.2017 08:30");
        notifications.add("14.12.2017 10:10");
        notifications.add("01.01.2018 18:45");

        listHash.put(listDataHeader.get(0),notifications);
    }
    public Spinner loadSubjects(){
        ArrayList<String> spinnerObjects = new ArrayList<String>();
        spinnerObjects.add("Kein Fach");
        for (Subject s : Home.db.getAllSubjects()){
            spinnerObjects.add(s.name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerObjects);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapter);
        subjectSpinner.setSelection(0);
        return subjectSpinner;
    }

}
