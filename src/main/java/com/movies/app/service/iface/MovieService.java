package com.movies.app.service.iface;

import com.movies.app.dto.MovieDto;
import com.movies.app.model.Movie;
import java.util.List;

public interface MovieService {
    
    List<Movie> getMovies();
    
    List<MovieDto> getMoviesDto() throws Exception;
    
    void create(Movie movie);
    
    void create(MovieDto movie) throws Exception;
    
    void update(int id, Movie movie);
    
    void update(int id, MovieDto movieDto) throws Exception;
    
    void delete(int id);
    
    void deleteById(int id) throws Exception;
    
    Movie getMovieById(int id);
}
