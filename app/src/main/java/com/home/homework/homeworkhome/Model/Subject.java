package com.home.homework.homeworkhome.Model;

/**
 * Created by loris on 04.12.2017.
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
