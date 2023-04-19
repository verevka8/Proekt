package com.example.proekt;

public class TeaSavedSettings {

    public Boolean tea_variety = false;
    public Integer tea_count = 7;
    public Integer sugar_count = 1;
    public Integer tea_temperature = 100;
    public String title;

    public void setTea_variety(Boolean tea_variety) {
        this.tea_variety = tea_variety;
    }
    public void addTea_count() {
        this.tea_count = Math.min(this.tea_count+7,21);
    }
    public void removeTea_count() {
        this.tea_count = Math.max(this.tea_count-7,0);
    }
    public int getTea_count(){
        return this.tea_count;
    }

    public void addSugar_count() {
        this.sugar_count = Math.min(this.sugar_count+1,4);
    }
    public void removeSugar_count() {
        this.sugar_count = Math.max(this.sugar_count-1,0);
    }
    public int getSugar_count(){
        return this.sugar_count;
    }

    public void setTea_temperature(Integer tea_temperature) {this.tea_temperature = tea_temperature;}
    public void setTitle(String title) {
        this.title = title;
    }
}
