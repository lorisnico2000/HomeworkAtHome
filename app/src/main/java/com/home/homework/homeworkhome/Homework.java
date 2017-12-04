package com.home.homework.homeworkhome;

/**
 * Created by loris on 04.12.2017.
 */

public class Homework {
    public Integer id;
    public String name;
    public Subject subject;

    public Homework(String n, Subject s){
        this.id = null;
        this.name = n;
        this.subject = s;
    }

    public void save(){
        if(this.id == null){//CREATE

        }else{//UPDATE

        }
    }
    public void delete(){

    }
}
