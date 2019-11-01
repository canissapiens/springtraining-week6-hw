package pl.mirek.ksb2.moviecollector.repository;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import pl.mirek.ksb2.moviecollector.model.Movie;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieRepository {
    private List<Movie> movies;

    public MovieRepository() {
        movies = new ArrayList<>();
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public Movie getLast() {
        if (movies.size() > 0) {
            return movies.get(movies.size() - 1);
        }
        return null;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    @EventListener(ApplicationReadyEvent.class)
    private void initMovies() {
        movies.add(new Movie("Potop", "1974", "Wilhelm Hollender"));
        movies.add(new Movie("Pralnia", "2019", "Steven Soderbergh"));
        movies.add(new Movie("Pod mocnym aniołem", "2014", "Jacek Rzehak"));
        movies.add(new Movie("Babel", "2006", "Ahmed Abounouom"));
    }
}
