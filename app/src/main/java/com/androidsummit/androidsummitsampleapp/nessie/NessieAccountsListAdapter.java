package com.androidsummit.androidsummitsampleapp.nessie;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidsummit.androidsummitsampleapp.R;
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
        TextView tvCurrentBalance, tvRewardBalance, tvAccountName, tvEndingIn;

        tvCurrentBalance = (TextView) view.findViewById(R.id.account_adapter_current_balance_tv);
        tvRewardBalance = (TextView) view.findViewById(R.id.account_adapter_reward_balance_tv);
        tvAccountName = (TextView) view.findViewById(R.id.account_adapter_account_name_tv);
        tvEndingIn = (TextView) view.findViewById(R.id.account_adapter_ending_tv);

        if (view == null) {
            LayoutInflater inflator = LayoutInflater.from(getContext());
            view = inflator.inflate(mResourceId, null);
        }

        Account account = mAccounts.get(position);

        if (account != null) {
            // Bind xml with members in account
            tvCurrentBalance.setText(account.getBalance());
            tvRewardBalance.setText(account.getRewards());
            tvAccountName.setText(account.getNickname());
            tvEndingIn.setText(getEndInStr(account.getAccountNumber()));
        }

        return view;
    }

    private String getEndInStr(String str) {
        try {
            String result = "(Ending in ";
            result += str.substring(str.length() - 1, str.length() - 4);
            result += ")";
            return result;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
