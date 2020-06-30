package com.example.demo2;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Person {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    private long id;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @Size(min=3, max=25)
    private String firstName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Size(min=3, max=25)
    private String lastName;

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    @NotNull
    @Size(min=3, max=25)
    private String favoriteFood;

    public Person() {
        firstName = null;
        lastName = null;
        favoriteFood = null;
    }

    public Person(String firstName, String lastName, String favoriteFood) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.favoriteFood = favoriteFood;
    }
}
