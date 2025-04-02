package com.example.projetagile.controleur.sessions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


import com.example.projetagile.R;
import com.example.projetagile.controleur.PropositionActivity;


public class ConsultSessionActivity extends AppCompatActivity {
private Button buttonAjouter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_session);

        buttonAjouter = findViewById(R.id.buttonAjouter);

        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirAjoutSession();
            }
        });
    }

    protected void ouvrirAjoutSession() {
        //Log.d("debuG", "ouvrirAjoutSession: ");
        Intent intent = new Intent(this, AjoutSessionActivity.class);
        startActivity(intent);
    }
}