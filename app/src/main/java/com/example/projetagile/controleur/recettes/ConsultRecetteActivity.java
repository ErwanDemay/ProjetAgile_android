package com.example.projetagile.controleur.recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projetagile.R;
import com.example.projetagile.controleur.recettes.AjoutRecetteActivity;


public class ConsultRecetteActivity extends AppCompatActivity {
private Button buttonAjouter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_recette);

        buttonAjouter = findViewById(R.id.buttonAjouter);

        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirAjoutRecette();
            }
        });
    }

    protected void ouvrirAjoutRecette() {
        Intent intent = new Intent(this, AjoutRecetteActivity.class);
        startActivity(intent);
    }
}
