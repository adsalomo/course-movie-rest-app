package com.movies.app.controller;

import com.movies.app.model.Gender;
import com.movies.app.model.Movie;
import com.movies.app.service.iface.GenderService;
import com.movies.app.service.iface.MovieService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class Moviecontroller {

    @Autowired
    private MovieService movieService;
    @Autowired
    private GenderService genderService;

    @RequestMapping("")
    public String getMovies(Model model) {
        List<Movie> movies = movieService.getMovies();
        model.addAttribute("movies", movies);
        return "index";
    }

    @RequestMapping(path = {"/create", "/edit/{id}"})
    public String createOrEdit(Model model,
            @PathVariable("id") Optional<Integer> id) {
        List<Gender> genders = genderService.getAll();
        model.addAttribute("genders", genders);

        if (id.isPresent()) {
            Movie movie = movieService.getMovieById(id.get());
            model.addAttribute("movie", movie);
        } else {
            Movie movie = new Movie();
            model.addAttribute("movie", movie);
        }

        return "add-edit-movie";
    }

    @RequestMapping(path = {"/create-edit-movie"}, method = RequestMethod.POST)
    public String create(Movie movie) {
        if (movie.getId() > 0) {
            movieService.update(movie.getId(), movie);
        } else {
            movieService.create(movie);
        }

        return "redirect:/";
    }
    
    @RequestMapping(path = {"/delete-movie/{id}"})
    public String delete(@PathVariable("id") Integer id) {
        movieService.delete(id);
        return "redirect:/";
    }

}
