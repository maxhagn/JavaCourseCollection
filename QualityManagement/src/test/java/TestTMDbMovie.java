import at.ac.tuwien.qs.movierental.TMDbMovie;
import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTMDbMovie {

    @Test
    public void testSetAndGetOriginalTitle_shouldReturnOriginalTitle() {
        TMDbMovie tmDbMovie = new TMDbMovie();
        tmDbMovie.setOriginalTitle("Matrix III");
        assertEquals("Matrix III", tmDbMovie.getOriginalTitle());
    }

    @Test
    public void testSetAndGetPopularity_shouldReturnPopularity() {
        TMDbMovie tmDbMovie = new TMDbMovie();
        tmDbMovie.setPopularity(3.5f);
        assertEquals(3.5f, tmDbMovie.getPopularity());
    }

    @Test
    public void testSetAndGetVoteAverage_shouldReturnVoteAverage() {
        TMDbMovie tmDbMovie = new TMDbMovie();
        tmDbMovie.setVoteAverage(3.2f);
        assertEquals(3.2f, tmDbMovie.getVoteAverage());
    }

    @Test
    public void testSetAndGetVoteCount_shouldReturnVoteCount() {
        TMDbMovie tmDbMovie = new TMDbMovie();
        tmDbMovie.setVoteCount(879213);
        assertEquals(879213, tmDbMovie.getVoteCount());
    }

    @Test
    public void testSetAndGetReleaseDate_shouldReturnReleaseDate() {
        Date date = new Date();
        TMDbMovie tmDbMovie = new TMDbMovie();
        tmDbMovie.setReleaseDate(date);
        assertEquals(date, tmDbMovie.getReleaseDate());
    }

}