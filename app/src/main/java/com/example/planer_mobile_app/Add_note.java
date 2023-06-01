package com.example.planer_mobile_app;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.widget.Toast;
import android.widget.Toast.Callback;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Add_note extends AppCompatActivity {
    private DatabaseReference mRefDB;//Переменная для адреса БД
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        String group_Notes = "Notes";
        String group_Active = "Active";
        mRefDB = FirebaseDatabase.getInstance().getReference(group_Notes).child(group_Active);//Запись адреcа БД


        Button btnAddNote = (Button) findViewById(R.id.btnCreateNote);
        EditText edTitle = (EditText)  findViewById(R.id.edTitle);
        EditText edNote = (EditText) findViewById(R.id.edNote);


        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = edTitle.getText().toString();
                String note = edNote.getText().toString();



                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(note)){
                    ListNotes newNote = new ListNotes(title,note);
                    mRefDB.push().setValue(newNote);
                    Toast.makeText(Add_note.this, "Новая задача создана!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Add_note.this, "Имеются пустые поля!", Toast.LENGTH_SHORT).show();
                }
                Intent toReg = new Intent(Add_note.this, Listactivity.class);
                startActivity(toReg);
            }
        });

               /* ".read": "auth.uid !== null",
                ".write": "auth.uid !== null"*/




    }


}