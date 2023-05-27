package com.example.planer_mobile_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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


        ListView lv = findViewById(R.id.listView);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String LOG_TAG="test";
                // Получаем выбранный элемент по позиции
                String selectedItem = (String) adapterView.getItemAtPosition(i);

//                Log.d(LOG_TAG, "itemSelect: position = , id = "+i);
               // createOneButtonAlertDialog("Нажат элемент:"+i,selectedItem);

            }
        });


    }
    private void createOneButtonAlertDialog(String title, String content) {
        // объект Builder для создания диалогового окна
        AlertDialog.Builder builder = new AlertDialog.Builder(Listactivity.this);
        // добавляем различные компоненты в диалоговое окно
        builder.setTitle(title);
        builder.setMessage(content);
        // устанавливаем кнопку, которая отвечает за позитивный ответ
        builder.setPositiveButton("OK",
                // устанавливаем слушатель
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // по нажатию создаем всплывающее окно с типом нажатой конпки
                        showMes("Нажали Ок");
                    }
                });
        // объект Builder создал диалоговое окно и оно готово появиться на экране
        // вызываем этот метод, чтобы показать AlertDialog на экране пользователя
        builder.show();
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
    private void showMes (String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }


}