package com.example.mo.shareyousport;


import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
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

import java.util.Iterator;
import java.util.Vector;


/**
 * Created by Max on 13/03/2016.
 */




public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,OnInfoWindowClickListener,LocationListener {
    private SportEventGroup userEvents = new SportEventGroup();

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

        //Utilisé pour récupérer la position de l'utilisateur
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
        SportEvent intermUserEvent;
        mMap = googleMap;
        //Création d'un evenement test
        userEvents.addSports(new SportEvent(0, "Stade de Deuil", "6 Rue Jean Bouin, 95170 Deuil-la-Barre", new LatLng(48.968628, 2.3222098999999616), 11, 1, true));


        /* Test inutilisé désormais
        mMap.setInfoWindowAdapter(new MapsWindowInter(getLayoutInflater()));
         Add a marker in Sydney and move the camera
        LatLng SYDNEY = new LatLng(-34, 151);
        Marker sydney = mMap.addMarker(new MarkerOptions().position(SYDNEY);
        */


        //Méthode utilisé pour parcourir l'ensemble des évenements existants
        Iterator<SportEvent> it = userEvents.iterator();
        while (it.hasNext()) {

            intermUserEvent = it.next();

            addMarker(mMap, intermUserEvent.getCoord().latitude, intermUserEvent.getCoord().longitude, R.string.info_window_participants, intermUserEvent.getPlayerIn() + "/" + intermUserEvent.getPlayersNeeded(), R.string.info_window_equipments, R.string.info_window_equipmentsInfo, R.string.info_window_gps, R.string.info_window_gps, R.string.info_window_participants);
            // sydney.showInfoWindow();
        }


        /* Auparavant utilisé pour adapter la taille de l'infoWindow
        mMap.setInfoWindowAdapter(new MapsWindowInter(getLayoutInflater()));
        */
        // Setting a custom info window adapter for the google map
        googleMap.setInfoWindowAdapter(new InfoWindowAdapter() {

            // Use default InfoWindow frame
            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            // Defines the contents of the InfoWindow
            @Override
            public View getInfoContents(Marker arg0) {

                // Getting view from the layout file info_window_layout
                View v = getLayoutInflater().inflate(R.layout.info_window_inter, null);

                // Getting the position from the marker
                LatLng latLng = arg0.getPosition();

                SportEvent markerEvent = userEvents.findSportByCoord(latLng);

                TextView tvParticipants = ((TextView)v.findViewById(R.id.participants_number));
                tvParticipants.setText(markerEvent.getPlayerIn()+"/"+markerEvent.getPlayersNeeded());

                TextView tvEquipments = ((TextView)v.findViewById(R.id.equipments));
                tvEquipments.setText("Equipements à déterminer via script");

                TextView tvGps = ((TextView)v.findViewById(R.id.gps_coordonate));
                tvGps.setText("Coordonnée GPS: "+latLng.latitude+","+latLng.longitude);

                TextView tvName = ((TextView)v.findViewById(R.id.localName));
                tvName.setText(markerEvent.getName());

                TextView tvAddress = ((TextView)v.findViewById(R.id.address));
                tvAddress.setText(markerEvent.getAdress());

                // Returning the view containing InfoWindow contents
                return v;

            }
        });


        mMap.setOnInfoWindowClickListener(this);


        mMap.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, marker.getTitle(), Toast.LENGTH_LONG).show();
    }


    //Methode utilisée pour créer un marker avec les informations d'un objet SportEvent
    private void addMarker(GoogleMap map, double lat, double lon,
                           int title, String participants, int snippet, int equipments, int snippet2, int gps, int snippet3) {
        map.addMarker(new MarkerOptions().position(new LatLng(lat, lon)).icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
    }

    //Methode pour récupérer la position de l'utilisateur au cas où elle changerait pendant l'utilisation
    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
        mMap.animateCamera(cameraUpdate);
        //locationManager.removeUpdates(this);
    }


    //Les 3 méthodes qui suivent sont "redéfinis" pour permettre d'"implements" LocationListener
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

}
