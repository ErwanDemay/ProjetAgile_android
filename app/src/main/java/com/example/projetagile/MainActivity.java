package com.example.projetagile;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

import android.os.Bundle;

import com.example.projetagile.controleur.ConnexionActivity;

public class MainActivity extends AppCompatActivity {

    private Button buttonConnexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonConnexion = findViewById(R.id.buttonConnexion);

        buttonConnexion.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirConnexion();
            }
        });
    }

    protected void ouvrirConnexion(){
        Intent intent = new Intent(this, ConnexionActivity.class);
        startActivity(intent);
    }
}