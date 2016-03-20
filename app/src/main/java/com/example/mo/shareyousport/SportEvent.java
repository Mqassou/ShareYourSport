package com.example.mo.shareyousport;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Max on 18/03/2016.
 */
public class SportEvent {
    private int id;
    private String name;
    private String adress;
    private LatLng coord;
    private int playersNeeded;
    private int playerIn;
    private boolean equipment;

    public SportEvent(){
        id = 0;
        name ="Undefined name";
        adress = "Undefined adress";
        coord = null;
        playersNeeded = 0;
        playerIn = 0;
        equipment = false;

    }

    public SportEvent(int _id, String _name, String _adress, LatLng _coord, int _playersNeeded, int _playerIn, boolean _equipment){
        id = _id;
        name = _name;
        adress = _adress;
        coord = _coord;
        playersNeeded = _playersNeeded;
        playerIn = _playerIn;
        equipment = _equipment;
    }

    public String getAdress() {
        return adress;
    }

    public String getName() {
        return name;
    }

    public LatLng getCoord() {
        return coord;
    }

    public int getPlayersNeeded() {
        return playersNeeded;
    }

    public int getPlayerIn() {
        return playerIn;
    }

    public boolean isEquipment() {
        return equipment;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setCoord(LatLng coord) {
        this.coord = coord;
    }

    public void setPlayersNeeded(int playersNeeded) {
        this.playersNeeded = playersNeeded;
    }

    public void setPlayerIn(int playerIn) {
        this.playerIn = playerIn;
    }

    public void setEquipment(boolean equipment) {
        this.equipment = equipment;
    }



    public int addPlayer(){
        if (playerIn<playersNeeded){
            playerIn++;
        }
        else{
            System.out.println("Evenement remplis");
        }
        return playerIn;
    }

}
