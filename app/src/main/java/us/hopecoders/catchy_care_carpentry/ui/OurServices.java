package us.hopecoders.catchy_care_carpentry.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Service;
import com.amplifyframework.datastore.generated.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import us.hopecoders.catchy_care_carpentry.R;
import us.hopecoders.catchy_care_carpentry.auth.ConfirmationCode;
import us.hopecoders.catchy_care_carpentry.auth.Profile;
import us.hopecoders.catchy_care_carpentry.auth.SignUp;

public class OurServices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_services);


        //************************************************ Start BottomNavigationView ********************************************

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.askForServiceInMenu);

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
//saveServices("Assembly");
//        saveServices("Installation");
//        saveServices("Painting");
//        saveServices("Remodeling");
//        saveServices("upholstery");
//        saveServices("other");

    }
    public void saveServices(String name){

        // save to dynamoDp
        Service service =
                Service.builder()
                .name(name)
                .build();
                    
                               System.out.println("this is user data ==> "+service);
        Amplify.API.mutate(
                ModelMutation.create(service),
                response2 -> {
                    Log.i("MyAmplifyApp", "Added user with id: " + response2.getData().getId());
                },
                error -> Log.e("MyAmplifyApp", "Create failed", error)
        );
    }
    @Override
    protected void onStart() {
        super.onStart();
        CardView card1 = findViewById(R.id.card1);
        card1.setOnClickListener(view -> {
            Intent goToAskForService = new Intent(OurServices.this, AskForService.class);
            goToAskForService.putExtra("serviceId", "3e8e3f13-4f84-4164-99d2-f8b273e5469d");
            startActivity(goToAskForService);
        });

        CardView card2 = findViewById(R.id.card2);
        card2.setOnClickListener(view -> {
            Intent goToAskForService = new Intent(OurServices.this, AskForService.class);
            goToAskForService.putExtra("serviceId", "e0da5b85-b0ea-476e-8ac3-7ad3af1ccb77");
            startActivity(goToAskForService);
        });

        CardView card3 = findViewById(R.id.card3);
        card3.setOnClickListener(view -> {
            Intent goToAskForService = new Intent(OurServices.this, AskForService.class);
            goToAskForService.putExtra("serviceId", "89285462-2593-4c47-acf1-5e45aa3d3397");
            startActivity(goToAskForService);
        });

        CardView card4 = findViewById(R.id.card4);
        card4.setOnClickListener(view -> {
            Intent goToAskForService = new Intent(OurServices.this, AskForService.class);
            goToAskForService.putExtra("serviceId", "c4cfab45-82af-4569-9328-2b61ad8726be");
            startActivity(goToAskForService);
        });

        CardView card5 = findViewById(R.id.card5);
        card5.setOnClickListener(view -> {
            Intent goToAskForService = new Intent(OurServices.this, AskForService.class);
            goToAskForService.putExtra("serviceId", "714a7aee-171a-4fbf-bb1a-eae04a3546c3");
            startActivity(goToAskForService);
        });

        CardView card6 = findViewById(R.id.card6);
        card6.setOnClickListener(view -> {
            Intent goToAskForService = new Intent(OurServices.this, AskForService.class);
            goToAskForService.putExtra("serviceId", "56b10031-ef50-439d-9a30-ab50487c0452");
            startActivity(goToAskForService);
        });

    }


}