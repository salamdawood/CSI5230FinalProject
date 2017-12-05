package edu.csi5230.salamdawood.mycare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import edu.csi5230.salamdawood.mycare.Dao.UserDao;
import edu.csi5230.salamdawood.mycare.Entity.Doctor;
import edu.csi5230.salamdawood.mycare.Entity.Patient;
import edu.csi5230.salamdawood.mycare.Entity.User;

public class LoginActivity extends AppCompatActivity {

    TextView usernameText = null, passwordText = null;
    Button loginButton = null;

    UserDao userDao;

    private static final String TAG = LoginActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final AppDatabase db = AppDatabase.getAppDatabase(this);

        usernameText = (TextView) findViewById(R.id.usernameText);
        passwordText = (TextView) findViewById(R.id.passwordText);
        loginButton = (Button) findViewById(R.id.passwordButton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();

                Patient patient = db.patientDao().findByUserName(username);
                Doctor doctor = db.doctorDao().findByUserName(username);

                if (patient != null) {
                    Log.d(TAG, patient.getFirstName());

                    if (patient.getPassword().equals(password)){
                        ((MyCareApplication) getApplication()).setCurrentPatient(patient);
                        Intent intent = new Intent(view.getContext(), UserHomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                    }
                }
                else if (doctor != null){
                    Log.d(TAG, doctor.getFirstName());

                    if (doctor.getPassword().equals(password)){
                        ((MyCareApplication) getApplication()).setCurrentDoctor(doctor);

                        Intent intent = new Intent(view.getContext(), DoctorHomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                    }
                }

                //DatabaseInitializer.populateAsync(db);
                //List<Patient> patients = db.patientDao().getAll();
                //usernameText.setText(patients.get(0).getFirstName());
            }
        });
    }

    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }

    @Override
    protected void onResume(){
        super.onResume();
        usernameText.setText("");
        passwordText.setText("");
    }
}
