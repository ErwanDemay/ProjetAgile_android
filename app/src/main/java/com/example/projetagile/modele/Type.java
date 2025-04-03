package com.example.projetagile.modele;

import android.content.ContentValues;

public class Type {

    private int id;
    private String libelleType;

    public static final String TABLE_NAME = "Type";
    public static final String COL_ID = "id";
    public static final String COL_LIBELLE_TYPE = "libelleType";

    // Constructeur
    public Type(int id, String libelleType) {
        this.id = id;
        this.libelleType = libelleType;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelleType() {
        return libelleType;
    }

    public void setLibelleType(String libelleType) {
        this.libelleType = libelleType;
    }

    // Conversion vers ContentValues pour insertion dans la DB
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(COL_ID, id);
        values.put(COL_LIBELLE_TYPE, libelleType);
        return values;
    }
}