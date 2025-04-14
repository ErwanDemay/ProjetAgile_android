package com.example.projetagile.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SessionDAO {
    private static String base = "CookFusion";
    private static int version = 1;
    private BD_SQLiteOpenHelper accesBD;

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

}
