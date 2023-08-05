package com.example.planer_mobile_app;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_individ_kat extends AppCompatActivity {
    private final  String group_users = "Users";
    private final  String group_notes = "Notes";
    private final  String group_active = "Active";
    private final  String group_info = "Info";
    private final  String group_friends = "Friends";
    private final  String group_complete = "Complete";
     DatabaseReference mRefDB =FirebaseDatabase.getInstance().getReference(group_users);


    public void createKatUsers (String UID,Object obj){
        mRefDB =FirebaseDatabase.getInstance().getReference(group_users);
        mRefDB.child(UID).child(group_info).push().setValue(obj);

  /*      mRefDB.push().child(UID).child(group_notes).child(group_active).setValue(0);
        mRefDB.push().child(UID).child(group_notes).child(group_complete).setValue(0);
        mRefDB.push().child(UID).child(group_friends).setValue(0);*/
    }


}
