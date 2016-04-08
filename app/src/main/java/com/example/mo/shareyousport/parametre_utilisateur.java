package com.example.mo.shareyousport;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

public class parametre_utilisateur extends AppCompatActivity {
     SharedPreferences sharedpreferences;
      String id_utilisateur;
    final  String RECUPERER="recupererdonnees";
     final  String UPDATE="mettreajour";
    EditText pseudo, nom, prenom,email,tel,adresse,ville,password, password_confirm;
    Button date_de_naissance;
    CheckBox sexe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametre_utilisateur);
        sharedpreferences  =getSharedPreferences("id_utilisateur", Context.MODE_PRIVATE);
        id_utilisateur =sharedpreferences.getString("id","");

        PostClass requeteHttp=new PostClass();
        requeteHttp.execute(id_utilisateur,RECUPERER);
    }
    private class PostClass extends AsyncTask<String, Void, HashMap> {


        @Override//Cette méthode s'execute en deuxième
        protected HashMap doInBackground(String... params) {
            System.out.println("\n\n\n\n\n  MMMMMMAAAAAAARQUUUUUEUUUUUUUR 111111\n\n\n\n\n ");
            ConnectivityManager check = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo[] info = check.getAllNetworkInfo();
            for (int i = 0; i < info.length; i++) {
                if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                    String result;

                    try {
                        System.out.println("\n\n\n\n\n  MMMMMMAAAAAAARQUUUUUEUUUUUUUR 2222222\n\n\n\n\n ");
                        /////////////////////////////// REQUETE HTTP /////////////////////
                        URL url = new URL("http://humanapp.assos.efrei.fr/shareyoursport/script/shareyoursportcontroller.php");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setConnectTimeout(3000);
                        connection.setRequestMethod("POST");
                        connection.setDoInput(true);
                        connection.setDoOutput(true);

                        /// Mise en place des differents parametre necessaire ////
                        if(params[1].equals(RECUPERER))
                        {
                            System.out.println("\n\n\n\n\n  MMMMMMAAAAAAARQUUUUUEUUUUUUUR 333333333\n\n\n\n\n ");
                            Uri.Builder builder = new Uri.Builder()
                                    .appendQueryParameter("OBJET", RECUPERER)
                                    .appendQueryParameter("ID", params[0]) ;
                            String query = builder.build().getEncodedQuery();

                            OutputStream os = connection.getOutputStream();
                            BufferedWriter writer = new BufferedWriter(
                                    new OutputStreamWriter(os, "UTF-8"));
                            writer.write(query);
                            writer.flush();
                            writer.close();
                            os.close();

                            connection.connect();
                            System.out.println("\n\n\n\n\n  MMMMMMAAAAAAARQUUUUUEUUUUUUUR 44444444\n\n\n\n\n ");


                            ///////////////////////////////BUFFERREADER/////////////////////

                            Reader reader =new InputStreamReader(connection.getInputStream(), "UTF-8");
                            char[] buffer = new char[1024];
                            reader.read(buffer);  /// On recupere ce que nous a envoyés le fichier php
                            result = new String(buffer);
                            reader.close();
                            System.out.println("\n\n\n\n\n  MMMMMMAAAAAAARQUUUUUEUUUUUUUR 555555\n\n\n\n\n ");

                            //////////////////////JSON////////////////////////////////////
                            try {
                                System.out.println("\n\n\n\n\n  MMMMMMAAAAAAARQUUUUUEUUUUUUUR 666666\n\n\n\n\n ");
                                JSONObject jObject = new JSONObject(result);
                                System.out.println("\n\n\n\n\n  MMMMMMAAAAAAARQUUUUUEUUUUUUUR 77777777\n\n\n\n\n ");

                                HashMap parametresUtilisateur = new HashMap();
                                parametresUtilisateur.put("OBJET",RECUPERER);
                                parametresUtilisateur.put("nom", jObject.getString("nom"));
                                parametresUtilisateur.put("prenom", jObject.getString("prenom"));
                                parametresUtilisateur.put("pseudo", jObject.getString("pseudo"));
                                parametresUtilisateur.put("adresse", jObject.getString("adresse"));
                                parametresUtilisateur.put("ville", jObject.getString("ville"));
                                parametresUtilisateur.put("date_de_naissance", jObject.getString("date_de_naissance"));
                                parametresUtilisateur.put("email", jObject.getString("email"));
                                parametresUtilisateur.put("tel", jObject.getString("tel"));
                                parametresUtilisateur.put("sexe", jObject.getString("sexe"));
                                connection.disconnect();
                                System.out.println("\n\n\n\n\n  MMMMMMAAAAAAARQUUUUUEUUUUUUUR 8888888\n\n\n\n\n ");
                                return parametresUtilisateur; // On retourne true ou false


                            } catch (JSONException e) {
                                Log.e("log_tag", "Error parsing data " + e.toString());
                                System.out.println("\n\n\n\n\n  MMMMMMAAAAAAARQUUUUUEUUUUUUUR JSOOOON\n\n\n\n\n ");
                            }

                            ///////////////// Code permettant de vérifier la connexion avecle server////////////////
                  /*      if (connection.getResponseCode() == 200) {
                            return   String.valueOf(connection.getResponseCode()) + " "+ connection.getResponseMessage();
                        }

                        return    String.valueOf(connection.getResponseCode()) + " "+ connection.getResponseMessage();
                    */

                        }



                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (ProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
            System.out.println("\n\n\n\n\n  MMMMMMAAAAAAARQUUUUUEUUUUUUUR 999999\n\n\n\n\n ");
            return null;


        }

        @Override // La troisème méthode qui s'execute en dernier
        // String th, est la valeur que nous a retournee doInBackground
        protected void onPostExecute(HashMap th) {
            Toast.makeText(getBaseContext()," value : "+((String) th.get("nom")).equals("null"), Toast.LENGTH_LONG).show();
        if(th!=null)
        {


            if(th.get("OBJET").equals(RECUPERER))
            {

                if(th.get("nom")!=null|| !((String) th.get("nom")).isEmpty() ||((String) th.get("nom")).equals("null")!=true )
                {
                    nom=(EditText) findViewById(R.id.lastname_p);
                    nom.setHint((String) th.get("nom"));

                }
                if(!th.get("prenom").equals("") || th.get("prenom")!=null || th.get("prenom").equals("null")!=true )
                {
                    prenom=(EditText) findViewById(R.id.firstname_p);
                    prenom.setHint((String) th.get("prenom"));
                }
                if(!th.get("pseudo").equals("") || th.get("pseudo")!=null || th.get("pseudo").equals("null")!=true )
                {
                    pseudo=(EditText) findViewById(R.id.pseudo_p);
                    pseudo.setHint((String) th.get("pseudo"));
                }
                if(!th.get("adresse").equals("") || th.get("adresse")!=null || th.get("adresse").equals("null")!=true)
                {
                    adresse=(EditText) findViewById(R.id.adresse_p);
                    adresse.setHint((String) th.get("adresse"));
                }
                if(!th.get("ville").equals("") || th.get("ville")!=null|| th.get("ville").equals("null")!=true )
                {
                    ville=(EditText) findViewById(R.id.ville_p);
                    ville.setHint((String) th.get("ville"));
                }
                if(!th.get("date_de_naissance").equals("") || th.get("date_de_naissance")!=null || th.get("date_de_naissance").equals("null")!=true)
                {
                    date_de_naissance=(Button) findViewById(R.id.naissance_p);
                    date_de_naissance.setHint((String) th.get("date_de_naissance"));
                }
                if(!th.get("email").equals("") || th.get("email")!=null || th.get("email").equals("null")!=true)
                {
                    email=(EditText) findViewById(R.id.email_p);
                    email.setHint((String) th.get("email"));
                }
                if(!th.get("tel").equals("") || th.get("tel")!=null || th.get("tel").equals("null")!=true)
                {
                    tel=(EditText) findViewById(R.id.tel_p);
                    tel.setHint((String) th.get("tel"));
                }
                if(!th.get("sexe").equals("") || th.get("sexe")!=null || th.get("sexe").equals("null")!=true)
                {

                    if(th.get("sexe").equals("homme"))
                    {
                        sexe=(CheckBox) findViewById(R.id.male_p);
                        sexe.setChecked(true);
                    }
                    else if(th.get("sexe").equals("femme"))
                    {
                        sexe=(CheckBox) findViewById(R.id.female_p);
                        sexe.setChecked(true);
                    }

                }

            }
        }
        else
        {
            Toast.makeText(getBaseContext(),"Erreur de connexion à vos données", Toast.LENGTH_LONG).show();
        }


        //



        }

    }

}
