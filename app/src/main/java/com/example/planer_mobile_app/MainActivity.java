package com.example.planer_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference mRefDB;//Переменная для адреса БД
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRefDB = FirebaseDatabase.getInstance().getReference();//Запись адреcа БД
    }

    public void toRegistration(View reg){
        Intent toReg = new Intent(MainActivity.this, Registration.class);
        startActivity(toReg);
    }

}