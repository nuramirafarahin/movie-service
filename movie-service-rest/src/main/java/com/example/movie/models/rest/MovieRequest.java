package com.example.movie.models.rest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MovieRequest {

    @NotNull
    @Size(min = 1, max = 100, message = "Title length should be between 1 and 100")
    private String title;

    @Size(min = 1, max = 100, message = "Category length should be between 1 and 100")
    private String category;

    @NotNull
    @Min(1)
    @Max(5)
    private int rating;

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
}
