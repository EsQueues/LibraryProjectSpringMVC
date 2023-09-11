package structure.kz.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class Book {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    @NotEmpty(message = "Name should not be empty")
    private String name;
    @NotEmpty(message = "Author's name should not be empty")
    @Pattern(regexp = "[A-Z]\\w+\\h[A-Z]\\w+",message = "Your first name and last name's first letter have to be BIG ex:'Qusain Sayat'")
    private String author;
    @Min(value = 500,message = "Year of book should not be less than 0")
    private int year;
    public Book(){

    }

    public Book(int id,String name, String author, int year) {
        this.id=id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
