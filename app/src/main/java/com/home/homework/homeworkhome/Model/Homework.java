package com.home.homework.homeworkhome.Model;

import com.home.homework.homeworkhome.HomeActivity;

/**
 * Created by loris on 30.10.2017.
 *
 * Die Klasse Homework beinhaltet alle Daten einer Aufgabe.
 * Durch die Methoden lassen sich Aufgaben Erstellen, ändern und löschen.
 */

public class Homework {

    private Integer id;
    private String name;
    private String desc;
    private Subject subject;

    public Homework(Integer i, String n, String d, Subject s){
        this.id = i;
        this.name = n;
        this.desc = d;
        this.subject = s;
    }

    /**
     * Mit der Methode lässt sich sowohl speichern als auch ändern.
     * Wenn die Id == Null ist, wird eine neue Aufgabe erstellt. Ansonsten die Aufgabe nach ID geändert.
     */
    public void save(){
        if(this.id == null){//CREATE
            HomeActivity.db.addHW(this);
        }else{//UPDATE
            HomeActivity.db.updateHW(this);
        }
    }

    /**
     * Aufgabe löschen.
     */
    public void delete(){
        HomeActivity.db.deleteHW(this.id);
    }

//Getter, Setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

}
