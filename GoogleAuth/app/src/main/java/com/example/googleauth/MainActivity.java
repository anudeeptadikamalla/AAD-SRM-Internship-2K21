package com.example.googleauth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    EditText number,otp;
    FirebaseAuth auth;
    /*To get Otp, Auto verification, to capture the failed cases */
    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    /*To resend otp*/
    PhoneAuthProvider.ForceResendingToken token;
    /*To store the verification code*/
    String id;
    /*need for sign in using google*/
    GoogleSignInClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number = findViewById(R.id.number);
        otp = findViewById(R.id.otp);
        auth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        client = GoogleSignIn.getClient(this,gso);

        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                id = s;
                token = forceResendingToken;
            }

            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signPhoneAuth(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(MainActivity.this, ""+e,
                        Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void signPhoneAuth(PhoneAuthCredential phoneAuthCredential) {
        auth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(MainActivity.this,
                                    HomeActivity.class));
                            finish();
                        }else {
                            Toast.makeText(MainActivity.this, "Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void send(View view) {
        String n = number.getText().toString();
        PhoneAuthOptions options=PhoneAuthOptions.newBuilder()
                .setPhoneNumber("+91"+n)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)
                .build();
            PhoneAuthProvider.verifyPhoneNumber(options);
        Toast.makeText(this, "Otp sent", Toast.LENGTH_SHORT).show();
    }

    public void verify(View view) {
        String s = otp.getText().toString().trim();
        if (s.isEmpty()){
            otp.setError("Can't be empty");
        }else {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(id,s);
            signPhoneAuth(credential);
        }
    }

    public void resend(View view) {
        String n = number.getText().toString();
        PhoneAuthOptions options=PhoneAuthOptions.newBuilder()
                .setPhoneNumber("+91"+n)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)
                .setForceResendingToken(token)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        Toast.makeText(this, "Otp Re-sent", Toast.LENGTH_SHORT).show();
    }

    public void gsign(View view) {
        Intent i = client.getSignInIntent();
        startActivityForResult(i,0);
    }
    /*for not disturbing the activity*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            firebaseGsign(account.getIdToken(),account.getEmail());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    private void firebaseGsign(String idToken, String email) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken,null);
        auth.signInWithCredential(credential).addOnCompleteListener(this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(MainActivity.this,
                                    HomeActivity.class));
                        }else {
                            Toast.makeText(MainActivity.this, "Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}