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

        // Récupérer le numéro de téléphone depuis l'intent
        Intent intent = getIntent();
        String phoneNumber = intent.getStringExtra("TELEPHONE");

        // Afficher le numéro de téléphone
        TextView textViewPhoneNumber = findViewById(R.id.textViewPhoneNumber);
        textViewPhoneNumber.setText(phoneNumber);

        // Gérer le bouton d'appel
        Button buttonAppeler = findViewById(R.id.buttonAppeler);
        buttonAppeler.setOnClickListener(v -> {
            // Créer un intent pour lancer l'application de téléphone
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        });

        // Gérer le bouton de retour à MainActivity
        Button buttonRetour = findViewById(R.id.buttonRetour);
        buttonRetour.setOnClickListener(v -> {
            // Créer un intent pour passer à MainActivity
            Intent mainActivityIntent = new Intent(ThirdActivity.this, MainActivity.class);
            startActivity(mainActivityIntent);
            finish(); // Facultatif : ferme l'activité actuelle pour éviter de revenir dessus
        });
    }
}
