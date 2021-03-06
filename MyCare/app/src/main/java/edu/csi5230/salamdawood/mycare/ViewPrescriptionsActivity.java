package edu.csi5230.salamdawood.mycare;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.csi5230.salamdawood.mycare.Entity.Patient;
import edu.csi5230.salamdawood.mycare.Entity.Prescription;

public class ViewPrescriptionsActivity extends AppCompatActivity {

    ListView viewPrescriptionsListView = null;

    ArrayList<String> prescriptions = new ArrayList<>();
    ArrayList<String> prescriptionStatus = new ArrayList<>();
    List<Prescription> prescriptionsQueryList = new ArrayList<>();

    StringAdapter adapter = null;

    final AppDatabase db = AppDatabase.getAppDatabase(this);
    int prescriptionID = -1;
    AlertDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prescriptions);

        viewPrescriptionsListView = (ListView) findViewById(R.id.viewPrescriptionsListView);

        prescriptionsQueryList = db.prescriptionDao().
                findPatientPrescriptions(((MyCareApplication) getApplication()).
                        getCurrentPatient().getUid());

        createPrescriptionList(prescriptionsQueryList);

        adapter = new StringAdapter(this, prescriptions, prescriptionStatus);

        viewPrescriptionsListView.setAdapter(adapter);

        viewPrescriptionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ViewGroup group = (ViewGroup) view;
                TextView textview = (TextView) group.getChildAt(0);

                String prescriptionInfo = textview.getText().toString();
                String[] splitPrescriptionInfo = prescriptionInfo.split(":");
                prescriptionID = Integer.valueOf(splitPrescriptionInfo[0]);

                dialog.show();

            }
        });

        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage("Do you want to request a refill?")
                .setTitle("Request Refill");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Prescription prescription = db.prescriptionDao().findPrescriptionByID(prescriptionID);
                prescription.setStatus("Awaiting Approval");
                db.prescriptionDao().updatePrescriptions(prescription);

                Toast.makeText(getApplicationContext(), "Request Sent", Toast.LENGTH_SHORT).show();

                prescriptionID = -1;
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do nothing
            }
        });

        // 3. Get the AlertDialog from create()
        dialog = builder.create();
    }

    public void createPrescriptionList(List<Prescription> prescriptionList){
        for (Prescription prescription: prescriptionList) {
            prescriptions.add(prescription.getUid() + ": " +
                    prescription.getName() + " : Quantity "
                    + prescription.getQuantity());

            prescriptionStatus.add("Status: " + prescription.getStatus());
        }
    }
}
