package com.example.planer_mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registration extends AppCompatActivity {

    EditText login,password;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        login = findViewById(R.id.login);
        password= findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user !=null){
                    //Пользователь зашел
                }
                else{
                    //Пользователь не зашел
                }
            }
        };

    }


    public void signIn(String login,String password){
        mAuth.signInWithEmailAndPassword(login,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("signIn", "Ready");
                }
                else{
                    Log.e("signIn", "Error");
                }
            }
        });
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