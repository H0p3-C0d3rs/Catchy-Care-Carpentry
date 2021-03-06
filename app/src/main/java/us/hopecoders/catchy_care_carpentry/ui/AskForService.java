package us.hopecoders.catchy_care_carpentry.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Furnuture;
import com.amplifyframework.datastore.generated.model.OurLocation;
import com.amplifyframework.datastore.generated.model.Request;
import com.amplifyframework.datastore.generated.model.Service;
import com.amplifyframework.datastore.generated.model.User;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.io.IOException;
import java.util.List;
import java.util.Locale;

import us.hopecoders.catchy_care_carpentry.Not;
import us.hopecoders.catchy_care_carpentry.R;
import us.hopecoders.catchy_care_carpentry.adapters.FurnitureAdapter;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.sns.SnsClient;
//import software.amazon.awssdk.services.sns.model.CreateTopicRequest;
//import software.amazon.awssdk.services.sns.model.CreateTopicResponse;
//import software.amazon.awssdk.services.sns.model.SnsException;

public class AskForService extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    EditText serviceDescription, phoneNumber;
    TextView countryName, cityName, serviceName;
    Button shareLocation, submit ,addFur;
    FusedLocationProviderClient fusedLocationProviderClient;
    String countryNameStorage, cityNameStorage;
    Double longitudeStorage, latitudeStorage;
    User user;
    Service service;
    OurLocation currentLocation = null;
    Furnuture furnuture;
    boolean checked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_for_service);


        serviceName = findViewById(R.id.serviceName);
        serviceDescription = findViewById(R.id.serviceDescription);
        phoneNumber = findViewById(R.id.phoneNumber);

        countryName = findViewById(R.id.displayTheCountry);
        cityName = findViewById(R.id.displayTheCity);
        shareLocation = findViewById(R.id.shareLocationBtn);
        submit = findViewById(R.id.submitBtn);
        addFur = findViewById(R.id.addFurFromRequest);

        addFur.animate().rotationX(360).setDuration(3000);
//        submit.animate().alpha(1f).translationYBy(50).setDuration(1500);


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(AskForService.this);
        shareLocationListener();

        ////////////////////////////////// get user and service by Id ////////////////////////////////////
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AskForService.this);
        String userId = sharedPreferences.getString("userId", "");
        Intent intent = getIntent();
        String serviceId = intent.getExtras().getString("serviceId");

        Amplify.API.query(
                ModelQuery.get(User.class, userId),
                response -> {
                    user = response.getData();
                    Log.i("User ================ ", response.getData().getUsername());

                    ////////////////////////////// Furniture's recycler view /////////////////////////////
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            List<Furnuture> furnitureList = user.getFurnuture();
                            RecyclerView furRecyclerView = findViewById(R.id.furRecycler);
                            furRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            furRecyclerView.setAdapter(new FurnitureAdapter(furnitureList));
                        }
                    });
                },
                error -> Log.e("MyAmplifyApp", error.toString(), error)
        );

        Amplify.API.query(
                ModelQuery.get(Service.class, serviceId),
                response -> {
                    Log.i("User ================ ", response.getData().getName());
                    serviceName.setText(response.getData().getName());
                    service = response.getData();
                },
                error -> Log.e("MyAmplifyApp", error.toString(), error)
        );
    }

    ////////////////////////////// Function to handle submit request /////////////////////////////
    @Override
    protected void onStart() {
        super.onStart();

        submit.setOnClickListener(view -> {
            if (checked && !serviceDescription.getText().toString().equals("") && !phoneNumber.getText().toString().equals("")) {
                String furId = sharedPreferences.getString("furId", "");
                if (!furId.equals("")) {
                    Amplify.API.query(
                            ModelQuery.get(Furnuture.class, furId),
                            response -> {
                                Log.i("Furni ================ ", response.getData().getId());
                                furnuture = response.getData();
                                saveTheDataInTheCloud();

                            },
                            error -> Log.e("MyAmplifyApp", error.toString(), error)
                    );
                    Not not=new Not();
                    not.notifications(getApplicationContext());
                }

            } else {
                handler2();
            }
        });

        ////////////////////////////// Function to move to add furniture ///////////////////////////
        addFur.setOnClickListener(view -> {
            Intent goToAddFur = new Intent(AskForService.this, AddFurniture.class);
            startActivity(goToAddFur);
        });

    }

    ////////////////////////////// Function to handle the listener of add location /////////////////////////////
    public void shareLocationListener() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            showLocation();
        } else
            ActivityCompat.requestPermissions(AskForService.this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 5);
    }


    private void saveTheDataInTheCloud() {
        saveLocationData(countryNameStorage, cityNameStorage, longitudeStorage, latitudeStorage);
        saveServiceData(serviceName.getText().toString(),
                serviceDescription.getText().toString(),
                phoneNumber.getText().toString());
        handler1();
    }

    ////////////////////////////// Function to show the location /////////////////////////////
    private void showLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            Geocoder geocoder = new Geocoder(AskForService.this, Locale.getDefault());
                            try {
                                List<Address> address = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
                                Address obj = address.get(0);
                                countryNameStorage = obj.getCountryName();
                                cityNameStorage = obj.getLocality();
                                longitudeStorage = location.getLongitude();
                                latitudeStorage = location.getLatitude();
                                System.out.println("++++++++++++++++++++++++++++++ " + obj.getLocality() + " ++++++++++++++++++++++++++++++");
                                Log.i("Location", obj.getCountryName());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    ////////////////////////////////// Function to save the service data ////////////////////////////////////
    private void saveServiceData(String name, String description, String number) {
        Request request = Request.builder()
                .name(name)
                .description(description)
                .phone(number)
                .isTaken(false)
                .service(service)
                .user(user)
                .ourLocation(currentLocation)
                .furnuture(furnuture)
                .build();

        Amplify.API.mutate(
                ModelMutation.create(request),
                response -> Log.i("MyAmplifyApp", "Added request with id: " + response.getData().getId()),
                error -> Log.e("MyAmplifyApp", "Create failed", error)
        );
        finish();
    }

    //////////////////////////////////// Function to save the location data ////////////////////////////////////
    private void saveLocationData(String country, String city,
                                  Double longitudeNumber, Double latitudeNumber) {
        OurLocation location = OurLocation.builder()
                .countryName(country != null ? country : "")
                .cityName(city != null ? city : "")
                .longitude(longitudeNumber != null ? longitudeNumber : 0.0)
                .latitude(latitudeNumber != null ? latitudeNumber : 0.0)
                .build();
        currentLocation = location;

        Amplify.API.mutate(
                ModelMutation.create(location),
                response -> Log.i("MyAmplifyApp", "Added request with id: " + response.getData().getId()),
                error -> Log.e("MyAmplifyApp", "Create failed", error)
        );
        finish();
    }

    ////////////////////////////// Function to check if location checked /////////////////////////////
    public boolean onCheckboxClicked(View view) {
        checked = ((CheckBox) view).isChecked();
        if (checked) {
            countryName.setText(countryNameStorage);
            cityName.setText(cityNameStorage);
        } else {
            countryName.setText("");
            cityName.setText("");
        }
        return checked;
    }

    ////////////////////////////// Function to handle if submitted done or not /////////////////////////////
    public void handler1() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Request has sent Successfully!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void handler2() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Please fill all fields!", Toast.LENGTH_LONG).show();
            }
        });
    }


}