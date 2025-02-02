package com.HAI811I.tp1_web;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.graphics.Color;
import java.util.Random;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerLangue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load the saved language BEFORE setting the content view
        loadLocale();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize language selector
        spinnerLangue = findViewById(R.id.spinnerLangue);

        // Restore the Spinner selection based on the saved language
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String currentLang = prefs.getString("Lang", "ar");
        switch (currentLang) {
            case "fr":
                spinnerLangue.setSelection(0); // French
                break;
            case "en":
                spinnerLangue.setSelection(1); // English
                break;
            case "ar":
                spinnerLangue.setSelection(2); // Arabic
                break;
        }

        spinnerLangue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        setLocale("fr"); // French
                        break;
                    case 1:
                        setLocale("en"); // English
                        break;
                    case 2:
                        setLocale("ar"); // Arabic
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Handle form validation with a confirmation dialog
        Button buttonValider = findViewById(R.id.buttonValider);
        buttonValider.setOnClickListener(view -> showConfirmationDialog());
    }

    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.confirmation_title)) // "Confirm Validation"
                .setMessage(getString(R.string.confirmation_message)) // "Are you sure you want to validate?"
                .setPositiveButton(getString(R.string.yes), (dialog, which) -> validateForm()) // Validate
                .setNegativeButton(getString(R.string.no), (dialog, which) -> dialog.dismiss()) // Cancel
                .setNeutralButton(getString(R.string.change_color), (dialog, which) -> changeEditTextBackground()) // Change background color
                .show();
    }



    private void changeEditTextBackground() {
        EditText editTextNom = findViewById(R.id.editTextNom);
        EditText editTextPrenom = findViewById(R.id.editTextPrenom);
        EditText editTextAge = findViewById(R.id.editTextAge);
        EditText editTextDomaine = findViewById(R.id.editTextDomaine);
        EditText editTextTelephone = findViewById(R.id.editTextTelephone);

        // Change background colors to random colors
        editTextNom.setBackgroundColor(getRandomColor());
        editTextPrenom.setBackgroundColor(getRandomColor());
        editTextAge.setBackgroundColor(getRandomColor());
        editTextDomaine.setBackgroundColor(getRandomColor());
        editTextTelephone.setBackgroundColor(getRandomColor());
    }

    private void setLocale(String lang) {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String currentLang = prefs.getString("Lang", "ar");

        if (!currentLang.equals(lang)) {
            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            Resources resources = getResources();
            Configuration config = resources.getConfiguration();
            DisplayMetrics dm = resources.getDisplayMetrics();
            config.setLocale(locale);
            resources.updateConfiguration(config, dm);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("Lang", lang);
            editor.apply();

            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }
    }

    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("Lang", "ar");

        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics dm = resources.getDisplayMetrics();
        config.setLocale(locale);
        resources.updateConfiguration(config, dm);
    }
    private int getRandomColor() {
        Random random = new Random();
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
    private void validateForm() {
        EditText editTextNom = findViewById(R.id.editTextNom);
        EditText editTextPrenom = findViewById(R.id.editTextPrenom);
        EditText editTextAge = findViewById(R.id.editTextAge);
        EditText editTextDomaine = findViewById(R.id.editTextDomaine);
        EditText editTextTelephone = findViewById(R.id.editTextTelephone);

        String nom = editTextNom.getText().toString();
        String prenom = editTextPrenom.getText().toString();
        String age = editTextAge.getText().toString();
        String domaine = editTextDomaine.getText().toString();
        String telephone = editTextTelephone.getText().toString();

        // Create an Intent to launch DisplayActivity
        Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
        intent.putExtra("NOM", nom);
        intent.putExtra("PRENOM", prenom);
        intent.putExtra("AGE", age);
        intent.putExtra("DOMAINE", domaine);
        intent.putExtra("TELEPHONE", telephone);
        startActivity(intent);
    }
}