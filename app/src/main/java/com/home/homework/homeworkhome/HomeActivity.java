package com.home.homework.homeworkhome;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.home.homework.homeworkhome.Adapter.HomeworkAdapter;
import com.home.homework.homeworkhome.Adapter.SubjectAdapter;
import com.home.homework.homeworkhome.Model.DatabaseHandler;
import com.home.homework.homeworkhome.Model.Subject;
import com.homeActivity.homework.homeworkhome.R;

/**
 * Die HomeActivity dient als Einstiegspunkt unserer App.
 * Auf dieser Seite werden alle Aufgaben aufgelistet und können nach Fach sortiert werden.
 * Des weiteren lassen sich Aufgaben löschen.
 */
public class HomeActivity extends AppCompatActivity {

    private Spinner subjectSpinner;
    private ListView lw;
    public static DatabaseHandler db;
    private Subject selectedSubject;
    private SubjectAdapter adapter;
    private HomeworkAdapter hwAdapter;

    /**
     *
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DatabaseHandler(getBaseContext());
        setupSubjectSpinner();
        loadSubjects();
        setupHwList();
        setupFAB();

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
        // automatically handle clicks on the HomeActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupSubjectSpinner() {
        subjectSpinner = (Spinner) findViewById(R.id.spinner);
        subjectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedSubject = adapter.getItem(position);
                loadHomework(selectedSubject);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void setupFAB(){
        final AlertDialog.Builder subjectBuilder = new AlertDialog.Builder(this);
        final View actionB = findViewById(R.id.action_b);
        final FloatingActionButton buttonB = (FloatingActionButton) actionB;
        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                subjectBuilder.setTitle("Fach Name");

                final EditText input = new EditText(getApplicationContext());

                input.setInputType(InputType.TYPE_CLASS_TEXT);
                subjectBuilder.setView(input);

                subjectBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.addSubject(input.getText().toString());
                        loadSubjects();
                    }
                });
                subjectBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                subjectBuilder.show();

            }
        });

        final View actionA = findViewById(R.id.action_a);
        actionA.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HomeWorkActivity.class);
                startActivity(intent);
            }
        });

        final FloatingActionsMenu menuMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
    }

    private void setupHwList(){
        lw = (ListView) findViewById(R.id.listview);
        final AlertDialog.Builder deleteBuilder;
        lw.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                new AlertDialog.Builder(HomeActivity.this)
                        .setTitle("Löschen")
                        .setMessage("Möchten Sie die Aufgabe wirklich löschen?")
                        .setIcon(android.R.drawable.ic_delete)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                hwAdapter.deleteItem(position);
                                loadHomework(selectedSubject);
                                Toast.makeText(HomeActivity.this, "Aufgabe wurde erfolgreich gelöscht.", Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
                return false;
            }
        });
        selectedSubject = new Subject(null, "Alle Fächer");
        loadHomework(selectedSubject);
    }

    private void loadSubjects(){
        adapter = new SubjectAdapter(this, android.R.layout.simple_spinner_item, HomeActivity.db.getAllSubjects("Alle Fächer"));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapter);
        subjectSpinner.setSelection(0);
    }
    private void loadHomework(Subject s){
        if(db.getAllHWBySubject(s) != null){
            hwAdapter = new HomeworkAdapter(this,  android.R.layout.simple_list_item_1, db.getAllHWBySubject(s));
            lw.setAdapter(hwAdapter);
        }
    }


}
