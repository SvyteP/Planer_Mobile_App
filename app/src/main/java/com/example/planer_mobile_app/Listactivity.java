package com.example.planer_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Listactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listactivity);
    }
    public void toSetting(View reg){
        Intent toReg = new Intent(Listactivity.this, Registration.class);
        startActivity(toReg);
    }

    public void toActivity(View reg){
        Intent toReg = new Intent(Listactivity.this, Listactivity.class);
        startActivity(toReg);
    }

    public void toFriends(View reg){
        Intent toReg = new Intent(Listactivity.this, friendlist.class);
        startActivity(toReg);
    }
}