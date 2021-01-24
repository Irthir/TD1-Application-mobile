package com.example.navigation2_leretour;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Initialisation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    EditText etNom=null;
    EditText etPrenom=null;
    EditText etEmail=null;
    RadioButton rbHomme=null;
    RadioButton rbFemme=null;
    RadioGroup rgGenre=null;
    CheckBox cbC=null;
    CheckBox cbCPP=null;
    CheckBox cbJava=null;
    ImageView ivPhoto=null;
    EditText etHeures=null;
    Button bReinitialiser=null;
    Button bValider=null;

    public void Initialisation()
    {
        //1.1 Gestion de la date.
        TextView tvDate = findViewById(R.id.Date);
        Date hui = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String date = formatter.format(hui);
        tvDate.setText(date);
        tvDate.setTextColor(Color.BLUE);
        tvDate.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));

        //1.2 Input Nom
        etNom = findViewById(R.id.Nom);
        etNom.setText("");
        etNom.setFilters(new InputFilter[] {new InputFilter.AllCaps()});


        //1.3 Input Prenom
        etPrenom = findViewById(R.id.Prenom);
        etPrenom.setText("");
        etPrenom.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);

        //1.4 Input Email
        etEmail = findViewById(R.id.Email);
        etNom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (etPrenom.getText().length()>0)
                {
                    String sMail = etPrenom.getText().toString()+"."+etNom.getText().toString()+"@Ludus-Academie.com";
                    etEmail.setText(sMail);
                }
            }
        });
        etPrenom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (etNom.getText().length()>0)
                {
                    String sMail = etPrenom.getText().toString()+"."+etNom.getText().toString()+"@Ludus-Academie.com";
                    etEmail.setText(sMail);
                }
            }
        });

        //1.5 Input Radios
        rbHomme = findViewById(R.id.Homme);
        rbFemme = findViewById(R.id.Femme);
        rgGenre = findViewById(R.id.Genre);
        rgGenre.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                /*Log.d("BoutonRadio","Checked Id = "+checkedId);
                Log.d("Bouton Homme", String.valueOf(R.id.Homme));
                Log.d("Bouton Femme", String.valueOf(R.id.Femme));*/

                String sMessage="";
                if (checkedId==R.id.Homme)
                {
                    sMessage = "Bienvenue monsieur "+etNom.getText().toString();
                }
                else
                {
                    sMessage = "Bienvenue madame "+etNom.getText().toString();
                }
                Toast.makeText(getApplicationContext(),sMessage,Toast.LENGTH_SHORT).show();
            }
        });

        //1.6 Input Checkbox
        cbC=findViewById(R.id.C);
        cbCPP=findViewById(R.id.CPP);
        cbJava=findViewById(R.id.Java);

        //1.7 ImageView
        ivPhoto = findViewById(R.id.Photo);
        ivPhoto.setBackgroundColor(Color.parseColor(CouleurAleatoire()));
        ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ivPhoto.setBackgroundColor(Color.parseColor(CouleurAleatoire()));
            }
        });

        //1.8 Heures de jeu par semaine
        etHeures = findViewById(R.id.Heures);
        etHeures.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (etHeures.getText().length()>0)
                {
                    int nHeures = Integer.parseInt(etHeures.getText().toString());
                    String sMessage="";
                    int nCouleur = Color.BLACK;
                    int nLongueurToast=Toast.LENGTH_SHORT;
                    if (nHeures<=6)
                    {
                        sMessage="Correct";
                        nCouleur = Color.GREEN;
                    }
                    else if (nHeures<=12)
                    {
                        sMessage="Attention";
                        nCouleur = Color.YELLOW;
                    }
                    else
                    {
                        sMessage="Addict";
                        nCouleur = Color.RED;
                        nLongueurToast=Toast.LENGTH_LONG;
                    }

                    Toast toast = Toast.makeText(getApplicationContext(),sMessage,nLongueurToast);
                    View vueToast = toast.getView();
                    TextView tvToast = (TextView) vueToast.findViewById(android.R.id.message);
                    tvToast.setTextColor(nCouleur);
                    etHeures.setTextColor(nCouleur);
                    toast.show();
                }
            }
        });

        //1.9 Bouton de Réinitialisation
        bReinitialiser = findViewById(R.id.Reinitialiser);
        bReinitialiser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reinitialiser();
            }
        });

        //1.10
        bValider = findViewById(R.id.Valider);
        bValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortieConsole();

                Navigation();
            }
        });
    }

    public void Navigation()
    {
        String EXTRA_USER = "user";

        //Préparation de la navigation.
        Intent monIntent = new Intent(MainActivity.this,Connexion.class);
        User user = new User(etNom.getText().toString(), etPrenom.getText().toString());
        monIntent.putExtra(EXTRA_USER,user);

        //Navigation
        startActivity(monIntent);
    }

    public String CouleurAleatoire()
    {
        String zeros = "000000";
        Random rnd = new Random();
        String s = Integer.toString(rnd.nextInt(0X1000000), 16);
        s = zeros.substring(s.length()) + s;
        s = "#ff"+s;
        //Log.d("Random",s);
        return s;
    }

    public void Reinitialiser()
    {
        etNom.setText("");
        etPrenom.setText("");
        etEmail.setText("");
        rbHomme.setChecked(false);
        rbFemme.setChecked(true);
        cbC.setChecked(false);
        cbCPP.setChecked(false);
        cbJava.setChecked(false);
        etHeures.setText("");
        Log.d("RAZ","Reinitialisation des inputs.");
    }

    public void SortieConsole()
    {
        String sNom="";
        String sPrenom="";
        String sEmail="";
        String sGenre="";
        String sProgrammation="";
        String sHeures="";

        sNom = etNom.getText().toString();
        sPrenom = etPrenom.getText().toString();
        sEmail = etEmail.getText().toString();
        sGenre = rbHomme.isChecked() ? "homme" : "femme";
        if (cbC.isChecked())
            sProgrammation+= "C ";
        if (cbCPP.isChecked())
            sProgrammation+= "C++ ";
        if (cbJava.isChecked())
            sProgrammation+= "Java ";
        sHeures = etHeures.getText().toString();

        String sConsole = "";
        sConsole += "Nom : "+sNom;
        sConsole += " Prenom : "+sPrenom;
        sConsole += " Email : "+sEmail;
        sConsole += " Genre : "+sGenre;
        sConsole += " Langages de Programmation : "+sProgrammation;
        sConsole += " Nombre d'Heures de jeu par semaine : "+sHeures+" heures.";
        Log.d("Resultat Formulaire",sConsole);
    }
}