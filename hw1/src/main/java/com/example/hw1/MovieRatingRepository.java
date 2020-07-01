package com.example.hw1;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRatingRepository extends CrudRepository<MovieRating, Long> {
    @Query("SELECT m FROM MovieRating m ORDER BY m.title, m.date DESC")
    List<MovieRating> findAllMovieRatingsOrderByTitleDateDesc();
}
