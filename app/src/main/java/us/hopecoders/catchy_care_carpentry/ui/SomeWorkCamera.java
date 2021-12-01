package us.hopecoders.catchy_care_carpentry.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Dashbord;
import com.amplifyframework.datastore.generated.model.User;
import com.amplifyframework.storage.StorageAccessLevel;
import com.amplifyframework.storage.options.StorageUploadFileOptions;
import com.otaliastudios.cameraview.PictureResult;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import us.hopecoders.catchy_care_carpentry.R;
import us.hopecoders.catchy_care_carpentry.auth.SignIn;

import static us.hopecoders.catchy_care_carpentry.ui.OurWork.RESULT;

public class SomeWorkCamera extends AppCompatActivity {
    private String imageURL;
    private Button save;
    private Button move_to_galary;
    public static final String HAHA = "uploadResult";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_some_work_camera);

        TextView signOutFromDashBoard = findViewById(R.id.signOutDashBoard);
        signOutFromDashBoard.setOnClickListener(v -> {
            Amplify.Auth.signOut(
                    () -> {
                        Log.i("AuthQuickstart", "Signed out successfully");
                        Intent goToSignIn = new Intent(SomeWorkCamera.this, SignIn.class);
                        startActivity(goToSignIn);
                        finish();
                    },
                    error -> Log.e("AuthQuickstart", error.toString())
            );
        });
        ImageView imageView=findViewById(R.id.back);
        imageView.setOnClickListener(v->{
            finish();
        });
        ImageButton camera = findViewById(R.id.camera);
        camera.setOnClickListener(view -> {
            cameraActivityResultLauncher.launch(new Intent(SomeWorkCamera.this, OurWork.class));

        });
        move_to_galary = findViewById(R.id.move_to_gallery);
        move_to_galary.setOnClickListener(view -> {

            Intent i=new Intent(getApplicationContext(),Galary.class);
            startActivity(i);
        });

        ImageButton addPhoto = findViewById(R.id.upload);
        addPhoto.setOnClickListener(view -> {
            Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
            chooseFile.setType("*/*");
            chooseFile = Intent.createChooser(chooseFile, "Choose a file");
            startActivityForResult(chooseFile, 1234);

        });
       }

    private void ourWork(String image) {
        Dashbord dashbord = Dashbord.builder()
                .imageUrl(image)
                .build();

        Amplify.API.mutate(
                ModelMutation.create(dashbord),
                observationSaved -> {
                    runOnUiThread(() -> Toast.makeText(getApplicationContext(),
                            "Image  saved",
                            Toast.LENGTH_SHORT).show());
                },
                error -> {
                    Log.e("TAG", "Image not saved  => " + error);
                });
    }
    // start activity for the result
    private final ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        // There are no request codes
                        Intent data = result.getData();
                        assert data != null;
                        String key = data.getExtras().getString(RESULT);
                        Log.i("TAG", "===============> " + key);
                        getObservationImageURL(key);

                    }


                }

            });

    private void getObservationImageURL(String key) {
        Amplify.Storage.getUrl(key, success -> {
            imageURL = success.getUrl().toString();
            Log.i("TAG", "Image URL ====================> " + imageURL);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    save = findViewById(R.id.btn_save);
                    save.setVisibility(View.VISIBLE);
                    save.setOnClickListener(view -> {
                        ourWork(imageURL);
                        Intent i=new Intent(getApplicationContext(),Galary.class);
                        startActivity(i);
                    });
                }
            });


        }, error -> {
            Log.e("TAG", "Error getting URL ====================> " + error.toString());
        });

    }
    @SuppressLint("LongLogTag")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 214) {
            if (resultCode == RESULT_OK) {
                assert data != null;
                String[] list = data.getStringArrayExtra("resultData");

                Log.i("TAG", "the images are 1 => " + list[0]);
                Log.i("TAG", "the images are 2 => " + list[1]);
                Log.i("TAG", "the images are 3 => " + list[2]);
            }
        }
        if(requestCode==1234){
            Uri uri=data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Log.v("cause yo're a natural a beating heart of stone 🎶🎶🎶🎶🎶",inputStream.toString());
                saveImgToS3(generateFileName(),uri);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
    private String generateFileName() {
        String pattern = "yyyy_MM_dd_HH_mm_ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.CANADA);

        return simpleDateFormat.format(new Date());
    }


    @SuppressLint("LongLogTag")
    public void saveImgToS3(String imgName, Uri imgData) {
        try {
imgName=imgName+".JPEG";
            InputStream exampleInputStream = getContentResolver().openInputStream(imgData);
            Amplify.Storage.uploadInputStream(
                    imgName,
                    exampleInputStream,
                    result -> {
                        Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey());
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra(HAHA, result.getKey());
                        setResult(23,returnIntent);
                        getObservationImageURL(result.getKey());
                        Log.v("I am not part of your Machine I am the Machine 🎶🎶🎶🎶🎶",imgData.getPath());

//                        renderNewImg(imgName);
                    },
                    storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
            );
        } catch (FileNotFoundException error) {
            Log.e("MyAmplifyApp", "Could not find file to open for input stream.", error);
        }
    }


    public void ll(View view) {
        Intent i=new Intent(getApplicationContext(),Galary.class);
        startActivity(i);
    }

    public void signout(View view) {
        Amplify.Auth.signOut(
                () -> {
                    Log.i("AuthQuickstart", "Signed out successfully");
                    Intent goToSignIn = new Intent(SomeWorkCamera.this, SignIn.class);
                    startActivity(goToSignIn);
                    finish();
                },
                error -> Log.e("AuthQuickstart", error.toString())
        );
    }
}