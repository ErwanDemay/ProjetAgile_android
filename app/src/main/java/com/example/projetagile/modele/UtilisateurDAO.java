package com.example.projetagile.modele;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import com.example.projetagile.modele.Utilisateur;

public class UtilisateurDAO{

    private static String base = "Utilisateur";
    private static int version = 1;
    private BD_SQLiteOpenHelper accesBD;

    public UtilisateurDAO(Context ct){
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    public ArrayList<Utilisateur> cursorToUtilisateursArrayList(){
        ArrayList<Utilisateur> listeUtilisteur = new ArrayList<Utilisateur>();
        String id;
        String mail;
        String motDePasse;
        String role;

        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from Utilisateur;",null);

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            id = curseur.getString(0);
            mail = curseur.getString(1);
            motDePasse = curseur.getString(2);
            role = curseur.getString(3);
            listeUtilisteur.add(new Utilisateur(id, mail, motDePasse, role));
            curseur.moveToNext();
        }

        return listeUtilisteur;
    }

    public Utilisateur seConnecter(String unIdentifiant, String unMotDePasse){
        Utilisateur unUtilisateur = null;
        ArrayList<Utilisateur> lesUtilisateurs = new ArrayList<Utilisateur>();
        SQLiteDatabase db = accesBD.getReadableDatabase();
        String query = "SELECT * FROM utilisateur WHERE mail = ? AND motDePasse = ?";
        Cursor cursor = db.rawQuery(query, new String[]{unIdentifiant, unMotDePasse});

        if (cursor != null && cursor.moveToFirst()){
            unUtilisateur = new Utilisateur(
                    cursor.getString(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("mail")),
                    cursor.getString(cursor.getColumnIndexOrThrow("motDePasse")),
                    cursor.getString(cursor.getColumnIndexOrThrow("role"))
            );
            cursor.close();
            db.close();

            return unUtilisateur;
        }else{
            return null;
        }
    }
}
