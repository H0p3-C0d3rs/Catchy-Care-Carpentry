package us.hopecoders.catchy_care_carpentry.auth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.collection.ArraySet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Request;
import com.amplifyframework.datastore.generated.model.User;
import com.amplifyframework.storage.StorageAccessLevel;
import com.amplifyframework.storage.options.StorageUploadFileOptions;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import us.hopecoders.catchy_care_carpentry.R;
import us.hopecoders.catchy_care_carpentry.adapters.OurAdapter;
import us.hopecoders.catchy_care_carpentry.ui.AddFurniture;
import us.hopecoders.catchy_care_carpentry.ui.ContactUs;
import us.hopecoders.catchy_care_carpentry.ui.MainScreen;
import us.hopecoders.catchy_care_carpentry.ui.OurServices;
public class Profile extends AppCompatActivity {
    ArraySet<User> list = null;

    List<Request> responseList = new ArrayList<Request>();
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Set home selected
        bottomNavigationView.setSelectedItemId(R.id.profileInMenu);

        BottomNavigationItemView profileInMenu = findViewById(R.id.profileInMenu);
        BottomNavigationItemView homeInMenu = findViewById(R.id.homeInMenu);
        BottomNavigationItemView contactUsInMenu = findViewById(R.id.contactUsInMenu);
        BottomNavigationItemView askForServiceInMenu = findViewById(R.id.askForServiceInMenu);

        profileInMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
            }
        });

        homeInMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainScreen.class));
            }
        });

        contactUsInMenu.setOnClickListener((v) -> {
            startActivity(new Intent(getApplicationContext(), ContactUs.class));
        });

        askForServiceInMenu.setOnClickListener((v) -> {
            startActivity(new Intent(getApplicationContext(), OurServices.class));
        });


        TextView editText = findViewById(R.id.firstAndLastName);
        TextView phoneNumberText = findViewById(R.id.phoneNumberText);
        TextView emailText = findViewById(R.id.emailText);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Profile.this);
        String userId = sharedPreferences.getString("userId", "");

        Amplify.API.query(
                ModelQuery.get(User.class, userId),
                response -> {
                    user = response.getData();
                    responseList = user.getRequest();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            renderNewImg(user.getId());
                            String allTheFirstName = user.getFirstname();
                            String firstLetter = allTheFirstName.substring(0, 1);// get First letter of the string
                            String remLettersString = allTheFirstName.substring(1).toLowerCase();// Get remaining letter using substring

                            firstLetter = firstLetter.toUpperCase();
                            String firstLetterCapitalizedName = firstLetter + remLettersString;

                            String allLastName = user.getLastname();
                            String firstLetterLastName = allLastName.substring(0, 1);
                            String remLastName = allLastName.substring(1).toLowerCase();

                            firstLetterLastName = firstLetterLastName.toUpperCase();
                            String lastName = firstLetterLastName + remLastName;

                            editText.setText(firstLetterCapitalizedName + " " + lastName);

                            phoneNumberText.setText(user.getPhone());
                            emailText.setText(user.getEmail());
                            renderTheData();
                        }
                    });

                },
                error -> Log.e("MyAmplifyApp", error.toString(), error)
        );


        TextView moveToCar = findViewById(R.id.moveToCar);

        moveToCar.setAlpha(0f);
        moveToCar.setTranslationY(50);

        moveToCar.animate().alpha(1f).translationYBy(-50).setDuration(4000);
        moveToCar.setOnClickListener(view -> {
            Intent moveToCarPage = new Intent(Profile.this, AddFurniture.class);
            startActivity(moveToCarPage);
        });

        TextView moveToServicePage = findViewById(R.id.moveToServicePage);
        moveToServicePage.setAlpha(0f);
        moveToServicePage.setTranslationY(50);

        moveToServicePage.animate().alpha(1f).translationYBy(-50).setDuration(4000);
        moveToServicePage.setOnClickListener(view -> {
            Intent moveToService = new Intent(Profile.this, OurServices.class);
            startActivity(moveToService);
        });

        Button addPhoto = findViewById(R.id.addPhoto);
        addPhoto.setOnClickListener(view -> {
            Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
            chooseFile.setType("*/*");
            chooseFile = Intent.createChooser(chooseFile, "Choose a file");
            startActivityForResult(chooseFile, 1234);


        });

        TextView signOutFromProfile = findViewById(R.id.signOutProfile);
        signOutFromProfile.setOnClickListener(v -> {
            Amplify.Auth.signOut(
                    () -> {
                        Log.i("AuthQuickstart", "Signed out successfully");
                        Intent goToSignIn = new Intent(Profile.this, SignIn.class);
                        startActivity(goToSignIn);
                        finish();
                    },
                    error -> Log.e("AuthQuickstart", error.toString())
            );
        });
    }

    public void renderTheData() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewInProfilePage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new OurAdapter(responseList));
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @SuppressLint("LongLogTag")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
   Uri uri=data.getData();
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            Log.v("cause yo're a natural a beating heart of stone 🎶🎶🎶🎶🎶",inputStream.toString());
            saveImgToS3(user.getId(),uri);
            updateImgData(user.getId());



//            Log.v("I am not part of your Machine I am the Machine 🎶🎶🎶🎶🎶",file.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    @SuppressLint("LongLogTag")
    public void saveImgToS3(String imgName, Uri imgData) {
        try {
            InputStream exampleInputStream = getContentResolver().openInputStream(imgData);

            StorageUploadFileOptions
                    .builder()
                    .accessLevel(StorageAccessLevel.PUBLIC)
                    .build();

            Amplify.Storage.uploadInputStream(
                    imgName,
                    exampleInputStream,
                    result -> {
                        Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey());
                        Log.v("I am not part of your Machine I am the Machine 🎶🎶🎶🎶🎶",imgData.getPath());

//                        renderNewImg(imgName);
                    },
                    storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
            );
        } catch (FileNotFoundException error) {
            Log.e("MyAmplifyApp", "Could not find file to open for input stream.", error);
        }
    }

    public void renderNewImg(String imgName) {
        if (user.getImage()!=null){
            ImageView profilePic = (ImageView) findViewById(R.id.profilePic);
            Amplify.Storage.downloadFile(
                    imgName,
                    new File(getApplicationContext().getFilesDir() + "/" + imgName + ".jpg"),
                    result -> {
                        Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getName());
                        Bitmap bitmap = BitmapFactory.decodeFile(result.getFile().getPath());
                        profilePic.setImageBitmap(bitmap);
                    },
                    error -> Log.e("MyAmplifyApp", "Download Failure", error)
            );
        }
    }

    private void updateImgData(String imgName) {
        Amplify.API.query(
                ModelQuery.get(User.class, user.getId()),
                response -> {
                    Log.i("MyAmplifyApp", "UpdateQuery");
                    User userUpdate = response.getData().copyOfBuilder()
                            .image(imgName)
                            .build();
                    Amplify.API.mutate(ModelMutation.update(userUpdate),
                            response3 ->  Log.i("MyAmplifyApp", "Updated Todo with id: " + response3.getData().getFirstname()),
                            error -> Log.e("MyAmplifyApp", "Update failed", error)
                    );
                },
                error -> Log.e("MyAmplifyApp", "Query failure", error)
        );

    }
}