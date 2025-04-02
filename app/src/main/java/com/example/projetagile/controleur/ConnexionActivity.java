package com.example.projetagile.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import android.os.Bundle;

import com.example.projetagile.controleur.PropositionActivity;

import com.example.projetagile.R;

public class ConnexionActivity extends AppCompatActivity {

    private Button buttonValider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        buttonValider = findViewById(R.id.buttonValider);

        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirProposition();
            }
        });
    }

    protected void ouvrirProposition() {
        Intent intent = new Intent(this, PropositionActivity.class);
        startActivity(intent);
    }
}