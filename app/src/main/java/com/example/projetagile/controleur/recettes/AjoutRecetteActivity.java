package com.example.projetagile.controleur.recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetagile.R;
import com.example.projetagile.modele.Recette;
import com.example.projetagile.modele.RecetteDAO;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AjoutRecetteActivity extends AppCompatActivity {
    private RecetteDAO recetteDAO;
    private EditText editTextNom , editTextNomDescription;
    private Spinner spinnerType;
    private Button buttonAjouter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_recette);

        //Initialisation des champs
        editTextNom = findViewById(R.id.editTextNom);
        editTextNomDescription = findViewById(R.id.editTextNomDescription);
        spinnerType = findViewById(R.id.spinnerType);
        buttonAjouter = findViewById(R.id.buttonAjouter);

        String[] types = {"Entrée", "Plat", "Dessert"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);


        //Listener sur le bouton d'ajout
        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validerAjout();
            }
        });

    }

    public void validerAjout() {
        String libelle = editTextNom.getText().toString();
        String description = editTextNomDescription.getText().toString();
        String uneImage = null; // Tu pourras gérer ça plus tard six² tu ajoutes une image
        String dateAjout = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH).format(new Date());

        // Récupération de l'ID en fonction du type sélectionné
        int position = spinnerType.getSelectedItemPosition(); // 0 = Entrée, 1 = Plat, 2 = Dessert
        int idType = position + 1; // Entrée = 1, Plat = 2, Dessert = 3

        // Création de l'objet Recette
        Recette nouvelleRecette = new Recette(0, libelle, description, uneImage, dateAjout, idType);

        // Ajout à la base
        recetteDAO = new RecetteDAO(this);
        recetteDAO.ajouterRecette(nouvelleRecette);

        Toast.makeText(this, "Recette ajoutée", Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(this, ConsultRecetteActivity.class);
        startActivity(intent);

    }

}