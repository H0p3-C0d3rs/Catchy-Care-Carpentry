package us.hopecoders.catchy_care_carpentry.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import com.amplifyframework.core.Amplify;
import com.otaliastudios.cameraview.CameraException;
import com.otaliastudios.cameraview.CameraListener;
import com.otaliastudios.cameraview.CameraView;
import com.otaliastudios.cameraview.PictureResult;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import us.hopecoders.catchy_care_carpentry.R;

public class OurWork extends AppCompatActivity {
    private static final String TAG = "CameraActivity";
    public static final String RESULT = "result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_work);

        CameraView camera = findViewById(R.id.camera);
        camera.setLifecycleOwner(this);
        camera.addCameraListener(new CameraListener() {
            @Override
            public void onCameraClosed() {
                super.onCameraClosed();

                Log.i(TAG, "Camera was closed");
            }

            @Override
            public void onCameraError(@NonNull CameraException exception) {
                super.onCameraError(exception);

                Log.e(TAG, "An error occurred taking the photo => " + exception.getMessage());
            }

            @Override
            public void onPictureTaken(@NonNull PictureResult result) {
                super.onPictureTaken(result);

                uploadPicture(result);
            }
        });
    }

    private String generateFileName() {
        String pattern = "yyyy_MM_dd_HH_mm_ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.CANADA);

        return simpleDateFormat.format(new Date());
    }

    private void uploadPicture(PictureResult pictureResult) {
        String fileName = generateFileName();
        fileName = fileName + "." + pictureResult.getFormat().name();

        Log.i(TAG, "Filename is => " + fileName);

        File exampleFile = new File(getApplicationContext().getFilesDir(), fileName);

        String finalFileName = fileName;
        pictureResult.toFile(exampleFile, file -> {
            assert file != null;

            new Handler(Looper.getMainLooper()).post(() -> {
                Amplify.Storage.uploadFile(
                        finalFileName,
                        file,
                        result -> {
                            Log.i(TAG, "Successfully uploaded: " + result.getKey());
                            Intent returnIntent = new Intent();
                            returnIntent.putExtra(RESULT, result.getKey());
                            setResult(Activity.RESULT_OK,returnIntent);
                            finish();
                        },
                        storageFailure -> Log.e(TAG, "Upload failed", storageFailure)
                );
            });


        });
    }

}