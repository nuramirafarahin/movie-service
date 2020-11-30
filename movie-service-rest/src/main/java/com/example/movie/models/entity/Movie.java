package com.example.movie.models.entity;

import com.example.movie.models.rest.MovieRequest;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Size(min = 1, max = 100, message = "Title length should be between 1 and 100")
    private String title;

    @Size(min = 1, max = 100, message = "Category length should be between 1 and 100")
    private String category;

    @NotNull
    @Min(1)
    @Max(5)
    private int rating;

    public Movie() {
    }

    public Movie(MovieRequest movieRequest) {
        this.title = movieRequest.getTitle();
        this.category = movieRequest.getCategory();
        this.rating = movieRequest.getRating();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void mergeMovie(long movieId, Movie movie) {
        this.id = movieId;
        this.setTitle(movie.getTitle());
        this.setCategory(movie.getCategory());
        this.setRating(movie.getRating());
    }
}
