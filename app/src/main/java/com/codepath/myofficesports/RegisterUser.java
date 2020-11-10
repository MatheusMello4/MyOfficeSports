package com.codepath.myofficesports;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {

    private TextView txtAppName, btnRegister;
    private EditText etName, etEmail, etUsername, etPassword;
    private RadioButton rbtnCoach, rbtnAthlete ;
    public String category;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        txtAppName = (TextView) findViewById(R.id.txtAppName);
        txtAppName.setOnClickListener((View.OnClickListener) this);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener((View.OnClickListener) this);

        etName = (EditText) findViewById(R.id.etName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etUsername =(EditText) findViewById(R.id.etUsername2);
        etPassword = (EditText) findViewById(R.id.etPassword2);

        rbtnCoach = (RadioButton) findViewById(R.id.rbtnCoach);
        rbtnAthlete = (RadioButton) findViewById(R.id.rbtnAthlete);



    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txtAppName:
                startActivity(new Intent(RegisterUser.this, LoginActivity.class));
                break;

            case R.id.btnRegister:
                registerUser();
                break;
        }

    }

    public void onRadioButtonClicked (View v) {
        // Is the button now checked?
        boolean checked = ((RadioButton) v).isChecked();


        // Check which radio button was clicked
        switch(v.getId()) {
            case R.id.rbtnCoach:
                if (checked)
                    category = "Coach";
                     break;
            case R.id.rbtnAthlete:
                if (checked)
                    category = "Athlete";
                    break;
        }
    }

    private void registerUser() {
        final String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        final String fullName = etName.getText().toString().trim();
        final String username = etUsername.getText().toString().trim();


        if(fullName.isEmpty()){
            etName.setError("Full name is required!");
            etName.requestFocus();
            return;
        }

        if(email.isEmpty()){
            etEmail.setError("Email is required!");
            etEmail.requestFocus();
            return;
        }

        /*if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Please provide valid email! ");
            etEmail.requestFocus();
            return;
        }*/

        if(password.isEmpty()){
            etPassword.setError("Password is required!");
            etPassword.requestFocus();
            return;
        }

       if(password.length() < 6){
           etPassword.setError("Min password length should be 6 characters!");
           etPassword.requestFocus();
           return;
       }

        if(username.isEmpty()){
            etUsername.setError("Username is required!");
            etUsername.requestFocus();
            return;
        }






        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(fullName, email, username, category);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterUser.this, "User has been registered successfully!", Toast.LENGTH_LONG).show();
                                        ((EditText) findViewById(R.id.etName)).getText().clear();
                                        ((EditText) findViewById(R.id.etUsername2)).getText().clear();
                                        ((EditText) findViewById(R.id.etPassword2)).getText().clear();
                                        ((EditText) findViewById(R.id.etEmail)).getText().clear();
                                        ((RadioButton) findViewById(R.id.rbtnCoach)).setChecked(false);
                                        ((RadioButton) findViewById(R.id.rbtnAthlete)).setChecked(false);


                                    }else{
                                        Toast.makeText(RegisterUser.this,"Failed to register! Try again!", Toast.LENGTH_LONG).show();

                                    }
                                }
                            });



                        }
                    }
                });
    }
}
