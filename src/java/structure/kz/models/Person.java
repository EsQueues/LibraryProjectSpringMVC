package structure.kz.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public class Person {
    private int id;
    public void setId(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }

    @NotEmpty(message = "You did not entered name, there is empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z]* [A-Z][a-zA-Z]*$", message = "Your first name and last name's first letter have to be capitalized, e.g., 'Qusain Sayat'")
    private String fullName;

    public Person() {

    }


    public Person(int id,String fullName, int year) {
        this.id = id;
        this.fullName = fullName;
        this.year = year;
    }

    @Min(value = 1900, message = "Ohh man are you alive really?")
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

