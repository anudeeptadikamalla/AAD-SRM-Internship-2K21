package com.example.lifecycleexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.i("ANUDEEP","Second Activity Created");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Anudeep","Second Activity Started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Anudeep","Second Activity Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Anudeep","Second Activity Paused");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Anudeep","Second Activity Restarted");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Anudeep","Second Activity Destroyed");
    }
}