package com.example.projetagile.controleur.recettes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projetagile.R;
import com.example.projetagile.controleur.recettes.AjoutRecetteActivity;
import com.example.projetagile.modele.Recette;
import com.example.projetagile.modele.RecetteDAO;

import java.util.ArrayList;

import android.widget.ListView;
import android.widget.ArrayAdapter;


public class ConsultRecetteActivity extends AppCompatActivity {

    private Button buttonAjouter;
    private ListView listViewRecettes;
    private RecetteDAO recetteDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_recette);

        buttonAjouter = findViewById(R.id.buttonAjouter);
        listViewRecettes = findViewById(R.id.listViewRecettes);
        recetteDAO = new RecetteDAO(this);
        ArrayList<Recette>  lesRecettes = recetteDAO.cursorToUtilisateursArrayList();

        ArrayAdapter<Recette> adapter = new ArrayAdapter<Recette>(this, android.R.layout.simple_list_item_1, lesRecettes);
        listViewRecettes.setAdapter(adapter);

        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ouvrirAjoutRecette();
            }
        });

        listViewRecettes.setOnItemClickListener((parent, view, position, id) -> {
            Recette recetteSelectionne = (Recette) parent.getItemAtPosition(position);

            Intent intent = new Intent(ConsultRecetteActivity.this, ModifierRecetteActivity.class);
            intent.putExtra("nom", recetteSelectionne.getLibelle());
            intent.putExtra("description", recetteSelectionne.getDescription());
            intent.putExtra("image", recetteSelectionne.getUneImage());

            startActivity(intent);
        });
    }

    protected void ouvrirAjoutRecette() {
        Intent intent = new Intent(this, AjoutRecetteActivity.class);
        startActivity(intent);
    }
}
