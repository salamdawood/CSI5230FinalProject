package edu.csi5230.salamdawood.mycare.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.csi5230.salamdawood.mycare.Entity.Patient;
import edu.csi5230.salamdawood.mycare.Entity.Prescription;

/**
 * Created by salamdawood on 12/4/17.
 */
@Dao
public interface PrescriptionDao {
    @Query("SELECT * FROM prescription")
    List<Prescription> getAll();

    @Query("SELECT * FROM prescription where patient_id = :patientID")
    List<Prescription> findPatientPrescriptions(int patientID);

    @Query("SELECT COUNT(*) from prescription")
    int countPrescription();

    @Query("SELECT * FROM prescription where uid = :prescriptionID")
    Prescription findPrescriptionByID(int prescriptionID);

    @Query("SELECT * FROM prescription where status = 'Awaiting Approval' and doctor_id = :doctorID")
    List<Prescription> findPrescriptionRefillRequests(int doctorID);

    @Insert
    void insertAll(Prescription... prescriptions);

    @Update
    void updatePrescriptions(Prescription... prescriptions);

    @Delete
    void delete(Prescription prescription);
}
