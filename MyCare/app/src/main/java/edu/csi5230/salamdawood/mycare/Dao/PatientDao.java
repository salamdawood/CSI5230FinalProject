package edu.csi5230.salamdawood.mycare.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

import edu.csi5230.salamdawood.mycare.Entity.Patient;

/**
 * Created by salamdawood on 12/4/17.
 */
@Dao
public interface PatientDao {
    @Query("SELECT * FROM patient")
    List<Patient> getAll();

    @Query("SELECT * FROM patient where first_name LIKE  :firstName AND last_name LIKE :lastName")
    Patient findByName(String firstName, String lastName);

    @Query("SELECT * FROM patient where (first_name || last_name) LIKE :username")
    Patient findByUserName(String username);

    @Query("SELECT * FROM patient where doctor_id = :doctorID")
    List<Patient> findPatientsForDoctor(int doctorID);

    @Query("SELECT * FROM patient where uid = :patientID")
    Patient findUserByID(int patientID);

    @Query("UPDATE patient SET doctor_id = 1")
    void updateDoctor();

    @Query("SELECT COUNT(*) from patient")
    int countPatients();

    @Insert
    void insertAll(Patient... patients);

    @Delete
    void delete(Patient patient);
}
