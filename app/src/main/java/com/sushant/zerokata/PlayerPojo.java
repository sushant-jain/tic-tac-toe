package com.sushant.zerokata;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sushant on 05-08-2016.
 */
public class PlayerPojo {

    String email;
    Integer online;
    Integer engaged;
    Integer chance;
    Integer currentChance;
    String oppmail,oppUId;
    Integer row;
    Integer col;

    public PlayerPojo() {
    }

    public PlayerPojo(String email, Integer online,Integer engaged) {
        this.email = email;
        this.online = online;
        this.engaged=engaged;
        this.chance=-1;
        this.currentChance=-1;
        oppmail="";
        oppUId="";
        row=-1;
        col=-1;


    }

    public PlayerPojo(String email, int online, int engaged, int chance, int currentChance, String oppmail, String oppUId, int row, int col) {
        this.email = email;
        this.online = online;
        this.engaged = engaged;
        this.chance = chance;
        this.currentChance = currentChance;
        this.oppmail = oppmail;
        this.oppUId = oppUId;
        this.row = row;
        this.col = col;
    }

    public Map<String,Object> toMap(){
        HashMap<String,Object> result=new HashMap<>();
        result.put("email",email);
        result.put("online",online);
        result.put("engaged",engaged);
        return result;
    }

    public  Map<String,Object> gameStartMapper(){
        HashMap<String,Object> result=new HashMap<>();
        result.put("email",email);
        result.put("online",online);
        result.put("engaged",engaged);
        result.put("chance",chance);
        result.put("currentChance",currentChance);
        result.put("oppmail",oppmail);
        result.put("oppUId",oppUId);
        result.put("row",row);
        result.put("col",col);
        return result;
    }

    public String getEmail() {
        return email;
    }

    public int getOnline() {
        return online;
    }

    public int getEngaged() {
        return engaged;
    }

    public int getChance() {
        return chance;
    }

    public int getCurrentChance() {
        return currentChance;
    }

    public String getOppmail() {
        return oppmail;
    }

    public String getOppUId() {
        return oppUId;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setCurrentChance(int currentChance) {
        this.currentChance = currentChance;
    }
}
