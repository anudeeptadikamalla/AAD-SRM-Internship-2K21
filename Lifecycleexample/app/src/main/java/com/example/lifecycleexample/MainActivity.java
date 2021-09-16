package com.example.lifecycleexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("ANUDEEP","Activity Created");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Anudeep","Activity Started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Anudeep","Activity Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Anudeep","Activity Paused");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Anudeep","Activity Restarted");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Anudeep","Activity Destroyed");
    }

    public void next(View view) {
        startActivity(new Intent(MainActivity.this,SecondActivity.class));
    }
}