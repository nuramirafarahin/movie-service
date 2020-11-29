package com.example.movie.models.rest;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MovieRequest {

    @NotNull
    @Size(min = 1, max = 100, message = "Title length should be between 1 and 100")
    private String title;

    @Size(min = 1, max = 100, message = "Category length should be between 1 and 100")
    private String category;

    @NotNull
    /*@Size(min = 0, max = 1, message = "Rating length should be between 1 and 100")*/
    private double rating;

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
