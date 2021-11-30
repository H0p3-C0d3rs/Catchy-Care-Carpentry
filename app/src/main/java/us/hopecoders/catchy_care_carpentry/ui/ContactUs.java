package us.hopecoders.catchy_care_carpentry.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telecom.Call;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.datastore.generated.model.Request;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import us.hopecoders.catchy_care_carpentry.R;
import us.hopecoders.catchy_care_carpentry.auth.Profile;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        FloatingActionButton goole = findViewById(R.id.fab_google);
        goole.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com"));
            startActivity(intent);
        });
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

//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.activity_contact_us, container, false);
//
//        Button button = rootView.findViewById(R.id.fab_google);
//        button.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
//                startActivity(browserIntent);
//            }
//        });
//
//        return rootView;
//    }
}