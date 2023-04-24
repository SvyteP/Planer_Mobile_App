package com.example.planer_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends AppCompatActivity {

    EditText login,password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        login = findViewById(R.id.login);
        password= findViewById(R.id.password);

    }

    public void toRegnewaccount(View regnew) {
        Intent toRegnew = new Intent(Registration.this, Regnewaccount.class);
        startActivity(toRegnew);
    }
    public void toStart(View view) {

        if (login.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
            Intent toHome = new Intent(Registration.this, Listactivity.class);
            startActivity(toHome);
        }
        else{
            Toast.makeText(Registration.this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
        }

    }



}