package com.example.planer_mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

public class Regnewaccount extends AppCompatActivity {
    EditText reg_email, reg_pass, reg_pass_repeat, reg_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regnewaccount);
        reg_email = findViewById(R.id.reg_email);
        reg_name = findViewById(R.id.reg_name);
        reg_pass = findViewById(R.id.reg_pass);
        reg_pass_repeat = findViewById(R.id.reg_pass_repeat);





        Button reg = findViewById(R.id.reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox check = findViewById(R.id.checkBox);
                if(check.isChecked()) {

                    if (reg_pass.getText().toString().equals(reg_pass_repeat.getText().toString())) {
                        Intent toReg = new Intent(Regnewaccount.this, Registration.class);
                        startActivity(toReg);
                    }
                }
                else if (reg_pass.getText().toString().equals("")){
                    Toast.makeText(Regnewaccount.this, "Поле пароль не может быть пустым", Toast.LENGTH_SHORT).show();
                }
                else if (reg_name.getText().toString().equals("")){
                    Toast.makeText(Regnewaccount.this, "Поле логин не может быть пуустым", Toast.LENGTH_SHORT).show();
                }
                else if(reg_email.getText().toString().equals("")) {
                    Toast.makeText(Regnewaccount.this, "Поле email не может быть пустым", Toast.LENGTH_SHORT).show();
                }
                else if (!check.isChecked()){
                    Toast.makeText(Regnewaccount.this, "Вы не согласились с нашей политикой", Toast.LENGTH_SHORT).show();
                }
                else if(!reg_pass.getText().toString().equals(reg_pass_repeat.getText().toString())) {
                    Toast.makeText(Regnewaccount.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}