package edu.csi5230.salamdawood.mycare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserHomeActivity extends AppCompatActivity {

    Button myInfoButton = null, myPrescriptionsButton = null, logoutButton = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

        myInfoButton = (Button) findViewById(R.id.myInfoButton);
        myPrescriptionsButton = (Button) findViewById(R.id.myPrescriptionsButton);
        logoutButton = (Button) findViewById(R.id.logoutButton);

        myInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        myPrescriptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
