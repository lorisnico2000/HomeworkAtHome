package com.home.homework.homeworkhome.Model;

/**
 * Created by loris on 30.10.2017.
 *
 * Die Klasse Subject beinhaltet lediglich die Daten einer Hausaufgabe.
 * Die Klasse wird vom SubjectAdapter benutzt, um die Aufgaben in einem Dropdown anzuzeigen.
 */

public class Subject {

    private Integer id;
    private String name;

    public Subject(Integer i, String n){
        this.id = i;
        this.name = n;
    }

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

}
