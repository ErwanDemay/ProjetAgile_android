package com.example.projetagile.modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BD_SQLiteOpenHelper extends SQLiteOpenHelper {
    private String visiteur = "CREATE TABLE IF NOT EXISTS visiteur (\n" +
            "  id text PRIMARY KEY NOT NULL,\n" +
            "  nom text DEFAULT NULL,\n" +
            "  prenom text DEFAULT NULL,\n" +
            "  login text DEFAULT NULL,\n" +
            "  mdp text DEFAULT NULL,\n" +
            "  adresse text DEFAULT NULL,\n" +
            "  cp text DEFAULT NULL,\n" +
            "  ville text DEFAULT NULL,\n" +
            "  dateEmbauche text DEFAULT NULL); ";
    public static final String tables =
            "CREATE TABLE Utilisateur(\n" +
            "        id         Int  Int  NOT NULL ,\n" +
            "        mail       Varchar (100) NOT NULL ,\n" +
            "        motDePasse Varchar (255) NOT NULL ,\n" +
            "        role       Varchar (25) NOT NULL\n" +
            "    ,CONSTRAINT Utilisateur_PK PRIMARY KEY (id)\n" +
            ");\n\n" +

            "CREATE TABLE Session(\n" +
            "        id          Int  Int  NOT NULL ,\n" +
            "        nomSession  Varchar (100) NOT NULL ,\n" +
            "        dateSession Date NOT NULL ,\n" +
            "        heureDebut  Time NOT NULL ,\n" +
            "        heureFin    Time NOT NULL ,\n" +
            "        prix        Float NOT NULL ,\n" +
            "        nbPlaces    Int NOT NULL\n" +
            "    ,CONSTRAINT Session_PK PRIMARY KEY (id)\n" +
            ");\n\n" +

            "CREATE TABLE Type(\n" +
            "        id          Int  Int  NOT NULL ,\n" +
            "        libelleType Varchar (50) NOT NULL\n" +
            "    ,CONSTRAINT Type_PK PRIMARY KEY (id)\n" +
            ");\n\n" +

            "CREATE TABLE Recette(\n" +
            "        id          Int  Int  NOT NULL ,\n" +
            "        libelle     Varchar (255) NOT NULL ,\n" +
            "        description Varchar (1000) NOT NULL ,\n" +
            "        uneImage    Varchar (255) NOT NULL ,\n" +
            "        dateAjout   Date NOT NULL ,\n" +
            "        id_Type     Int NOT NULL\n" +
            "    ,CONSTRAINT Recette_PK PRIMARY KEY (id)\n" +
            "\n" +
            "    ,CONSTRAINT Recette_Type_FK FOREIGN KEY (id_Type) REFERENCES Type(id)\n" +
            ");\n\n" +

            "CREATE TABLE Reserver(\n" +
            "        id             Int NOT NULL ,\n" +
            "        id_Utilisateur Int NOT NULL\n" +
            "    ,CONSTRAINT Reserver_PK PRIMARY KEY (id,id_Utilisateur)\n" +
            "\n" +
            "    ,CONSTRAINT Reserver_Session_FK FOREIGN KEY (id) REFERENCES Session(id)\n" +
            "    ,CONSTRAINT Reserver_Utilisateur0_FK FOREIGN KEY (id_Utilisateur) REFERENCES Utilisateur(id)\n" +
            ");\n\n" +

            "CREATE TABLE Proposer(\n" +
            "        id         Int NOT NULL ,\n" +
            "        id_Session Int NOT NULL\n" +
            "    ,CONSTRAINT Proposer_PK PRIMARY KEY (id,id_Session)\n" +
            "\n" +
            "    ,CONSTRAINT Proposer_Recette_FK FOREIGN KEY (id) REFERENCES Recette(id)\n" +
            "    ,CONSTRAINT Proposer_Session0_FK FOREIGN KEY (id_Session) REFERENCES Session(id)\n" +
            ");";

    String jeuDeTest =
            "INSERT INTO Utilisateur (id, mail, motDePasse, role) VALUES \n" +
             "    (1, 'alice@example.com', 'password123', 'admin'), \n" +
             "    (2, 'bob@example.com', 'password456', 'user'), \n" +
             "    (3, 'charlie@example.com', 'password789', 'user');\n\n" +

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