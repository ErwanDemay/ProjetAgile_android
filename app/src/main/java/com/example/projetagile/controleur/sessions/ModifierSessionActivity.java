package com.example.projetagile.controleur.sessions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Spinner;

import com.example.projetagile.R;
import com.example.projetagile.modele.ProposerDAO;
import com.example.projetagile.modele.Recette;
import com.example.projetagile.modele.RecetteDAO;
import com.example.projetagile.modele.SessionDAO;

import java.util.List;
import java.util.ArrayList;

public class ModifierSessionActivity extends AppCompatActivity {

    Button buttonModifier, buttonASupprimer, buttonAjouterRecette;
    EditText editTextNom, editTextDate, editTextHeureDebut, editTextHeureFin, editTextPrix, editTextNbPlaces;
    Spinner spinnerRecettes, spinnerRecettesLiees;
    int idSession;

    List<Recette> toutesLesRecettes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_session);

        // Récupérer les vues
        buttonModifier = findViewById(R.id.buttonModifier);
        buttonASupprimer = findViewById(R.id.buttonASupprimer);
        buttonAjouterRecette = findViewById(R.id.buttonAjouterRecette);

        editTextNom = findViewById(R.id.editTextNom);
        editTextDate = findViewById(R.id.editTextDate);
        editTextHeureDebut = findViewById(R.id.editTextHeureDebut);
        editTextHeureFin = findViewById(R.id.editTextHeureFin);
        editTextPrix = findViewById(R.id.editTextPrix);
        editTextNbPlaces = findViewById(R.id.editTextNbPlaces);
        spinnerRecettes = findViewById(R.id.spinnerRecettesSession);
        spinnerRecettesLiees = findViewById(R.id.spinnerRecettesLiees);

        // Récupération des données envoyées à l'activité
        Intent intent = getIntent();
        if (intent != null) {
            idSession = intent.getIntExtra("id", -1);
            editTextNom.setText(intent.getStringExtra("nomSession"));
            editTextDate.setText(intent.getStringExtra("dateSession"));
            editTextHeureDebut.setText(intent.getStringExtra("heureDebut"));
            editTextHeureFin.setText(intent.getStringExtra("heureFin"));
            editTextPrix.setText(String.valueOf(intent.getFloatExtra("prix", 0.0f)));
            editTextNbPlaces.setText(String.valueOf(intent.getIntExtra("nbPlaces", 0)));
        }

        chargerToutesLesRecettes();

        // Charger les recettes déjà liées à la session
        loadRecettesLiees();

        buttonModifier.setOnClickListener(v -> {
            modifierSession();
        });

        buttonASupprimer.setOnClickListener(v -> {
            supprimerSession();
        });

        buttonAjouterRecette.setOnClickListener(v -> {
            ajouterRecetteASession();
        });
    }

    private void ajouterRecetteASession() {
        int position = spinnerRecettes.getSelectedItemPosition();
        if (position >= 0 && position < toutesLesRecettes.size()) {
            Recette recetteSelectionnee = toutesLesRecettes.get(position);
            ProposerDAO proposerDAO = new ProposerDAO(ModifierSessionActivity.this);

            boolean ajoutOK = proposerDAO.ajouterRecetteASession(recetteSelectionnee.getId(), idSession);

            if (ajoutOK) {
                Toast.makeText(getApplicationContext(), "Recette ajoutée à la session", Toast.LENGTH_SHORT).show();
                loadRecettesLiees(); // 🔁 Recharge la liste des recettes liées
            } else {
                Toast.makeText(getApplicationContext(), "Erreur : recette déjà liée ou problème d'ajout", Toast.LENGTH_SHORT).show();
            }
        }
    }



    private void modifierSession(){
        String nouveauNom = editTextNom.getText().toString();
        String nouvelleDate = editTextDate.getText().toString();
        String nouvelleHeureDebut = editTextHeureDebut.getText().toString();
        String nouvelleHeureFin = editTextHeureFin.getText().toString();
        float nouveauPrix = Float.parseFloat(editTextPrix.getText().toString());
        int nouveauNbPlaces = Integer.parseInt(editTextNbPlaces.getText().toString());

        SessionDAO sessionDAO = new SessionDAO(ModifierSessionActivity.this);
        sessionDAO.modifierSession(idSession, nouveauNom, nouvelleDate, nouvelleHeureDebut, nouvelleHeureFin, nouveauPrix, nouveauNbPlaces);

        Toast.makeText(getApplicationContext(), "Session modifiée avec succès", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(ModifierSessionActivity.this, ConsultSessionActivity.class));
        finish();
    }

    private void supprimerSession(){
        new androidx.appcompat.app.AlertDialog.Builder(ModifierSessionActivity.this)
                .setTitle("Confirmation")
                .setMessage("Êtes-vous sûr de vouloir supprimer cette session ?")
                .setPositiveButton("Oui", (dialog, which) -> {
                    SessionDAO sessionDAO = new SessionDAO(ModifierSessionActivity.this);
                    sessionDAO.supprimerSession(idSession);

                    Toast.makeText(getApplicationContext(), "Session supprimée avec succès", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(ModifierSessionActivity.this, ConsultSessionActivity.class));
                    finish();
                })
                .setNegativeButton("Non", null)
                .show();
    }


    private void chargerToutesLesRecettes() {
        RecetteDAO recetteDAO = new RecetteDAO(this);
        toutesLesRecettes = recetteDAO.cursorToUtilisateursArrayList();

        List<String> noms = new ArrayList<>();
        for (Recette r : toutesLesRecettes) {
            noms.add(r.getLibelle());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, noms);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRecettes.setAdapter(adapter);
    }

    private void loadRecettesLiees() {
        ProposerDAO proposerDAO = new ProposerDAO(this);
        ArrayList<Recette> recettesLiees = proposerDAO.getRecettesPourSession(idSession);

        // Créer une liste de noms de recettes
        List<String> nomsRecettesLiees = new ArrayList<>();
        for (Recette recette : recettesLiees) {
            nomsRecettesLiees.add(recette.getLibelle());
        }

        // Adapter pour le Spinner
        ArrayAdapter<String> adapterRecettesLiees = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nomsRecettesLiees);
        adapterRecettesLiees.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Appliquer l'adapter au Spinner
        spinnerRecettesLiees.setAdapter(adapterRecettesLiees);
    }
}

