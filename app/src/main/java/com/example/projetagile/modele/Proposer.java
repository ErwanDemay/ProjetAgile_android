package com.example.projetagile.modele;

import android.content.ContentValues;

public class Proposer {

    private int id;
    private int idSession;

    public static final String TABLE_NAME = "Proposer";
    public static final String COL_ID = "id";
    public static final String COL_ID_SESSION = "id_Session";

    // Constructeur
    public Proposer(int id, int idSession) {
        this.id = id;
        this.idSession = idSession;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    // Conversion vers ContentValues pour insertion dans la DB
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(COL_ID, id);
        values.put(COL_ID_SESSION, idSession);
        return values;
    }
}