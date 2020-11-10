package com.codepath.myofficesports;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "LoginActivity";
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private TextView txtRegister;

    private RadioButton rbtnCoach;
    private RadioButton rbtnAthlete;

    private RegisterUser registerUser = new RegisterUser();

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        rbtnAthlete = findViewById(R.id.rbtnAthlete);
        rbtnCoach = findViewById(R.id.rbtnCoach);

        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        txtRegister = findViewById(R.id.txtRegister);
        txtRegister.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    private void loginUser(String username, String password){
        Log.i(TAG,"Attempting to login user " + username);

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null){
                    Log.e(TAG,"Issue with login", e);
                    return;

                }
                //TODO: navigate to the main activity if the user has signed in properly
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
            }


        });
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.txtRegister:
                goRegisterUser();
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnLogin:
                userLogin();
                break;
        }
    }

    private void userLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(email.isEmpty()){
            etEmail.setError("Username is required!");
            etEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            etPassword.setError("Password is required!");
            etPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            etPassword.setError("Min password length is 6 characters!");
            etPassword.requestFocus();
            return;
        }

        String currentUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference category = rootRef.child("Users").child(currentUser).child("category");


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //redirect to user profile

                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this,"Failed to login! Please check your credentials",Toast.LENGTH_LONG).show();
                }
            }
        });




    }

    private void goMainActivity() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goRegisterUser(){
        Intent i = new Intent(LoginActivity.this, RegisterUser.class);
        startActivity(i);
        finish();
    }
}
