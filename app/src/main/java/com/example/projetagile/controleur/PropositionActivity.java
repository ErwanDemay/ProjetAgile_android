package com.example.projetagile.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.projetagile.controleur.recettes.ConsultRecetteActivity;
import com.example.projetagile.controleur.sessions.ConsultSessionActivity;

import android.os.Bundle;

import com.example.projetagile.R;

public class PropositionActivity extends AppCompatActivity {

    private Button buttonRecettes;
    private Button buttonSessions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposition);

        buttonRecettes = findViewById(R.id.buttonRecettes);
        buttonSessions = findViewById(R.id.buttonSessions);

        buttonRecettes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirRecettes();
            }
        });
        buttonSessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirSessions();
            }
        });
    }

    protected void ouvrirRecettes() {
        Intent intent = new Intent(this, ConsultRecetteActivity.class);
        startActivity(intent);
    }
    protected void ouvrirSessions() {
        Intent intent = new Intent(this, ConsultSessionActivity.class);
        startActivity(intent);
    }
}