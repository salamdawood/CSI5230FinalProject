package edu.csi5230.salamdawood.mycare;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.sql.Date;
import java.util.List;

import edu.csi5230.salamdawood.mycare.Entity.Doctor;
import edu.csi5230.salamdawood.mycare.Entity.Patient;
import edu.csi5230.salamdawood.mycare.Entity.User;

/**
 * Created by salamdawood on 12/4/17.
 */

public class DatabaseInitializer {

    private static final String TAG = DatabaseInitializer.class.getName();

    public static void populateAsync(@NonNull final AppDatabase db) {
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public static void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    private static User addUser(final AppDatabase db, User user) {
        db.userDao().insertAll(user);
        return user;
    }

    private static Patient addPatient(final AppDatabase db, Patient patient) {
        db.patientDao().insertAll(patient);
        return patient;
    }

    private static Doctor addDoctor(final AppDatabase db, Doctor doctor) {
        db.doctorDao().insertAll(doctor);
        return doctor;
    }

    private static void populateWithTestData(AppDatabase db) {
        Doctor doctor = new Doctor();
        doctor.setFirstName("Nilesh");
        doctor.setLastName("Patel");
        doctor.setPassword("opendoctor");

        addDoctor(db, doctor);

        List<Doctor> doctorList = db.doctorDao().getAll();
        Log.d(DatabaseInitializer.TAG, "Doctor Rows Count: " + doctorList.size());

        Patient patient = new Patient();
        patient.setFirstName("Salam");
        patient.setLastName("Dawood");
        patient.setDateOfBirth("01-02-2017");
        patient.setPassword("open");
        patient.setDoctorID(0);

        addPatient(db, patient);

        List<Patient> patientList = db.patientDao().getAll();
        Log.d(DatabaseInitializer.TAG, "Patient Rows Count: " + patientList.size());
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }
}
