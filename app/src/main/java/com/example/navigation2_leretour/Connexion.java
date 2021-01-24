package com.example.navigation2_leretour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Connexion extends AppCompatActivity {

    TextView tvPrenom=null;
    TextView tvNom=null;
    TextView tvEmail=null;
    TextView tvGenre=null;

    Button bAccueil=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        String EXTRA_USER = "user";

        Intent monIntent = getIntent();
        User user = monIntent.getParcelableExtra(EXTRA_USER);
        String strNom = "Nom : "+user.getNom();
        String strPrenom = "Prenom : "+user.getPrenom();
        /*String strMail = "Email : ";
        String strGenre = "Genre : ";*/

        tvNom = findViewById(R.id.Nom);
        tvPrenom = findViewById(R.id.Prenom);
        /*tvEmail = findViewById(R.id.Mail);
        tvGenre = findViewById(R.id.Genre);*/

        tvNom.setText(strNom);
        tvPrenom.setText(strPrenom);
        /*tvEmail.setText(strMail);
        tvGenre.setText(strGenre);*/

        bAccueil = (Button) findViewById(R.id.Accueil);
        bAccueil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation();
            }
        });
    }

    public void Navigation()
    {
        //Préparation de la navigation.
        Intent monIntent = new Intent(Connexion.this,MainActivity.class);

        //Navigation
        startActivity(monIntent);
    }
}
