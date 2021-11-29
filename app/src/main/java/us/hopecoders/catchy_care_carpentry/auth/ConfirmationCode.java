package us.hopecoders.catchy_care_carpentry.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

import us.hopecoders.catchy_care_carpentry.R;

public class ConfirmationCode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_code);
        EditText confirmationInput = findViewById(R.id.confirmationInput);
        EditText usernameConfirm = findViewById(R.id.usernameConfirm);
        Button confirmButton = findViewById(R.id.confirmButton);


        confirmButton.animate().rotationY(360).setDuration(3000);




        confirmButton.setOnClickListener(v -> {
            Amplify.Auth.confirmSignUp(
                    usernameConfirm.getText().toString(),
                    confirmationInput.getText().toString(),
                    result -> {
                        Intent goSignIn = new Intent(ConfirmationCode.this, SignIn.class);
                        startActivity(goSignIn);
                        Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                    },
                    error -> Log.e("AuthQuickstart", error.toString())
            );
        });
    }
}