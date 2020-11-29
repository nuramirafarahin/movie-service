package com.example.movie.models.rest;

import com.example.movie.models.entity.Movie;

import java.util.List;

public class MovieListRestResponse extends DataTableResponse{
    private List<Movie> data;

    public MovieListRestResponse() {
    }

    public MovieListRestResponse(Integer recordsTotal, Integer recordsFiltered, List<Movie> data) {
        super(recordsTotal, recordsFiltered);
        this.data = data;
    }

    public List<Movie> getData() {
        return this.data;
    }

    public void setData(List<Movie> data) {
        this.data = data;
    }

}
