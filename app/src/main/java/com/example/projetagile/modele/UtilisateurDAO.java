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

    //Pour quand on aura l'API
    /*public Utilisateur seConnecter(String unIdentifiant, String unMotDePasse){
        String result = "";
        //adresse de l'URL de l\'API à interroger et fichier php permettant d'\ajouter le visiteur
        String myUrl = "https://blackmythwukong.alwaysdata.net/API/getVisiteurs.php?";
        //informations à transmettre pour effectuer l'ajout
        String params = "login="+unIdentifiant+
                "&mdp="+unMotDePasse;
        Log.d("requete", params);

        Visiteur leVisiteur = null;

        HttpPostRequest postRequest = new HttpPostRequest();
        try {
            result = postRequest.execute(new String[]{myUrl, params}).get();
            //Log.d("resultat",result.);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try{
            JSONArray array = new JSONArray(result);

            for (int i = 0; i < array.length(); i++) {
                if (unIdentifiant.equals(array.getJSONObject(i).getString("login"))){
                    if (unMotDePasse.equals(array.getJSONObject(i).getString("mdp")){
                        String id = array.getJSONObject(i).getString("id");
                        String nom = array.getJSONObject(i).getString("nom");
                        String prenom = array.getJSONObject(i).getString("prenom");
                        String login = array.getJSONObject(i).getString("login");
                        String mdp = array.getJSONObject(i).getString("mdp");
                        String adresse = array.getJSONObject(i).getString("adresse");
                        String cp = array.getJSONObject(i).getString("cp");
                        String ville = array.getJSONObject(i).getString("ville");
                        String dateEmbauche = array.getJSONObject(i).getString("dateEmbauche");

                        leVisiteur = new Visiteur(id, nom, prenom, login, mdp, adresse, cp, ville, dateEmbauche);
                    }
                }

            }
        }catch(JSONException e) {
            e.printStackTrace();
        }

        return leVisiteur;
    }*/
}
