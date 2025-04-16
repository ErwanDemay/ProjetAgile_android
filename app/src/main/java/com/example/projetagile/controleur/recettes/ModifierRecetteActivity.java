package com.example.projetagile.controleur.recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.projetagile.R;
import com.example.projetagile.modele.Recette;
import com.example.projetagile.modele.RecetteDAO;
import com.example.projetagile.controleur.recettes.ConsultRecetteActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.util.Log;
import android.widget.Spinner;
import android.widget.Toast;

public class ModifierRecetteActivity extends AppCompatActivity {

    Button buttonModifier;
    Button buttonASupprimer;
    EditText editTextNom;
    EditText editTextDescription;
    Spinner spinnerType;
    int idRecette;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_recette);

        buttonModifier = findViewById(R.id.buttonModifier);
        buttonASupprimer = findViewById(R.id.buttonASupprimer);
        editTextNom = findViewById(R.id.editTextNom);
        editTextDescription = findViewById(R.id.editTextNomDescription);
        spinnerType = findViewById(R.id.spinnerType);

        String[] typesRecette = {"Entrée", "Plat", "Dessert"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, typesRecette);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);


        String ancNom = editTextNom.toString();
        String ancDesc = editTextDescription.toString();


        Intent intent = getIntent();
        if (intent != null) {
            idRecette = intent.getIntExtra("id", -1);
            editTextNom.setText(intent.getStringExtra("nom"));
            editTextDescription.setText(intent.getStringExtra("description"));

            int typeRecu = intent.getIntExtra("type", 1); // 1 par défaut
            spinnerType.setSelection(typeRecu - 1); // car 0 = Entrée, 1 = Plat...
        }



        buttonModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nouveauNom = editTextNom.getText().toString();
                String nouvelleDescription = editTextDescription.getText().toString();
                int nouveauType = spinnerType.getSelectedItemPosition() + 1;

                RecetteDAO recetteDAO = new RecetteDAO(ModifierRecetteActivity.this);
                recetteDAO.modifierRecette(idRecette, nouveauNom, nouvelleDescription, nouveauType);

                Toast.makeText(getApplicationContext(), "Recette modifiée avec succès", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ModifierRecetteActivity.this, ConsultRecetteActivity.class);
                startActivity(intent);
                finish(); // pour fermer cette activité et éviter de revenir dessus avec le bouton retour
            }
        });


        buttonASupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new androidx.appcompat.app.AlertDialog.Builder(ModifierRecetteActivity.this)
                        .setTitle("Confirmation")
                        .setMessage("Êtes-vous sûr de vouloir supprimer cette recette ?")
                        .setPositiveButton("Oui", (dialog, which) -> {
                            RecetteDAO recetteDAO = new RecetteDAO(ModifierRecetteActivity.this);
                            recetteDAO.supprimerRecette(idRecette);

                            Toast.makeText(getApplicationContext(), "Recette supprimée avec succès", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(ModifierRecetteActivity.this, ConsultRecetteActivity.class);
                            startActivity(intent);
                            finish();
                        })
                        .setNegativeButton("Non", null)
                        .show();
            }
        });

    }
}