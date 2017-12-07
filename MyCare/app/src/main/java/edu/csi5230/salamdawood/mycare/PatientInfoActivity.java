package edu.csi5230.salamdawood.mycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PatientInfoActivity extends AppCompatActivity {

    TextView firstNameTextView = null, lastNameTextView = null, DOBTextView = null;
    Button updateInfoButton = null;
    Intent otherIntent = null;
    int index = -1;
    final AppDatabase db = AppDatabase.getAppDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);

        firstNameTextView = (TextView) findViewById(R.id.firstNameTextView);
        lastNameTextView = (TextView) findViewById(R.id.lastNameTextView);
        DOBTextView = (TextView) findViewById(R.id.DOBTextView);

        updateInfoButton = (Button) findViewById(R.id.updateInfoButton);

        otherIntent = getIntent();

        if (otherIntent.hasExtra("index")){
            index = otherIntent.getIntExtra("index", -1);
            firstNameTextView.setText(otherIntent.getStringExtra("first"));
            lastNameTextView.setText(otherIntent.getStringExtra("last"));
            DOBTextView.setText(otherIntent.getStringExtra("dob"));
        }

        updateInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
