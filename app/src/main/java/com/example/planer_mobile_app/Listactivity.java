package com.example.planer_mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Listactivity extends AppCompatActivity {
    private DatabaseReference mRefDB;//Переменная для адреса БД
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    String group_Notes = "Notes";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listactivity);
        init();
        getDataFromBD();

    }
    public void toSetting(View reg){
        Intent toReg = new Intent(Listactivity.this, Registration.class);
        startActivity(toReg);
    }

    public void toActivity(View reg){
        Intent toReg = new Intent(Listactivity.this, Listactivity.class);
        startActivity(toReg);
    }

   /* public void toFriends(View reg){
        Intent toReg = new Intent(Listactivity.this, ReadDataBase.class);
        startActivity(toReg);
    }*/

    public void AddNote(View reg){
        Intent toReg = new Intent(Listactivity.this, Add_note.class);
        startActivity(toReg);
    }
    private void init(){
        listView = findViewById(R.id.listView);
        listData = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(adapter);
        mRefDB = FirebaseDatabase.getInstance().getReference(group_Notes);//Запись адреcа БД

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
                    listData.add(notes.Title + ": \n" + notes.Note);

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        };
        mRefDB.addValueEventListener(vListener);
    }


}