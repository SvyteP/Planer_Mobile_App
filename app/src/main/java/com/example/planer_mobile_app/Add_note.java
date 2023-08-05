package com.example.planer_mobile_app;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_note extends AppCompatActivity {

    private final  String group_users = "Users";
    private final  String group_notes = "Notes";
    private final  String group_active = "Active";

    private DatabaseReference mRefDB;//Переменная для адреса БД
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        mAuth = FirebaseAuth.getInstance();
        mRefDB = FirebaseDatabase.getInstance().getReference(group_users).child(mAuth.getUid()).child(group_notes).child(group_active);//Запись адреcа БД




        Button btnAddNote = (Button) findViewById(R.id.btnCreateNote);
        EditText edTitle = (EditText)  findViewById(R.id.addTitle);
        EditText edNote = (EditText) findViewById(R.id.addContent);


        btnAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = edTitle.getText().toString();
                String note = edNote.getText().toString();



                if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(note)){
                    ListNotes newNote = new ListNotes(title,note);
                    mRefDB.push().setValue(newNote);
                    Toast.makeText(Add_note.this, "Новая задача создана!", Toast.LENGTH_SHORT).show();
                    Intent toReg = new Intent(Add_note.this, Listactivity.class);
                    startActivity(toReg);
                }
                else
                {
                    Toast.makeText(Add_note.this, "Имеются пустые поля!", Toast.LENGTH_SHORT).show();
                }

            }
        });

               /* ".read": "auth.uid !== null",
                ".write": "auth.uid !== null"*/




    }


}