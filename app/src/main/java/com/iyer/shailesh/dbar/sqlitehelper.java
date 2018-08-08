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
    private static final String TABLE_SET8 = "set8";
    private static final String TABLE_LOCATIONS = "locations";


    //fields in riddles table
    private static final String RID = "rid";
    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";
    private static final String DBID = "dbid";


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
    private static final String R7 = "r7";
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
        String query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SET1 + "(" + RID + " INTEGER PRIMARY KEY ," + QUESTION + " TEXT," + ANSWER + " TEXT," + DBID + " TEXT);";

        db.execSQL(query1);
        query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SET2 + "(" + RID + " INTEGER PRIMARY KEY ," + QUESTION + " TEXT," + ANSWER + " TEXT," + DBID + " TEXT);";
        db.execSQL(query1);
        query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SET3 + "(" + RID + " INTEGER PRIMARY KEY ," + QUESTION + " TEXT," + ANSWER + " TEXT," + DBID + " TEXT);";
        db.execSQL(query1);
        query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SET4 + "(" + RID + " INTEGER PRIMARY KEY ," + QUESTION + " TEXT," + ANSWER + " TEXT," + DBID + " TEXT);";
        db.execSQL(query1);
        query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SET5 + "(" + RID + " INTEGER PRIMARY KEY ," + QUESTION + " TEXT," + ANSWER + " TEXT," + DBID + " TEXT);";
        db.execSQL(query1);
        query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SET6 + "(" + RID + " INTEGER PRIMARY KEY ," + QUESTION + " TEXT," + ANSWER + " TEXT," + DBID + " TEXT);";
        db.execSQL(query1);
        query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SET7 + "(" + RID + " INTEGER PRIMARY KEY ," + QUESTION + " TEXT," + ANSWER + " TEXT," + DBID + " TEXT);";
        db.execSQL(query1);
        query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SET8 + "(" + RID + " INTEGER PRIMARY KEY ," + QUESTION + " TEXT," + ANSWER + " TEXT," + DBID + " TEXT);";
        db.execSQL(query1);
        String query2 = "CREATE TABLE IF NOT EXISTS " + TABLE_DETAILS + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + STARTTIME + " TEXT," + ENDTIME + " TEXT," + R1 + " INTEGER," + R2 + " INTEGER," + R3 + " INTEGER," + R4 + " INTEGER," + R5 + " INTEGER," + R6 + " INTEGER," + R7 + " INTEGER," + SET + " INTEGER);";
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

        for(int k = 1 ; k <= 8 ; k++) {
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
                values.put(QUESTION, "Walk into the zone and you might find some rides or more");
                values.put(ANSWER, "Parking");
                values.put(DBID, "db1");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "Metal, wooden or concrete, it doesn’t matter. While going from me, all of you chatter");
                values.put(ANSWER, "Stairs");
                values.put(DBID, "db2");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "alexmelc (reshuffle)" +
                        "\n\t_ _ _ _    _ _ _ _");
                values.put(ANSWER, "Exam Cell");
                values.put(DBID, "db3");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "Sheets and plates and pipes " +
                        "All kinds of metal clank below");
                values.put(ANSWER, "Workshop");
                values.put(DBID, "db4");
                dbase.insert(tablename, null, values);
                //r5
                values.put(RID, "5");
                values.put(QUESTION, "Solve: " + "\nXXX = ABC" + "\nA + C = 5" + "\nC - A = B" + "\n(A + B + C)/3 = 2 ");
                values.put(ANSWER, "213");
                values.put(DBID, "db5");
                dbase.insert(tablename, null, values);
                //r6
                values.put(RID, "6");
                values.put(QUESTION, "You eat from me but I am not a spoon. Come find me in the place with posters and cartoons");
                values.put(ANSWER, "Canteen");
                values.put(DBID, "db6");
                dbase.insert(tablename, null, values);
                //r7
                values.put(RID, "7");
                values.put(QUESTION, "No lights, no curtains or backstage. The open breeze and stars are all you get.");
                values.put(ANSWER, "Amphitheatre");
                values.put(DBID, "db7");
                dbase.insert(tablename,null , values);
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
                values.put(QUESTION, "\tYou send the letters.\n" +
                        "\tIt’s me who receives.\n" +
                        "\tI am made of metal.\n" +
                        "\tThrough me you leave." +
                        "\n\t_ _ _ _ _    _ _ _ _");
                values.put(ANSWER, "Front Gate");
                values.put(DBID, "db1");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "I am a jumbled up ‘Africa’. So take a sip of ‘tee’ and just chill.");
                values.put(ANSWER, "Cafeteria");
                values.put(DBID, "db2");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "\tSolve:\n" +
                        "\tXXX = ABC\n" +
                        "\tB+C = 10\n" +
                        "\tA-B = 2\n" +
                        "\t(A+B+C) is the 6th prime number.");
                values.put(ANSWER, "319");
                values.put(DBID, "db3");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "\tMusic, dance, drama and seminars.\n" +
                        "\tHere, your cell phone doesn’t get any signal bars.");
                values.put(ANSWER, "Auditorium");
                values.put(DBID, "db4");
                dbase.insert(tablename, null, values);
                //r5
                values.put(RID, "5");
                values.put(QUESTION, "The Gourmet ice-creaM you had Yesterday had around 2000 calories");
                values.put(ANSWER, "Gymnasium");
                values.put(DBID, "db5");
                dbase.insert(tablename, null, values);
                //r6
                values.put(RID, "6");
                values.put(QUESTION, "ribyarl (Reshuffle)");
                values.put(ANSWER, "Library");
                values.put(DBID, "db6");
                dbase.insert(tablename, null, values);
                //r7
                values.put(RID, "7");
                values.put(QUESTION, "A pirate ship can be hidden in a cave. Where does your ride stand?");
                values.put(ANSWER, "Parking");
                values.put(DBID, "db7");
                dbase.insert(tablename,null , values);
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
                values.put(QUESTION, "tenance (Reshuffle)");
                values.put(ANSWER, "Canteen");
                values.put(DBID, "db1");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "A semi oval place, with a tier of seats. No one to judge you, but a handful to please.");
                values.put(ANSWER, "Amphitheatre");
                values.put(DBID, "db2");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "\tShoes and coats are an absolute necessity.\n" +
                        "\tRespect those from the manual labour community.");
                values.put(ANSWER, "Workshop");
                values.put(DBID, "db3");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "I got millions of stories. I can’t tell unless you peep in as if I am a well.");
                values.put(ANSWER,  "Library");
                values.put(DBID, "db4");
                dbase.insert(tablename, null, values);
                //r5
                values.put(RID, "5");
                values.put(QUESTION, "I swing in and out to let you through. I sometimes creak and that’s your clue." +
                        "\n\t_ _ _ _ _    _ _ _ _");
                values.put(ANSWER, "Front Gate");
                values.put(DBID, "db5");
                dbase.insert(tablename, null, values);
                //r6
                values.put(RID, "6");
                values.put(QUESTION, "\tSolve:\n" + "\tXXX = ABC\n" +
                        "\tC-B = 8\n" +
                        "\tC = 3A\n" +
                        "\t(A+B+C)-1 is the 6th composite number.");
                values.put(ANSWER, "319");
                values.put(DBID, "db6");
                dbase.insert(tablename, null, values);
                //r7
                values.put(RID, "7");
                values.put(QUESTION, "What takes you up and down, but never moves?");
                values.put(ANSWER, "Stairs");
                values.put(DBID, "db7");
                dbase.insert(tablename,null , values);
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
                values.put(QUESTION, "elxcelam (Reshuffle)" +
                        "\n\t_ _ _ _    _ _ _ _" );
                values.put(ANSWER, "Exam Cell");
                values.put(DBID, "db1");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "\tAcoustics are very important here.\n" +
                        "\tThe most common thing is stage fear.");
                values.put(ANSWER, "Auditorium");
                values.put(DBID, "db2");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "\tSolve:\n" +
                        "\tXXX=ABC. \n" +
                        "\tA-C = -1. \n" +
                        "\tC+A = 5B. \n" +
                        "\t(A-B+C)/2 = 2.");
                values.put(ANSWER, "213");
                values.put(DBID, "db3");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "Walk and run on me all day, but you won’t get anywhere. Come to the place where you’ll find me.");
                values.put(ANSWER, "Gymnasium");
                values.put(DBID, "db4");
                dbase.insert(tablename, null, values);
                //r5
                values.put(RID, "5");
                values.put(QUESTION, "\tIt always has been a pleasure\n" +
                        "\tTo go to Nigeria.\n" +
                        "\tBut for now, \n" +
                        "\tLet’s settle for ________?");
                values.put(ANSWER, "Cafeteria");
                values.put(DBID, "db5");
                dbase.insert(tablename, null, values);
                //r6
                values.put(RID, "6");
                values.put(QUESTION, "Enter or exit, you will anyway have to walk through me." +
                        "\n\t_ _ _ _ _    _ _ _ _");
                values.put(ANSWER, "Front Gate");
                values.put(DBID, "db6");
                dbase.insert(tablename, null, values);
                //r7
                values.put(RID, "7");
                values.put(QUESTION, "Starts with a P and ends with a G. Don’t forget your keys.");
                values.put(ANSWER, "Parking");
                values.put(DBID, "db7");
                dbase.insert(tablename,null , values);
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
                values.put(QUESTION, "lacemelx (Reshuffle)" +
                        "\n\t_ _ _ _    _ _ _ _");
                values.put(ANSWER, "Exam Cell");
                values.put(DBID, "db1");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "A place of Roman heredity for spectacles and contests. Find the place to stay in the race.");
                values.put(ANSWER, "Amphitheatre");
                values.put(DBID, "db2");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "\tSolve:\n" +
                        "\tXXX=ABC. \n" +
                        "\tA+B = 3. \n" +
                        "\t3(A-B) = C. \n" +
                        "\t(A+B-C) = 0.");
                values.put(ANSWER, "213");
                values.put(DBID, "db3");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "Silence and loud sounds take turns here.\n" +
                        "If the audience makes noise, it’s very ugly to hear.");
                values.put(ANSWER, "Auditorium");
                values.put(DBID, "db4");
                dbase.insert(tablename, null, values);
                //r5
                values.put(RID, "5");
                values.put(QUESTION, "A place that is filled with teens. Bills that make the wallet lean.");
                values.put(ANSWER, "Canteen");
                values.put(DBID, "db5");
                dbase.insert(tablename, null, values);
                //r6
                values.put(RID, "6");
                values.put(QUESTION, "I am sometimes spiral, sometimes straight. During rush hour, you have to wait.");
                values.put(ANSWER, "Stairs");
                values.put(DBID, "db6");
                dbase.insert(tablename, null, values);
                //r7
                values.put(RID, "7");
                values.put(QUESTION, "Here you have lots of fun. You sweat as you lift and run.");
                values.put(ANSWER, "Gymnasium");
                values.put(DBID, "db7");
                dbase.insert(tablename,null , values);
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
                values.put(QUESTION, "You walk in through it every day. But you wouldn’t want to walk through it on your last day." +
                        "\n\t_ _ _ _ _    _ _ _ _");
                values.put(ANSWER, "Front Gate");
                values.put(DBID, "db1");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "A place with rusting files, beaten mallets and clipped wires,\n" +
                        "It has many hazards, but the least probable is fire.");
                values.put(ANSWER, "Workshop");
                values.put(DBID, "db2");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "\tSolve:\n" +
                        "\tXXX = ABC\n" +
                        "\tA = 3B\n" +
                        "\tC-B = 8\n" +
                        "\t(A+B+C)-2 is the 5th prime number.");
                values.put(ANSWER, "319");
                values.put(DBID, "db3");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "ericafate (Reshuffle)");
                values.put(ANSWER, "Cafeteria");
                values.put(DBID, "db4");
                dbase.insert(tablename, null, values);
                //r5
                values.put(RID, "5");
                values.put(QUESTION, "Deserted throughout the semester, I get graced by students before submission and during a bunked lecture.");
                values.put(ANSWER, "Library");
                values.put(DBID, "db5");
                dbase.insert(tablename, null, values);
                //r6
                values.put(RID, "6");
                values.put(QUESTION, "Mornings end up into frustration if you find me full. You begin to wonder why you didn’t car pool.");
                values.put(ANSWER, "Parking");
                values.put(DBID, "db6");
                dbase.insert(tablename, null, values);
                //r7
                values.put(RID, "7");
                values.put(QUESTION, "Array of stairs everyone flocks, to watch, perform and mock.");
                values.put(ANSWER, "Amphitheatre");
                values.put(DBID, "db7");
                dbase.insert(tablename,null , values);
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
                values.put(QUESTION, "All of the world’s wisdom combined here. Come seek me.");
                values.put(ANSWER, "Library");
                values.put(DBID, "db1");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "Sloping aisles, padded walls and cushioned chairs.\n" +
                        "All performances take place here, but not choirs.");
                values.put(ANSWER, "Auditorium");
                values.put(DBID, "db2");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "He had to get his id card checked before he walked in." +
                        "\n\t_ _ _ _ _    _ _ _ _");
                values.put(ANSWER, "Front Gate");
                values.put(DBID, "db3");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "This is a place where you run, cycle and jump. But you still end up at the same place.");
                values.put(ANSWER, "Gymnasium");
                values.put(DBID, "db4");
                dbase.insert(tablename, null, values);
                //r5
                values.put(RID, "5");
                values.put(QUESTION, "tiercefaa (Reshuffle)");
                        values.put(ANSWER, "Cafeteria");
                values.put(DBID, "db5");
                dbase.insert(tablename, null, values);
                //r6
                values.put(RID, "6");
                values.put(QUESTION, "\tSolve:\n" +
                        "\tXXX = ABC\n" +
                        "\tB+C = 10\n" +
                        "\tC = 3A\n" +
                        "\t(A+B+C) is the 6th prime number.");
                values.put(ANSWER, "319");
                values.put(DBID, "db6");
                dbase.insert(tablename, null, values);
                //r7
                values.put(RID, "7");
                values.put(QUESTION, "pam was too lAte foR colleGe, so she asKed kevIn to droP her Nearby.");
                values.put(ANSWER, "Parking");
                values.put(DBID, "db7");
                dbase.insert(tablename,null , values);
            }
            cursor.close();
        }

        else if (set==8)
        {
            dbase=this.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE_SET8;
            Cursor cursor=dbase.rawQuery(selectQuery,null);
            int flag=cursor.getCount();
            if (flag == 0) {
                ContentValues values = new ContentValues();
                //r1
                values.put(RID, "1");
                values.put(QUESTION, "The smallest stage in the place. Come and seek the open space.");
                values.put(ANSWER, "Amphitheatre");
                values.put(DBID, "db1");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "malecelx (Reshuffle)" +
                        "\n\t_ _ _ _    _ _ _ _");
                values.put(ANSWER, "Exam Cell");
                values.put(DBID, "db2");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "Down below we work with sweaty and dirty hands.\n" +
                        "Where noise pollution is aplenty and there is no signal.");
                values.put(ANSWER, "Workshop");
                values.put(DBID, "db3");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "This is a shortcut to some places. You may leave from here without any traces.");
                values.put(ANSWER, "Stairs");
                values.put(DBID, "db4");
                dbase.insert(tablename, null, values);
                //r5
                values.put(RID, "5");
                values.put(QUESTION, "\tSolve:\n" +
                        "\tXXX = ABC. \n" +
                        "\tB+C = 4. \n" +
                        "\tC-B = A. \n" +
                        "\t(A+B+C)/2 = 3");
                values.put(ANSWER, "213");
                values.put(DBID, "db5");
                dbase.insert(tablename, null, values);
                //r6
                values.put(RID, "6");
                values.put(QUESTION, "A feeble body weakens the mind. To strengthen it, you know which place to find.");
                values.put(ANSWER, "Gymnasium");
                values.put(DBID, "db6");
                dbase.insert(tablename, null, values);
                //r7
                values.put(RID, "7");
                values.put(QUESTION, "i Am a TeeN but i have No age. you bEtter hurry beCause now it’s late.");
                values.put(ANSWER, "Canteen");
                values.put(DBID, "db7");
                dbase.insert(tablename,null , values);
            }
            cursor.close();
        }
    }

    public void createnewuser(int set) {
        for(int i = 1 ; i <= 8 ; i ++)
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
            values.put(R7, 0);
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

                ridList.add(ridd);
                Log.e("riddlist", "creating list");
            } while (cursor.moveToNext());
        }
        cursor.close();
        return ridList;
    }

    public ArrayList<Riddles> initialiseDetails(ArrayList<Riddles> list) {
        // Select All Query

        String selectQuery = "SELECT " + R1 + "," + R2 + "," + R3 + "," + R4 + "," + R5 + "," + R6 + "," + R7 + " FROM " + TABLE_DETAILS + " WHERE " + ENDTIME + "=''";
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // putting status in Riddle object
        if (cursor.moveToFirst()) {
            for (int i = 0; i < 7; i++) {
                list.get(i).setStatus(cursor.getInt(i));
            }
        }
        cursor.close();
        return list;
    }

    public void update_Status(int position) {
        dbase = this.getWritableDatabase();
        String query = "SELECT " + R1 + "," + R2 + "," + R3 + "," + R4 + "," + R5 + "," + R6 + "," + R7 + " FROM " + TABLE_DETAILS + " WHERE " + ENDTIME + "=''";
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
        String query = "SELECT * FROM " + TABLE_DETAILS + " WHERE " + ENDTIME + "='' AND " + R1 + "='2' AND " + R2 + "='2' AND " + R3 + "='2' AND " + R4 + "='2' AND " + R5 + "='2'  AND " + R6 + "='2'  AND " + R7 + "='2' ;";
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
        String query = "SELECT * FROM " + TABLE_DETAILS + " WHERE " + ENDTIME + "='' AND " + R1 + "='2' AND " + R2 + "='2' AND " + R3 + "='2' AND " + R4 + "='2' AND " + R5 + "='2'  AND " + R6 + "='2'  AND " + R7 + "='2' ;";
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
                set=String.valueOf(cursor.getInt(10));
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
}