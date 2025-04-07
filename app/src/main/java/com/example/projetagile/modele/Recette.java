package com.example.projetagile.modele;

import android.content.ContentValues;

public class Recette {

    private int id;
    private String libelle;
    private String description;
    private String uneImage;
    private String dateAjout;
    private int idType;

    public static final String TABLE_NAME = "Recette";
    public static final String COL_ID = "id";
    public static final String COL_LIBELLE = "libelle";
    public static final String COL_DESCRIPTION = "description";
    public static final String COL_UNE_IMAGE = "uneImage";
    public static final String COL_DATE_AJOUT = "dateAjout";
    public static final String COL_ID_TYPE = "id_Type";

    // Constructeur
    public Recette(int id, String libelle, String description, String uneImage, String dateAjout, int idType) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.uneImage = uneImage;
        this.dateAjout = dateAjout;
        this.idType = idType;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUneImage() {
        return uneImage;
    }

    public void setUneImage(String uneImage) {
        this.uneImage = uneImage;
    }

    public String getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = dateAjout;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    // Conversion vers ContentValues pour insertion dans la DB
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(COL_ID, id);
        values.put(COL_LIBELLE, libelle);
        values.put(COL_DESCRIPTION, description);
        values.put(COL_UNE_IMAGE, uneImage);
        values.put(COL_DATE_AJOUT, dateAjout);
        values.put(COL_ID_TYPE, idType);
        return values;
    }
}