package com.androidsummit.androidsummitsampleapp.nessie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.androidsummit.androidsummitsampleapp.R;
import com.androidsummit.androidsummitsampleapp.apimenu.NessieClientWithLog;

/**
 * Created by yuzechen on 11/19/16.
 */

public class NessieCustomerRegistration extends AppCompatActivity {
    private NessieClientWithLog mClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nessie_create_customer_activity);

    }
}
