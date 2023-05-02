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
    public void setInstance(ListSavedSettings instance){this.instance = instance;}
    public boolean addSavedSettings(TeaSavedSettings settings){
        if (isUnique(settings.title)) {
            settingsList.add(settings);
            mDatabase.child(id).setValue(instance);
            return true;
        }
        return false;
    }
    private boolean isUnique(String title){
        for (int i = 0; i < settingsList.size();i++){
            if (settingsList.get(i).title.equals(title)){
                return false;
            }
        }
        return true;
    }
    public boolean updateSavedSettings(int position, TeaSavedSettings settings){
        if (isUnique(settings.title)) {
            settingsList.remove(position);
            settingsList.add(position, settings);
            HashMap<String, Object> result = new HashMap<>();
            result.put(id, instance);
            mDatabase.updateChildren(result);
            return true;
        }
        return false;
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
                + ";" + Integer.toString(settings.tea_temperature);
        mDatabase.child("192-168-1-160").setValue(res);
        MyThread myThread = new MyThread();
        myThread.start();
}

class MyThread extends Thread {
    public void run() {
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        mDatabase.child("192-168-1-160").removeValue();
    }
    }
}

