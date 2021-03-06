package com.home.homework.homeworkhome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.home.homework.homeworkhome.Adapter.ExpandableListAdapter;
import com.home.homework.homeworkhome.Adapter.SubjectAdapter;
import com.home.homework.homeworkhome.Model.Homework;
import com.home.homework.homeworkhome.Model.Subject;
import com.homeActivity.homework.homeworkhome.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Die HomeWorkActivity dient zu Erstellung einer neuen Hausaufgabe.
 * Hier lassen sich die Attribute einer Aufgabe vergeben und ein Fach auswählen.
 */
public class HomeWorkActivity extends AppCompatActivity {

    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;

    private List<String> listDataHeader;
    private HashMap<String,List<String>> listHash;

    private Spinner subjectSpinner;
    private SubjectAdapter adapter;
    private Subject selectedSubject;

    private Button btnSave;
    private EditText txtName;
    private EditText txtDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        setupInput();

        setupSubjectSpinner();

        loadSubjects();

        /*
        listView = (ExpandableListView)findViewById(R.id.list);
        initData();
        listAdapter = new ExpandableListAdapter(this,listDataHeader,listHash);
        listView.setAdapter(listAdapter);
        */

    }

    private void setupSubjectSpinner() {
        subjectSpinner = (Spinner) findViewById(R.id.workSpinner);
        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSubject = adapter.getItem(position);
                Toast.makeText(getApplicationContext(), position + " " + selectedSubject.getId() + selectedSubject.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void setupInput() {
        txtName = (EditText) findViewById(R.id.txtName);
        txtDesc = (EditText) findViewById(R.id.txtDescription);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String n = txtName.getText().toString();
                if(TextUtils.isEmpty(txtName.getText().toString())){

                    txtName.setError("Bitte einen Namen eingeben...");

                }else{

                    String d = txtDesc.getText().toString();
                    Subject s = selectedSubject;

                    Homework hw = new Homework(null, n, d, s);
                    hw.save();
                    Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                    startActivity(intent);

                }
            }
        });
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
        adapter = new SubjectAdapter(this, android.R.layout.simple_spinner_item, HomeActivity.db.getAllSubjects("Kein Fach"));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapter);
        subjectSpinner.setSelection(0);
        return subjectSpinner;
    }

}
