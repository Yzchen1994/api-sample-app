package com.androidsummit.androidsummitsampleapp.nessie;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidsummit.androidsummitsampleapp.R;
import com.reimaginebanking.api.nessieandroidsdk.models.Customer;

import java.util.List;

/**
 * An adapter to display the results from the GET customers call in {@link NessieActivity}.
 */
public class NessieCustomerListAdapter extends ArrayAdapter<Customer> implements AdapterView.OnItemClickListener {

    private List<Customer> mCustomers;

    private int mResourceId;

    private Context mContext;

    public NessieCustomerListAdapter(Context context, int resource, List<Customer> customers) {
        super(context, resource, customers);
        mCustomers = customers;
        mResourceId = resource;
        mContext = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflator = LayoutInflater.from(getContext());
            view = inflator.inflate(mResourceId, null);
        }

        Customer customer = mCustomers.get(position);

        if (customer != null) {
            TextView firstNameTextView = (TextView) view.findViewById(R.id.first_name_textview);
            firstNameTextView.setText(customer.getFirstName());

            TextView lastNameTextView = (TextView) view.findViewById(R.id.last_name_textview);
            lastNameTextView.setText(customer.getLastName());
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = NessieAccountsManagementActivity.getLaunchIntent(getContext(),
                        mCustomers.get(position).getId());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
