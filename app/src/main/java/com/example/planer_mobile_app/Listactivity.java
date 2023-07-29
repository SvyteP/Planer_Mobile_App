package com.example.planer_mobile_app;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Listactivity extends AppCompatActivity {
    private DatabaseReference mRefDB_Active;//Переменная для адреса БД
    private DatabaseReference mRefDB_Compl;//Переменная для адреса БД
    private Del_note del_note = new Del_note();
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    String group_Notes = "Notes";
    String group_Active = "Active";
    String group_Compl = "Completed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listactivity);
        init();
        getDataFromBD();
        ListView lv = findViewById(R.id.listView);
        delNote(lv);
       // setContentView(R.layout.activity_profile);

    }

    private void delNote(ListView lv){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Получаем выбранный элемент по позиции
                String selectedItem = (String) adapterView.getItemAtPosition(i);
                String title_note = "";
                String content = "";


                for(int g = 0 ; g<selectedItem.length();g++){
                    if(selectedItem.charAt(g) == ':'){
                        for (int j=g+1;j<selectedItem.length();j++) {
                            content += selectedItem.charAt(j);
                        }
                        break;
                    }
                    else{
                        title_note += selectedItem.charAt(g);

                    }
                }
                createOneButtonAlertDialog(title_note,content);

            }
        });
    }
    private void createOneButtonAlertDialog(String title_note, String content ) {
        // объект Builder для создания диалогового окна
        AlertDialog.Builder builder = new AlertDialog.Builder(Listactivity.this);

        // добавляем различные компоненты в диалоговое окно
        builder.setTitle(title_note + ":");
        builder.setMessage(content);

        // устанавливаем кнопку, которая отвечает за позитивный ответ
        builder.setPositiveButton("Удалить",
                // устанавливаем слушатель
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {

                        del_note.Delete(mRefDB_Active,title_note);//удаление
                        showMes("Задача удалена!");

                    }

                });


        builder.setNegativeButton("Редактировать", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent toReg = new Intent(Listactivity.this, EditNote.class);
                toReg.putExtra("title", title_note);
                toReg.putExtra("content", content);
                startActivity(toReg);

            }
        });
        builder.setNeutralButton("Выполнено", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String LOG_TAG="Completed";
                ListNotes Copy_note= new ListNotes(title_note,content);
                mRefDB_Compl.push().setValue(Copy_note);
                del_note.Delete(mRefDB_Active,title_note);
                showMes("Задача выполнена!");
            }
        });
        // объект Builder создал диалоговое окно и оно готово появиться на экране
        // вызываем этот метод, чтобы показать AlertDialog на экране пользователя
        builder.show();
    }

    public void toSetting(View reg){
        Intent toReg = new Intent(Listactivity.this, Completed_note.class);
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

        adapter = new ArrayAdapter<>(this,R.layout.list_txt,R.id.list_content, listData);


        listView.setAdapter(adapter);
        mRefDB_Active = FirebaseDatabase.getInstance().getReference(group_Notes).child(group_Active);
        mRefDB_Compl = FirebaseDatabase.getInstance().getReference(group_Notes).child(group_Compl);

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
        mRefDB_Active.addValueEventListener(vListener);
    }
    private void showMes (String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }


}