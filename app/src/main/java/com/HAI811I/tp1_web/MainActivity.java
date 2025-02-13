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

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import android.os.Bundle;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerLangue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load the saved language before setting the content view.
        loadLocale();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Spinner
        spinnerLangue = findViewById(R.id.spinnerLangue);

        // Define languages and their flags
        List<String> languages = Arrays.asList(getResources().getStringArray(R.array.langues));
        int[] flagImages = {
                R.drawable.ic_flag_fr,  // French flag
                R.drawable.ic_flag_en,  // English flag
                R.drawable.ic_flag_ar   // Arabic flag
        };

        // Set up the custom adapter for the spinner
        LanguageAdapter adapter = new LanguageAdapter(this, languages, flagImages);
        spinnerLangue.setAdapter(adapter);

        // Restore the spinner selection based on the saved language
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

        // Set an item selected listener for the spinner
        spinnerLangue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        setLocale("fr"); // Switch to French
                        break;
                    case 1:
                        setLocale("en"); // Switch to English
                        break;
                    case 2:
                        setLocale("ar"); // Switch to Arabic
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Button listener for form validation
        Button buttonValider = findViewById(R.id.buttonValider);
        buttonValider.setOnClickListener(view -> showConfirmationDialog());
    }


    private void showConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.confirmation_title))
                .setMessage(getString(R.string.confirmation_message))
                .setPositiveButton(getString(R.string.yes), (dialog, which) -> validateForm())
                .setNegativeButton(getString(R.string.no), (dialog, which) -> dialog.dismiss())
                .setNeutralButton(getString(R.string.change_color), (dialog, which) -> changeEditTextBackground())
                .show();
    }

    private void changeEditTextBackground() {
        EditText editTextNom = findViewById(R.id.editTextNom);
        EditText editTextPrenom = findViewById(R.id.editTextPrenom);
        EditText editTextAge = findViewById(R.id.editTextAge);
        EditText editTextDomaine = findViewById(R.id.editTextDomaine);
        EditText editTextTelephone = findViewById(R.id.editTextTelephone);


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

        String nom = editTextNom.getText().toString().trim();
        String prenom = editTextPrenom.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();
        String domaine = editTextDomaine.getText().toString().trim();
        String telephone = editTextTelephone.getText().toString().trim();

        boolean hasError = false;


        if (nom.isEmpty()) {
            editTextNom.setBackgroundResource(R.drawable.edittext_error_background);
            hasError = true;
        } else {
            editTextNom.setBackgroundResource(R.drawable.edittext_background);
        }

        if (prenom.isEmpty()) {
            editTextPrenom.setBackgroundResource(R.drawable.edittext_error_background);
            hasError = true;
        } else {
            editTextPrenom.setBackgroundResource(R.drawable.edittext_background);
        }

        if (age.isEmpty()) {
            editTextAge.setBackgroundResource(R.drawable.edittext_error_background);
            hasError = true;
        } else {
            editTextAge.setBackgroundResource(R.drawable.edittext_background);
        }

        if (domaine.isEmpty()) {
            editTextDomaine.setBackgroundResource(R.drawable.edittext_error_background);
            hasError = true;
        } else {
            editTextDomaine.setBackgroundResource(R.drawable.edittext_background);
        }

        if (telephone.isEmpty()) {
            editTextTelephone.setBackgroundResource(R.drawable.edittext_error_background);
            hasError = true;
        } else {
            editTextTelephone.setBackgroundResource(R.drawable.edittext_background);
        }


        if (hasError) {
            Toast.makeText(this, getString(R.string.all_fields_required), Toast.LENGTH_SHORT).show();
        } else {

            Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
            intent.putExtra("NOM", nom);
            intent.putExtra("PRENOM", prenom);
            intent.putExtra("AGE", age);
            intent.putExtra("DOMAINE", domaine);
            intent.putExtra("TELEPHONE", telephone);
            startActivity(intent);
        }
    }
}