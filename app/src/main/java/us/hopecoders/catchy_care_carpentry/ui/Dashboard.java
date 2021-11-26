package us.hopecoders.catchy_care_carpentry.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Request;

import java.util.ArrayList;
import java.util.List;

import us.hopecoders.catchy_care_carpentry.R;
import us.hopecoders.catchy_care_carpentry.adapters.PendingRequestsAdapter;
import us.hopecoders.catchy_care_carpentry.auth.SignIn;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        RecyclerView dashBoardRecycler = findViewById(R.id.dashBoardRecycler);
        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message message) {
                dashBoardRecycler.getAdapter().notifyDataSetChanged();
                return false;
            }
        });

        List<Request> requestsList = new ArrayList<Request>();
        Amplify.API.query(
                ModelQuery.list(com.amplifyframework.datastore.generated.model.Request.class),
                response -> {
                    for (Request request : response.getData()) {
                        if (!request.getIsTaken()) {
                            requestsList.add(request);
                        }
                    }
                    handler.sendEmptyMessage(1);
                },
                error -> Log.e("MyAmplifyApp", error.toString(), error)
        );
        dashBoardRecycler.setLayoutManager(new LinearLayoutManager(this));
        dashBoardRecycler.setAdapter(new PendingRequestsAdapter(requestsList, getApplicationContext()));


        TextView signOutFromDashBoard = findViewById(R.id.signOutDashBoard);
        signOutFromDashBoard.setOnClickListener(v -> {
            Amplify.Auth.signOut(
                    () -> {
                        Log.i("AuthQuickstart", "Signed out successfully");
                        Intent goToSignIn = new Intent(Dashboard.this, SignIn.class);
                        startActivity(goToSignIn);
                        finish();
                    },
                    error -> Log.e("AuthQuickstart", error.toString())
            );
        });

    }
}