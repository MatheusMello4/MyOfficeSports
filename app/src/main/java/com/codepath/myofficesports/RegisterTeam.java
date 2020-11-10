package com.codepath.myofficesports;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterTeam extends AppCompatActivity {

    private EditText etTeamName, etSport, etCityState, etHeadCoach;
    private Button btnCreateTeams;
    private ImageView imgTeamLogo;

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_team);



        imgTeamLogo = (ImageView) findViewById(R.id.imgTeamLogo);

        etTeamName = (EditText) findViewById(R.id.etTeamName);
        etSport = (EditText) findViewById(R.id.etSport);
        etCityState =(EditText) findViewById(R.id.etCityState);
        etHeadCoach = (EditText) findViewById(R.id.etHeadCoach);

        btnCreateTeams = (Button) findViewById(R.id.btnCreateTeam);

        btnCreateTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerTeam();
            }
        });

    }





    private void registerTeam() {
        final String teamName = etTeamName.getText().toString().trim();
        final String sport = etSport.getText().toString().trim();
        final String cityState = etCityState.getText().toString().trim();
        final String headCoach = etHeadCoach.getText().toString().trim();

        Teams teams = new Teams(teamName, sport, cityState, headCoach);

       // mDatabase.child("Teams").setValue(teams);

        FirebaseDatabase.getInstance().getReference("Teams")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(teams).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterTeam.this, "Team has been registered successfully!", Toast.LENGTH_LONG).show();
                    ((EditText) findViewById(R.id.etTeamName)).getText().clear();
                    ((EditText) findViewById(R.id.etSport)).getText().clear();
                    ((EditText) findViewById(R.id.etCityState)).getText().clear();
                    ((EditText) findViewById(R.id.etHeadCoach)).getText().clear();
                    //((RadioButton) findViewById(R.id.rbtnCoach)).setChecked(false);
                    //((RadioButton) findViewById(R.id.rbtnAthlete)).setChecked(false);

                }else{
                    Toast.makeText(RegisterTeam.this,"Failed to register! Try again!", Toast.LENGTH_LONG).show();

                }




            }
        });
    }
}