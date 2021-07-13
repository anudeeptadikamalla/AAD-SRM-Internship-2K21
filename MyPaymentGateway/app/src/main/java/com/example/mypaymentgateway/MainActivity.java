package com.example.mypaymentgateway;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final String API_KEY="rzp_test_uyu3bCylMdhlpv";
    Checkout checkout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Checkout.preload(getApplicationContext());
    }

    public void doPayment(View view) {
        checkout = new Checkout();
        checkout.setKeyID(API_KEY);
        // checkout.preload(getApplicationContext());
        checkout.setImage(R.drawable.pay);
        final Activity activity = this;
        JSONObject options = new JSONObject();
        try {
            JSONObject object = new JSONObject();
            object.put("Name","Sai Sankar");
            object.put("amount","10000");
            object.put("theme.color","#44BB04");
            object.put("image","https://p7.hiclipart.com/preview/212/540/940/logo-banner-health-care-home-care-service-logo-design.jpg");
            object.put("currency","INR");
            checkout.open(activity,object);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this, "payment success", Toast.LENGTH_SHORT).show();
        Log.i(TAG,"success");
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "payment faild", Toast.LENGTH_SHORT).show();

    }
}