package com.example.proekt;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListSavedSettings {
    private static ListSavedSettings instance;
    public ArrayList<TeaSavedSettings> settingsList;
    final private DatabaseReference mDatabase;
    public String id;
    private ListSavedSettings(){
        settingsList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public static ListSavedSettings getInstance(){
        if (instance == null){
            instance = new ListSavedSettings();
        }
        return instance;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setInstance(ListSavedSettings i){instance = i;}

    public void addSavedSettings(TeaSavedSettings settings){
        settingsList.add(settings);
        mDatabase.child(id).setValue(instance);
    }
    public void updateSavedSettings(int position, TeaSavedSettings new_settings){
        settingsList.remove(position);
        settingsList.add(position,new_settings);
        HashMap<String, Object> result = new HashMap<>();
        result.put(id,instance);
        mDatabase.updateChildren(result);
    }
    public void removeSavedSettings(int position){
        settingsList.remove(position);
        HashMap<String, Object> result = new HashMap<>();
        result.put(id,instance);
        mDatabase.updateChildren(result);
    }
    public void prepareTea(int posotion){
        TeaSavedSettings settings = settingsList.get(posotion);
        String res = Integer.toString(settings.tea_variety ? 1 : 0) + ";" + Integer.toString(settings.tea_count) + ";" + Integer.toString(settings.sugar_count)
                + ";" + "30"; //Integer.toString(settings.tea_temperature)
        mDatabase.child("192-168-1-160").setValue(res);
        long lastTime = System.currentTimeMillis();
        long currentTime = lastTime;
        while (currentTime - lastTime < 9000){
            currentTime = System.currentTimeMillis();
        }
        mDatabase.child("192-168-1-160").removeValue();
    }
}
