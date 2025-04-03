package com.example.projetagile.modele;

import android.content.ContentValues;

public class Utilisateur {

    private String id;
    private String mail;
    private String motDePasse;
    private String role;

    public static final String TABLE_NAME = "Utilisateur";
    public static final String COL_ID = "id";
    public static final String COL_MAIL = "mail";
    public static final String COL_MOT_DE_PASSE = "motDePasse";
    public static final String COL_ROLE = "role";

    // Constructeur
    public Utilisateur(String id, String mail, String motDePasse, String role) {
        this.id = id;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Conversion vers ContentValues pour insertion dans la DB
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(COL_ID, id);
        values.put(COL_MAIL, mail);
        values.put(COL_MOT_DE_PASSE, motDePasse);
        values.put(COL_ROLE, role);
        return values;
    }
}