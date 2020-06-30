package com.example.hw1;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieRatingRepository extends CrudRepository<MovieRating, Long> {
    List<MovieRating> findByOrderByDateDesc();
}
