package pl.mirek.ksb2.moviecollector.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import pl.mirek.ksb2.moviecollector.mailservice.MailService;
import pl.mirek.ksb2.moviecollector.model.Email;
import pl.mirek.ksb2.moviecollector.model.Movie;
import pl.mirek.ksb2.moviecollector.service.MovieService;

@Component
@Aspect
public class MovieAspect {

    private Environment env;
    private MailService mailService;
    private MovieService movieService;

    @Autowired
    public MovieAspect(MailService mailService, Environment env, MovieService movieService) {
        this.mailService = mailService;
        this.env = env;
        this.movieService = movieService;
    }

    @After("@annotation(MovieSendMail)")
    private void sendMail() {
        System.out.println("--> Send mail here");
        // czy jest 'password' w konfiguracji
        if (env.getProperty("spring.mail.password").length() == 16) {
            Movie movie = movieService.getLast();
            Email.EmailBuilder builder = new Email.EmailBuilder();
            Email email = builder
                    .setEmailAddress(env.getProperty("moviecollector.email.recipient.address"))
                    .setSubject("Dodałeś, jakbyś nie wiedział, do kolekcji film: " + movie.getTitle())
                    .setContent(movie.toString() + "\n;-)")
                    .build();
            mailService.sendEmail(email);
            System.out.println("--> Wysłano wiadomość");
        } else {
            System.out.println("--> Nie wysłano wiadomości");
        }

    }

}
