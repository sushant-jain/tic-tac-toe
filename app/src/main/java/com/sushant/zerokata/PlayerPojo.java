package com.sushant.zerokata;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sushant on 05-08-2016.
 */
public class PlayerPojo {

    String email;
    int online;
    int engaged;
    int chance;
    int currentChance;
    String oppmail,oppUId;
    int row;
    int col;

    public PlayerPojo() {
    }

    public PlayerPojo(String email, int online,int engaged) {
        this.email = email;
        this.online = online;
        this.engaged=engaged;


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
        result.put("oppUid",oppUId);
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
}
