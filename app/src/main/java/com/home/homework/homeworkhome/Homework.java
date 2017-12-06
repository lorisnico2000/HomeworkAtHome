package com.home.homework.homeworkhome;

/**
 * Created by loris on 04.12.2017.
 */

public class Homework {

    public Integer id;
    public String name;
    public String desc;
    public Subject subject;

    public Homework(Integer i, String n, String d, Subject s){
        this.id = i;
        this.name = n;
        this.desc = d;
        this.subject = s;
    }

    public void save(){
        if(this.id == null){//CREATE
            Home.db.addHW(this);
        }else{//UPDATE
            Home.db.updateHW(this);
        }
    }

    public void delete(){

    }
}
