package com.example.planer_mobile_app;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class friendlist extends AppCompatActivity {

    ArrayList<String> users = new ArrayList<String>();
    ArrayList<String> selectedUsers = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    ListView usersList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendlist);


        Collections.addAll(users, "Serega", "Uyebok", "Umri", "Pzh", "Nahuy");

        usersList = findViewById(R.id.usersList);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, users);

        usersList.setAdapter(adapter);


        usersList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {

                String user = adapter.getItem(position);
                if(usersList.isItemChecked(position))
                    selectedUsers.add(user);
                else
                    selectedUsers.remove(user);
            }
        });
    }

    public void add(View view){

        EditText userName = findViewById(R.id.userName);
        String user = userName.getText().toString();
        if(!user.isEmpty()){
            adapter.add(user);
            userName.setText("");
            adapter.notifyDataSetChanged();
        }
    }
    public void remove(View view){

        for(int i=0; i< selectedUsers.size();i++){
            adapter.remove(selectedUsers.get(i));
        }

        usersList.clearChoices();

        selectedUsers.clear();

        adapter.notifyDataSetChanged();
    }
}