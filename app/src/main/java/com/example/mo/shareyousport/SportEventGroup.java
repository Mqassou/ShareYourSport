package com.example.mo.shareyousport;

import com.google.android.gms.maps.model.LatLng;

import java.util.Iterator;
import java.util.Vector;

/**
 * Created by Max on 20/03/2016.
 */
public class SportEventGroup implements Iterable<SportEvent>{
    Vector<SportEvent> allSports = new Vector<SportEvent>();

    public void getAllSportEvent(){
        //TODO
    }

    public void addSports(SportEvent newEvent){
        allSports.add(newEvent);
    }

    @Override
    public Iterator<SportEvent> iterator() {
        return allSports.iterator();
    }



    public SportEvent findSportByCoord(LatLng Coord){
        Iterator<SportEvent> it = allSports.iterator();
        SportEvent finalEvent = new SportEvent();
        SportEvent interEvent = new SportEvent();
        while(it.hasNext()){
            interEvent = it.next();
            if(interEvent.getCoord().equals(Coord)){
                finalEvent = interEvent;
            }


        }
        return finalEvent;
    }
}
