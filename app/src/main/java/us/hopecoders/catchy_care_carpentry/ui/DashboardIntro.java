package us.hopecoders.catchy_care_carpentry.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import us.hopecoders.catchy_care_carpentry.R;

public class DashboardIntro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_intro);
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