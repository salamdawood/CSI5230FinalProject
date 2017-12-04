package edu.csi5230.salamdawood.mycare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoctorHomeActivity extends AppCompatActivity {

    Button patientsButton = null, refillsButton = null, addPrescriptionButton = null, logoutButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);

        patientsButton = (Button) findViewById(R.id.patientsButton);
        refillsButton = (Button) findViewById(R.id.refillsButton);
        addPrescriptionButton = (Button) findViewById(R.id.addPrescriptionButton);
        logoutButton = (Button) findViewById(R.id.logoutButtonDoctor);

        patientsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        refillsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        addPrescriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
