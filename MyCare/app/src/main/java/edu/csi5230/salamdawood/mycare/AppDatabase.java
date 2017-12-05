package edu.csi5230.salamdawood.mycare;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import edu.csi5230.salamdawood.mycare.Dao.DoctorDao;
import edu.csi5230.salamdawood.mycare.Dao.PatientDao;
import edu.csi5230.salamdawood.mycare.Dao.PrescriptionDao;
import edu.csi5230.salamdawood.mycare.Dao.UserDao;
import edu.csi5230.salamdawood.mycare.Entity.Doctor;
import edu.csi5230.salamdawood.mycare.Entity.Patient;
import edu.csi5230.salamdawood.mycare.Entity.Prescription;
import edu.csi5230.salamdawood.mycare.Entity.User;

/**
 * Created by salamdawood on 12/4/17.
 */

@Database(entities = {User.class, Patient.class, Doctor.class, Prescription.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract PatientDao patientDao();
    public abstract DoctorDao doctorDao();
    public abstract PrescriptionDao prescriptionDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
