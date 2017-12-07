package edu.csi5230.salamdawood.mycare;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.csi5230.salamdawood.mycare.Entity.Patient;

public class UserHomeActivity extends AppCompatActivity {

    Button myInfoButton = null, myPrescriptionsButton = null, logoutButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        myInfoButton = (Button) findViewById(R.id.myInfoButton);
        myPrescriptionsButton = (Button) findViewById(R.id.myPrescriptionsButton);
        logoutButton = (Button) findViewById(R.id.logoutButton);

        myInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Patient patient = ((MyCareApplication) getApplication()).getCurrentPatient();

                Intent intent = new Intent(view.getContext(), PatientInfoActivity.class);
                intent.putExtra("index", patient.getUid());
                intent.putExtra("first", patient.getFirstName());
                intent.putExtra("last", patient.getLastName());
                intent.putExtra("dob", patient.getDateOfBirth());
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                intent.setFlags(PendingIntent.FLAG_UPDATE_CURRENT);
                startActivity(intent);
            }
        });

        myPrescriptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ViewPrescriptionsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MyCareApplication) getApplication()).setCurrentPatient(null);
                Intent intent = new Intent(view.getContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }
}
