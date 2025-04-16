package com.example.projetagile.controleur.sessions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projetagile.R;
import com.example.projetagile.modele.Session;
import com.example.projetagile.modele.SessionDAO;

public class AjoutSessionActivity extends AppCompatActivity {

    private EditText editTextNom, editTextDate, editTextHeureDebut, editTextHeureFin, editTextPrix, editTextNbPlaces;
    private Button buttonAjouter;
    private SessionDAO sessionDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_session);

        // Récupération des vues
        editTextNom = findViewById(R.id.editTextNom);
        editTextDate = findViewById(R.id.editTextDate);
        editTextHeureDebut = findViewById(R.id.editTextHeureDebut);
        editTextHeureFin = findViewById(R.id.editTextHeureFin);
        editTextPrix = findViewById(R.id.editTextPrix);
        editTextNbPlaces = findViewById(R.id.editTextNbPlaces);
        buttonAjouter = findViewById(R.id.buttonAjouter);

        sessionDAO = new SessionDAO(this);

        buttonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterSession();
            }
        });
    }

    private void ajouterSession() {
        String nom = editTextNom.getText().toString().trim();
        String date = editTextDate.getText().toString().trim(); // Ex: 2025-04-20
        String heureDebut = editTextHeureDebut.getText().toString().trim(); // Ex: 14:00
        String heureFin = editTextHeureFin.getText().toString().trim();
        String prixStr = editTextPrix.getText().toString().trim();
        String nbPlacesStr = editTextNbPlaces.getText().toString().trim();

        if (nom.isEmpty() || date.isEmpty() || heureDebut.isEmpty() || heureFin.isEmpty() || prixStr.isEmpty() || nbPlacesStr.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        float prix;
        int nbPlaces;
        try {
            prix = Float.parseFloat(prixStr);
            nbPlaces = Integer.parseInt(nbPlacesStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Prix ou nombre de places invalide", Toast.LENGTH_SHORT).show();
            return;
        }

        Session nouvelleSession = new Session(0, nom, date, heureDebut, heureFin, prix, nbPlaces);
        sessionDAO.ajouterSession(nouvelleSession);

        Toast.makeText(this, "Session ajoutée avec succès", Toast.LENGTH_SHORT).show();

        // Redirection vers l'activité de consultation
        Intent intent = new Intent(this, ConsultSessionActivity.class);
        startActivity(intent);
        finish();
    }
}
