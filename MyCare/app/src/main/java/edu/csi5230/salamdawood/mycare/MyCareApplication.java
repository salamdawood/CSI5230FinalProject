package edu.csi5230.salamdawood.mycare;

import android.app.Application;

import edu.csi5230.salamdawood.mycare.Entity.Doctor;
import edu.csi5230.salamdawood.mycare.Entity.Patient;

/**
 * Created by salamdawood on 12/4/17.
 */

public class MyCareApplication extends Application {
    private Patient currentPatient = null;
    private Doctor currentDoctor = null;

    public Patient getCurrentPatient() {
        return currentPatient;
    }

    public void setCurrentPatient(Patient currentPatient) {
        this.currentPatient = currentPatient;
    }

    public Doctor getCurrentDoctor() {
        return currentDoctor;
    }

    public void setCurrentDoctor(Doctor currentDoctor) {
        this.currentDoctor = currentDoctor;
    }
}
