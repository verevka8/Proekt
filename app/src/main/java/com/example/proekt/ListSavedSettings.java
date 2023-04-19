package com.example.proekt;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListSavedSettings {
    private static ListSavedSettings instance;
    public List<TeaSavedSettings> settingsList;
    private DatabaseReference mDatabase;
    private String id;
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

    public void addSavedSettings(TeaSavedSettings settings){
        settingsList.add(settings);
        mDatabase.child(id).setValue(instance);
    }
}
