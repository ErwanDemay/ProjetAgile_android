package com.example.projetagile.modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BD_SQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String tables = "CREATE TABLE Utilisateur(\n" +
            "        id         INTEGER NOT NULL ,\n" +
            "        mail       VARCHAR (100) NOT NULL ,\n" +
            "        motDePasse VARCHAR (255) NOT NULL ,\n" +
            "        role       VARCHAR (25) NOT NULL ,\n" +
            "        CONSTRAINT Utilisateur_PK PRIMARY KEY (id)\n" +
            ");\n\n" +

            "CREATE TABLE Session(\n" +
            "        id          INTEGER NOT NULL ,\n" +
            "        nomSession  VARCHAR (100) NOT NULL ,\n" +
            "        dateSession DATE NOT NULL ,\n" +
            "        heureDebut  TIME NOT NULL ,\n" +
            "        heureFin    TIME NOT NULL ,\n" +
            "        prix        FLOAT NOT NULL ,\n" +
            "        nbPlaces    INTEGER NOT NULL ,\n" +
            "        CONSTRAINT Session_PK PRIMARY KEY (id)\n" +
            ");\n\n" +

            "CREATE TABLE Type(\n" +
            "        id          INTEGER NOT NULL ,\n" +
            "        libelleType VARCHAR (50) NOT NULL ,\n" +
            "        CONSTRAINT Type_PK PRIMARY KEY (id)\n" +
            ");\n\n" +

            "CREATE TABLE Recette(\n" +
            "        id          INTEGER NOT NULL ,\n" +
            "        libelle     VARCHAR (255) NOT NULL ,\n" +
            "        description VARCHAR (1000) NOT NULL ,\n" +
            "        uneImage    VARCHAR (255) NOT NULL ,\n" +
            "        dateAjout   DATE NOT NULL ,\n" +
            "        id_Type     INTEGER NOT NULL ,\n" +
            "        CONSTRAINT Recette_PK PRIMARY KEY (id) ,\n" +
            "        CONSTRAINT Recette_Type_FK FOREIGN KEY (id_Type) REFERENCES Type(id)\n" +
            ");\n\n" +

            "CREATE TABLE Reserver(\n" +
            "        id             INTEGER NOT NULL ,\n" +
            "        id_Utilisateur INTEGER NOT NULL ,\n" +
            "        CONSTRAINT Reserver_PK PRIMARY KEY (id, id_Utilisateur) ,\n" +
            "        CONSTRAINT Reserver_Session_FK FOREIGN KEY (id) REFERENCES Session(id) ,\n" +
            "        CONSTRAINT Reserver_Utilisateur_FK FOREIGN KEY (id_Utilisateur) REFERENCES Utilisateur(id)\n" +
            ");\n\n" +

            "CREATE TABLE Proposer(\n" +
            "        id         INTEGER NOT NULL ,\n" +
            "        id_Session INTEGER NOT NULL ,\n" +
            "        CONSTRAINT Proposer_PK PRIMARY KEY (id, id_Session) ,\n" +
            "        CONSTRAINT Proposer_Recette_FK FOREIGN KEY (id) REFERENCES Recette(id) ,\n" +
            "        CONSTRAINT Proposer_Session_FK FOREIGN KEY (id_Session) REFERENCES Session(id)\n" +
            ");";


    String jeuDeTest =
            "INSERT INTO Utilisateur (id, mail, motDePasse, role) VALUES \n" +
             "    (1, 'alice@mmb.com', 'motdepasse2fou', 'admin'), \n" +
             "    (2, 'bob@mbb.com', 'mdp123', 'user'), \n" +
             "    (3, 'charlie@bbm.com', '321pdm', 'user');\n\n" +

             "INSERT INTO Type (id, libelleType) VALUES \n" +
             "    (1, 'Entrée'), \n" +
             "    (2, 'Plat'), \n" +
             "    (3, 'Dessert');\n\n" +

             "INSERT INTO Session (id, nomSession, dateSession, heureDebut, heureFin, prix, nbPlaces) VALUES \n" +
             "    (1, 'Session A', '2025-04-10', '10:00:00', '12:00:00', 20.50, 50), \n" +
             "    (2, 'Session B', '2025-04-12', '14:00:00', '16:00:00', 25.00, 40), \n" +
             "    (3, 'Session C', '2025-04-15', '09:00:00', '11:00:00', 30.00, 30);\n\n" +

             "INSERT INTO Recette (id, libelle, description, uneImage, dateAjout, id_Type) VALUES \n" +
             "    (1, 'Salade Végétarienne', 'Une salade pleine de légumes frais et de saveurs', 'salade_vegetarienne.jpg', '2025-04-01', 1), \n" +
             "    (2, 'Poulet Grillé', 'Poulet mariné et grillé avec des épices', 'poulet_grille.jpg', '2025-04-02', 2), \n" +
             "    (3, 'Soupe de Lentilles', 'Une soupe chaude et réconfortante à base de lentilles', 'soupe_lentilles.jpg', '2025-04-03', 3);\n\n" +

             "INSERT INTO Reserver (id, id_Utilisateur) VALUES \n" +
             "    (1, 1),  -- Alice réserve la session 1 \n" +
             "    (2, 2),  -- Bob réserve la session 2 \n" +
             "    (3, 3);  -- Charlie réserve la session 3\n\n" +

             "INSERT INTO Proposer (id, id_Session) VALUES \n" +
             "    (1, 1),  -- La recette 1 est proposée pour la session 1 \n" +
             "    (2, 2),  -- La recette 2 est proposée pour la session 2 \n" +
             "    (3, 3);  -- La recette 3 est proposée pour la session 3";


    public BD_SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tables);
        db.execSQL(jeuDeTest);


        Log.d("log","base de test cree");
    }
}