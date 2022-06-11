package com.example.practice16_news_sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    Button back, register;
    EditText login, password;
    RadioButton admin, reader;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        databaseHelper = new DatabaseHelper(this);
        back = findViewById(R.id.btnBack);
        register = findViewById(R.id.btnRegister);
        login = findViewById(R.id.txtLogin);
        password = findViewById(R.id.txtPassword);
        admin = findViewById(R.id.roleAdmin);
        reader = findViewById(R.id.roleReader);

        back.setOnClickListener(view -> {
            Intent back = new Intent(this,MainActivity.class);
            startActivity(back);
        });

        register.setOnClickListener(view -> {
            String userRole;
            Validation validation_ex = new Validation();

            String loginV = login.getText().toString();
            String passwordV = password.getText().toString();

            User registering = new User(loginV, passwordV);

            if(admin.isChecked()) {
                userRole = "1";
                Boolean checkInsertData = databaseHelper.insertUser(
                        login.getText().toString(),
                        password.getText().toString(), userRole);
                if(checkInsertData && validation_ex.validate_user(registering)) {
                    Toast.makeText(getApplicationContext(),
                            "Успешная регистрация", Toast.LENGTH_LONG).show();

                    Intent back = new Intent(this, MainActivity.class);
                    startActivity(back);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Произошла ошибка", Toast.LENGTH_LONG).show();
                }
            }

            if(reader.isChecked()) {
                userRole = "0";
                Boolean checkInsertData = databaseHelper.insertUser(
                        login.getText().toString(),
                        password.getText().toString(), userRole);
                if(checkInsertData && validation_ex.validate_user(registering)) {
                    Toast.makeText(getApplicationContext(),
                            "Успешная регистрация", Toast.LENGTH_LONG).show();

                    Intent back = new Intent(this, MainActivity.class);
                    startActivity(back);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Произошла ошибка", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
