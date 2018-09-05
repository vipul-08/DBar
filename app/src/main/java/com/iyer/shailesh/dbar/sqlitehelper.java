package com.iyer.shailesh.dbar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by sweth on 8/7/2016.
 */
public class sqlitehelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "dbar";


    // Tables
    private static final String TABLE_DETAILS = "details";
    private static final String TABLE_SET1 = "set1";
    private static final String TABLE_SET2 = "set2";
    private static final String TABLE_SET3 = "set3";
    private static final String TABLE_SET4 = "set4";
    private static final String TABLE_SET5 = "set5";
    private static final String TABLE_SET6 = "set6";
    private static final String TABLE_SET7 = "set7";
    private static final String TABLE_LOCATIONS = "locations";


    //fields in riddles table
    private static final String RID = "rid";
    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";
    private static final String DBID = "dbid";
    private static final String IMAGE = "imagePresent";


    //fields in details table
    private static final String ID = "id";
    private static final String STARTTIME = "start";
    private static final String ENDTIME = "end";
    private static final String R1 = "r1";
    private static final String R2 = "r2";
    private static final String R3 = "r3";
    private static final String R4 = "r4";
    private static final String R5 = "r5";
    private static final String R6 = "r6";
    private static final String SET = "setno";


    //fields in locations table
    private static final String LID = "lid";
    private static final String LOC = "loc";


    //different methods in the database

    private SQLiteDatabase dbase;

    public sqlitehelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SET1 + "(" + RID + " INTEGER PRIMARY KEY ," + QUESTION + " TEXT," + ANSWER + " TEXT," + DBID + " TEXT,"+ IMAGE +" TEXT );";
        db.execSQL(query1);
        query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SET2 + "(" + RID + " INTEGER PRIMARY KEY ," + QUESTION + " TEXT," + ANSWER + " TEXT," + DBID + " TEXT,"+ IMAGE +" TEXT );";
        db.execSQL(query1);
        query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SET3 + "(" + RID + " INTEGER PRIMARY KEY ," + QUESTION + " TEXT," + ANSWER + " TEXT," + DBID + " TEXT,"+ IMAGE +"  TEXT );";
        db.execSQL(query1);
        query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SET4 + "(" + RID + " INTEGER PRIMARY KEY ," + QUESTION + " TEXT," + ANSWER + " TEXT," + DBID + " TEXT,"+ IMAGE +"  TEXT );";
        db.execSQL(query1);
        query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SET5 + "(" + RID + " INTEGER PRIMARY KEY ," + QUESTION + " TEXT," + ANSWER + " TEXT," + DBID + " TEXT,"+ IMAGE +"  TEXT );";
        db.execSQL(query1);
        query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SET6 + "(" + RID + " INTEGER PRIMARY KEY ," + QUESTION + " TEXT," + ANSWER + " TEXT," + DBID + " TEXT,"+ IMAGE +"  TEXT );";
        db.execSQL(query1);
        query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SET7 + "(" + RID + " INTEGER PRIMARY KEY ," + QUESTION + " TEXT," + ANSWER + " TEXT," + DBID + " TEXT,"+ IMAGE +"  TEXT );";
        db.execSQL(query1);
        String query2 = "CREATE TABLE IF NOT EXISTS " + TABLE_DETAILS + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + STARTTIME + " TEXT," + ENDTIME + " TEXT," + R1 + " INTEGER," + R2 + " INTEGER," + R3 + " INTEGER," + R4 + " INTEGER," + R5 + " INTEGER," + R6 + " INTEGER," + SET + " INTEGER);";
        db.execSQL(query2);
        String query4 = "CREATE TABLE IF NOT EXISTS " + TABLE_LOCATIONS + "(" + LID + " INTEGER PRIMARY KEY AUTOINCREMENT," + LOC + " TEXT);";
        db.execSQL(query4);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void insertLocations() {

        Set<String> hash_Set = new HashSet<String>();
        dbase = this.getWritableDatabase();
        Cursor res;
        String query1;
        String number = "";

        for(int k = 1 ; k <= 7 ; k++) {
            number = String.valueOf(k);
            String tableName = "set" + number;
            query1 = "SELECT DISTINCT "+ ANSWER + " FROM " + tableName;
            res=dbase.rawQuery(query1,null);
            while (res.moveToNext()) {
                hash_Set.add(res.getString(0));
            }
        }
        Iterator<String> it = hash_Set.iterator();
        while (it.hasNext()){
            String temp_ans = it.next();
            Log.d("TEMP_ANS",temp_ans+"");
            ContentValues values = new ContentValues();
            values.put(LOC,temp_ans);
            dbase.insert(TABLE_LOCATIONS,null,values);
        }
    }
    public void insertquestions(int set) {
        dbase = this.getWritableDatabase();
        Log.e("set no", String.valueOf(set));
        String tablename = "set" + String.valueOf(set);
        if (set==1)
        {
            dbase=this.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE_SET1;
            Cursor cursor = dbase.rawQuery(selectQuery,null);
            int flag=cursor.getCount();
            if (flag == 0)
            {
                ContentValues values = new ContentValues();
                //r1
                values.put(RID, "1");
                values.put(QUESTION, "Decode the image: ");
                values.put(ANSWER, "Gopuram");
                values.put(DBID, "db1");
                values.put(IMAGE , "1_1");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "Lina Is Binge Reading ARY Books.");
                values.put(ANSWER, "Library");
                values.put(DBID, "db2");
                values.put(IMAGE , "NO");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "You will never see so many great personalities together in a room.");
                values.put(ANSWER, "Auditorium");
                values.put(DBID, "db3");
                values.put(IMAGE,"NO");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "Reshuffle:\nIEAEMPHTHTRA");
                values.put(ANSWER, "Amphitheatre");
                values.put(DBID, "db4");
                values.put(IMAGE , "NO");
                dbase.insert(tablename, null, values);
                //r5
                values.put(RID, "5");
                values.put(QUESTION, "Drink from it but it’s not a glass, It consists of 7 letters, includes 3 vowels and has 4 consonants.");
                values.put(ANSWER, "Canteen");
                values.put(DBID, "db5");
                values.put(IMAGE , "NO");
                dbase.insert(tablename, null, values);
                //r6
                values.put(RID, "6");
                values.put(QUESTION, "A + A + A = 24 \n" +
                        "Y + Y + A = 20\n" +
                        "Y x B – B = 10            \n" +
                        "A-5, Y-6, B/2 = ?");
                values.put(ANSWER, "301");
                values.put(DBID, "db6");
                values.put(IMAGE , "NO");
                dbase.insert(tablename, null, values);

            }
            cursor.close();
        }
        else if(set ==2)
        {
            dbase=this.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE_SET2;
            Cursor cursor=dbase.rawQuery(selectQuery,null);
            int flag=cursor.getCount();
            if (flag == 0)
            {
                ContentValues values = new ContentValues();
                //r1
                values.put(RID, "1");
                values.put(QUESTION, "Decode the image: ");
                values.put(ANSWER, "NT2");
                values.put(IMAGE, "2_1");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "P + P + P = 30\n" +
                        "P + 2T = 18 \n" +
                        "T – W = 2 \n" +
                        "P/10, T-4, W-1 =?");
                values.put(ANSWER, "101");
                values.put(DBID, "db2");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "Run all you want, you’re not going anywhere!");
                values.put(ANSWER, "Gym");
                values.put(DBID, "db3");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "When Oreo Really Kicked, Sam HOPped..");
                values.put(ANSWER, "Workshop");
                values.put(DBID, "db4");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r5
                values.put(RID, "5");
                values.put(QUESTION, "I try to send a letter, But all I do is receive. The port to all things outside. I am metal I believe."
                +"\n_ _ _ _   _ _ _ _");
                values.put(ANSWER, "Main Gate");
                values.put(DBID, "db5");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r6
                values.put(RID, "6");
                values.put(QUESTION, "Reshuffle: \nROPINGTALK\n_ _ _ _ _ _ _   _ _ _");
                values.put(ANSWER, "Parking Lot");
                values.put(DBID, "db6");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);

            }
            cursor.close();
        }
        else if(set ==3)
        {
            dbase=this.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE_SET3;
            Cursor cursor=dbase.rawQuery(selectQuery,null);
            int flag=cursor.getCount();
            if (flag == 0)
            {
                ContentValues values = new ContentValues();
                //r1
                values.put(RID, "1");
                values.put(QUESTION, "2S =10 \n" +
                        "S x G + G =12\n" +
                        "S x G – K x S = S \n" +
                        "S-2, G/2, Kx9  =?");
                values.put(ANSWER, "319");
                values.put(DBID, "db1");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "Decode the image: ");
                values.put(ANSWER, "Canteen");
                values.put(DBID, "db2");
                values.put(IMAGE, "3_2");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "Reshuffle: \nROUPMAG");
                values.put(ANSWER, "Gopuram");
                values.put(DBID, "db3");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "When each turn finds you in a new mystery, a new adventure or a new romance.");
                values.put(ANSWER,  "Library");
                values.put(DBID, "db4");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r5
                values.put(RID, "5");
                values.put(QUESTION, "OFF Iceland, Coming to England");
                values.put(ANSWER, "Office");
                values.put(DBID, "db5");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r6
                values.put(RID, "6");
                values.put(QUESTION, "I swing in and out to let you through, sometimes I creek, that’s your clue\n_ _ _ _   _ _ _ _");
                values.put(ANSWER, "Main Gate");
                values.put(DBID, "db6");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);

            }
            cursor.close();
        }
        else if (set==4)
        {
            dbase=this.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE_SET4;
            Cursor cursor=dbase.rawQuery(selectQuery,null);
            int flag=cursor.getCount();
            if (flag == 0) {
                ContentValues values = new ContentValues();
                //r1
                values.put(RID, "1");
                values.put(QUESTION, "Reshuffle: \nAYBILRR" );
                values.put(ANSWER, "Library");
                values.put(DBID, "db1");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "A + A + A = 24 \n" +
                        "Y + Y + A = 20\n" +
                        "Y x B – B = 10\n" +
                        "A-5, Y-6, B/2 = ?");
                values.put(ANSWER, "319");
                values.put(DBID, "db2");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "Decode the image: ");
                values.put(ANSWER, "Workshop");
                values.put(DBID, "db3");
                values.put(IMAGE, "4_3");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "Acoustics are very important here, the most common thing is stage fear");
                values.put(ANSWER, "Amphitheatre");
                values.put(DBID, "db4");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r5
                values.put(RID, "5");
                values.put(QUESTION, "Gaming On Pc Under Really Angry Mother");
                values.put(ANSWER, "Gopuram");
                values.put(DBID, "db5");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r6
                values.put(RID, "6");
                values.put(QUESTION, "What’s black and white and read all over?");
                values.put(ANSWER, "Library");
                values.put(DBID, "db6");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);

            }
            cursor.close();
        }
        else if (set==5)
        {
            dbase=this.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE_SET5;
            Cursor cursor=dbase.rawQuery(selectQuery,null);
            int flag=cursor.getCount();
            if (flag == 0) {
                ContentValues values = new ContentValues();
                //r1
                values.put(RID, "1");
                values.put(QUESTION, "Decode the image: \n_ _ _ _ _ _ _   _ _ _");
                values.put(ANSWER, "Parking Lot");
                values.put(DBID, "db1");
                values.put(IMAGE, "5_1");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "Maya Alone Is Not Going At Temple Eidiya\n_ _ _ _   _ _ _ _");
                values.put(ANSWER, "Main Gate");
                values.put(DBID, "db2");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "Reshuffle: \nEATENNC");
                values.put(ANSWER, "Canteen");
                values.put(DBID, "db3");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "It has million stories but cannot tell");
                values.put(ANSWER, "Library");
                values.put(DBID, "db4");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r5
                values.put(RID, "5");
                values.put(QUESTION, "The more you come here, the more you get inspired");
                values.put(ANSWER, "Auditorium");
                values.put(DBID, "db5");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r6
                values.put(RID, "6");
                values.put(QUESTION, "3F = 60 \n" +
                        "F + 2U = 30 \n" +
                        "U – K = 3 \n" +
                        "F/20, 0/U, K/2 =?");
                values.put(ANSWER, "101");
                values.put(DBID, "db6");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);

            }
            cursor.close();
        }
        else if (set==6)
        {
            dbase=this.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE_SET6;
            Cursor cursor=dbase.rawQuery(selectQuery,null);
            int flag=cursor.getCount();
            if (flag == 0) {
                ContentValues values = new ContentValues();
                //r1
                values.put(RID, "1");
                values.put(QUESTION, "It is said that work smart and not work hard but here it is vice versa");
                values.put(ANSWER, "Workshop");
                values.put(DBID, "db1");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "Decode the image: \n_ _ _ _ _   _ _ _   _ _ _ _");
                values.put(ANSWER, "First Aid Room");
                values.put(DBID, "db2");
                values.put(IMAGE, "6_2");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "A + A + A = 24 \n" +
                        "Y + Y + A = 20\n" +
                        "Y x B – B = 10            \n" +
                        "A-5, Y-6, B/2 = ?");
                values.put(ANSWER, "301");
                values.put(DBID, "db3");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "Reshuffle: \nFICAREETA");
                values.put(ANSWER, "Cafeteria");
                values.put(DBID, "db4");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r5
                values.put(RID, "5");
                values.put(QUESTION, "Patrick And Rose Kissing In Garage\n_ _ _ _ _ _ _   _ _ _");
                values.put(ANSWER, "Parking Lot");
                values.put(DBID, "db5");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r6
                values.put(RID, "6");
                values.put(QUESTION, "When you are in college, you desperately want to go HERE but when you are HERE you want college back. HERE is where your clue is");
                values.put(ANSWER, "Office");
                values.put(DBID, "db6");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);

            }
            cursor.close();
        }
        else if (set==7)
        {
            dbase=this.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE_SET7;
            Cursor cursor=dbase.rawQuery(selectQuery,null);
            int flag=cursor.getCount();
            if (flag == 0) {
                ContentValues values = new ContentValues();
                //r1
                values.put(RID, "1");
                values.put(QUESTION, "Catherine, Andy and Nick Tranced on Eccentric Edm Night");
                values.put(ANSWER, "Canteen");
                values.put(DBID, "db1");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "Decode the image: ");
                values.put(ANSWER, "101");
                values.put(DBID, "db2");
                values.put(IMAGE, "7_2");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "2S =10 \n" +
                        "S x G + G =12\n" +
                        "S x G – K x S = S \n" +
                        "S-2, G/2, Kx9  =?");
                values.put(ANSWER, "319");
                values.put(DBID, "db3");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "Reshuffle: \nANTIMAGE\n_ _ _ _   _ _ _ _");
                values.put(ANSWER, "Main Gate");
                values.put(DBID, "db4");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r5
                values.put(RID, "5");
                values.put(QUESTION, "A thousand wheels, but move I do not. Call me what I am, call me a lot. What am I?\n_ _ _ _ _ _ _   _ _ _");
                        values.put(ANSWER, "Parking Lot");
                values.put(DBID, "db5");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r6
                values.put(RID, "6");
                values.put(QUESTION, "Like skits? Like dance? Like speeches? This is the place for you");
                values.put(ANSWER, "Auditorium");
                values.put(DBID, "db6");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);

            }
            cursor.close();
        }
    }

    public void createnewuser(int set) {
        for(int i = 1 ; i <= 7 ; i ++)
            insertquestions(i);
        insertLocations();

        dbase = this.getWritableDatabase();
        String query = "SELECT " + ENDTIME + " FROM " + TABLE_DETAILS + " WHERE " + ENDTIME + "=''";
        Cursor cursor = dbase.rawQuery(query, null);
        if (cursor.getCount() == 0) {
            Log.e("user", "new user inserting details");
            ContentValues values = new ContentValues();

            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy kk:mm:ss");
            Date now = new Date();
            String datetime = dateformat.format(now);
            values.put(STARTTIME, datetime);
            values.put(ENDTIME, "");
            values.put(R1, 0);
            values.put(R2, 0);
            values.put(R3, 0);
            values.put(R4, 0);
            values.put(R5, 0);
            values.put(R6, 0);
            values.put(SET, set);
            dbase.insert(TABLE_DETAILS, null, values);

        } else {
            cursor.moveToFirst();
            Log.e("user", "old user continue '" + cursor.getString(0) + "' ok");
        }

        cursor.close();
    }

    //returning the table as an array list object
    public ArrayList<Riddles> initialiseRiddles(int setno) {
        ArrayList<Riddles> ridList = new ArrayList<Riddles>();
        // Select All Query
        String tablename = "set" + String.valueOf(setno);
        String selectQuery = "SELECT  * FROM " + tablename;
        Log.e("query", selectQuery);
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Riddles ridd = new Riddles();
                ridd.setRID(cursor.getInt(0));
                ridd.setQUESTION(cursor.getString(1));
                ridd.setANSWER(cursor.getString(2));
                ridd.setDBID(cursor.getString(3));
                ridd.setIMAGE(cursor.getString(4));

                ridList.add(ridd);
                Log.e("riddlist", "creating list");
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ridList;
    }

    public ArrayList<Riddles> initialiseDetails(ArrayList<Riddles> list) {
        // Select All Query

        String selectQuery = "SELECT " + R1 + "," + R2 + "," + R3 + "," + R4 + "," + R5 + "," + R6 +  " FROM " + TABLE_DETAILS + " WHERE " + ENDTIME + "=''";
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // putting status in Riddle object
        if (cursor.moveToFirst()) {
            for (int i = 0; i < 6; i++) {
                list.get(i).setStatus(cursor.getInt(i));
            }
        }
        cursor.close();
        return list;
    }

    public int get_Status(int position) {
        dbase = this.getReadableDatabase();
        int k = 0;
        Log.d("Awkaat","pos"+position);
        String query = "SELECT r"+position+" FROM " +TABLE_DETAILS + " WHERE " + ENDTIME + "=''";
        Cursor cursor = dbase.rawQuery(query,null);
        if(cursor.moveToFirst()) {
            Log.d("Awkaat","In If");
            k = cursor.getInt(0);
        }
        cursor.close();
        Log.d("Awkaat",k+"");
        return k;
    }

    public void update_Status(int position) {
        dbase = this.getWritableDatabase();
        String query = "SELECT " + R1 + "," + R2 + "," + R3 + "," + R4 + "," + R5 + "," + R6 + " FROM " + TABLE_DETAILS + " WHERE " + ENDTIME + "=''";
        Cursor cursor = dbase.rawQuery(query, null);

        String riddle = cursor.getColumnName(position);
        cursor.moveToLast();
        int status = cursor.getInt(position);
        if (status < 2) {
            status = status + 1;
            ContentValues values = new ContentValues();
            values.put(riddle, status);
            Log.e("QUERY", query + " " + riddle + " " + status);
            dbase.update(TABLE_DETAILS, values, ENDTIME + "= ?", new String[]{""});
        } else {
            Log.e("NOQUERY", "Dragon Ball already caught");
        }
        cursor.close();
    }


    public void finishGame() {
        dbase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_DETAILS + " WHERE " + ENDTIME + "='' AND " + R1 + "='2' AND " + R2 + "='2' AND " + R3 + "='2' AND " + R4 + "='2' AND " + R5 + "='2'  AND " + R6 + "='2';";
        Cursor cursor = dbase.rawQuery(query, null);
        int flag = cursor.getCount();
        if (flag == 1) {
            Log.e("finishGame", "Game won");
            ContentValues values = new ContentValues();
            SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy kk:mm:ss");
            Date now = new Date();
            String datetime = dateformat.format(now);
            values.put(ENDTIME, datetime);
            dbase.update(TABLE_DETAILS, values, ENDTIME + "= ?", new String[]{""});
            Log.e("Database upadate", "game finish");
        }
        cursor.close();
    }

    public boolean canfinishgame() {
        dbase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_DETAILS + " WHERE " + ENDTIME + "='' AND " + R1 + "='2' AND " + R2 + "='2' AND " + R3 + "='2' AND " + R4 + "='2' AND " + R5 + "='2'  AND " + R6 + "='2';";
        Cursor cursor = dbase.rawQuery(query, null);
        int flag = cursor.getCount();
        if (flag == 1) {
            return true;
        }
        return false;
    }

    public boolean isGameInProgress() {
        String selectQuery = "SELECT * FROM " + TABLE_DETAILS + " WHERE " + ENDTIME + "=''";
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        int flag = cursor.getCount();
        if (flag != 0) {
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public String[] get_times() {
        int hours, minutes, seconds, size, i = 0;
        String[] strings;
        String set;
        String selectQuery = "SELECT * FROM " + TABLE_DETAILS + " WHERE " + ENDTIME + "!=''";
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMM-yyyy kk:mm:ss");
        size = cursor.getCount();
        if (size != 0) {
            strings = new String[size];

            hours = 0;
            minutes = 0;
            seconds = 0;
            cursor.moveToFirst();
            do {
                try {
                    Date time1 = dateformat.parse(cursor.getString(1));
                    Date time2 = dateformat.parse(cursor.getString(2));
                    long difference = time2.getTime() - time1.getTime();
                    hours = (int) (difference / (1000 * 60 * 60));
                    minutes = (int) (difference / (1000 * 60)) % 60;
                    seconds = (int) (difference / 1000) % 60;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                set=String.valueOf(cursor.getInt(9));
                String str = "Player " + cursor.getInt(0) + " completed in " + String.valueOf(hours) + " hours " + String.valueOf(minutes) + " mins " + String.valueOf(seconds) + " seconds set:"+set;
                strings[i] = str;
                i++;
            } while (cursor.moveToNext());
            cursor.close();
            return strings;
        }
        strings = new String[1];
        strings[0] = "The game is still in progress please come back here when you finish the game";
        cursor.close();
        return strings;
    }

    //return current playing user set no.
    public int getUserSet() {
        int set = 1;
        String selectQuery = "SELECT " + SET + " FROM " + TABLE_DETAILS + " WHERE " + ENDTIME + "=''";
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            set = cursor.getInt(0);
        }
        return set;
    }

    public int getCurrentQues() {
        dbase = this.getReadableDatabase();
        int quesNum = 0;
        for(int i = 1 ; i < 7 ; i++){
            String query = "SELECT r"+i+" FROM " +TABLE_DETAILS + " WHERE " + ENDTIME + "=''";
            Cursor cursor = dbase.rawQuery(query,null);
            if(cursor.moveToFirst()) {
                int k = cursor.getInt(0);
                if(k==0) {
                    break;
                }
                quesNum = i;
            }
        }
        if(quesNum == 0)
            quesNum = 1;
        return quesNum;
    }

}