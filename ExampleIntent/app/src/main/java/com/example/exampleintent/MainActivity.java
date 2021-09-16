package com.example.exampleintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText name,num,mail,web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        num = findViewById(R.id.num);
        mail = findViewById(R.id.mail);
        web = findViewById(R.id.web);
    }

    public void save(View view) {
        //Explicit Intent
        //Intent i = new Intent(presentact,nextact)
        //startActivity(i)
        String s = name.getText().toString();
        Intent i = new Intent(this,SecondActivity.class);
        i.putExtra("key",s);
        startActivity(i);
    }

    public void dail(View view) {
        //Implicit Intent
        String s = num.getText().toString().trim();
        //tel: - phone number/ https:// - website/ geo: - location
        Uri u = Uri.parse("tel:"+s);
        Intent i = new Intent(Intent.ACTION_DIAL,u);
        startActivity(i);
    }

    public void mail(View view) {
        String s = num.getText().toString();
        //tel: - phone number/ https:// - website/ geo: - location
        Uri u = Uri.parse("mailto:"+s);
        Intent i = new Intent(Intent.ACTION_VIEW,u);
        startActivity(i);
    }

    public void web(View view) {
    }
}