package com.example.booksinfo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.booksinfo.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Please Wait..!");
        dialog.setMessage("Loading..!");
        dialog.show();
        EndPointInterface pointInterface=RetroFitInstance.getRetrofit().create(EndPointInterface.class);
        Call<String> c=pointInterface.getData();
        c.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("MainActivity",response.body());
                dialog.dismiss();
                try {
                    JSONObject rootObj=new JSONObject(response.body());
                    JSONArray rootAry=rootObj.getJSONArray("items");
                    JSONObject itemsObj=rootAry.getJSONObject(0);
                    JSONObject volumeInfoObj=itemsObj.getJSONObject("volumeInfo");
                    String resTitle=volumeInfoObj.getString("title");
                    /*Toast.makeText(MainActivity.this, ""+resTitle, Toast.LENGTH_SHORT).show();*/
                    binding.tvTitle.setText("Title: "+resTitle);
                    JSONArray authorsary=volumeInfoObj.getJSONArray("authors");
                    binding.tvAuthor.setText("Authors: "+authorsary);
                    String resDescription=volumeInfoObj.getString("description");
                    binding.tvDes.setText("Description: "+resDescription);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failed for Loading..", Toast.LENGTH_SHORT).show();
            }
        });

    }
}