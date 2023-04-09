package com.example.proekt.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel{
    private MutableLiveData<Boolean> tea_variety = new MutableLiveData<>(false);
    private MutableLiveData<Integer> tea_count = new MutableLiveData<>(7);
    private MutableLiveData<Integer> sugar_count = new MutableLiveData<>(1);
    private MutableLiveData<Integer> tea_temperature = new MutableLiveData<>(100);


    public MutableLiveData<Boolean> getTea_variety() { return tea_variety;}
    public MutableLiveData<Integer> getTea_count() { return tea_count;}
    public MutableLiveData<Integer> getSugar_count() { return sugar_count;}
    public MutableLiveData<Integer> getTea_temperature() {return tea_temperature;}

    public void setTea_variety(boolean tea_variety) {
        this.tea_variety.setValue(tea_variety);
        // false - первый сорт, true - второй
    }

    public void setTea_count(int tea_count) {
        this.tea_count.setValue(Math.min(tea_count, 21));
    }

    public void setSugar_count(int sugar_count) {
        this.sugar_count.setValue(Math.min(sugar_count, 4));
    }

    public void setTea_temperature(int tea_temperature) {
        this.tea_temperature.setValue(Math.min(100,tea_temperature));
    }

}
