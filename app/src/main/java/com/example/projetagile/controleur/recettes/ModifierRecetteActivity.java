package com.example.projetagile.controleur.recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.projetagile.R;
import com.example.projetagile.modele.Recette;
import com.example.projetagile.modele.RecetteDAO;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.util.Log;
import android.widget.Toast;

public class ModifierRecetteActivity extends AppCompatActivity {

    Button buttonAjouter;
    Button buttonASupprimer;
    EditText editTextNom;
    EditText editTextDescription;
    EditText editTextImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_recette);

        buttonAjouter = findViewById(R.id.buttonAjouter);
        buttonASupprimer = findViewById(R.id.buttonASupprimer);
        editTextNom = findViewById(R.id.editTextNom);
        editTextDescription = findViewById(R.id.editTextNomDescription);
        editTextImage = findViewById(R.id.editTextImage);

        String ancNom = editTextNom.toString();
        String ancDesc = editTextDescription.toString();
        String ancImg = editTextImage.toString();

        Intent intent = getIntent();
        if (intent != null) {
            editTextNom.setText(intent.getStringExtra("nom"));
            editTextDescription.setText(intent.getStringExtra("description"));
            editTextImage.setText(intent.getStringExtra("image"));
        }

        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Recette nvRecette = new Recette(
                        editTextNom.getText().toString(),
                        editTextDescription.getText().toString(),
                        editTextImage.getText().toString()
                );

                int retour = RecetteDAO.addVisiteur(nvRecette);
                if (retour >= 1){
                    Toast.makeText(getApplicationContext(), "Recette modifiée", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        buttonASupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Recette ancRecette = new Recette(
                        editTextNom.getText().toString(),
                        editTextDescription.getText().toString(),
                        editTextImage.getText().toString()
                );

                int retour = RecetteDAO.deleteVisiteur(ancRecette);
                if (retour >= 1){
                    Toast.makeText(getApplicationContext(), "Recette modifiée", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }
}