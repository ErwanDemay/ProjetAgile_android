package com.example.projetagile.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ProposerDAO {
    private BD_SQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    public ProposerDAO(Context context) {
        accesBD = new BD_SQLiteOpenHelper(context, "CookFusion", null, 1);
    }

    public ArrayList<Recette> getRecettesPourSession(int sessionId) {
        ArrayList<Recette> recettes = new ArrayList<>();
        bd = accesBD.getReadableDatabase();

        Cursor cursor = bd.rawQuery(
                "SELECT Recette.id, libelle, description, uneImage, dateAjout, id_Type " +
                        "FROM Proposer " +
                        "INNER JOIN Recette ON Proposer.id = Recette.id " +
                        "WHERE Proposer.id_Session = ?", // toujours à garder
                new String[]{String.valueOf(sessionId)}
        );


        if (cursor.moveToFirst()) {
            do {
                Recette r = new Recette(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5)
                );
                recettes.add(r);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return recettes;
    }
    public boolean ajouterRecetteASession(int idRecette, int idSession) {
        SQLiteDatabase db = accesBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", idRecette);         // id de la recette
        values.put("id_Session", idSession); // id de la session

        long result = db.insert("Proposer", null, values);
        db.close();

        return result != -1;
    }

}
