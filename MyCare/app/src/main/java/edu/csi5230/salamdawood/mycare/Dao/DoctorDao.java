package edu.csi5230.salamdawood.mycare.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import edu.csi5230.salamdawood.mycare.Entity.Doctor;

/**
 * Created by salamdawood on 12/4/17.
 */
@Dao
public interface DoctorDao {
    @Query("SELECT * FROM doctor")
    List<Doctor> getAll();

    @Query("SELECT * FROM doctor where (first_name || last_name) LIKE :username")
    Doctor findByUserName(String username);

    @Query("SELECT COUNT(*) from doctor")
    int countDoctors();

    @Insert
    void insertAll(Doctor... doctors);

    @Delete
    void delete(Doctor doctor);
}
