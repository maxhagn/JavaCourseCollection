import at.ac.tuwien.qs.movierental.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TestRatingServiceMock {

    private RatingService ratingService;
    private MovieDataService movieDataServiceMock;

    @BeforeEach
    public void init() {
        movieDataServiceMock = Mockito.mock(MovieDataService.class);
        ratingService = new SimpleRatingService(movieDataServiceMock);
    }

    @Test
    public void testTooManyMoviesFound() throws NoMovieFoundException, TooManyMoviesFound, ServiceNotAvailableException {
        Movie movie = new Movie();
        movie.setTitle("Bambi");

        when(movieDataServiceMock.searchMovies(any())).thenReturn(List.of(movie, movie));
        assertThrows(TooManyMoviesFound.class, () -> ratingService.loadRatingForMovie(movie));
        verify(movieDataServiceMock, times(1)).searchMovies(movie);
    }

    @Test
    public void testNoMovieFoundException() throws NoMovieFoundException, TooManyMoviesFound, ServiceNotAvailableException {
        Movie movie = new Movie();
        movie.setTitle("ABCDEFGHIJKLM");

        when(movieDataServiceMock.searchMovies(any())).thenReturn(new ArrayList<>());
        assertThrows(NoMovieFoundException.class, ()->ratingService.loadRatingForMovie(movie));
        verify(movieDataServiceMock, times(1)).searchMovies(movie);
    }

    @Test
    public void testServiceNotAvailableException() throws NoMovieFoundException, TooManyMoviesFound, ServiceNotAvailableException {
        Movie movie = new Movie();
        movie.setTitle("Bambi");

        when(movieDataServiceMock.searchMovies(any())).thenThrow(ServiceNotAvailableException.class);
        assertThrows(ServiceNotAvailableException.class, () ->ratingService.loadRatingForMovie(movie));
        verify(movieDataServiceMock, times(1)).searchMovies(movie);
    }

    @Test
    public void testRatingValueIsCorrect() throws NoMovieFoundException, TooManyMoviesFound, ServiceNotAvailableException {
        Movie movie = new Movie();
        movie.setTitle("Bambi");
        movie.setYearPublished(Year.of(1942));
        movie.setRating(3.5f);

        when(movieDataServiceMock.searchMovies(any())).thenReturn(List.of(movie));
        assertThat(ratingService.loadRatingForMovie(movie)).isEqualTo(3.5f);
        verify(movieDataServiceMock, times(1)).searchMovies(movie);
    }
}
