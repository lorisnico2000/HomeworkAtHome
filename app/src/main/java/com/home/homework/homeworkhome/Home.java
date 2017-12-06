package com.home.homework.homeworkhome;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private Spinner subjectSpinner;
    private ListView lw;
    public static DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        subjectSpinner = (Spinner) findViewById(R.id.spinner);
        db = new DatabaseHandler(getBaseContext());
        loadSubjects();

        lw = (ListView) findViewById(R.id.listview);
        if(db.getAllHW() != null){
            HomeworkAdapter hwAdapter = new HomeworkAdapter(this,  android.R.layout.simple_list_item_1, db.getAllHW());
            lw.setAdapter(hwAdapter);
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View actionB = findViewById(R.id.action_b);
        final FloatingActionButton buttonB = (FloatingActionButton) actionB;
        buttonB.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View view) {
                                           //Intent intent = new Intent(getBaseContext(), Work.class);
                                           //startActivity(intent);

                                           builder.setTitle("Title");

                                           final EditText input = new EditText(getApplicationContext());

                                           input.setInputType(InputType.TYPE_CLASS_TEXT);
                                           builder.setView(input);

                                           builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                               @Override
                                               public void onClick(DialogInterface dialog, int which) {
                                                   db.addSubject(input.getText().toString());
                                                   loadSubjects();
                                               }
                                           });
                                           builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                               @Override
                                               public void onClick(DialogInterface dialog, int which) {
                                                   dialog.cancel();
                                               }
                                           });

                                           builder.show();
                                       }
                                   });
        final View actionA = findViewById(R.id.action_a);
        actionA.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), Work.class);
                startActivity(intent);
            }
        });

        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private Spinner loadSubjects(){
        SubjectAdapter adapter = new SubjectAdapter(this, android.R.layout.simple_spinner_item, Home.db.getAllSubjects("Kein Fach"));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapter);
        subjectSpinner.setSelection(0);
        return subjectSpinner;
    }
    private void loadHomework(){
        //TODO
    }


}
