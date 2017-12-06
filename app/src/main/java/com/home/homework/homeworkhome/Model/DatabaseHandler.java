package com.home.homework.homeworkhome.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.home.homework.homeworkhome.HomeActivity;

import java.util.ArrayList;

/**
 * Created by loris on 06.11.2017.
 *
 * Der DatabaseHandler regelt den Zugriff auf die Datenbank.
 * Er ist zuständig für die Erstellung und Aktualisierung der Db.
 * Des weiteren werden Funktionen für alle CRUD Operationen bereitgestellt.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "homework";

    private static final String TABLE_SUBJECT = "subject";
    private static final String TABLE_HOMEWORK = "homework";

    private static final String SUBJECT_ID = "id";
    private static final String SUBJECT_NAME = "name";

    private static final String HW_ID = "id";
    private static final String HW_NAME = "name";
    private static final String HW_DESC = "description";
    private static final String HW_SUBJECT = "fk_subject";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SUBJECT_TABLE = "CREATE TABLE " + TABLE_SUBJECT + "("
                + SUBJECT_ID + " INTEGER PRIMARY KEY," + SUBJECT_NAME + " TEXT"
                + ")";
        db.execSQL(CREATE_SUBJECT_TABLE);
        String CREATE_HOMEWORK_TABLE = "CREATE TABLE " + TABLE_HOMEWORK + "("
                + HW_ID + " INTEGER PRIMARY KEY," + HW_NAME + " TEXT, " + HW_DESC + " TEXT, "+ HW_SUBJECT + " INTEGER"
                + ")";
        db.execSQL(CREATE_HOMEWORK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HOMEWORK);

        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations for Subjects
     */

    /**
     * Neues Fach hinzufügen
     * @param subject Fach Objekt das hinzugefügt wird.
     */
    public void addSubject(String subject) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SUBJECT_NAME, subject);

        db.insert(TABLE_SUBJECT, null, values);
        db.close();
    }

    /**
     * Einzelnes Fach nach ID erhalten.
     * @param id ID nach der gesucht wird.
     * @return gibt das Fach Objekt oder null zurück.
     */
    public Subject getSubject(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SUBJECT, new String[] { SUBJECT_ID,
                        SUBJECT_NAME }, SUBJECT_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Subject subject = new Subject(cursor.getInt(1), cursor.getString(1));
            return subject;
        }else{
            return null;
        }
    }

    /**
     * Gibt alle Fächer zurück.
     * @param defaultMsg Die Message, die als Standard bei einem Dropdown eingestellt ist. (Z.B. Alle Fächer anzeigen)
     * @return Liste aus Fächern.
     */
    public ArrayList<Subject> getAllSubjects(String defaultMsg) {
        ArrayList<Subject> list = new ArrayList<Subject>();
        list.add(new Subject(null, defaultMsg));

        String selectQuery = "SELECT  * FROM " + TABLE_SUBJECT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(new Subject(cursor.getInt(1), cursor.getString(1)));
            } while (cursor.moveToNext());
        }

        return list;
    }

    /**
     * Fach ändern.
     * @param id Id des zu ändernden Fachs.
     * @param name Neuer Name
     */
    public void updateSubject(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SUBJECT_NAME, name);

        db.update(TABLE_SUBJECT, values, SUBJECT_ID + " = ?",
                new String[] { Integer.toString(id) });
    }

    /**
     * Fach löschen.
     * @param id ID des zu löschenden Fachs.
     */
    public void deleteSubject(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SUBJECT, SUBJECT_ID + " = ?",
                new String[] { Integer.toString(id) });
        db.close();
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations for Homework
     */

    /**
     * Neuee Aufgabe hinzufügen
     * @param hw Homework Objekt das hinzugefügt wird.
     */
    public void addHW(Homework hw) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HW_NAME, hw.getName()); // Homework Name
        values.put(HW_DESC, hw.getName()); // Homework Description
        values.put(HW_SUBJECT, hw.getSubject().getId()); // Homework Subject (FK)

        db.insert(TABLE_HOMEWORK, null, values);
        db.close();
    }

    /**
     * Einzelne Aufgabe nach ID erhalten.
     * @param id ID nach der gesucht wird.
     * @return gibt das Aufgabe Objekt oder null zurück.
     */
    public Homework getHW(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HOMEWORK, new String[] { HW_ID,
                        HW_NAME, HW_DESC, HW_SUBJECT }, HW_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Log.d("CursorType", "" + cursor.getType(cursor.getColumnIndex(HW_SUBJECT)));
            Homework hw = new Homework(cursor.getInt(1), cursor.getString(1), cursor.getString(1), HomeActivity.db.getSubject(cursor.getInt(1)));
            return hw;
        }else{
            return null;
        }
    }

    /**
     * Gibt alle Hausaufgaben eines Fachs zurück. Falls ID des Fachs null ist, werden alle Fächer zurückgegeben.
     * @param s Das Fach, nachdem gefiltert wird.
     * @return Liste aus Hausaugaben.
     */
    public ArrayList<Homework> getAllHWBySubject(Subject s) {
        ArrayList<Homework> list = new ArrayList<Homework>();
        String selectQuery;

        if(s.getId() == null){//Select all
            selectQuery = "SELECT  * FROM " + TABLE_HOMEWORK;
        }else{// Select By Subject ID
            selectQuery = "SELECT  * FROM " + TABLE_HOMEWORK + " WHERE " + HW_SUBJECT + " = " + s.getId() ;
        }

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                list.add(new Homework(cursor.getInt(1), cursor.getString(1), cursor.getString(1), getSubject(cursor.getInt(cursor.getColumnIndex(HW_SUBJECT)))));
            } while (cursor.moveToNext());
        }

        return list;
    }

    /**
     * Hausaufgabe ändern.
     * @param hw Die zu ändernde Aufgabe.
     */
    public int updateHW(Homework hw) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HW_NAME, hw.getName()); // Homework Name
        values.put(HW_DESC, hw.getName()); // Homework Description
        values.put(HW_SUBJECT, hw.getSubject().getId()); // Homework Subject (FK)

        return db.update(TABLE_HOMEWORK, values, HW_ID + " = ?",
                new String[] { Integer.toString(hw.getId()) });
    }

    /**
     * Hausaufgabe löschen.
     * @param id ID der zu löschenden Aufgabe.
     */
    public void deleteHW(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_HOMEWORK, HW_ID + " = ?",
                new String[] { Integer.toString(id) });
        Log.d("DELETE", "DELETED MF");
        db.close();
    }

}
