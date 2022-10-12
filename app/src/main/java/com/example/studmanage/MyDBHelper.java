package com.example.studmanage;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SCHOOL.db";
    private static final int DATABASE_VERSION = 2;

    //자동 추가후 정리가 바람직
    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //커서어댑터를 사용하려면 _id 필드가 반드시 필요함 --
        String sqlStatement = "CREATE TABLE STUDENT (_id INTEGER PRIMARY KEY AUTOINCREMENT, SNO CHAR(10), SNAME CHAR(10), YEAR INTEGER, DEPT CHAR(20));";
        sqLiteDatabase.execSQL(sqlStatement);
        //Toast.makeText(this, "성공적으로 학생정보 추가", Toast.LENGTH_SHORT).show();
        String SQLStatement = "INSERT INTO STUDENT VALUES(null, '2022219001', '손흥민', 3, '스마트')";
        sqLiteDatabase.execSQL(SQLStatement);
        sqLiteDatabase.execSQL("INSERT INTO STUDENT VALUES(null, '2022219002', '김민재', 2, '스마트')");
        sqLiteDatabase.execSQL("INSERT INTO STUDENT VALUES(null, '2022219003', '황인범', 1, '컴퓨터')");
        sqLiteDatabase.execSQL("INSERT INTO STUDENT VALUES(null, '2022219004', '박지성', 2, '스마트')");
        sqLiteDatabase.execSQL("INSERT INTO STUDENT VALUES(null, '2022219005', '박주영', 2, '컴퓨터')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDENT");
        onCreate(sqLiteDatabase);
    }
}

