package us.hopecoders.catchy_care_carpentry.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;

import us.hopecoders.catchy_care_carpentry.R;
import us.hopecoders.catchy_care_carpentry.auth.SignIn;

public class DashboardIntro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_intro);
        TextView signOutFromDashBoard = findViewById(R.id.signOutDashBoard);
        signOutFromDashBoard.setOnClickListener(v -> {
            Amplify.Auth.signOut(
                    () -> {
                        Log.i("AuthQuickstart", "Signed out successfully");
                        Intent goToSignIn = new Intent(DashboardIntro.this, SignIn.class);
                        startActivity(goToSignIn);
                        finish();
                    },
                    error -> Log.e("AuthQuickstart", error.toString())
            );
        });
    }

    public void addPhoto(View view) {
        Intent intent=new Intent(getApplicationContext(),Galary.class);
        startActivity(intent);
    }

    public void dashboard(View view) {
        Intent intent=new Intent(getApplicationContext(),Dashboard.class);
        startActivity(intent);
    }

}