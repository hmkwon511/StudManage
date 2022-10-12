package com.example.studmanage;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StudList extends AppCompatActivity {
    MyDBHelper dbHelper;
    SQLiteDatabase sqliteDB;
    ListView lvStudent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //Intent it;
        Cursor cursor;
        String sqlStatement;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stud_list);
        lvStudent = (ListView)findViewById(R.id.lvStudent);

        try {
            dbHelper = new MyDBHelper(this);
            sqliteDB = dbHelper.getWritableDatabase();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"DB 작업 불가 :" + e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
        //DB 작업 가능함
        Intent it = getIntent();
        String search_cond = it.getStringExtra("it_search_cond"); //검색조건 여부
        if (search_cond.equals("all")) { //모든 학생 정보 출력
            sqlStatement = "SELECT * FROM STUDENT";
        }
        else { //조건을 만족하는 학생 정보만 출력 -- 정보가 잇는지 여부는 보내기전에 체크함
            String name = it.getStringExtra("it_name");
            String dept = it.getStringExtra("it_dept");
            if (name.equals(""))
                sqlStatement = "SELECT * FROM STUDENT where DEPT = '" + dept + "'";
            else if (dept.equals(""))
                sqlStatement = "SELECT * FROM STUDENT where SNAME = '" + name + "'";
            else //이름과 학과정보가 다 존재함
                sqlStatement = "SELECT * FROM STUDENT where SNAME = '" + name + "' AND DEPT = '" + dept + "'";
        }
        try {
            cursor = sqliteDB.rawQuery(sqlStatement, null);
        } catch (SQLiteException e) {
            Toast.makeText(getApplicationContext(), "SQL실행에러: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
        //검색 성공
        if (cursor.getCount() == 0)
            Toast.makeText(getApplicationContext(), "검색 데이터가 존재하지 않습니다!", Toast.LENGTH_SHORT).show();
        //startManagingCursor(cursor);
        try {
           String[] from = {"SNO", "SNAME", "YEAR", "DEPT"};
           int[] to = {R.id.tvSno,  R.id.tvName, R.id.tvYear, R.id.tvDept};

           SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.listitem, cursor, from, to,0);
           lvStudent.setAdapter(adapter);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("Err1:",e.getMessage() );
        }

    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        menu.getItem(2).setEnabled(false);
        return true;
        //return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent it;
        switch(item.getItemId()) {
            case R.id.menuHome:
                it = new Intent(this, MainActivity.class);
                startActivity(it);
                finish();
                return true;
            case R.id.menuInsert:
                it = new Intent(this, StudInsert.class);
                startActivity(it);
                finish();
                return true;
            case R.id.menuSearch:
                it = new Intent(this, StudSearch.class);
                startActivity(it);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
