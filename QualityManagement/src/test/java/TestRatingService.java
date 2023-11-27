import at.ac.tuwien.qs.movierental.Movie;
import at.ac.tuwien.qs.movierental.MovieDataService;
import at.ac.tuwien.qs.movierental.NoMovieFoundException;
import at.ac.tuwien.qs.movierental.RatingService;
import at.ac.tuwien.qs.movierental.ServiceNotAvailableException;
import at.ac.tuwien.qs.movierental.SimpleRatingService;
import at.ac.tuwien.qs.movierental.TheMovieDbMovieDataService;
import at.ac.tuwien.qs.movierental.TooManyMoviesFound;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRatingService {

    private static RatingService ratingService;

    @BeforeAll
    public static void setUp() {
        MovieDataService movieDataService = new TheMovieDbMovieDataService();
        ratingService = new SimpleRatingService(movieDataService);
    }

    @Test
    public void testTooManyMoviesFound() throws NoMovieFoundException, TooManyMoviesFound, ServiceNotAvailableException {
        Movie movie = new Movie();
        movie.setTitle("Bambi");
        assertThrows(TooManyMoviesFound.class, () -> ratingService.loadRatingForMovie(movie));
    }

    @Test
    public void testNoMovieFoundException() throws NoMovieFoundException, TooManyMoviesFound, ServiceNotAvailableException {
        Movie movie = new Movie();
        movie.setTitle("ABCDEFGHIJKLM");
        assertThrows(NoMovieFoundException.class, () -> ratingService.loadRatingForMovie(movie));
    }

    /* Remember to disconnect before running this test
    @Test
    public void testServiceNotAvailableException() throws NoMovieFoundException, TooManyMoviesFound, ServiceNotAvailableException {
        Movie movie = new Movie();
        movie.setTitle("Bambi");
        assertThrows(ServiceNotAvailableException.class, () ->ratingService.loadRatingForMovie(movie));
    }
    */

    // Remember to check TMDb if the rating value has changed when this test fails
    @Test
    public void testRatingValueIsCorrect() throws NoMovieFoundException, TooManyMoviesFound, ServiceNotAvailableException {
        Movie movie = new Movie();
        movie.setTitle("Bambi");
        movie.setYearPublished(Year.of(1942));
        assertThat(ratingService.loadRatingForMovie(movie)).isEqualTo(3.5f);
    }
}
