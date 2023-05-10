package com.example.proekt;

public class TeaSavedSettings implements Cloneable{

    public String tea_variety = "Черный";
    public Double tea_count = 0.5;
    public Integer sugar_count = 1;
    public Integer tea_temperature = 100;
    public String title = "";

    public void setTea_variety(String tea_variety) {
        this.tea_variety = tea_variety;
    }
    public void addTea_count() {
        this.tea_count = Math.min(this.tea_count+0.5,2);
    }
    public void removeTea_count() {
        this.tea_count = Math.max(this.tea_count-0.5,0);
    }
    public void addSugar_count() {
        this.sugar_count = Math.min(this.sugar_count+1,4);
    }
    public void removeSugar_count() {
        this.sugar_count = Math.max(this.sugar_count-1,0);
    }
    public void addTeaTemp() {this.tea_temperature = Math.min(this.tea_temperature+5,100);}
    public void removeTeaTemp() {this.tea_temperature = Math.max(this.tea_temperature-5,50);}
    public void setTitle(String title) {this.title = title;}
    @Override
    public Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
