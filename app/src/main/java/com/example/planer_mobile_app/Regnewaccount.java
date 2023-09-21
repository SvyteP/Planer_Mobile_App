package com.example.planer_mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Regnewaccount extends AppCompatActivity {

    EditText reg_email, reg_pass, reg_pass_repeat, reg_name;
    FirebaseAuth mAuth;
    String email,login,company,pass,rep_pass;
    FirebaseAuth.AuthStateListener mAuthListener;
    Users user;
    Add_individ_kat add_individ_kat =  new Add_individ_kat();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regnewaccount);
        reg_email = findViewById(R.id.reg_email);
        reg_name = findViewById(R.id.reg_name);
        reg_pass = findViewById(R.id.reg_pass);
        reg_pass_repeat = findViewById(R.id.reg_pass_repeat);




        mAuth = FirebaseAuth.getInstance();//инециализациия ссылки на пользователя

        //слушатель входа пользователя
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    //Пользователь зашел
                }
                else{
                    //Пользователь вышел
                }
            }
        };


        Button reg = findViewById(R.id.reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox check = findViewById(R.id.checkBox);
                email = reg_email.getText().toString();
                login = reg_name.getText().toString();
                pass = reg_pass.getText().toString();
                rep_pass =reg_pass_repeat.getText().toString();

                if(check.isChecked()) {

                    if (pass.equals(rep_pass)) {

                        regNewAcc(email,pass);//регистрация новго аккаунта

                        
                        user = new Users(email,login,mAuth.getUid());

                        add_individ_kat.createKatUsers(mAuth.getUid(),user);




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
    public void regNewAcc(String email,String pass){
        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("regNewAcc","Ready");

                }
                else{
                    Toast.makeText(Regnewaccount.this, "Неверный логин или длина пароля менее 6 символов", Toast.LENGTH_SHORT).show();
                    Log.e("regNewAcc","Error");
                }
            }
        });
    }

}