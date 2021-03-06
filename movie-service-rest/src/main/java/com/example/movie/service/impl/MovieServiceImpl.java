package com.example.movie.service.impl;

import com.example.movie.exception.ResourceNotFoundException;
import com.example.movie.models.entity.Movie;
import com.example.movie.models.rest.MovieListRestResponse;
import com.example.movie.models.rest.MovieRequest;
import com.example.movie.respositories.MovieRepository;
import com.example.movie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public ResponseEntity<Movie> getMovieById(Long movieId) {
        return this.movieRepository
                .findById(movieId)
                .map(ResponseEntity.ok()::body)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id = " + movieId));
    }

    @Override
    public ResponseEntity<MovieListRestResponse> getMovies(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        MovieListRestResponse movieListRestResponse = new MovieListRestResponse();
        Page<Movie> moviePage = movieRepository.findAll(pageable);
        List<Movie> movies = moviePage.getContent();

        movieListRestResponse.setPageNumber(moviePage.getNumber());
        movieListRestResponse.setPageSize(moviePage.getSize());
        movieListRestResponse.setRecordsFiltered(moviePage.getNumberOfElements());
        movieListRestResponse.setRecordsTotal((int) moviePage.getTotalElements());
        movieListRestResponse.setData(movies);

        return ResponseEntity.ok(movieListRestResponse);
    }

    @Override
    public ResponseEntity<Movie> createMovie(MovieRequest movieRequest) {
        Movie movie = new Movie(movieRequest);
        Movie savedMovie = movieRepository.save(movie);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedMovie.getId()).toUri();

        return ResponseEntity.created(location).body(savedMovie);
    }

    @Override
    public ResponseEntity<Movie> updateMovie(long movieId, MovieRequest movieRequest) {

        Optional<Movie> movieOptional = movieRepository.findById(movieId);

        if (!movieOptional.isPresent()){
            return createMovie(movieRequest);
        }

        Movie movie = movieOptional.get();
        movie.setTitle(movieRequest.getTitle());
        movie.setCategory(movieRequest.getCategory());
        movie.setRating(movieRequest.getRating());

        Movie updatedMovie = movieRepository.save(movie);

        return ResponseEntity
                .ok(this.movieRepository.save(updatedMovie));

    }

    @Override
    public ResponseEntity<?> deleteMovie(long movieId) {
        return this.movieRepository
                .findById(movieId)
                .map(movieFound -> {
                    this.movieRepository.delete(movieFound);
                    return ResponseEntity
                            .noContent()
                            .build();
                }).orElseThrow(() -> new ResourceNotFoundException("Movie not found with id = " + movieId));
    }
}
