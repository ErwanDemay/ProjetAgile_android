package com.example.projetagile.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class RecetteDAO {

    private static String base = "CookFusion";
    private static int version = 1;
    private BD_SQLiteOpenHelper accesBD;
    private static SQLiteDatabase database;
    private static ArrayList<Recette>listeRecette;


    public RecetteDAO(Context ct){
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
        this.listeRecette = new ArrayList<>();

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

    public void ajouterRecette(Recette uneRecette){

        database = accesBD.getWritableDatabase();

        //Pour la date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
        String currentDate = sdf.format(new Date());

        ContentValues values = new ContentValues();

        values.put("libelle",uneRecette.getLibelle());
        values.put("description",uneRecette.getDescription());
        values.put("id_Type",uneRecette.getIdType());
        values.put("dateAjout", currentDate );

        database.insert("recette",null,values);

        listeRecette.add(uneRecette);

        Log.d("DEBUG", "La recette a été ajoutée avec succès !");
    }

    public boolean deleteVisiteur(String id) {
        database = accesBD.getReadableDatabase();
        int rowsDeleted = database.delete("Recette", "id = ?", new String[]{id});
        database.close();

        return rowsDeleted > 0;
    }


    }


