package com.example.admin.nutritionist;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.support.annotation.Nullable;
import android.content.ContentValues;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "nutrition.db";
    public static final String TABLE_USER = "login";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_AGE = "age";
    public static final String COLUMN_USER_HEIGHT = "height";
    public static final String COLUMN_USER_SEX = "sex";
    public static final String COLUMN_USER_WEIGHT = "weight";
    public static final String COLUMN_USER_DIABETIC = "diabetic";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_BMI = "bmi";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT NOT NULL," + COLUMN_USER_WEIGHT + " INTEGER, " + COLUMN_USER_DIABETIC + " TEXT, "
            + COLUMN_USER_AGE + " INTEGER, " + COLUMN_USER_HEIGHT + " DECIMAL(4,2), " + COLUMN_USER_SEX + " TEXT, "
            +COLUMN_USER_PASSWORD + " TEXT," +  COLUMN_USER_BMI + "DOUBLE" +")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
       // SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

   /* public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.userName);
        values.put(COLUMN_USER_EMAIL, user.email);
        values.put(COLUMN_USER_PASSWORD, user.password);
        values.put(COLUMN_USER_AGE, user.age);
        values.put(COLUMN_USER_WEIGHT, user.weight);
        values.put(COLUMN_USER_HEIGHT, user.height);
        values.put(COLUMN_USER_SEX, user.sex);
        values.put(COLUMN_USER_DIABETIC, user.diabetic);
        values.put(COLUMN_USER_BMI, user.bmi);

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }*/
   /* public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER,// Selecting Table
                new String[]{COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_USER_EMAIL, COLUMN_USER_PASSWORD},//Selecting columns want to query
                COLUMN_USER_EMAIL + "=?",
                new String[]{user.email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }*/

   /* public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER,// Selecting Table
                new String[]{COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_USER_EMAIL, COLUMN_USER_PASSWORD},//Selecting columns want to query
                COLUMN_USER_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }*/
}
