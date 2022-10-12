package com.example.studmanage;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StudSearch extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stud_search);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        menu.getItem(3).setEnabled(false);
        Toast.makeText(getApplicationContext(),"옵션메뉴 생성", Toast.LENGTH_SHORT).show();
        return true;

        //return super.onCreateOptionsMenu(menu);
    }


    //test
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
            case R.id.menuInsert:
                it = new Intent(this, StudInsert.class);
                startActivity(it);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onInsert(View v) {
        EditText etName = (EditText)findViewById(R.id.etSname);
        EditText etDept = (EditText)findViewById(R.id.etDept);
        String name = etName.getText().toString().trim();
        String dept = etDept.getText().toString().trim();

        if (name.equals("") && dept.equals("")) {
            Toast.makeText(this, "이름이나 학과 정보를 입력해야 합니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Toast.makeText(getApplicationContext(), "정보검색 실행", Toast.LENGTH_SHORT).show();
        //인텐트를 생성하고 검색 조건을 전달하고 학생정보를 출력하는 StudList 액티비티 실행
        Intent it = new Intent(this, StudList.class);
        it.putExtra("it_search_cond", "cond_search");   //조건 검색을 의미
        it.putExtra("it_name", name);
        it.putExtra("it_dept", dept);
        startActivity(it);
        finish();
    }
}
