package com.home.homework.homeworkhome.Model;

import com.home.homework.homeworkhome.HomeActivity;

/**
 * Created by loris on 04.12.2017.
 *
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

    public void save(){
        if(this.id == null){//CREATE
            HomeActivity.db.addHW(this);
        }else{//UPDATE
            HomeActivity.db.updateHW(this);
        }
    }

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
