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
    public List<TeaSavedSettings> settingsList;
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
}
