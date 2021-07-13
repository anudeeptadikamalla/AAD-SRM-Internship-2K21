package com.example.helloone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Declare all the views here
    Button toast;
    Button count;
    Button count1;
    TextView tv;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Instantiate the views here
        toast = findViewById(R.id.toast);
        count = findViewById(R.id.count);
        count1 = findViewById(R.id.count1);
        tv = findViewById(R.id.tv);
        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                tv.setText(""+i);
                //tv.setText(String.valueOf(i));
            }
        });
        count1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i--;
                tv.setText(""+i);
                //tv.setText(String.valueOf(i));
            }
        });
        toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,
                        "Welcome to Android.Your count is "+i,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}