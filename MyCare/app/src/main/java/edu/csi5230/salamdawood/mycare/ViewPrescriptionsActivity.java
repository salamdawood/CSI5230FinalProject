package edu.csi5230.salamdawood.mycare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.csi5230.salamdawood.mycare.Entity.Patient;
import edu.csi5230.salamdawood.mycare.Entity.Prescription;

public class ViewPrescriptionsActivity extends AppCompatActivity {

    ListView viewPrescriptionsListView = null;

    ArrayList<String> prescriptions = new ArrayList<>();
    List<Prescription> prescriptionsQueryList = new ArrayList<>();
    ArrayAdapter<String> adapter = null;
    final AppDatabase db = AppDatabase.getAppDatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_prescriptions);

        viewPrescriptionsListView = (ListView) findViewById(R.id.viewPrescriptionsListView);

        prescriptionsQueryList = db.prescriptionDao().
                findPatientPrescriptions(((MyCareApplication) getApplication()).
                        getCurrentPatient().getUid());

        createPrescriptionList(prescriptionsQueryList);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, prescriptions);
        viewPrescriptionsListView.setAdapter(adapter);

        viewPrescriptionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }

    public void createPrescriptionList(List<Prescription> prescriptionList){
        for (Prescription prescription: prescriptionList) {
            prescriptions.add(prescription.getName() + " : Quantity " + prescription.getQuantity());
        }
    }
}
