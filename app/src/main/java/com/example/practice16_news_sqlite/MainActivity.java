package com.example.practice16_news_sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText login, password;
    Button log, register;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        log = findViewById(R.id.btnLogIn);
        register = findViewById(R.id.btnRegister);
        login = findViewById(R.id.txtLogin);
        password = findViewById(R.id.txtPassword);

        log.setOnClickListener(view -> {
            Cursor res = databaseHelper.getDataOnUsers();
            if(res.getCount()==0) {
                Toast.makeText(getApplicationContext(),
                        "Нет пользователей",Toast.LENGTH_LONG).show();
                return;
            } while (res.moveToNext()) {
                Validation validation_ex = new Validation();

                String loginA = login.getText().toString();
                String passwordA = password.getText().toString();

                User registering = new User(loginA, passwordA);

                if(validation_ex.validate_user(registering)) {
                    Toast.makeText(getApplicationContext(),
                            "Успешная авторизация", Toast.LENGTH_LONG).show();

                    Intent back = new Intent(this, MainActivity.class);
                    startActivity(back);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Произошла ошибка", Toast.LENGTH_LONG).show();
                }

                String logIn = login.getText().toString();
                String logOut = res.getString(1);
                String passIn = password.getText().toString();
                String passOut = res.getString(2);

                if(logIn.equals(logOut)) {
                    if(passIn.equals(passOut)) {
                        String role = res.getString(3).toString();
                        if(role.equals("1")) {
                            Intent intent = new Intent(this, AdminActivity.class);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(this, ReaderActivity.class);
                            startActivity(intent);
                        }
                    } else
                        Toast.makeText(getApplicationContext(),
                                "Неверный пароль",Toast.LENGTH_LONG).show();
                }
            }
        });

        register.setOnClickListener(view -> {
                    Intent back = new Intent(this, RegistrationActivity.class);
                    startActivity(back);
                }
        );
    }
}