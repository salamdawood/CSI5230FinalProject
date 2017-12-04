package edu.csi5230.salamdawood.mycare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPrescriptionActivity extends AppCompatActivity {

    EditText patientEditText = null, prescriptionEditText = null, quantityEditText = null, instructionsEditText = null;
    Button submitButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_prescription);

        patientEditText = (EditText) findViewById(R.id.patientEditText);
        prescriptionEditText = (EditText) findViewById(R.id.prescriptionNameEditText);
        quantityEditText = (EditText) findViewById(R.id.quantityEditText);
        instructionsEditText = (EditText) findViewById(R.id.instructionsEditText);

        submitButton = (Button) findViewById(R.id.submitPrescriptionButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

    }
}
