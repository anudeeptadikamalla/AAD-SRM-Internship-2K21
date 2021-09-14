package com.example.activitylifecycles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.activitylifecycles.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    /*  1.onCreate()
        2.onStart()
        3.onResume()
        4.onPause()
        5.onStop()
        6.onRestart()
        7.onDestroy() */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.tv.append("onCreate()");
        Log.i("hello","onCreate()");
        Toast.makeText(this, "App is Created", Toast.LENGTH_SHORT).show();
    }
    //onStart()
    @Override
    protected void onStart() {
        super.onStart();
        binding.tv.append("onStart()");
        Log.i("hello","onStart()");
        Toast.makeText(this, "App is Started", Toast.LENGTH_SHORT).show();
    }
    //onResume()
    @Override
    protected void onResume() {
        super.onResume();
        binding.tv.append("onResume()");
        Log.i("hello","onResume()");
        Toast.makeText(this, "App is Resumed", Toast.LENGTH_SHORT).show();
    }
    //onPause()
    @Override
    protected void onPause() {
        super.onPause();
        binding.tv.append("onPause()");
        Log.i("hello","onPause()");
        Toast.makeText(this, "App is Paused", Toast.LENGTH_SHORT).show();
    }
    //onStop()
    @Override
    protected void onStop() {
        super.onStop();
        binding.tv.append("onStop()");
        Log.i("hello","onStop()");
        Toast.makeText(this, "App is Stopped", Toast.LENGTH_SHORT).show();
    }
    //onRestart()
    @Override
    protected void onRestart() {
        super.onRestart();
        binding.tv.append("onRestart()");
        Log.i("hello","onRestart()");
        Toast.makeText(this, "App is Restarted", Toast.LENGTH_SHORT).show();
    }
    //onDestroy()
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.tv.append("onDestroy()");
        Log.i("hello","onDestroy()");
        Toast.makeText(this, "App is Destroyed", Toast.LENGTH_SHORT).show();
    }
}