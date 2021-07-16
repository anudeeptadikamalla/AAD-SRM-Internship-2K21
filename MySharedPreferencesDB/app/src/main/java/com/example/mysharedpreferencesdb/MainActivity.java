package com.example.mysharedpreferencesdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mysharedpreferencesdb.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        sp=getSharedPreferences("apssdc",MODE_PRIVATE);

    }

    public void showData(View view) {
        Toast.makeText(this, ""+binding.etUsername.getText().toString()+"\n"+
                binding.etUserpassword.getText().toString(), Toast.LENGTH_SHORT).show();

        editor=sp.edit();
        editor.putString("name",binding.etUsername.getText().toString());
        editor.putString("pass",binding.etUserpassword.getText().toString());
        editor.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        editor=sp.edit();
        editor.putString("name",binding.etUsername.getText().toString());
        editor.putString("pass",binding.etUserpassword.getText().toString());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.etUsername.setText(sp.getString("name",""));
        binding.etUserpassword.setText(sp.getString("pass",""));

    }
}