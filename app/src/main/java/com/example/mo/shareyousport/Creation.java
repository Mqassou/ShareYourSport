package com.example.mo.shareyousport;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Creation extends AppCompatActivity {

    private Button choixDusport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TestCommitMax2
        Intent thisIntent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        ////////////////////  Boutton liste des sports ///////////////////////////////////
        choixDusport= (Button) findViewById(R.id.Liste_sport);

        choixDusport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAlertDialogWithListview();

            }
        });
        ////////////////////  Fin Boutton liste des sports ///////////////////////////////////

        ////////////////////  Choix du nombre de personnes ///////////////////////////////////

        ImageView augmenter = (ImageView) findViewById(R.id.plus);
        ImageView diminuer = (ImageView) findViewById(R.id.minus);
        final EditText nombrepersonne = (EditText) findViewById(R.id.nombrepersonne);

        augmenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String nombre= nombrepersonne.getText().toString();
                int nbr=Integer.parseInt(nombre);
                nbr++;
                nombre=Integer.toString(nbr);
                nombrepersonne.setText(nombre);
            }
        });

        diminuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre= nombrepersonne.getText().toString();
                int nbr=Integer.parseInt(nombre);
                if(nbr>0) {
                    nbr--;
                }
                nombre=Integer.toString(nbr);
                nombrepersonne.setText(nombre);
            }
        });

        ////////////////////  fin Choix du nombre de personnes ///////////////////////////////////


        ////////////////////  Géolocation ///////////////////////////////////
        String textPosition = thisIntent.getStringExtra("donnée");

        EditText txt = (EditText) findViewById(R.id.editText);

        if(textPosition != null){
            txt.setText(textPosition);
        }

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Creation.this, MapsActivityCreation.class);
                startActivity(myIntent);
            }
        });
        //////////////////// Fin Géolocation ///////////////////////////////////

        ImageView img = (ImageView) findViewById(R.id.back);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Creation.this, Interface.class);
                startActivity(myIntent);
            }
        });

    }
    public void ShowAlertDialogWithListview()
    {

        ArrayList<String> listeSport = new ArrayList<>();
        listeSport.add(getString(R.string.Football));
        listeSport.add(getString(R.string.Rugby));
        listeSport.add(getString(R.string.Basketball));
        listeSport.add(getString(R.string.Tennis));
        listeSport.add(getString(R.string.Badminton));
        listeSport.add(getString(R.string.Volley_ball));
        listeSport.add(getString(R.string.Handball));
        listeSport.add(getString(R.string.Course));
        listeSport.add(getString(R.string.Cyclisme));

        final CharSequence[] dialogSport = listeSport.toArray(new String[listeSport.size()]);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Sport");
        dialogBuilder.setItems(dialogSport, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                String selectedText = dialogSport[item].toString();  //Selected item in listview
                choixDusport.setText(selectedText);
            }
        });
        //Create alert dialog object via builder
        AlertDialog alertDialogObject = dialogBuilder.create();
        //Show the dialog
        alertDialogObject.show();
    }

}
