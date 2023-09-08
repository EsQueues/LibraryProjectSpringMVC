package structure.kz.models;

import java.util.List;

public class Person {
    private int id;
    public void setId(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }

    private String fullName;

    public Person() {
    }


    public Person(int id,String fullName, int year) {
        this.id = id;
        this.fullName = fullName;
        this.year = year;
    }

    private int year;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

