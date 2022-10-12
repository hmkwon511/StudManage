package com.example.studmanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        menu.getItem(0).setEnabled(false);  //0번째 메뉴항목 비활성화 -- 이 작업은 주로 아래의 메소드에서 하는 것이 바람직
        return true;
        //return super.onCreateOptionsMenu(menu);
    }

    /*
    @Override
    public boolean onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.getItem(0).setEnabled(false);  //0번째 메뉴항목 비활성화
        return super.onPrepareOptionsMenu(menu);
    }
     */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //int id = item.getItemId();
        Intent it;
        switch(item.getItemId()) {
            case R.id.menuInsert:
                it = new Intent(this, StudInsert.class);
                startActivity(it);
                finish();
                return true;
            case R.id.menuList:
                it = new Intent(this, StudList.class);
                it.putExtra("it_search_cond", "all");   //모든 학생 정보 출력
                startActivity(it);
                finish();
                break;
            case R.id.menuSearch:
                it = new Intent(this, StudSearch.class);
                startActivity(it);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}