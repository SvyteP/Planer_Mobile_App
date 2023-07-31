package com.example.planer_mobile_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Completed_note  extends AppCompatActivity {
    Listactivity listactivity;
    String group_Notes = "Notes";
    String group_Active = "Active";
    String group_Compl = "Completed";
    ListView listCompl;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private DatabaseReference mRefDB_Compl;//Переменная для адреса БД
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed);
        init();
        getDataFromBD();


    }
    public void getDataFromBD()
    {
        ValueEventListener vListener =new ValueEventListener() {

            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (listData.size()>0) {
                    listData.clear();
                }// проверка на наличие посторонних данных

                for (DataSnapshot ds : snapshot.getChildren()) {
                    ListNotes notes = ds.getValue(ListNotes.class);
                    assert notes !=null;// проверка на пустоту
                    listData.add(notes.Title + ":" + notes.Note);

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        };
        mRefDB_Compl.addValueEventListener(vListener);
    }
    private void init(){
        listCompl = findViewById(R.id.listCompl);
        listData = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,R.layout.list_txt,R.id.list_content,listData);
        listCompl.setAdapter(adapter);
        mRefDB_Compl = FirebaseDatabase.getInstance().getReference(group_Notes).child(group_Compl);
    }
    public void toBack(View reg){
        Intent toReg = new Intent(Completed_note.this, Listactivity.class);
        startActivity(toReg);
    }

}
