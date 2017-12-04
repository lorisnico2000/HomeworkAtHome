package com.home.homework.homeworkhome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by loris on 14.11.2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "homework";

    // table names
    private static final String TABLE_SUBJECT = "subject";
    private static final String TABLE_HOMEWORK = "homework";

    // Subject Table Columns
    private static final String SUBJECT_ID = "id";
    private static final String SUBJECT_NAME = "name";
    // Homework Table Columns
    private static final String HW_ID = "id";
    private static final String HW_NAME = "name";
    private static final String HW_DESC = "description";
    private static final String HW_SUBJECT = "fk_subject";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SUBJECT + "("
                + SUBJECT_ID + " INTEGER PRIMARY KEY," + SUBJECT_NAME + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECT);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations for Subjects
     */

    // Adding new subject
    void addSubject(String subject) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SUBJECT_NAME, subject); // Contact Name

        // Inserting Row
        db.insert(TABLE_SUBJECT, null, values);
        db.close(); // Closing database connection
    }

    // Getting single subject
    public Subject getSubject(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SUBJECT, new String[] { SUBJECT_ID,
                        SUBJECT_NAME }, SUBJECT_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Subject subject = new Subject(cursor.getInt(1), cursor.getString(1));
        // return contact
        return subject;
    }

    // Getting All subjects
    public ArrayList<Subject> getAllSubjects() {
        ArrayList<Subject> list = new ArrayList<Subject>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SUBJECT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // Adding contact to list
                list.add(new Subject(cursor.getInt(1), cursor.getString(1)));
            } while (cursor.moveToNext());
        }

        // return contact list
        return list;
    }

    // Updating single subject
    public int updateSubject(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SUBJECT_NAME, name);

        // updating row
        return db.update(TABLE_SUBJECT, values, SUBJECT_ID + " = ?",
                new String[] { Integer.toString(id) });
    }

    // Deleting single subject
    public void deleteSubject(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SUBJECT, SUBJECT_ID + " = ?",
                new String[] { Integer.toString(id) });
        db.close();
    }

    // Count subjects
    public int countSubjects() {
        String countQuery = "SELECT  * FROM " + TABLE_SUBJECT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}
