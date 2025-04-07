package com.example.projetagile.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetagile.controleur.PropositionActivity;
import com.example.projetagile.modele.Utilisateur;
import com.example.projetagile.modele.UtilisateurDAO;

import com.example.projetagile.R;

import java.util.ArrayList;

public class ConnexionActivity extends AppCompatActivity {

    private UtilisateurDAO utilisateurDAO;

    private String mail;
    private String mdp;

    private EditText editTextLogin;
    private EditText editTextMDP;
    private Button buttonValider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        utilisateurDAO = new UtilisateurDAO(this);

        editTextLogin = (EditText) findViewById(R.id.editTextNom);
        editTextMDP = (EditText) findViewById(R.id.editTextMDP);
        buttonValider = findViewById(R.id.buttonValider);

        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connexion();
            }
        });
    }

    protected void ouvrirProposition() {
        Intent intent = new Intent(this, PropositionActivity.class);
        startActivity(intent);
    }

    protected void connexion(){

        mail = editTextLogin.getText().toString();
        mdp = editTextMDP.getText().toString();

        if(utilisateurDAO.seConnecter(mail, mdp) != null){
            ouvrirProposition();
        }else{
            Toast.makeText(this, "Identifiants incorrects", Toast.LENGTH_SHORT).show();
        }
    }
}