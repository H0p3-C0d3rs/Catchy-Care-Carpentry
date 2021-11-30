package us.hopecoders.catchy_care_carpentry.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.Call;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.TextView;

import com.amplifyframework.datastore.generated.model.Request;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import us.hopecoders.catchy_care_carpentry.R;
import us.hopecoders.catchy_care_carpentry.auth.Profile;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);


        //************************************************ Start BottomNavigationView ********************************************
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.contactUsInMenu);


        BottomNavigationItemView profileInMenu = findViewById(R.id.profileInMenu);
        BottomNavigationItemView homeInMenu = findViewById(R.id.homeInMenu);
        BottomNavigationItemView contactUsInMenu= findViewById(R.id.contactUsInMenu);
        BottomNavigationItemView askForServiceInMenu = findViewById(R.id.askForServiceInMenu);
        BottomNavigationItemView galleryMenu = findViewById(R.id.gallery);
        galleryMenu.setOnClickListener((v)->{
            startActivity(new Intent(getApplicationContext() , Galary.class));
        });

        profileInMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , Profile.class));
            }
        });

        homeInMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext() , MainScreen.class));
            }
        });

        contactUsInMenu.setOnClickListener((v)->{
            startActivity(new Intent(getApplicationContext() , ContactUs.class));
        });

        askForServiceInMenu.setOnClickListener((v)->{
            startActivity(new Intent(getApplicationContext() , OurServices.class));
        });

//************************************************ End BottomNavigationView ********************************************
    }


}