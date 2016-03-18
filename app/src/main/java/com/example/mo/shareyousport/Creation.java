package com.example.mo.shareyousport;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class Creation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TestCommitMax2
        Intent thisIntent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        String textPosition = thisIntent.getStringExtra("donnée");

        ListView listeSport = (ListView) findViewById(R.id.Liste_sport);

        ArrayList<String> listeItem = new ArrayList<>();
        listeItem.add(getString(R.string.Football));
        listeItem.add(getString(R.string.Rugby));
        listeItem.add(getString(R.string.Basketball));
        listeItem.add(getString(R.string.Tennis));
        listeItem.add(getString(R.string.Badminton));
        listeItem.add(getString(R.string.Volley_ball));
        listeItem.add(getString(R.string.Handball));
        listeItem.add(getString(R.string.Course));
        listeItem.add(getString(R.string.Cyclisme));

        ArrayAdapter<String> itemsAdapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, listeItem);
        listeSport.setAdapter(itemsAdapter);



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





        ImageView img = (ImageView) findViewById(R.id.back);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Creation.this, Interface.class);
                startActivity(myIntent);
            }
        });



    }

}
