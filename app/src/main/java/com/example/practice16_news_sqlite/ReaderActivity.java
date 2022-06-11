package com.example.practice16_news_sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ReaderActivity extends AppCompatActivity {

    Button back, selectNews;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        selectNews = findViewById(R.id.btnSelect);
        back = findViewById(R.id.btnBack);
        databaseHelper = new DatabaseHelper(this);

        selectNews.setOnClickListener(view -> {
            Cursor res = databaseHelper.getDataOnNews();
            if(res.getCount() == 0) {
                Toast.makeText(getApplicationContext(),"Нет данных",Toast.LENGTH_LONG).show();
                return;
            }

            StringBuilder buffer = new StringBuilder();
            while (res.moveToNext()){
                buffer.append("Заголовок: ").append(res.getString(1)).append("\n");
                buffer.append("Текст: ").append(res.getString(2)).append("\n");
            }

            AlertDialog.Builder builder = new AlertDialog.Builder(ReaderActivity.this);
            builder.setCancelable(true);
            builder.setMessage(buffer.toString());
            builder.show();
        });

        back.setOnClickListener(view ->{
            Intent a = new Intent(this, MainActivity.class);
            startActivity(a);
        });
    }
}
