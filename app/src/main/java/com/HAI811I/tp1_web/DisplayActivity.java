package com.HAI811I.tp1_web;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        // Get the Intent that started this activity
        Intent intent = getIntent();
        String nom = intent.getStringExtra("NOM");
        String prenom = intent.getStringExtra("PRENOM");
        String age = intent.getStringExtra("AGE");
        String domaine = intent.getStringExtra("DOMAINE");
        String telephone = intent.getStringExtra("TELEPHONE");

        // Display the data
        TextView textViewData = findViewById(R.id.textViewData);
        String data = getString(R.string.nom) + ": " + nom + "\n"
                + getString(R.string.prenom) + ": " + prenom + "\n"
                + getString(R.string.age) + ": " + age + "\n"
                + getString(R.string.domaine) + ": " + domaine + "\n"
                + getString(R.string.telephone) + ": " + telephone;
        textViewData.setText(data);

        // Handle the "OK" button
        Button buttonOk = findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(v -> {
            Intent thirdIntent = new Intent(DisplayActivity.this, ThirdActivity.class);
            thirdIntent.putExtra("TELEPHONE", telephone); // Pass the phone number
            startActivity(thirdIntent);
        });

        // Handle the "Retour" button
        Button buttonRetour = findViewById(R.id.buttonRetour);
        buttonRetour.setOnClickListener(v -> finish()); // Go back to the previous activity
    }
}