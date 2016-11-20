package com.androidsummit.androidsummitsampleapp.nessie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidsummit.androidsummitsampleapp.BuildConfig;
import com.androidsummit.androidsummitsampleapp.R;
import com.androidsummit.androidsummitsampleapp.apimenu.NessieClientWithLog;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.reimaginebanking.api.nessieandroidsdk.NessieError;
import com.reimaginebanking.api.nessieandroidsdk.NessieResultsListener;
import com.reimaginebanking.api.nessieandroidsdk.constants.AccountType;
import com.reimaginebanking.api.nessieandroidsdk.models.Account;

import java.util.Random;

/**
 * Created by yuzechen on 11/19/16.
 */

public class NessieCreateAccountActivity extends AppCompatActivity {
    private static final long MIN_ACC_NUM = 5000000000000000l;
    private static final long MAX_ACC_NUM = 5999999999999999l;
    private static final String USER_ID = "extra_user_id";
    private NessieClientWithLog mClient;

    private String mUserId;

    private Spinner mSpAccountType;
    private EditText mEtNickname;
    private Button mBtSignUp;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public static Intent getLaunchIntent(@NonNull Context context, @NonNull String userId) {
        return new Intent(context, NessieCreateAccountActivity.class).putExtra(USER_ID, userId);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nessie_create_account_activity);

        mClient = NessieClientWithLog.getInstance(BuildConfig.NESSIE_API_KEY);

        mUserId = getIntent().getStringExtra(USER_ID);

        mSpAccountType = (Spinner) findViewById(R.id.new_account_sp_type);
        mEtNickname = (EditText) findViewById(R.id.new_account_et_nickname);
        mBtSignUp = (Button) findViewById(R.id.new_account_bt_signup);

        ArrayAdapter<CharSequence> statesAdapter = ArrayAdapter.createFromResource(this,
                R.array.account_type_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        statesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the mSpStates
        mSpAccountType.setAdapter(statesAdapter);

        mBtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountType type = getAccountType(mSpAccountType.getSelectedItem().toString());
                if (type != null) {
                    Account account = new Account.Builder()
                            .nickname(mEtNickname.getText().toString())
                            .type(type)
                            .rewards(0)
                            .balance(0)
                            .accountNumber(getRandomAccountNumber())
                            .build();
                    mClient.ACCOUNT.createAccount(mUserId, account, new NessieResultsListener() {
                        @Override
                        public void onSuccess(Object result) {
                            Toast.makeText(NessieCreateAccountActivity.this,
                                    "Successfully created account", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(NessieError error) {
                            Toast.makeText(NessieCreateAccountActivity.this,
                                    "Failed to create account: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    Toast.makeText(NessieCreateAccountActivity.this,
                            "Error: type is null", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    AccountType getAccountType(String str) {
        AccountType type;
        if (str.equals("Checking")) {
            type = AccountType.CHECKING;
        } else if (str.equals("Savings")) {
            type = AccountType.SAVINGS;
        } else if (str.equals("Credit Card")) {
            type = AccountType.CREDITCARD;
        } else {
            type = null;
        }
        return type;
    }

    private String getRandomAccountNumber() {
        return Long.toString(MIN_ACC_NUM + (long) (new Random().nextDouble() * (MAX_ACC_NUM - MIN_ACC_NUM)));
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("NessieCreateAccount Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
