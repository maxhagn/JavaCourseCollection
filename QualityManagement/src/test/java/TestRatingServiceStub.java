import at.ac.tuwien.qs.movierental.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestRatingServiceStub {

    private static RatingService ratingService;
    private static MovieDataServiceStub movieDataServiceStub;

    @BeforeAll
    public static void setUp() {
        movieDataServiceStub = new MovieDataServiceStub();
        ratingService = new SimpleRatingService(movieDataServiceStub);
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

    @Test
    public void testServiceNotAvailableException() throws NoMovieFoundException, TooManyMoviesFound, ServiceNotAvailableException {
        Movie movie = new Movie();
        movie.setTitle("Bambi");
        movieDataServiceStub.setServiceAvailable(false);
        assertThrows(ServiceNotAvailableException.class, () ->ratingService.loadRatingForMovie(null));
        movieDataServiceStub.setServiceAvailable(true);
    }

    @Test
    public void testRatingValueIsCorrect() throws NoMovieFoundException, TooManyMoviesFound, ServiceNotAvailableException {
        Movie movie = new Movie();
        movie.setTitle("Bambi");
        movie.setYearPublished(Year.of(1942));
        assertThat(ratingService.loadRatingForMovie(movie)).isEqualTo(3.5f);
    }
}
