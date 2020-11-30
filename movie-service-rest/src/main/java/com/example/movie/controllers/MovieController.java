package com.example.movie.controllers;

import com.example.movie.models.entity.Movie;
import com.example.movie.models.rest.MovieListRestResponse;
import com.example.movie.models.rest.MovieRequest;
import com.example.movie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Tag(name = "Movie", description = "The movie API(s)")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping(value = "/movies/{movieId}")
    @Operation(summary = "Find movie by ID", description = "Returns a single movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "404", description = "Movie not found") })
    private ResponseEntity<Movie> getMovieById(
            @Parameter(description="Id of the movie to be obtained. Cannot be empty.", required=true)
            @PathVariable Long movieId) {
        return movieService.getMovieById(movieId);
    }

    @GetMapping(value = "/movies")
    @Operation(summary = "Get list of movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MovieListRestResponse.class))) })
    private ResponseEntity<MovieListRestResponse> getMovies(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "count", defaultValue = "10", required = false) int size) {
        return movieService.getMovies(page, size);
    }

    @PostMapping(value = "/movies", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Add a new movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movie created",
                    content = @Content(schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request, invalid input") })
    private ResponseEntity<Movie> createMovie(@Valid @RequestBody MovieRequest movieRequest) {
        return movieService.createMovie(movieRequest);
    }

    @PutMapping(value = "/movies/{movieId}", consumes = { MediaType.APPLICATION_JSON_VALUE })
    @Operation(summary = "Update if exist, delete if not exist")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie has been updated",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Movie.class))) })
    private ResponseEntity<Movie> updateMovie(
            @Parameter(description="Id of the movie to be updated. Cannot be empty.", required=true) @PathVariable Long movieId,
            @Valid @RequestBody MovieRequest movieRequest) {
        return movieService.updateMovie(movieId, movieRequest);
    }

    @DeleteMapping(value = "/movies/{movieId}")
    @Operation(summary = "Deletes a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful operation with no content") })
    private ResponseEntity deleteMovie(
            @Parameter(description="Id of the movie to be delete. Cannot be empty.", required=true)
            @PathVariable Long movieId) {
        return movieService.deleteMovie(movieId);
    }
}
