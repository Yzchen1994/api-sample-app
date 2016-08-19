package com.androidsummit.androidsummitsampleapp.apimenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.androidsummit.androidsummitsampleapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * The initial activity that opens when starting the app.  Select an API from this activity to be taken to that APIs implementation page.
 */
public class ApiMenuActivity extends AppCompatActivity {

    private GridView apiMenuGrid;
    private List<ApiMenuItem> apiMenuItemList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.api_menu);

        // TODO: move this setup to an XML file...

        // Set up API Menu Items here
        apiMenuItemList = new ArrayList<>();

        // Nessie
        ApiMenuItem nessieItem = new ApiMenuItem.Builder()
            .title(getString(R.string.nessie_title))
            .imageResource(R.mipmap.nessie_logo)
            .build();
        apiMenuItemList.add(nessieItem);

        // Card.IO
        ApiMenuItem cardioItem = new ApiMenuItem.Builder()
            .title(getString(R.string.cardio_title))
            .imageResource(R.mipmap.cardio)
            .build();
        apiMenuItemList.add(cardioItem);

        // Firebase Auth
        ApiMenuItem firebaseAuthItem = new ApiMenuItem.Builder()
            .title(getString(R.string.firebase_authui_title))
            .imageResource(R.mipmap.firebase)
            .build();
        apiMenuItemList.add(firebaseAuthItem);

        // Firebase Messaging
        ApiMenuItem firebaseMessagingItem = new ApiMenuItem.Builder()
            .title(getString(R.string.firebase_messaging_title))
            .imageResource(R.mipmap.firebase)
            .build();
        apiMenuItemList.add(firebaseMessagingItem);

        apiMenuGrid = (GridView) findViewById(R.id.api_menu_gridlayout);
        apiMenuGrid.setAdapter(new ApiMenuGridAdapter(this, apiMenuItemList));
    }


}