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
            "--------------------------------------------------------------\n" +
            "--        Script MySQL.\n" +
            "--------------------------------------------------------------\n\n" +

            "--------------------------------------------------------------\n" +
            "-- Table: Utilisateur\n" +
            "--------------------------------------------------------------\n\n" +

            "CREATE TABLE Utilisateur(\n" +
            "        id         Int  Auto_increment  NOT NULL ,\n" +
            "        mail       Varchar (100) NOT NULL ,\n" +
            "        motDePasse Varchar (255) NOT NULL ,\n" +
            "        role       Varchar (25) NOT NULL\n" +
            "    ,CONSTRAINT Utilisateur_PK PRIMARY KEY (id)\n" +
            ")ENGINE=InnoDB;\n\n" +

            "--------------------------------------------------------------\n" +
            "-- Table: Session\n" +
            "--------------------------------------------------------------\n\n" +

            "CREATE TABLE Session(\n" +
            "        id          Int  Auto_increment  NOT NULL ,\n" +
            "        nomSession  Varchar (100) NOT NULL ,\n" +
            "        dateSession Date NOT NULL ,\n" +
            "        heureDebut  Time NOT NULL ,\n" +
            "        heureFin    Time NOT NULL ,\n" +
            "        prix        Float NOT NULL ,\n" +
            "        nbPlaces    Int NOT NULL\n" +
            "    ,CONSTRAINT Session_PK PRIMARY KEY (id)\n" +
            ")ENGINE=InnoDB;\n\n" +

            "--------------------------------------------------------------\n" +
            "-- Table: Type\n" +
            "--------------------------------------------------------------\n\n" +

            "CREATE TABLE Type(\n" +
            "        id          Int  Auto_increment  NOT NULL ,\n" +
            "        libelleType Varchar (50) NOT NULL\n" +
            "    ,CONSTRAINT Type_PK PRIMARY KEY (id)\n" +
            ")ENGINE=InnoDB;\n\n" +

            "--------------------------------------------------------------\n" +
            "-- Table: Recette\n" +
            "--------------------------------------------------------------\n\n" +

            "CREATE TABLE Recette(\n" +
            "        id          Int  Auto_increment  NOT NULL ,\n" +
            "        libelle     Varchar (255) NOT NULL ,\n" +
            "        description Varchar (1000) NOT NULL ,\n" +
            "        uneImage    Varchar (255) NOT NULL ,\n" +
            "        dateAjout   Date NOT NULL ,\n" +
            "        id_Type     Int NOT NULL\n" +
            "    ,CONSTRAINT Recette_PK PRIMARY KEY (id)\n" +
            "\n" +
            "    ,CONSTRAINT Recette_Type_FK FOREIGN KEY (id_Type) REFERENCES Type(id)\n" +
            ")ENGINE=InnoDB;\n\n" +

            "--------------------------------------------------------------\n" +
            "-- Table: Reserver\n" +
            "--------------------------------------------------------------\n\n" +

            "CREATE TABLE Reserver(\n" +
            "        id             Int NOT NULL ,\n" +
            "        id_Utilisateur Int NOT NULL\n" +
            "    ,CONSTRAINT Reserver_PK PRIMARY KEY (id,id_Utilisateur)\n" +
            "\n" +
            "    ,CONSTRAINT Reserver_Session_FK FOREIGN KEY (id) REFERENCES Session(id)\n" +
            "    ,CONSTRAINT Reserver_Utilisateur0_FK FOREIGN KEY (id_Utilisateur) REFERENCES Utilisateur(id)\n" +
            ")ENGINE=InnoDB;\n\n" +

            "--------------------------------------------------------------\n" +
            "-- Table: Proposer\n" +
            "--------------------------------------------------------------\n\n" +

            "CREATE TABLE Proposer(\n" +
            "        id         Int NOT NULL ,\n" +
            "        id_Session Int NOT NULL\n" +
            "    ,CONSTRAINT Proposer_PK PRIMARY KEY (id,id_Session)\n" +
            "\n" +
            "    ,CONSTRAINT Proposer_Recette_FK FOREIGN KEY (id) REFERENCES Recette(id)\n" +
            "    ,CONSTRAINT Proposer_Session0_FK FOREIGN KEY (id_Session) REFERENCES Session(id)\n" +
            ")ENGINE=InnoDB;";


    public BD_SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tables);
        //insérer jeu de test
        //db.execSQL("");


        Log.d("log","base de test cree");
    }
}