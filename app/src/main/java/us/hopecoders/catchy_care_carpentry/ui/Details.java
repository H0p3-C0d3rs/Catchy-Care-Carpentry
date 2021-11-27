package us.hopecoders.catchy_care_carpentry.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import us.hopecoders.catchy_care_carpentry.R;

public class Details extends AppCompatActivity implements OnMapReadyCallback {

    public double lat;
    public double lng;
    public String cityName;
    public String countryName;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        lat = intent.getExtras().getDouble("lat");
        lng = intent.getExtras().getDouble("lng");
        cityName = intent.getExtras().getString("cityName");
        countryName = intent.getExtras().getString("countryName");

        TextView pendingView = findViewById(R.id.pendingView);
        Boolean taken = intent.getExtras().getBoolean("taken");
        if (taken){
            pendingView.setText("Approved");
            pendingView.setBackgroundColor(Color.parseColor("#6ECB63"));
        }


        // render fields
        TextView requestNameView = findViewById(R.id.requestNameView);
        requestNameView.setText("Request Name: " + intent.getExtras().getString("requestName"));

        TextView descriptionView = findViewById(R.id.descriptionView);
        descriptionView.setText("Description : " + intent.getExtras().getString("requestDescription"));

        TextView phoneView = findViewById(R.id.phoneView);
        phoneView.setText("Phone Number: " + intent.getExtras().getString("phone"));

        TextView furTypeView = findViewById(R.id.furTypeView);
        furTypeView.setText("fur Type: " + intent.getExtras().getString("woodType"));

        TextView furModelView = findViewById(R.id.furModelView);
        furModelView.setText("fur Model: " + intent.getExtras().getString("woodModel"));

        TextView gasolineView = findViewById(R.id.gasolineView);
        gasolineView.setText("Gasoline Type: " + intent.getExtras().getString("gasoline"));

        TextView cityNameView = findViewById(R.id.cityNameView);
        cityNameView.setText("Country Name: " + countryName);

        TextView countryNameView = findViewById(R.id.countryNameView);
        countryNameView.setText("City Name: " + cityName);

        // preparing map
        if (lat != 0 && lng != 0) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
    }

    // when map is ready
    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng location = new LatLng(lat, lng);
        googleMap.addMarker(new MarkerOptions().position(location).title(cityName));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
    }
}