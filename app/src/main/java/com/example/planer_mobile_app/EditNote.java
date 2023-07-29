package com.example.planer_mobile_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditNote extends AppCompatActivity{
    String group_Notes = "Notes";
    String group_Active = "Active";
    DatabaseReference mRefDB_Active = FirebaseDatabase.getInstance().getReference(group_Notes).child(group_Active);
    EditText edTitle;
    EditText edContent ;
    Button btnAccept;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        edTitle= (EditText) findViewById(R.id.edTitle);
        edContent =  (EditText) findViewById(R.id.edContent);
        btnAccept = (Button)findViewById(R.id.btnAccept);

       Bundle arguments = getIntent().getExtras();
        String title = arguments.get("title").toString();
        String content = arguments.get("content").toString();
        edNote(title,content);

    }
    public void edNote (String title,String content){
        String LOG_TAG="Edit";

        edTitle.setText(title);
        edContent.setText(content);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTitle = edTitle.getText().toString();
                String newContent = edContent.getText().toString();
                ListNotes newListNotes =new ListNotes(newTitle,newContent);
                mRefDB_Active.orderByChild("Title").equalTo(title).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        dataSnapshot.getRef().setValue(newListNotes);// удалление выбранных данных из БД
                        Log.d(LOG_TAG,"onChildAdded");
                        Intent toReg = new Intent(EditNote.this, Listactivity.class);
                        startActivity(toReg);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                        Log.d(LOG_TAG,"onChildChanged");
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                        Log.d(LOG_TAG,"onChildRemoved");
                        mRefDB_Active.removeEventListener(this);// удаляем слушатель
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                        Log.d(LOG_TAG,"onChildMoved");
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.d(LOG_TAG,"Error_Del");
                    }

                });


            }
        });

    }

    public void Cancel(View reg){
        Intent toReg = new Intent(EditNote.this, Listactivity.class);
        startActivity(toReg);
    }


}
