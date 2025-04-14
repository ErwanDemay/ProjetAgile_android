package com.example.projetagile.modele;

import android.content.ContentValues;

public class Session {

    private int id;
    private String nomSession;
    private String dateSession;
    private String heureDebut;
    private String heureFin;
    private float prix;
    private int nbPlaces;

    public static final String TABLE_NAME = "Session";
    public static final String COL_ID = "id";
    public static final String COL_NOM_SESSION = "nomSession";
    public static final String COL_DATE_SESSION = "dateSession";
    public static final String COL_HEURE_DEBUT = "heureDebut";
    public static final String COL_HEURE_FIN = "heureFin";
    public static final String COL_PRIX = "prix";
    public static final String COL_NB_PLACES = "nbPlaces";

    // Constructeur
    public Session(int id, String nomSession, String dateSession, String heureDebut, String heureFin, float prix, int nbPlaces) {
        this.id = id;
        this.nomSession = nomSession;
        this.dateSession = dateSession;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.prix = prix;
        this.nbPlaces = nbPlaces;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomSession() {
        return nomSession;
    }

    public void setNomSession(String nomSession) {
        this.nomSession = nomSession;
    }

    public String getDateSession() {
        return dateSession;
    }

    public void setDateSession(String dateSession) {
        this.dateSession = dateSession;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    // Conversion vers ContentValues pour insertion dans la DB
    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(COL_ID, id);
        values.put(COL_NOM_SESSION, nomSession);
        values.put(COL_DATE_SESSION, dateSession);
        values.put(COL_HEURE_DEBUT, heureDebut);
        values.put(COL_HEURE_FIN, heureFin);
        values.put(COL_PRIX, prix);
        values.put(COL_NB_PLACES, nbPlaces);
        return values;
    }

    @Override
    public String toString(){
        return this.getNomSession()+" - "+this.getDateSession()+" - " + this.getHeureDebut();
    }
}