package com.androidsummit.androidsummitsampleapp.nessie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidsummit.androidsummitsampleapp.BuildConfig;
import com.androidsummit.androidsummitsampleapp.R;
import com.androidsummit.androidsummitsampleapp.apimenu.NessieClientWithLog;
import com.reimaginebanking.api.nessieandroidsdk.NessieError;
import com.reimaginebanking.api.nessieandroidsdk.NessieResultsListener;
import com.reimaginebanking.api.nessieandroidsdk.models.Address;
import com.reimaginebanking.api.nessieandroidsdk.models.Customer;

/**
 * Created by yuzechen on 11/19/16.
 */

public class NessieCustomerRegistration extends AppCompatActivity {
    private NessieClientWithLog mClient;

    private EditText mEtFirstName, mEtLastName, mEtStreetNumber, mEtStreetName, mEtCity,
            mEtState, mEtZip;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nessie_create_customer_activity);

        mClient = NessieClientWithLog.getInstance(BuildConfig.NESSIE_API_KEY);

        mEtFirstName = (EditText) findViewById(R.id.fragment_signup_et_first_name);
        mEtLastName = (EditText) findViewById(R.id.fragment_signup_et_last_name);
        mEtStreetNumber = (EditText) findViewById(R.id.fragment_signup_et_street_number);
        mEtStreetName = (EditText) findViewById(R.id.fragment_signup_et_street_name);
        mEtCity = (EditText) findViewById(R.id.fragment_signup_et_city);
        mEtState = (EditText) findViewById(R.id.fragment_signup_et_state);
        mEtZip = (EditText) findViewById(R.id.fragment_signup_et_zip);


        Button button = (Button) findViewById(R.id.fragment_signup_bt_signup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    Address address = new Address.Builder()
                            .streetNumber(mEtStreetNumber.getText().toString())
                            .streetName(mEtStreetName.getText().toString())
                            .city(mEtCity.getText().toString())
                            .state(mEtState.getText().toString())
                            .zip(mEtZip.getText().toString())
                            .build();
                    Customer customer = new Customer.Builder()
                            .firstName(mEtFirstName.getText().toString())
                            .lastName(mEtLastName.getText().toString())
                            .address(address)
                            .build();
                    mClient.CUSTOMER.createCustomer(customer, new NessieResultsListener() {
                        @Override
                        public void onSuccess(Object result) {
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.signup_success_message), Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(NessieError error) {
                            Toast.makeText(getApplicationContext(),
                                    getString(R.string.signup_failure_message) + ": " + error.getMessage(), Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });
                }
            }
        });
    }

    private boolean isValid() {
        // Check if any column is empty.
        boolean valid = true;
        String firstName = mEtFirstName.getText().toString();
        String lastName = mEtLastName.getText().toString();
        String streetNumber = mEtStreetNumber.getText().toString();
        String streetName = mEtStreetName.getText().toString();
        String city = mEtCity.getText().toString();
        String state = mEtState.getText().toString();
        String zip = mEtZip.getText().toString();

        if (isEmpty(firstName)) {
            valid = false;
            String estring = getResources().getString(R.string.signup_first_name_empty);
            mEtFirstName.setError(estring);
        }

        if (isEmpty(lastName)) {
            valid = false;
            String estring = getResources().getString(R.string.signup_last_name_empty);
            mEtLastName.setError(estring);
        }

        if (isEmpty(streetNumber)) {
            valid = false;
            String estring = getResources().getString(R.string.signup_street_number_empty);
            mEtStreetNumber.setError(estring);
        }

        if (isEmpty(streetName)) {
            valid = false;
            String estring = getResources().getString(R.string.signup_street_name_empty);
            mEtStreetName.setError(estring);
        }

        if (isEmpty(city)) {
            valid = false;
            String estring = getResources().getString(R.string.signup_city_empty);
            mEtCity.setError(estring);
        }

        if (isEmpty(state)) {
            valid = false;
            String estring = getResources().getString(R.string.signup_state_empty);
            mEtState.setError(estring);
        }

        if (isEmpty(zip)) {
            valid = false;
            String estring = getResources().getString(R.string.signup_zip_empty);
            mEtZip.setError(estring);
        }

        return valid;
    }

    private boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        } else {
            return false;
        }
    }
}

