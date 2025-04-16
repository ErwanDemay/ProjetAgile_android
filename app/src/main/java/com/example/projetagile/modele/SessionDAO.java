package com.example.projetagile.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class SessionDAO {
    private static String base = "CookFusion";
    private static int version = 1;
    private BD_SQLiteOpenHelper accesBD;
    private static SQLiteDatabase database;
    private static ArrayList<Session>listeSession;


    public SessionDAO(Context ct){
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    public ArrayList<Session> cursorToSessionsArrayList(){
        ArrayList<Session> listeSession = new ArrayList<Session>();
        int id;
        String nomSession;
        String dateSession;
        String heureDebut;
        String heureFin;
        float prix;
        int nbPlaces;

        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from Session;",null);

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            id = Integer.parseInt(curseur.getString(0));
            nomSession = curseur.getString(1);
            dateSession = curseur.getString(2);
            heureDebut = curseur.getString(3);
            heureFin = curseur.getString(4);
            prix = Float.parseFloat(curseur.getString(5));
            nbPlaces = Integer.parseInt(curseur.getString(6));
            listeSession.add(new Session(id, nomSession,dateSession,heureDebut,heureFin,prix,nbPlaces));
            curseur.moveToNext();
        }

        return listeSession;
    }

    public void ajouterSession(Session uneSession){

        database = accesBD.getWritableDatabase();

        //Pour la date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        String currentDate = sdf.format(new Date());

        ContentValues values = new ContentValues();

        values.put("nomSession",uneSession.getNomSession());
        values.put("dateSession",uneSession.getDateSession());
        values.put("heureDebut",uneSession.getHeureDebut());
        values.put("heureFin",uneSession.getHeureFin());
        values.put("prix",uneSession.getPrix());
        values.put("nbPlaces",uneSession.getNbPlaces());


        database.insert("session",null,values);

        listeSession.add(uneSession);

        Log.d("DEBUG", "La session a été ajoutée avec succès !");
    }

    public void supprimerSession(int idSession) {
        database = accesBD.getWritableDatabase();
        database.delete("Session", "id = ?", new String[]{String.valueOf(idSession)});
        database.close();
    }


    public void modifierSession(int idSession, String nouveauNom , String nouvelleDateSession , String nouvelleHeureDebut , String nouvelleHeureFin , Float nouveauPrix , Integer nouveauxNbPlaces) {
        database = accesBD.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nomSession", nouveauNom);
        values.put("dateSession", String.valueOf(nouvelleDateSession));
        values.put("heureDebut", String.valueOf(nouvelleHeureDebut));
        values.put("heureFin", String.valueOf(nouvelleHeureFin));
        values.put("prix", nouveauPrix);
        values.put("nbPlaces", nouveauxNbPlaces);


        database.update("Session", values, "id = ?", new String[]{String.valueOf(idSession)});
        database.close();

        Log.d("DEBUG", "Session modifiée avec succès !");
    }

}
