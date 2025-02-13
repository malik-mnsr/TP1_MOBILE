package com.HAI811I.tp1_web;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        // Get data from the Intent
        Intent intent = getIntent();
        String nom = intent.getStringExtra("NOM");
        String prenom = intent.getStringExtra("PRENOM");
        String age = intent.getStringExtra("AGE");
        String domaine = intent.getStringExtra("DOMAINE");
        String telephone = intent.getStringExtra("TELEPHONE");

        // Create a TextView for displaying the formatted data
        TextView textViewData = findViewById(R.id.textViewData);

        // Build the string and bold the user-typed parts
        SpannableString data = new SpannableString(
                getString(R.string.nom) + ": " + nom + "\n" +
                        getString(R.string.prenom) + ": " + prenom + "\n" +
                        getString(R.string.age) + ": " + age + "\n" +
                        getString(R.string.domaine) + ": " + domaine + "\n" +
                        getString(R.string.telephone) + ": " + telephone
        );

        // Apply bold style to user-typed data (e.g., nom)
        int nomStart = getString(R.string.nom).length() + 2; // "+ 2" accounts for ": "
        data.setSpan(new StyleSpan(Typeface.BOLD), nomStart, nomStart + nom.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        int prenomStart = nomStart + nom.length() + getString(R.string.prenom).length() + 3; // "+ 3" accounts for "\n"
        data.setSpan(new StyleSpan(Typeface.BOLD), prenomStart, prenomStart + prenom.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        int ageStart = prenomStart + prenom.length() + getString(R.string.age).length() + 3;
        data.setSpan(new StyleSpan(Typeface.BOLD), ageStart, ageStart + age.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        int domaineStart = ageStart + age.length() + getString(R.string.domaine).length() + 3;
        data.setSpan(new StyleSpan(Typeface.BOLD), domaineStart, domaineStart + domaine.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        int telephoneStart = domaineStart + domaine.length() + getString(R.string.telephone).length() + 3;
        data.setSpan(new StyleSpan(Typeface.BOLD), telephoneStart, telephoneStart + telephone.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the formatted text
        textViewData.setText(data);

        // Handle OK button to go to ThirdActivity
        Button buttonOk = findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(v -> {
            Intent thirdIntent = new Intent(DisplayActivity.this, ThirdActivity.class);
            thirdIntent.putExtra("TELEPHONE", telephone); // Pass the phone number
            startActivity(thirdIntent);
        });

        // Handle Retour button to finish the activity
        Button buttonRetour = findViewById(R.id.buttonRetour);
        buttonRetour.setOnClickListener(v -> finish());
    }
}
