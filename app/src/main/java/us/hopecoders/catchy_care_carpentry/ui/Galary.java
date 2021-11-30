package us.hopecoders.catchy_care_carpentry.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Dashbord;

import java.util.ArrayList;
import java.util.List;

import us.hopecoders.catchy_care_carpentry.R;
import us.hopecoders.catchy_care_carpentry.adapters.GalaryAdapter;

public class Galary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galary);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("");
        ImageView imageView=findViewById(R.id.back);
        imageView.setOnClickListener(v->{
            finish();
        });
        setSupportActionBar(toolbar);
        RecyclerView dashBoardRecycler = findViewById(R.id.recy);
        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                dashBoardRecycler.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        List<Dashbord> requestsList = new ArrayList<Dashbord>();
        Amplify.API.query(
                ModelQuery.list(Dashbord.class),
                response -> {
                    for (Dashbord request : response.getData()) {
                            requestsList.add(request);

                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", error.toString(), error)
        );
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL); // set Horizontal Orientation
        dashBoardRecycler.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        dashBoardRecycler.setAdapter(new GalaryAdapter(requestsList,getApplicationContext()));


    }

    public void add(View view) {
            Intent intent=new Intent(getApplicationContext(), SomeWorkCamera.class);
            startActivity(intent);

    }
}