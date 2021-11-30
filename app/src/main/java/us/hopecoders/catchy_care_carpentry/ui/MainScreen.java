package us.hopecoders.catchy_care_carpentry.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;

import com.amplifyframework.storage.s3.AWSS3StoragePlugin;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import us.hopecoders.catchy_care_carpentry.R;
import us.hopecoders.catchy_care_carpentry.auth.Profile;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


//************************************************ Start BottomNavigationView ********************************************

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.homeInMenu);

        BottomNavigationItemView profileInMenu = findViewById(R.id.profileInMenu);
        BottomNavigationItemView homeInMenu = findViewById(R.id.homeInMenu);
        BottomNavigationItemView contactUsInMenu= findViewById(R.id.contactUsInMenu);
        BottomNavigationItemView askForServiceInMenu = findViewById(R.id.askForServiceInMenu);

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


        AuthUser authUser = Amplify.Auth.getCurrentUser();
        String username = authUser.getUsername();
        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.User.class),
                response -> {
                    for (User user : response.getData()) {
                        if (user.getUsername().equals(username)) {
                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainScreen.this);
                            SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
                            sharedPreferencesEditor.putString("userId", user.getId());
                            sharedPreferencesEditor.putString("username", user.getUsername());
                            sharedPreferencesEditor.apply();
                        }
                    }
                },
                error -> Log.e("MyAmplifyApp", error.toString(), error)
        );



        RelativeLayout serviscesMain = findViewById(R.id.servicesmain);
        RelativeLayout profilemain = findViewById(R.id.profilemain);
        RelativeLayout contactusmain = findViewById(R.id.contactusmain);

        serviscesMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToOurServices = new Intent(MainScreen.this, OurServices.class);
                startActivity(goToOurServices);
            }
        });

        profilemain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToProfileMain = new Intent(MainScreen.this, Profile.class);
                startActivity(goToProfileMain);
            }
        });

        contactusmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToContactUsMains = new Intent(MainScreen.this, ContactUs.class);
                startActivity(goToContactUsMains);
            }
        });
    }
}


