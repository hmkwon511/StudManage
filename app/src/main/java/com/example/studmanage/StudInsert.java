package com.example.studmanage;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StudInsert extends AppCompatActivity {  //상속받아 제작 --액티비티 매니페스에 등록 필요
    MyDBHelper dbHelper;
    SQLiteDatabase sqliteDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.stud_add);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        menu.getItem(1).setEnabled(false);
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
            case R.id.menuList:
                it = new Intent(this, StudList.class);
                it.putExtra("it_search_cond", "all");   //모든 학생 정보 출력
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

    public void onInsert(View v) {  //추가하기 버튼
        EditText etSno = (EditText) findViewById(R.id.etSno);
        EditText etSname = (EditText) findViewById(R.id.etSname);
        EditText etYear = (EditText) findViewById(R.id.etYear);
        EditText etDept = (EditText) findViewById(R.id.etDept);

        String sno = etSno.getText().toString().trim();
        if (sno.equals("")) {
            Toast.makeText(getApplicationContext(), "학번값을 입력해야 합니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        String sname = etSname.getText().toString().trim();
        int year = Integer.parseInt(etYear.getText().toString());
        String dept = etDept.getText().toString().trim();

        try {
            dbHelper = new MyDBHelper(this);
            sqliteDB = dbHelper.getWritableDatabase();
            String SQLStatement = "INSERT INTO STUDENT VALUES (null, '" + sno + "', '" + sname + "'," + year + ",'" + dept + "');";
            sqliteDB.execSQL(SQLStatement);
            Toast.makeText(getApplicationContext(), "성공적으로 학생정보 추가", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            return;
        }
        etSno.setText("");
        etSname.setText("");
        etYear.setText("");
        etDept.setText("");
    }
}
