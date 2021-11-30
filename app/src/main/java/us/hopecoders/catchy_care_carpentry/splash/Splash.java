package us.hopecoders.catchy_care_carpentry.splash;

import androidx.appcompat.app.AppCompatActivity;
import us.hopecoders.catchy_care_carpentry.R;
import us.hopecoders.catchy_care_carpentry.intros.OnBoardingActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            //This method will be executed once the timer is over
            // Start your app main activity
            startActivity(new Intent(Splash.this, OnBoardingActivity.class));
            // close this activity
            finish();

        }, 6000); // pause the launch of the dashboard for 3 seconds

    }
}