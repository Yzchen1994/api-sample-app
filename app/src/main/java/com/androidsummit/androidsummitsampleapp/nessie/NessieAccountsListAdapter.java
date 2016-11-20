package com.androidsummit.androidsummitsampleapp.nessie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.reimaginebanking.api.nessieandroidsdk.models.Account;

import java.util.List;

/**
 * Created by yuzechen on 11/19/16.
 */

public class NessieAccountsListAdapter extends ArrayAdapter<Account> {
    private List<Account> mAccounts;
    private int mResourceId;

    public NessieAccountsListAdapter(Context context, int resource, List<Account> accounts) {
        super(context, resource);
        this.mAccounts = accounts;
        this.mResourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflator = LayoutInflater.from(getContext());
            view = inflator.inflate(mResourceId, null);
        }

        Account account = mAccounts.get(position);

        if (account != null) {
            //TODO: Bind xml with members in account
        }

        return view;
    }
}
