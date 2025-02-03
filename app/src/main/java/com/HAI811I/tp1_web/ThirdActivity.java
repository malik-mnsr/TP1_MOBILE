package com.HAI811I.tp1_web;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Get the phone number from the Intent
        Intent intent = getIntent();
        String phoneNumber = intent.getStringExtra("TELEPHONE");

        // Display the phone number
        TextView textViewPhoneNumber = findViewById(R.id.textViewPhoneNumber);
        textViewPhoneNumber.setText(phoneNumber);

        // Handle the "Appeler" button
        Button buttonAppeler = findViewById(R.id.buttonAppeler);
        buttonAppeler.setOnClickListener(v -> {
            // Create an Intent to launch the phone dialer
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        });
    }
}