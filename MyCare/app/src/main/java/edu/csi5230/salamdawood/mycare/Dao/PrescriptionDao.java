package edu.csi5230.salamdawood.mycare.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import edu.csi5230.salamdawood.mycare.Entity.Prescription;

/**
 * Created by salamdawood on 12/4/17.
 */
@Dao
public interface PrescriptionDao {
    @Query("SELECT * FROM prescription")
    List<Prescription> getAll();

    @Query("SELECT COUNT(*) from prescription")
    int countPrescription();

    @Insert
    void insertAll(Prescription... prescriptions);

    @Delete
    void delete(Prescription prescription);
}
