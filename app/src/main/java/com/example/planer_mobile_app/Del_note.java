package com.example.planer_mobile_app;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

public class Del_note extends AppCompatActivity {

    public void Delete(DatabaseReference mRefDB,String title_note){
    String LOG_TAG="Delete";
        mRefDB.orderByChild("Title").equalTo(title_note).addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            dataSnapshot.getRef().removeValue();// удалление выбранных данных из БД
            Log.d(LOG_TAG,"onChildAdded");
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            Log.d(LOG_TAG,"onChildChanged");
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            Log.d(LOG_TAG,"onChildRemoved");
            mRefDB.removeEventListener(this);// удаляем слушатель
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


}

