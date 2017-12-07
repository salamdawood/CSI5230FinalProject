package edu.csi5230.salamdawood.mycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.csi5230.salamdawood.mycare.Entity.Patient;

public class AddEditPatientActivity extends AppCompatActivity {

    EditText firstNameEditText = null, lastNameEditText = null, DOBEditText = null;
    Button savePatientButton = null;
    Intent otherIntent = null;
    int index = -1;

    final AppDatabase db = AppDatabase.getAppDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_patient);

        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        DOBEditText = (EditText) findViewById(R.id.DOBEditText);

        savePatientButton = (Button) findViewById(R.id.savePatientbutton);

        otherIntent = getIntent();

        if (otherIntent.hasExtra("index")){
            index = otherIntent.getIntExtra("index", -1);
            firstNameEditText.setText(otherIntent.getStringExtra("first"));
            lastNameEditText.setText(otherIntent.getStringExtra("last"));
            DOBEditText.setText(otherIntent.getStringExtra("dob"));
        }

        savePatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == -1){
                    Patient patient = new Patient();

                    patient.setFirstName(firstNameEditText.getText().toString());
                    patient.setLastName(lastNameEditText.getText().toString());
                    patient.setDateOfBirth(DOBEditText.getText().toString());
                    patient.setPassword("open");
                    patient.setDoctorID(((MyCareApplication) getApplication()).
                            getCurrentDoctor().getUid());

                    db.patientDao().insertAll(patient);

                    finish();
                }
                else {
                    //update user info
                }
            }
        });
    }
}
