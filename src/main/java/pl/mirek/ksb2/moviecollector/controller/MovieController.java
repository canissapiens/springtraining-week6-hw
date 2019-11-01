package pl.mirek.ksb2.moviecollector.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.mirek.ksb2.moviecollector.model.Movie;
import pl.mirek.ksb2.moviecollector.service.MovieService;

@Controller
// @RequestMapping(value = "/movies", method = {RequestMethod.GET, RequestMethod.POST})
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public String getCars(Model model) {
        model.addAttribute("movies", movieService.getAll());
        model.addAttribute("newMovie", new Movie("", "1901", ""));
        return "index";
    }

    @PostMapping("/movies")
    public String addMovie(@ModelAttribute Movie movie) {
        System.out.println(movie);
        if (Integer.valueOf(movie.getYear()).intValue() > 1900) {
            System.out.println("Add movie --> " + movie.getTitle());
            movieService.addMovie(movie);
        }
        return "redirect:/movies";
    }
}
