package com.example.hw1;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class MovieRating {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(min=3, max=50)
    private String title;

    @NotNull
    @Min(1)
    @Max(5)
    private int rating = 1;

    @NotNull
    @Size(min=3, max=25)
    private String name;

    @CreationTimestamp
    public Date date;

    public MovieRating() {
        title = null;
        rating = 1;
        name = null;
    }

    public MovieRating(String title, int rating, String name) {
        this.title = title;
        this.rating = rating;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
