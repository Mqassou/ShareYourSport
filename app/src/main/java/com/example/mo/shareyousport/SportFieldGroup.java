package com.example.mo.shareyousport;

import com.google.android.gms.maps.model.LatLng;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Max on 20/03/2016.
 * CLASSE SUPPOSER CONTENIR TOUS LES SPORTS
 *
 */
public class SportFieldGroup implements Iterable<SportField>{
    Vector<SportField> allSports = new Vector<SportField>();

    public void getAllSportEvent(){
        //TODO quand la BDD sera prête
    }

    public void addSports(SportField newSport){
        allSports.add(newSport);
    }

    @Override
    public Iterator<SportField> iterator() {
        return allSports.iterator();
    }



    public SportField findFieldByCoord(LatLng Coord){
        Iterator<SportField> it = allSports.iterator();
        SportField finalSport = new SportField();
        SportField interSport = new SportField();
        while(it.hasNext()){
            interSport = it.next();
            if(interSport.getCoord().equals(Coord)){
                finalSport = interSport;
            }


        }
        return finalSport;
    }
}
