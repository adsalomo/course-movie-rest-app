package com.movies.app.service.impl;

import com.movies.app.dto.MovieDto;
import com.movies.app.model.Gender;
import com.movies.app.model.Movie;
import com.movies.app.repository.GenderRepository;
import com.movies.app.repository.MovieRepository;
import com.movies.app.service.iface.MovieService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MovieServiceImpl implements MovieService {

    private final GenderRepository genderRepository;
    private final MovieRepository movieRepository;

    public MovieServiceImpl(GenderRepository genderRepository,
            MovieRepository movieRepository) {
        this.genderRepository = genderRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        return movies;
    }

    @Override
    public void create(Movie movie) {
        Optional<Gender> gender = genderRepository
                .findById(movie.getGenderId().getId());
        if (!gender.isPresent()) {
            System.out.println("No existe el genero");
        } else {
            movie.setCreatedAt(LocalDateTime.now());
            movie.setGenderId(gender.get());
            movieRepository.save(movie);
        }
    }

    @Override
    @Transactional
    public void update(int id, Movie movie) {
        Optional<Movie> existsMovie = movieRepository.findById(id);
        if (!existsMovie.isPresent()) {
            System.out.println("No existe la pelicula para actualizar");
        } else {
            Optional<Gender> gender = genderRepository
                    .findById(movie.getGenderId().getId());
            if (!gender.isPresent()) {
                System.out.println("No existe el genero");
            } else {
                existsMovie.get().setName(movie.getName());
                existsMovie.get().setDescription(movie.getDescription());
                existsMovie.get().setActors(movie.getActors());
                existsMovie.get().setImage(movie.getImage());
                existsMovie.get().setRating(movie.getRating());
                existsMovie.get().setReleaseDate(movie.getReleaseDate());
                existsMovie.get().setGenderId(gender.get());
                movieRepository.save(existsMovie.get());
            }
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        Optional<Movie> existsMovie = movieRepository.findById(id);
        if (!existsMovie.isPresent()) {
            System.out.println("No existe la pelicula para eliminar");
        } else {
            //genderRepository.delete(existsMovie);
            movieRepository.delete(existsMovie.get());
        }
    }

    @Override
    public Movie getMovieById(int id) {
        Optional<Movie> movie = movieRepository.findById(id);
        if (movie.isPresent()) {
            return movie.get();
        } else {
            return new Movie();
        }
    }

    @Override
    public List<MovieDto> getMoviesDto() throws Exception {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        if (movies.isEmpty()) {
            throw new Exception("No existen datos");
        }

        List<MovieDto> moviesDto = new ArrayList<>();

        for (Movie movie : movies) {
            MovieDto movieDto = new MovieDto();
            movieDto.setId(movie.getId());
            movieDto.setName(movie.getName());
            movieDto.setDescription(movie.getDescription());
            movieDto.setImage(movie.getImage());
            movieDto.setActors(movie.getActors());
            movieDto.setGenderId(movie.getGenderId().getId());
            movieDto.setGenderName(movie.getGenderId().getName());
            movieDto.setReleaseDate(movie.getReleaseDate());
            movieDto.setRating(movie.getRating());
            moviesDto.add(movieDto);
        }

        return moviesDto;
    }

    @Override
    public void create(MovieDto movieDto) throws Exception {
        Optional<Gender> gender = genderRepository
                .findById(movieDto.getGenderId());
        if (!gender.isPresent()) {
            throw new Exception("No existen datos");
        }
        
        Movie movie = new Movie();
        movie.setName(movieDto.getName());
        movie.setDescription(movieDto.getDescription());
        movie.setActors(movieDto.getActors());
        movie.setImage(movieDto.getImage());
        movie.setRating(movieDto.getRating());
        movie.setGenderId(gender.get());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setCreatedAt(LocalDateTime.now());
        
        this.movieRepository.save(movie);
    }
    
    @Override
    public void update(int id, MovieDto movieDto) throws Exception {
        Optional<Movie> existsMovie = movieRepository.findById(id);
        if (!existsMovie.isPresent()) {
            throw new Exception("No existen datos");
        }
        
        Optional<Gender> gender = genderRepository
                .findById(movieDto.getGenderId());
        if (!gender.isPresent()) {
            throw new Exception("No existen datos");
        }
        
        Movie movie = new Movie();
        movie.setId(existsMovie.get().getId());
        movie.setName(movieDto.getName());
        movie.setDescription(movieDto.getDescription());
        movie.setActors(movieDto.getActors());
        movie.setImage(movieDto.getImage());
        movie.setRating(movieDto.getRating());
        movie.setGenderId(gender.get());
        movie.setReleaseDate(movieDto.getReleaseDate());
        movie.setCreatedAt(existsMovie.get().getCreatedAt());
        
        this.movieRepository.save(movie);
    }
    
    @Override
    public void deleteById(int id) throws Exception {
        Optional<Movie> existsMovie = movieRepository.findById(id);
        if (!existsMovie.isPresent()) {
             throw new Exception("No existen datos");
        } else {
            //genderRepository.delete(existsMovie);
            movieRepository.delete(existsMovie.get());
        }
    }

}
