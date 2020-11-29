package com.example.movie.service;

import com.example.movie.models.entity.Movie;
import com.example.movie.models.rest.MovieListRestResponse;
import com.example.movie.models.rest.MovieRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface MovieService{
    ResponseEntity<Movie> getMovieById(Long movieId);
    ResponseEntity<MovieListRestResponse> getMovies(Pageable pageable);
    ResponseEntity<Movie> createMovie(MovieRequest movieRequest);
    ResponseEntity<Movie> updateMovie(long movieId, MovieRequest movieRequest);
    ResponseEntity<?> deleteMovie(long movieId);
}
