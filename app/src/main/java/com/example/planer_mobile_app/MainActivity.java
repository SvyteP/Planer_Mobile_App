package com.example.planer_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//хуй залупа сперма пирожки

    }
    public void toRegistration(View reg){
        Intent toReg = new Intent(MainActivity.this, Registration.class);
        startActivity(toReg);
    }

}