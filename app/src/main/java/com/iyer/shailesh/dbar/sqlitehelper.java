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
    private static final String TABLE_SET9 = "set9";
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
        query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SET8 + "(" + RID + " INTEGER PRIMARY KEY ," + QUESTION + " TEXT," + ANSWER + " TEXT," + DBID + " TEXT,"+ IMAGE +"  TEXT );";
        db.execSQL(query1);
        query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_SET9 + "(" + RID + " INTEGER PRIMARY KEY ," + QUESTION + " TEXT," + ANSWER + " TEXT," + DBID + " TEXT,"+ IMAGE +"  TEXT );";
        db.execSQL(query1);
        String query2 = "CREATE TABLE IF NOT EXISTS " + TABLE_DETAILS + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + STARTTIME + " TEXT , " + ENDTIME + " TEXT," + R1 + " INTEGER," + R2 + " INTEGER," + R3 + " INTEGER," + R4 + " INTEGER," + " INTEGER," + SET + " INTEGER);";
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

        for(int k = 1 ; k <= 9 ; k++) {
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
                values.put(QUESTION, "A stationary room which is never utilized.\n" +
                        "Stationary Store Besides _ _ _ _   _ _ _ _");
                values.put(ANSWER, "Exam Cell");
                values.put(DBID, "db1");
                values.put(IMAGE , "NO");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "Best out of waste is always made.\n" +
                        "A step towards nature and our hardwork gets paid.\n" +
                        "_ _ _ _ _ _ _    _ _ _");
                values.put(ANSWER, "Compost Pit");
                values.put(DBID, "db2");
                values.put(IMAGE , "NO");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "Studying in a classroom is necessary but their business minds need more than just a classroom.\n" +
                        "_ _ _   _ _ _ _");
                values.put(ANSWER, "MBA Audi");
                values.put(DBID, "db3");
                values.put(IMAGE,"NO");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "Unscramble This:  O P G R M U A");
                values.put(ANSWER, "Gopuram");
                values.put(DBID, "db4");
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
                values.put(QUESTION, "Pehle pet puja\n" +
                        "Fir kaam duja");
                values.put(ANSWER, "Canteen");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "KT aaye na aaye\n" +
                        "Tu yaha aayega\n" +
                        "_ _ _ _   _ _ _ _");
                values.put(ANSWER, "Exam Cell");
                values.put(DBID, "db2");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "Unscramble This: A O D U S P I\n" +
                        "_ _ _   _ _ _ _");
                values.put(ANSWER, "SOP Audi");
                values.put(DBID, "db3");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "The only place where not your mind, but your body is trained.");
                values.put(ANSWER, "Gym");
                values.put(DBID, "db4");
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
                values.put(QUESTION, "Where period is not a part of menstruation but a table\n" +
                        "_ _ _ _ _ _ _ _ _   _ _ _");
                values.put(ANSWER, "Chemistry Lab");
                values.put(DBID, "db1");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "My name is at the entrance\n" +
                        "For all the graduate students");
                values.put(ANSWER, "Gate");
                values.put(DBID, "db2");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "Comedy Central Logo\n" +
                        "Decode the image:");
                values.put(ANSWER, "Computer Center");
                values.put(DBID, "db3");
                values.put(IMAGE, "3_3");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "Nowdays, everything is available on a click\n" +
                        "But the room of aid provides cure to the sick\n" +
                        "_ _ _ _ _   _ _ _   _ _ _ _");
                values.put(ANSWER,  "First Aid Room");
                values.put(DBID, "db4");
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
                values.put(QUESTION, "To solve this little fix\n" +
                        "Einstein and Newton made their mix\n" +
                        "Head to the place where they did their tricks.\n" +
                        "_ _ _ _ _ _ _   _ _ _" );
                values.put(ANSWER, "Physics Lab");
                values.put(DBID, "db1");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "Decode the image:");
                values.put(ANSWER, "Amphitheatre");
                values.put(DBID, "db2");
                values.put(IMAGE, "4_2");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "I am always open for your suggestions\n" +
                        "Come meet me outside Principal Office\n" +
                        "Without any Hesitation\n" +
                        "_ _ _ _ _ _ _ _ _ _   _ _ _");
                values.put(ANSWER, "Suggestion Box");
                values.put(DBID, "db3");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "Best out of waste is always made\n" +
                        "A step towards nature and our hardwork is paid.\n" +
                        "_ _ _ _ _ _ _   _ _ _");
                values.put(ANSWER, "Compost Pit");
                values.put(DBID, "db4");
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
                values.put(QUESTION, "There is bio\n" +
                        "There is technology\n" +
                        "There are books\n" +
                        "What else do you need?\n" +
                        "_ _ _ _ _ _ _ _ _ _ _ _ _    _ _ _ _ _ _ _");
                values.put(ANSWER, "Biotechnology Library");
                values.put(DBID, "db1");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "The bridge from where Juliet could see Romeo\n" +
                        "But Romeo couldn't see Juliet.\n" +
                        "_ _ _   _ _ _ _ _ _");
                values.put(ANSWER, "PPT Bridge");
                values.put(DBID, "db2");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "An underground world of huff and puff.\n" +
                        "Full of metal, wood and mechanical stuff.");
                values.put(ANSWER, "Workshop");
                values.put(DBID, "db3");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "FE ke liye nayi par kahaani ye puraani\n" +
                        "75% attendance hua ki nahi dekhlo FEs\n" +
                        "Warna assignment me nikal jaayegi jawaani.\n" +
                        "_ _   _ _ _ _ _ _   _ _ _ _ _");
                values.put(ANSWER, "FE Notice Board");
                values.put(DBID, "db4");
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
                values.put(QUESTION, "Issi jagah milenge\n" +
                        "संगणक के कलाकार..\n" +
                        "_ _ _   _ _ _ _ _ _ _");
                values.put(ANSWER, "Art Gallary");
                values.put(DBID, "db1");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "A room where only leaders can enter.\n" +
                        "_ _ _ _ _ _ _   _ _ _ _ _ _ _    _ _ _ _");
                values.put(ANSWER, "Student Council Room");
                values.put(DBID, "db2");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "A place you can make a copy of someone's notes\n" +
                        "But not their personality\n" +
                        "_ _ _ _ _    _ _ _ _");
                values.put(ANSWER, "Xerox Shop");
                values.put(DBID, "db3");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "Paani kam hai matke me\n" +
                        "Package kardu ek jhatke me\n" +
                        "_ _ _   _ _ _ _ _ _    _ _ _ _ _ _ _");
                values.put(ANSWER, "PPT Sample Display");
                values.put(DBID, "db4");
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
                values.put(QUESTION, "A crystal clear place full of machinery\n" +
                        "_ _ _ _ _    _ _ _ _ _ _ _ _");
                values.put(ANSWER, "Glass Building");
                values.put(DBID, "db1");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "I am a small room consisting of knowledge from around the world.");
                values.put(ANSWER, "Library");
                values.put(DBID, "db2");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "A place where you test all type of forces but you will find me at the forever closed courses.\n" +
                        "_ _   _ _ _");
                values.put(ANSWER, "EM Lab");
                values.put(DBID, "db3");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "A room from where education is taken but the irony is\n" +
                        "It sounds like bacon\n" +
                        "_ _ _ _ _ _   _ _ _ _");
                values.put(ANSWER, "Beacon Room");
                values.put(DBID, "db4");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
            }
            cursor.close();
        } else if (set==8)
        {
            dbase=this.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE_SET8;
            Cursor cursor=dbase.rawQuery(selectQuery,null);
            int flag=cursor.getCount();
            if (flag == 0) {
                ContentValues values = new ContentValues();
                //r1
                values.put(RID, "1");
                values.put(QUESTION, "Mocha, Latte, Iced Tea\n" +
                        "What will the flavour of my maggie be?");
                values.put(ANSWER, "Cafeteria");
                values.put(DBID, "db1");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "The mother room for all computers\n" +
                        "Where data will be served.\n" +
                        "_ _ _ _ _ _    _ _ _ _");
                values.put(ANSWER, "Server Room");
                values.put(DBID, "db2");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "Studying in a classroom is necessary\n" +
                        "But there business minds\n" +
                        "Need more than just a classroom\n" +
                        "_ _ _   _ _ _ _");
                values.put(ANSWER, "MBA Audi");
                values.put(DBID, "db3");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "Where period is not a part of menstruation but a table\n" +
                        "_ _ _ _ _ _ _ _ _   _ _ _");
                values.put(ANSWER, "Chemistry Lab");
                values.put(DBID, "db4");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
            }
            cursor.close();
        } else if (set==9)
        {
            dbase=this.getWritableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE_SET9;
            Cursor cursor=dbase.rawQuery(selectQuery,null);
            int flag=cursor.getCount();
            if (flag == 0) {
                ContentValues values = new ContentValues();
                //r1
                values.put(RID, "1");
                values.put(QUESTION, "An underground world of huff and puff.\n" +
                        "Full of metal, wood and mechanical stuff.");
                values.put(ANSWER, "Workshop");
                values.put(DBID, "db1");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r2
                values.put(RID, "2");
                values.put(QUESTION, "A gender personal room.\n" +
                        "Where the girls don't groom.");
                values.put(ANSWER, "BCR");
                values.put(DBID, "db2");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r3
                values.put(RID, "3");
                values.put(QUESTION, "Where people use their mind and creativity\n" +
                        "For the betterment of the society\n" +
                        "_ _ _   _ _ _ _");
                values.put(ANSWER, "NSS Room");
                values.put(DBID, "db3");
                values.put(IMAGE, "NO");
                dbase.insert(tablename, null, values);
                //r4
                values.put(RID, "4");
                values.put(QUESTION, "Decode the image:\n" +
                        "_ _ _    _ _ _ _");
                values.put(ANSWER, "SOP Audi");
                values.put(DBID, "db4");
                values.put(IMAGE, "9_1");
                dbase.insert(tablename, null, values);
            }
            cursor.close();
        }
    }

    public void createnewuser(int set) {
        for(int i = 1 ; i <= 9 ; i ++)
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

        String selectQuery = "SELECT " + R1 + "," + R2 + "," + R3 + "," + R4 +  " FROM " + TABLE_DETAILS + " WHERE " + ENDTIME + "=''";
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // putting status in Riddle object
        if (cursor.moveToFirst()) {
            for (int i = 0; i < 4; i++) {
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
        String query = "SELECT " + R1 + "," + R2 + "," + R3 + "," + R4 + " FROM " + TABLE_DETAILS + " WHERE " + ENDTIME + "=''";
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
        String query = "SELECT * FROM " + TABLE_DETAILS + " WHERE " + ENDTIME + "='' AND " + R1 + "='2' AND " + R2 + "='2' AND " + R3 + "='2' AND " + R4 + "='2';";
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
        String query = "SELECT * FROM " + TABLE_DETAILS + " WHERE " + ENDTIME + "='' AND " + R1 + "='2' AND " + R2 + "='2' AND " + R3 + "='2' AND " + R4 + "='2';";
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
                set=String.valueOf(cursor.getInt(7));
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
        for(int i = 1 ; i < 5 ; i++){
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