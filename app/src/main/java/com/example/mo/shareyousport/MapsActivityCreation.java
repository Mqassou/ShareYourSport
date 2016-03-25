package com.example.mo.shareyousport;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import android.util.Log;
import android.widget.Toast;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowCloseListener;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Iterator;
import java.util.Vector;

//import org.json.simple.JSONValue; Trouve run moyen de résoudre le soucis de dépendance
//import org.json.simple.JSONObject;


/**
 * Created by Max on 13/03/2016.
 * VOIR LES COMMENTAIRES DE MapsActivity SI BESOIN
 */




public class MapsActivityCreation extends FragmentActivity implements OnMapReadyCallback,OnInfoWindowClickListener,LocationListener,OnMarkerClickListener {
    private Vector<SportField> userFields = new Vector<SportField>();

    //mettre en place en methode pour récupérer tous les évenements en bdd ici

    private GoogleMap mMap;

    private LocationManager locationManager;

    private static final long MIN_TIME = 400;

    private static final float MIN_DISTANCE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this); //You can also use LocationManager.GPS_PROVIDER and LocationManager.PASSIVE_PROVIDER
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        SportField intermUserField;
        mMap = googleMap;
        userFields.add(new SportField(0, "Stade de Deuil", "6 Rue Jean Bouin, 95170"," Deuil La Barre", new LatLng(48.968628, 2.3222098999999616)));

        //Communication php a mettre en place
        // Classe qui contient 3 méthodes pour pouvoir effectuer une requete http
        // Ces requêtes nécessitent d'être effectuer dans un  thread
        /*private class PostClass extends AsyncTask<String, Void, String> {

           //final ProgressDialog progressDialog = new ProgressDialog(Login.this, R.style.AppTheme_Dark_Dialog); Inutile

            @Override // INUTILE ICI => Cette méthode s'execute en premier, elle ouvre une simple boite de dialogue
            protected void onPreExecute() {
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();
            }

            @Override//Cette méthode s'execute en deuxième
            protected String doInBackground(String... params) {

                ConnectivityManager check = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo[] info = check.getAllNetworkInfo();
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        String result;

                        try {
                            /////////////////////////////// REQUETE HTTP /////////////////////
                            URL url = new URL("http://humanapp.assos.efrei.fr/shareyoursport/script/shareyoursportcontroller.php");
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.setConnectTimeout(3000);
                            connection.setRequestMethod("POST");
                            connection.setDoInput(true);
                            connection.setDoOutput(true);

                            /// Mise en place des differents parametre necessaire ////

                            Uri.Builder builder = new Uri.Builder()
                                    .appendQueryParameter("OBJET", "login")
                                    .appendQueryParameter("EMAIL", params[0]) // params[0] entree en parametre dans la method login (cf:  requeteHttp.execute(email, password);)
                                    .appendQueryParameter("PASSWORD", params[1]); //idem
                            String query = builder.build().getEncodedQuery();

                            OutputStream os = connection.getOutputStream();
                            BufferedWriter writer = new BufferedWriter(
                                    new OutputStreamWriter(os, "UTF-8"));
                            writer.write(query);
                            writer.flush();
                            writer.close();
                            os.close();

                            connection.connect();

                            ///////////////////////////////BUFFERREADER/////////////////////

                            Reader reader =new InputStreamReader(connection.getInputStream(), "UTF-8");
                            char[] buffer = new char[50];
                            reader.read(buffer);  /// On recupere ce que nous a envoyés le fichier php
                            result = new String(buffer);
                            reader.close();


                            //////////////////////JSON////////////////////////////////////
                            try {

                                JSONObject object = new JSONObject(result);
                                connection.disconnect();
                                return object.getString("value"); // On retourne true ou false


                            } catch (JSONException e) {
                                Log.e("log_tag", "Error parsing data " + e.toString());

                            }

                            //////JSON ADAPTE A CE CAS/////



        LatLng coordInter;

        try {
            String myurl= "http://humanapp.assos.efrei.fr/shareyoursport/script/shareyoursportcontroller.php;

            URL url = new URL(myurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();

             // InputStreamOperations est une classe complémentaire:
             //Elle contient une méthode InputStreamToString.

        String result = InputStreamOperations.InputStreamToString(inputStream);

        // On récupère le JSON complet
        JSONObject jsonObject = new JSONObject(result);
        // On récupère le tableau d'objets qui nous concernent
        JSONArray array = new JSONArray(jsonObject.getString("terrain"));
        // Pour tous les objets on récupère les infos
        for (int i = 0; i < array.length(); i++) {
            // On récupère un objet JSON du tableau
            JSONObject obj = new JSONObject(array.getString(i));
            // On fait le lien Terrain - Objet JSON
            SportField sports = new SportField();
            sport.setName(obj.getString("name"));
            sport.setAdress(obj.getString("adress"));
            sport.setCity(obj.getString("city"));
            coordInter.latitude = obj.getDouble("latitude");
            coordInter.longitude = objgetDouble("longitude");
            personne.setCoord(coordInter);
            // On ajoute la personne à la liste
            userSports.add(sports);

        }

    } catch (Exception e) {
        e.printStackTrace();
    }




                            ///////////////// Code permettant de vérifier la connexion avecle server////////////////
                  /*      if (connection.getResponseCode() == 200) {
                            return   String.valueOf(connection.getResponseCode()) + " "+ connection.getResponseMessage();
                        }

                        return    String.valueOf(connection.getResponseCode()) + " "+ connection.getResponseMessage();
                    */
                        /*} catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (ProtocolException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

                return "false";

            }

            @Override // La troisème méthode qui s'execute en dernier
            // String th, est la valeur que nous a retournee doInBackground
            protected void onPostExecute(String th) {
                progressDialog.dismiss();

                if (Boolean.parseBoolean(th)) {
                    Intent myIntent = new Intent(Login.this, Creation.class);
                    startActivity(myIntent);


                } else {
                    Toast.makeText(getBaseContext(), "Erreur de connexion ", Toast.LENGTH_LONG).show();
                }



            }

        }

        */


        //mMap.setInfoWindowAdapter(new MapsWindowInter(getLayoutInflater()));
        // Add a marker in Sydney and move the camera
        LatLng SYDNEY = new LatLng(-34, 151);
        //Marker sydney = mMap.addMarker(new MarkerOptions().position(SYDNEY);
        Iterator<SportField> it = userFields.iterator();
        while (it.hasNext()) {

            intermUserField = (SportField) it.next();

            addMarker(mMap, intermUserField.getCoord().latitude, intermUserField.getCoord().longitude, intermUserField.getName());
            // sydney.showInfoWindow();
        }
        //mMap.setInfoWindowAdapter(new MapsWindowInter(getLayoutInflater()));
        //mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMarkerClickListener(this);

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_LONG).show();
    }

    private void addMarker(GoogleMap map, double lat, double lon,
                           String adress) {
        map.addMarker(new MarkerOptions().title(adress).position(new LatLng(lat, lon)).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        mMap.animateCamera(cameraUpdate);
        //locationManager.removeUpdates(this);
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }


    //Methode utilisé pour changer d'un activité tout en passant des information à la nouvelle activité lorsque l'utilisateur clique sur un marker
    @Override
    public boolean onMarkerClick(Marker marker) {
        // TODO Auto-generated method stub
        Intent intent = new Intent(this, Creation.class);
        intent.putExtra("donnée", marker.getTitle());
        intent.putExtra("latitude", marker.getPosition().latitude);
        intent.putExtra("longitude", marker.getPosition().longitude);
        startActivity(intent);
        return false;
    }



}
