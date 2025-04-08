package com.example.projetagile.controleur.sessions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.projetagile.R;
import com.example.projetagile.modele.UtilisateurDAO;

import java.sql.Time;
import java.util.Date;

public class AjoutSessionActivity extends AppCompatActivity {

    private UtilisateurDAO utilisateurDAO;
    private EditText editTextNom, editTextDate,editTextHeureDebut,editTextHeureFin,editTextPrix,editTextNbPlaces;
    private Button buttonAjouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_session);

        //Initialisation du DAO
         utilisateurDAO = new UtilisateurDAO(this);

        //Initialisation de tous les champs
        editTextNom = findViewById(R.id.editTextNom);
        editTextDate = findViewById(R.id.editTextDate);
        editTextHeureDebut = findViewById(R.id.editTextHeureDebut);
        editTextHeureFin = findViewById(R.id.editTextHeureFin);
        editTextPrix = findViewById(R.id.editTextPrix);
        editTextNbPlaces = findViewById(R.id.editTextNbPlaces);
        buttonAjouter = findViewById(R.id.buttonAjouter);

        //Listener sur le bouton d'ajout
        buttonAjouter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                validerAjoutSession();
            }
        });
    }

    public void validerAjoutSession(){
        try {
            String nom = editTextNom.getText().toString();
            Date date = editTextDate.getText();
            Time heureDebut = editTextHeureDebut.getText();
            Time heureFin = editTextHeureFin.getText();
            Float prix = editTextPrix.getText();
            Int nbPlaces = editTextNbPlaces();

            //SessionDAO.addSession(session);

            Toast.makeText(this, "Session ajoutée avec succès", Toast.LENGTH_SHORT).show();
            //Log.d("DB_DEBUG", "Session ajouté : " + session.getNom() + ", ";
        }catch (Error e){
            Toast.makeText(this, "Erreur" + e , Toast.LENGTH_SHORT).show();
        }
    }
}