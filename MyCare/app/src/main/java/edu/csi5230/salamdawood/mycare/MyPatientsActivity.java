package edu.csi5230.salamdawood.mycare;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.csi5230.salamdawood.mycare.Entity.Patient;

public class MyPatientsActivity extends AppCompatActivity {

    ListView patientsListView = null;
    Button addPatientButton = null;
    ArrayList<String> patients = new ArrayList<>();
    List<Patient> patientQueryList = new ArrayList<>();
    ArrayAdapter<String> adapter = null;
    final AppDatabase db = AppDatabase.getAppDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_patients);

        patientsListView = (ListView) findViewById(R.id.patientsListView);
        addPatientButton = (Button) findViewById(R.id.addPatientButton);

        patientQueryList = db.patientDao().
                findPatientsForDoctor(((MyCareApplication) getApplication()).
                        getCurrentDoctor().getUid());

        createPatientList(patientQueryList);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, patients);
        patientsListView.setAdapter(adapter);

        patientsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String patientInfo = (String) patientsListView.getItemAtPosition(i);
                String[] splitPatientInfo = patientInfo.split(":");
                int patientID = Integer.valueOf(splitPatientInfo[0]);
                //Toast.makeText(getApplicationContext(), patientID, Toast.LENGTH_SHORT).show();
                Patient patient = db.patientDao().findUserByID(patientID);

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

        addPatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddEditPatientActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.clear();
        adapter.notifyDataSetChanged();

        patientQueryList = db.patientDao().
                findPatientsForDoctor(((MyCareApplication) getApplication()).
                        getCurrentDoctor().getUid());

        createPatientList(patientQueryList);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, patients);
        patientsListView.setAdapter(adapter);
    }

    public void createPatientList(List<Patient> patientList){
        for (Patient patient: patientList) {
            patients.add(patient.getUid() + ": " + patient.getFirstName() + " " + patient.getLastName());
        }
    }
}
