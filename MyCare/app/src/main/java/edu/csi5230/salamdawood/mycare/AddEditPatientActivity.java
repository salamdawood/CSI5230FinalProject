package edu.csi5230.salamdawood.mycare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddEditPatientActivity extends AppCompatActivity {

    EditText firstNameEditText = null, lastNameEditText = null, DOBEditText = null;
    Button savePatientButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_patient);

        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        DOBEditText = (EditText) findViewById(R.id.DOBEditText);

        savePatientButton = (Button) findViewById(R.id.savePatientbutton);

        savePatientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
