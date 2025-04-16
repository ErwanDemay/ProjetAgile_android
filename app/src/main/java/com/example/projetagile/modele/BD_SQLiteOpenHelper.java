package com.example.projetagile.modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BD_SQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String tableUtilisateur =
            "CREATE TABLE Utilisateur(\n" +
            "        id         Int  Int  NOT NULL ,\n" +
            "        mail       Varchar (100) NOT NULL ,\n" +
            "        motDePasse Varchar (255) NOT NULL ,\n" +
            "        role       Varchar (25) NOT NULL\n" +
            "    ,CONSTRAINT Utilisateur_PK PRIMARY KEY (id)\n" +
            ");\n\n";
    private static final String tableSession =
            "CREATE TABLE Session(\n" +
            "        id          Int  Int  NOT NULL ,\n" +
            "        nomSession  Varchar (100) NOT NULL ,\n" +
            "        dateSession Varchar (25) NOT NULL ,\n" +
            "        heureDebut  Varchar (25) NOT NULL ,\n" +
            "        heureFin    Varchar (25) NOT NULL ,\n" +
            "        prix        Float NOT NULL ,\n" +
            "        nbPlaces    Int NOT NULL\n" +
            "    ,CONSTRAINT Session_PK PRIMARY KEY (id)\n" +
            ");\n\n";

    private static final String tableType =
            "CREATE TABLE Type(\n" +
            "        id          Int  Int  NOT NULL ,\n" +
            "        libelleType Varchar (50) NOT NULL\n" +
            "    ,CONSTRAINT Type_PK PRIMARY KEY (id)\n" +
            ");\n\n";

    private static final String tableRecette =
            "CREATE TABLE Recette(\n" +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                    "    libelle VARCHAR(255) NOT NULL,\n" +
                    "    description VARCHAR(1000) NOT NULL,\n" +
                    "    uneImage VARCHAR(255),\n" +
                    "    dateAjout Varchar (25) NOT NULL,\n" +
                    "    id_Type INTEGER NOT NULL,\n" +
                    "    CONSTRAINT Recette_Type_FK FOREIGN KEY (id_Type) REFERENCES Type(id)\n" +
                    ");";

    private static final String tableReserver =
            "CREATE TABLE Reserver(\n" +
            "        id             Int NOT NULL ,\n" +
            "        id_Utilisateur Int NOT NULL\n" +
            "    ,CONSTRAINT Reserver_PK PRIMARY KEY (id,id_Utilisateur)\n" +
            "\n" +
            "    ,CONSTRAINT Reserver_Session_FK FOREIGN KEY (id) REFERENCES Session(id)\n" +
            "    ,CONSTRAINT Reserver_Utilisateur0_FK FOREIGN KEY (id_Utilisateur) REFERENCES Utilisateur(id)\n" +
            ");\n\n";

    private static final String tableProposer =
            "CREATE TABLE Proposer(\n" +
            "        idRecette  Int NOT NULL,\n" +
            "        idSession  Int NOT NULL\n" +
            "\n" +
            "    ,CONSTRAINT Proposer_Recette_FK FOREIGN KEY (idRecette) REFERENCES Recette(id)\n" +
            "    ,CONSTRAINT Proposer_Session_FK FOREIGN KEY (idSession) REFERENCES Session(id)\n" +
            ");";

    private static final String jdtUtilisateur =
            "INSERT INTO Utilisateur (id, mail, motDePasse, role) VALUES \n" +
             "    (1, 'alice@exemple.com', 'password123', 'admin'), \n" +
             "    (2, 'bob@exemple.com', 'password456', 'user'), \n" +
             "    (3, 'charlie@exemple.com', 'password789', 'user');\n\n";

    private static final String jdtType =
             "INSERT INTO Type (id, libelleType) VALUES \n" +
             "    (1, 'Entrée'), \n" +
             "    (2, 'Plat'), \n" +
             "    (3, 'Dessert');\n\n";

    private static final String jdtSession =
             "INSERT INTO Session (id, nomSession, dateSession, heureDebut, heureFin, prix, nbPlaces) VALUES \n" +
             "    (1, 'Session A', '2025-04-10', '10:00:00', '12:00:00', 20.50, 50), \n" +
             "    (2, 'Session B', '2025-04-12', '14:00:00', '16:00:00', 25.00, 40), \n" +
             "    (3, 'Session C', '2025-04-15', '09:00:00', '11:00:00', 30.00, 30);\n\n";

    private static final String jdtRecette =
             "INSERT INTO Recette (id, libelle, description, uneImage, dateAjout, id_Type) VALUES \n" +
             "    (1, 'Salade Végétarienne', 'Une salade pleine de légumes frais et de saveurs', 'salade_vegetarienne.jpg', '2025-04-01', 1), \n" +
             "    (2, 'Poulet Grillé', 'Poulet mariné et grillé avec des épices', 'poulet_grille.jpg', '2025-04-02', 2), \n" +
             "    (3, 'Soupe de Lentilles', 'Une soupe chaude et réconfortante à base de lentilles', 'soupe_lentilles.jpg', '2025-04-03', 3);\n\n";

    private static final String jdtReserver =
             "INSERT INTO Reserver (id, id_Utilisateur) VALUES \n" +
             "    (1, 1),  -- Alice réserve la session 1 \n" +
             "    (2, 2),  -- Bob réserve la session 2 \n" +
             "    (3, 3);  -- Charlie réserve la session 3\n\n";

    private static final String jdtProposer =
             "INSERT INTO Proposer (idRecette, idSession) VALUES \n" +
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
        db.execSQL(tableUtilisateur);
        db.execSQL(tableSession);
        db.execSQL(tableType);
        db.execSQL(tableRecette);
        db.execSQL(tableReserver);
        db.execSQL(tableProposer);

        db.execSQL(jdtUtilisateur);
        db.execSQL(jdtType);
        db.execSQL(jdtSession);
        db.execSQL(jdtRecette);
        db.execSQL(jdtReserver);
        db.execSQL(jdtProposer);


        Log.d("log","base de test cree");
    }
}