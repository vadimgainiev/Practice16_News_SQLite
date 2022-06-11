package com.example.practice16_news_sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class AdminActivity extends AppCompatActivity {

    EditText title, text;
    Button select, add, edit, delete, back;
    DatabaseHelper databaseHelper;
    String editTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        select = findViewById(R.id.btnSelect);
        add = findViewById(R.id.btnAdd);
        edit = findViewById(R.id.btnEdit);
        delete = findViewById(R.id.btnDelete);
        back = findViewById(R.id.btnBack);
        databaseHelper = new DatabaseHelper(this);
        title = findViewById(R.id.txtNewsTitle);
        text = findViewById(R.id.txtNewsText);

        select.setOnClickListener(view -> {
            Cursor res = databaseHelper.getDataOnNews();
            if(res.getCount() == 0) {
                Toast.makeText(getApplicationContext(),"Нет данных",Toast.LENGTH_LONG).show();
                return;
            }
            StringBuilder buffer = new StringBuilder();
            while (res.moveToNext()) {
                buffer.append("ЗАГОЛОВОК: \n").append(res.getString(1)).append("\n\n");
                buffer.append("ТЕКСТ: \n").append(res.getString(2)).append("\n\n");
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(AdminActivity.this);
            builder.setCancelable(true);
            builder.setMessage(buffer.toString());
            builder.show();
        });

        add.setOnClickListener(view -> {
            Boolean checkInsertData = databaseHelper.insertNews(title.getText().toString(),
                    text.getText().toString());
            if(checkInsertData) {
                Toast.makeText(getApplicationContext(),
                        "Новость опубликована",Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "Произошла ошибка",Toast.LENGTH_LONG).show();
            }
        });

        delete.setOnClickListener(view -> {
            Boolean checkInsertData = databaseHelper.deleteNews(title.getText().toString());
            if(checkInsertData) {
                Toast.makeText(getApplicationContext(),
                        "Новость удалена",Toast.LENGTH_LONG).show();
                title.setText("");
                text.setText("");
            } else {
                Toast.makeText(getApplicationContext(),
                        "Произошла ошибка",Toast.LENGTH_LONG).show();
            }
        });

        edit.setOnClickListener(view -> {
            if(title.getText().toString() == null || title.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(),
                        "Введите заголовок новости, данные которой хотите изменить",
                        Toast.LENGTH_LONG).show();
            } else {
                Cursor res = databaseHelper.getDataOnNews();
                if(res.getCount() == 0) {
                    Toast.makeText(getApplicationContext(),
                            "Нет данных для изменения",Toast.LENGTH_LONG).show();
                    return;
                }
                while (res.moveToNext()) {
                    if(editTitle.equals("")) {
                        editTitle = title.getText().toString();
                        Toast.makeText(getApplicationContext(),
                                "Измените данные",Toast.LENGTH_LONG).show();
                    } else {
                        Boolean checkInsertData =  databaseHelper.editNews(editTitle,
                                title.getText().toString(), text.getText().toString());
                        if(checkInsertData) {
                            Toast.makeText(getApplicationContext(),
                                    "Данные успешно изменены",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Произошла ошибка",Toast.LENGTH_LONG).show();
                        }

                        editTitle = "";
                    }
                }
            }
        });

        back.setOnClickListener(view -> {
            Intent a = new Intent(this,MainActivity.class);
            startActivity(a);
        });
    }
}
