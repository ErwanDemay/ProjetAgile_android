package com.example.projetagile.modele;

import android.content.ContentValues;

public class Reserver {

    private int id;
    private int idUtilisateur;

    public static final String TABLE_NAME = "Reserver";
    public static final String COL_ID = "id";
    public static final String COL_ID_UTILISATEUR = "id_Utilisateur";

    // Constructeur
    public Reserver(int id, int idUtilisateur) {
        this.id = id;
        this.idUtilisateur = idUtilisateur;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    // Conversion vers ContentValues pour insertion dans la DB
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(COL_ID, id);
        values.put(COL_ID_UTILISATEUR, idUtilisateur);
        return values;
    }
}