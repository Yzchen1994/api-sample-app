package com.androidsummit.androidsummitsampleapp.nessie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.androidsummit.androidsummitsampleapp.BuildConfig;
import com.androidsummit.androidsummitsampleapp.R;
import com.androidsummit.androidsummitsampleapp.apimenu.NessieClientWithLog;
import com.reimaginebanking.api.nessieandroidsdk.NessieError;
import com.reimaginebanking.api.nessieandroidsdk.NessieResultsListener;
import com.reimaginebanking.api.nessieandroidsdk.constants.AccountType;
import com.reimaginebanking.api.nessieandroidsdk.models.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzechen on 11/19/16.
 */

public class NessieAccountManagementActivity extends AppCompatActivity {
    private static final String USER_ID = "extra_user_id";
    private NessieClientWithLog mClient;

    private String mUserId;

    private ListView mListView;

    private List<Account> mAccounts = new ArrayList<>();

    public static Intent getLaunchIntent(@NonNull Context context, @NonNull String userId) {
        return new Intent(context, NessieAccountManagementActivity.class)
                .putExtra(USER_ID, userId);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nessie_account_management_activity);

        mUserId = getIntent().getStringExtra(USER_ID);

        String key = BuildConfig.NESSIE_API_KEY;
        mClient = NessieClientWithLog.getInstance(key);

        mListView = (ListView) findViewById(R.id.accounts_listview);

        getCheckingInfo();
    }

    void getCheckingInfo() {
        mClient.ACCOUNT.getAccounts(AccountType.CHECKING, new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                List<Account> accounts = (List<Account>) result;
                if (accounts != null && accounts.size() > 0) {
                    for (int i = 0; i < accounts.size(); i++) {
                        mAccounts.add(accounts.get(i));
                    }
                }
                getSavingInfo();
            }

            @Override
            public void onFailure(NessieError error) {

            }
        });
    }

    void getSavingInfo() {
        mClient.ACCOUNT.getAccounts(AccountType.SAVINGS, new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                List<Account> accounts = (List<Account>) result;
                if (accounts != null && accounts.size() > 0) {
                    for (int i = 0; i < accounts.size(); i++) {
                        mAccounts.add(accounts.get(i));
                    }
                }
                getCreditCardInfo();
            }

            @Override
            public void onFailure(NessieError error) {

            }
        });
    }

    void getCreditCardInfo() {
        mClient.ACCOUNT.getAccounts(AccountType.CREDITCARD, new NessieResultsListener() {
            @Override
            public void onSuccess(Object result) {
                List<Account> accounts = (List<Account>) result;
                if (accounts != null && accounts.size() > 0) {
                    for (int i = 0; i < accounts.size(); i++) {
                        mAccounts.add(accounts.get(i));
                    }
                }
                showAccounts();
            }

            @Override
            public void onFailure(NessieError error) {

            }
        });
    }

    void showAccounts() {
        NessieAccountsListAdapter adapter = new NessieAccountsListAdapter(getApplicationContext(),
                R.layout.nessie_account_adapter, mAccounts);
        mListView.setAdapter(adapter);
    }
}
