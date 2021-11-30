package us.hopecoders.catchy_care_carpentry.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Furnuture;
import com.amplifyframework.datastore.generated.model.User;

import java.util.List;

import us.hopecoders.catchy_care_carpentry.R;
import us.hopecoders.catchy_care_carpentry.adapters.FurnitureAdapter;
import us.hopecoders.catchy_care_carpentry.auth.Profile;

public class AddFurniture extends AppCompatActivity {

    private String furType;
    private String model;
    private String woodType;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_furnuture);

        // // // // // // // // // Get furniture's List // // // // // // // // // // // // // //
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AddFurniture.this);
        String userId = sharedPreferences.getString("userId", "");
        Amplify.API.query(
                ModelQuery.get(User.class, userId),
                response -> {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            user = response.getData();
                            List<Furnuture> furList = response.getData().getFurnuture();
                            RecyclerView addFurRecyclerView = findViewById(R.id.addFurRecycler);
                            addFurRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            addFurRecyclerView.setAdapter(new FurnitureAdapter(furList));
                        }
                    });
                },
                error -> Log.e("MyAmplifyApp", error.toString(), error)
        );

        // // // // // // // // // Get views and text // // // // // // // // // // // // // //
        EditText editTextFurModel = findViewById(R.id.editTextWoodModel);
        EditText editTextFurType = findViewById(R.id.editTextWoodType);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);


        // // // // // // // // // add furniture's listener // // // // // // // // // // // // // //
        Button addFur = findViewById(R.id.addFur);
        addFur.setOnClickListener(view -> {
            furType = editTextFurType.getText().toString();
            model = editTextFurModel.getText().toString();
            int chosenGasolineType = radioGroup.getCheckedRadioButtonId();
            RadioButton chosenButton = findViewById(chosenGasolineType);

            if (chosenButton != null && !furType.equals("") && !model.equals("")) {
                woodType = chosenButton.getText().toString();
                addFur(furType, model, woodType);
            } else {
                handler2();
            }
        });

        // // // // // // // // // go to profile listener // // // // // // // // // // // // // //
        Button buttonGoProfile = findViewById(R.id.buttonGoProfile);
        buttonGoProfile.setOnClickListener(view -> {
            Intent goToProfile = new Intent(AddFurniture.this, Profile.class);
            startActivity(goToProfile);
        });

    }

    // // // // // // // // // method to save furniture's in could // // // // // // // // // // // // // //
    private void addFur(String furType, String furModel, String woodType) {

        Furnuture fur = Furnuture.builder()
                .type(furType)
                .user(user)
                .model(furModel)
                .woodType(woodType)
                .build();

        Amplify.API.mutate(
                ModelMutation.create(fur),
                response2 -> {
                    Log.i("MyAmplifyApp", "Added furniture's with id: " + response2.getData().getId());
                    handler1();
                    finish();
                    startActivity(getIntent());
                },
                error -> Log.i("MyAmplifyApp", "Create failed", error)
        );
    }

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