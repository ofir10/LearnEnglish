package com.firedeluxe.learnenglish;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.firedeluxe.learnenglish.MainActivity.context;

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 2;

    // Database Name
    private static final String DATABASE_NAME = "Manager";

    // Wordss table name
    private static final String TABLE_UNIT1 = "unit1";
    private static final String TABLE_UNIT2A = "unit2A";
    private static final String TABLE_UNIT2B = "unit2B";
    private static final String TABLE_UNIT3A = "unit3A";
    private static final String TABLE_UNIT3B = "unit3B";
    private static final String TABLE_UNIT4A = "unit4A";
    private static final String TABLE_UNIT4B = "unit4B";
    private static final String TABLE_UNIT5A = "unit5A";
    private static final String TABLE_UNIT5B = "unit5B";
    private static final String TABLE_UNIT6A = "unit6A";
    private static final String TABLE_UNIT6B = "unit6B";
    private static final String TABLE_UNIT7A = "unit7A";
    private static final String TABLE_UNIT7B = "unit7B";
    private static final String TABLE_UNIT8A = "unit8A";
    private static final String TABLE_UNIT8B = "unit8B";
    private static final String TABLE_UNIT9A = "unit9A";
    private static final String TABLE_UNIT9B = "unit9B";
    private static final String TABLE_UNIT10B = "unit10B";
    private static final String TABLE_UNITCUSTOM = "unitcustom";
    private static ArrayList<String> words_table, translate_table;
    private static String CURRECTTABLE;

    // Words Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_ORIGINAL = "original";
    private static final String KEY_TRANSLATE = "translate";
    private static final String KEY_KNOWLEDGE= "knowledge";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

    public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHandler(Context context, String name, CursorFactory factory,
                           int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for(int i = 0; i < 19; i++) {
            int currectUnitFile = R.raw.unit1;
            CURRECTTABLE = "";
            switch (i) {
                case 0: currectUnitFile = R.raw.unit1;
                        CURRECTTABLE = TABLE_UNIT1;
                        break;
                case 1: currectUnitFile = R.raw.unit2a;
                        CURRECTTABLE = TABLE_UNIT2A;
                        break;
                case 2: currectUnitFile = R.raw.unit2b;
                        CURRECTTABLE = TABLE_UNIT2B;
                        break;
                case 3: currectUnitFile = R.raw.unit3a;
                        CURRECTTABLE = TABLE_UNIT3A;
                        break;
                case 4: currectUnitFile = R.raw.unit3b;
                        CURRECTTABLE = TABLE_UNIT3B;
                        break;
                case 5: currectUnitFile = R.raw.unit4a;
                        CURRECTTABLE = TABLE_UNIT4A;
                        break;
                case 6: currectUnitFile = R.raw.unit4b;
                        CURRECTTABLE = TABLE_UNIT4B;
                        break;
                case 7: currectUnitFile = R.raw.unit5a;
                        CURRECTTABLE = TABLE_UNIT5A;
                        break;
                case 8: currectUnitFile = R.raw.unit5b;
                        CURRECTTABLE = TABLE_UNIT5B;
                        break;
                case 9: currectUnitFile = R.raw.unit6a;
                        CURRECTTABLE = TABLE_UNIT6A;
                        break;
                case 10: currectUnitFile = R.raw.unit6b;
                        CURRECTTABLE = TABLE_UNIT6B;
                        break;
                case 11: currectUnitFile = R.raw.unit7a;
                        CURRECTTABLE = TABLE_UNIT7A;
                        break;
                case 12: currectUnitFile = R.raw.unit7b;
                         CURRECTTABLE = TABLE_UNIT7B;
                         break;
                case 13: currectUnitFile = R.raw.unit8a;
                         CURRECTTABLE = TABLE_UNIT8A;
                         break;
                case 14: currectUnitFile = R.raw.unit8b;
                         CURRECTTABLE = TABLE_UNIT8B;
                         break;
                case 15: currectUnitFile = R.raw.unit9a;
                         CURRECTTABLE = TABLE_UNIT9A;
                         break;
                case 16: currectUnitFile = R.raw.unit9b;
                         CURRECTTABLE = TABLE_UNIT9B;
                         break;
                case 17: currectUnitFile = R.raw.unit10b;
                         CURRECTTABLE = TABLE_UNIT10B;
                         break;
                case 18: currectUnitFile = R.raw.unitcustom;
                         CURRECTTABLE = TABLE_UNITCUSTOM;
                         break;
            }
            words_table = (ArrayList) new ArrayList<>();
            translate_table = (ArrayList) new ArrayList<>();
            try {
                InputStream inputStream = context.getResources().openRawResource(currectUnitFile);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String eachline = bufferedReader.readLine();
                boolean isOriginal = true;
                while (eachline != null) {
                    if (eachline.equals("&")) {
                        isOriginal = false;
                    } else {
                        if (isOriginal) {
                            words_table.add(eachline);
                        } else {
                            translate_table.add(eachline);
                        }
                    }
                    eachline = bufferedReader.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("ofir", "table name: " + CURRECTTABLE);
            String CREATE_CONTACTS_TABLE = "CREATE TABLE " + CURRECTTABLE + "("
                    + KEY_ID + " INTEGER,"
                    + KEY_ORIGINAL + " TEXT,"
                    + KEY_TRANSLATE + " TEXT,"
                    + KEY_KNOWLEDGE + " TEXT)";
            db.execSQL(CREATE_CONTACTS_TABLE);
            for (int j = 0; j < words_table.size(); j++) {
                ContentValues values = new ContentValues();
                values.put(KEY_ID, j);
                values.put(KEY_ORIGINAL, words_table.get(j));
                values.put(KEY_TRANSLATE, translate_table.get(j));
                values.put(KEY_KNOWLEDGE, "0");
                db.insert(CURRECTTABLE, null, values);
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT2A);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT2B);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT3A);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT3B);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT4A);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT4B);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT5A);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT5B);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT6A);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT6B);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT7A);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT7B);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT8A);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT8B);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT9A);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT9B);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNIT10B);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNITCUSTOM);

        // Create tables again
        onCreate(db);
    }
    // Adding new word
    public void addWord(Word word, String tablename) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ORIGINAL, word.getOriginal()); // Word original
        values.put(KEY_TRANSLATE, word.getTranslate()); // Word translate
        values.put(KEY_KNOWLEDGE, word.getKnowledge()); // Word knowledge
        values.put(KEY_ID, word.get_id());

        // Inserting Row
        db.insert(tablename, null, values);
        //  db.close(); // Closing database connection
    }
    // Getting All Words
    public ArrayList<Word> getAllWords(String tablename) {
        ArrayList<Word> wordList = new ArrayList<Word>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + tablename;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Word word = new Word();
                word.set_id(Integer.parseInt(cursor.getString(0)));
                word.setOriginal(cursor.getString(1));
                word.setTranslate(cursor.getString(2));
                word.setKnowledge(cursor.getString(3));

                // Adding word to list
                wordList.add(word);
            } while (cursor.moveToNext());
        }
        // return word list
        return wordList;
    }

    // Set Word Knowledge
    public void setWordKnowledge(String _id, String knowledge, String tablename) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_KNOWLEDGE, knowledge);
        db.update(tablename, cv, KEY_ID + "= ?", new String[] {_id});
    }
}