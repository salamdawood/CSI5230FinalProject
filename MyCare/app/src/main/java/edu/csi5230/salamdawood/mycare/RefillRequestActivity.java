package edu.csi5230.salamdawood.mycare;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.csi5230.salamdawood.mycare.Entity.Prescription;

public class RefillRequestActivity extends AppCompatActivity {

    ListView refillRequestsListView = null;

    ArrayList<String> prescriptions = new ArrayList<>();
    List<Prescription> prescriptionsQueryList = new ArrayList<>();
    ArrayAdapter<String> adapter = null;

    final AppDatabase db = AppDatabase.getAppDatabase(this);

    int prescriptionID = -1;
    AlertDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refill_request);

        refillRequestsListView = (ListView) findViewById(R.id.refillRequestListView);

        prescriptionsQueryList = db.prescriptionDao().
                findPrescriptionRefillRequests(((MyCareApplication) getApplication()).
                        getCurrentDoctor().getUid());

        createPrescriptionList(prescriptionsQueryList);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, prescriptions);

        refillRequestsListView.setAdapter(adapter);

        refillRequestsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String prescriptionInfo = (String) refillRequestsListView.getItemAtPosition(i);
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
                prescription.setStatus("Approved");
                db.prescriptionDao().updatePrescriptions(prescription);

                Toast.makeText(getApplicationContext(), "Request Approved", Toast.LENGTH_SHORT).show();

                prescriptionID = -1;
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Prescription prescription = db.prescriptionDao().findPrescriptionByID(prescriptionID);
                prescription.setStatus("Denied, Call Doctor");
                db.prescriptionDao().updatePrescriptions(prescription);

                Toast.makeText(getApplicationContext(), "Request Denied", Toast.LENGTH_SHORT).show();

                prescriptionID = -1;
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
        }
    }
}
