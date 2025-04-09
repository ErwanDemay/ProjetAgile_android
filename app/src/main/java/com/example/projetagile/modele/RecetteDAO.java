package com.example.projetagile.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class RecetteDAO {

    private static String base = "CookFusion";
    private static int version = 1;
    private BD_SQLiteOpenHelper accesBD;

    public RecetteDAO(Context ct){
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }


    public ArrayList<Recette> cursorToUtilisateursArrayList(){
        ArrayList<Recette> listeRecette = new ArrayList<Recette>();
        int id;
        String libelle;
        String description;
        String uneImage;
        String dateAjout;
        int idType;

        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from Recette;",null);

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            id = Integer.parseInt(curseur.getString(0));
            libelle = curseur.getString(1);
            description = curseur.getString(2);
            uneImage = curseur.getString(3);
            dateAjout = curseur.getString(4);
            idType = Integer.parseInt(curseur.getString(5));
            listeRecette.add(new Recette(id, libelle, description, uneImage, dateAjout, idType));
            curseur.moveToNext();
        }

        return listeRecette;
    }
}
