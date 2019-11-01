package pl.mirek.ksb2.moviecollector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import pl.mirek.ksb2.moviecollector.aspect.MovieSendMail;
import pl.mirek.ksb2.moviecollector.mailservice.MailService;
import pl.mirek.ksb2.moviecollector.model.Movie;
import pl.mirek.ksb2.moviecollector.repository.MovieRepository;

import java.util.List;

@Service
public class MovieService {

    private MovieRepository repository;
    private MailService mailService;
    private Environment env;

    @Autowired
    public MovieService(MovieRepository repository, MailService mailService, Environment env) {
        this.repository = repository;
        this.mailService = mailService;
        this.env = env;
    }

    public List<Movie> getAll() {
        return repository.getMovies();
    }

    public Movie getLast() {
        return repository.getLast();
    }

    @MovieSendMail
    public void addMovie(Movie movie) {
        repository.addMovie(movie);
    }
}
