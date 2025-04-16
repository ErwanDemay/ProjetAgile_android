package com.example.projetagile.controleur.sessions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.projetagile.R;
import com.example.projetagile.modele.SessionDAO;
import com.example.projetagile.controleur.sessions.ConsultSessionActivity;

public class ModifierSessionActivity extends AppCompatActivity {

    Button buttonModifier, buttonASupprimer;
    EditText editTextNom, editTextDate, editTextHeureDebut, editTextHeureFin, editTextPrix, editTextNbPlaces;
    int idSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_session);

        buttonModifier = findViewById(R.id.buttonModifier);
        buttonASupprimer = findViewById(R.id.buttonASupprimer);

        editTextNom = findViewById(R.id.editTextNom);
        editTextDate = findViewById(R.id.editTextDate);
        editTextHeureDebut = findViewById(R.id.editTextHeureDebut);
        editTextHeureFin = findViewById(R.id.editTextHeureFin);
        editTextPrix = findViewById(R.id.editTextPrix);
        editTextNbPlaces = findViewById(R.id.editTextNbPlaces);

        // Récupération des données
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

        buttonModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nouveauNom = editTextNom.getText().toString();
                String nouvelleDate = editTextDate.getText().toString();
                String nouvelleHeureDebut = editTextHeureDebut.getText().toString();
                String nouvelleHeureFin = editTextHeureFin.getText().toString();
                float nouveauPrix = Float.parseFloat(editTextPrix.getText().toString());
                int nouveauNbPlaces = Integer.parseInt(editTextNbPlaces.getText().toString());

                SessionDAO sessionDAO = new SessionDAO(ModifierSessionActivity.this);
                sessionDAO.modifierSession(idSession, nouveauNom, nouvelleDate, nouvelleHeureDebut, nouvelleHeureFin, nouveauPrix, nouveauNbPlaces);

                Toast.makeText(getApplicationContext(), "Session modifiée avec succès", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ModifierSessionActivity.this, ConsultSessionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonASupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new androidx.appcompat.app.AlertDialog.Builder(ModifierSessionActivity.this)
                        .setTitle("Confirmation")
                        .setMessage("Êtes-vous sûr de vouloir supprimer cette session ?")
                        .setPositiveButton("Oui", (dialog, which) -> {
                            SessionDAO sessionDAO = new SessionDAO(ModifierSessionActivity.this);
                            sessionDAO.supprimerSession(idSession);

                            Toast.makeText(getApplicationContext(), "Session supprimée avec succès", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(ModifierSessionActivity.this, ConsultSessionActivity.class);
                            startActivity(intent);
                            finish();
                        })
                        .setNegativeButton("Non", null)
                        .show();
            }
        });
    }
}
