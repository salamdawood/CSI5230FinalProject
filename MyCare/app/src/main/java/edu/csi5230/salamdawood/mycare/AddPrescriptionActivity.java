package edu.csi5230.salamdawood.mycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.csi5230.salamdawood.mycare.Entity.Patient;
import edu.csi5230.salamdawood.mycare.Entity.Prescription;

public class AddPrescriptionActivity extends AppCompatActivity {

    EditText prescriptionEditText = null, quantityEditText = null, instructionsEditText = null;
    Spinner patientSpinner = null;
    Button submitButton = null;

    List<Patient> patientQueryList = new ArrayList<>();
    ArrayList<String> patients = new ArrayList<>();
    ArrayAdapter<String> adapter = null;
    final AppDatabase db = AppDatabase.getAppDatabase(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);

        prescriptionEditText = (EditText) findViewById(R.id.prescriptionNameEditText);
        quantityEditText = (EditText) findViewById(R.id.quantityEditText);
        instructionsEditText = (EditText) findViewById(R.id.instructionsEditText);

        patientSpinner = (Spinner) findViewById(R.id.patientSpinner);

        patientQueryList = db.patientDao().
                findPatientsForDoctor(((MyCareApplication) getApplication()).
                        getCurrentDoctor().getUid());

        createPatientList(patientQueryList);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, patients);

        patientSpinner.setAdapter(adapter);

        submitButton = (Button) findViewById(R.id.submitPrescriptionButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String patientInfo = (String) patientSpinner.getItemAtPosition(patientSpinner.getSelectedItemPosition());
                String[] splitPatientInfo = patientInfo.split(":");
                int patientID = Integer.valueOf(splitPatientInfo[0]);

                Prescription prescription = new Prescription();

                prescription.setName(prescriptionEditText.getText().toString());
                prescription.setQuantity(Integer.parseInt(quantityEditText.getText().toString()));
                prescription.setDescription(instructionsEditText.getText().toString());
                prescription.setPatientID(patientID);
                prescription.setDoctorID(((MyCareApplication) getApplication()).
                        getCurrentDoctor().getUid());
                prescription.setStatus("");

                db.prescriptionDao().insertAll(prescription);

                finish();
            }
        });

    }

    public void createPatientList(List<Patient> patientList){
        for (Patient patient: patientList) {
            patients.add(patient.getUid() + ": " + patient.getFirstName() + " " + patient.getLastName());
        }
    }
}
