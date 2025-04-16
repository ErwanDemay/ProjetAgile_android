    package com.example.projetagile.controleur.sessions;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.Button;
    import android.widget.ListView;
    import android.widget.ArrayAdapter;

    import com.example.projetagile.MainActivity;
    import com.example.projetagile.R;
    import com.example.projetagile.controleur.PropositionActivity;
    import com.example.projetagile.modele.Session;
    import com.example.projetagile.modele.SessionDAO;

    import java.util.ArrayList;

    public class ConsultSessionActivity extends AppCompatActivity {

        private Button buttonAjouterSession;
        private ListView listViewSessions;
        private SessionDAO sessionDAO;
        private Button buttonRetourAccueil;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_consult_session);

            buttonAjouterSession = findViewById(R.id.buttonAjouter);
            listViewSessions = findViewById(R.id.listViewSessions);
            buttonRetourAccueil = findViewById(R.id.buttonRetourAccueil);

            sessionDAO = new SessionDAO(this);

            ArrayList<Session> lesSessions = sessionDAO.cursorToSessionsArrayList();

            ArrayAdapter<Session> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lesSessions);
            listViewSessions.setAdapter(adapter);

            buttonAjouterSession.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ouvrirAjoutSession();
                }
            });

            buttonRetourAccueil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    retourAccueil();
                }
            });

            listViewSessions.setOnItemClickListener((parent, view, position, id) -> {
                Session sessionSelectionnee = (Session) parent.getItemAtPosition(position);

                Intent intent = new Intent(ConsultSessionActivity.this, ModifierSessionActivity.class);
                intent.putExtra("id", sessionSelectionnee.getId());
                intent.putExtra("nomSession", sessionSelectionnee.getNomSession());
                intent.putExtra("dateSession", sessionSelectionnee.getDateSession());
                intent.putExtra("heureDebut", sessionSelectionnee.getHeureDebut());
                intent.putExtra("heureFin", sessionSelectionnee.getHeureFin());
                intent.putExtra("prix", sessionSelectionnee.getPrix());
                intent.putExtra("nbPlaces", sessionSelectionnee.getNbPlaces());

                startActivity(intent);
            });
        }

        protected void ouvrirAjoutSession() {
            Intent intent = new Intent(this, AjoutSessionActivity.class);
            startActivity(intent);
        }

        protected void retourAccueil(){
            Log.d("","Retour accueil créé");
            Intent intent = new Intent(this , PropositionActivity.class);
            startActivity(intent);
        }


    }
