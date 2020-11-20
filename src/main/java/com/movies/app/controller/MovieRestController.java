package com.movies.app.controller;

import com.movies.app.dto.MovieDto;
import com.movies.app.model.Movie;
import com.movies.app.service.iface.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
@CrossOrigin("*")
public class MovieRestController {
    
    @Autowired
    private MovieService movieService;
    
    @GetMapping
    public List<MovieDto> getMovies() throws Exception {
        return this.movieService.getMoviesDto();
    }
    
    @PostMapping
    public void create(@RequestBody MovieDto movieDto) throws Exception {
        this.movieService.create(movieDto);
    }
    
    //http://localhost:5001/movie/delete/{id}
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id)  throws Exception {
        this.movieService.deleteById(id);
    }
    
    @PutMapping("/edit/{id}")
    public void edit(@PathVariable int id, 
            @RequestBody MovieDto movieDto) throws Exception {
        this.movieService.update(id, movieDto);
    }
}
