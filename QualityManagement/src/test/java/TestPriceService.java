import at.ac.tuwien.qs.movierental.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestPriceService {

    private final PriceService simplePriceService = new SimplePriceService();

    @Test
    public void testMovieNormal_shouldReturnPrice100() {
        Rental rental = new Rental();
        Movie movie = new Movie();
        Customer customer = new Customer();
        movie.setGenre(Genre.NORMAL);
        movie.setPriceInCents(100);
        rental.setMovie(movie);
        rental.setDateLent(LocalDate.now());
        List<Rental> returnedList = List.of(rental);
        assertEquals(100, simplePriceService.getTotalPriceInCents(customer, returnedList, LocalDate.now()));
    }

    @Test
    public void testMovieKinder_shouldReturnPrice75() {
        Rental rental = new Rental();
        Movie movie = new Movie();
        Customer customer = new Customer();
        movie.setGenre(Genre.CHILDREN);
        movie.setPriceInCents(100);
        rental.setMovie(movie);
        rental.setDateLent(LocalDate.now());
        List<Rental> returnedList = List.of(rental);
        assertEquals(75, simplePriceService.getTotalPriceInCents(customer, returnedList, LocalDate.now()));
    }

    @Test
    public void testMovieKlassiker_shouldReturnPrice90() {
        Rental rental = new Rental();
        Movie movie = new Movie();
        Customer customer = new Customer();
        movie.setGenre(Genre.CLASSIC);
        movie.setPriceInCents(100);
        rental.setMovie(movie);
        rental.setDateLent(LocalDate.now());
        List<Rental> returnedList = List.of(rental);
        assertEquals(90, simplePriceService.getTotalPriceInCents(customer, returnedList, LocalDate.now()));
    }

    @Test
    public void testMovieHorror_shouldReturnPrice110() {
        Rental rental = new Rental();
        Movie movie = new Movie();
        Customer customer = new Customer();
        movie.setGenre(Genre.HORROR);
        movie.setPriceInCents(100);
        rental.setMovie(movie);
        rental.setDateLent(LocalDate.now());
        List<Rental> returnedList = List.of(rental);
        assertEquals(110, simplePriceService.getTotalPriceInCents(customer, returnedList, LocalDate.now()));
    }

    @Test
    public void testMovieSciFi_shouldReturnPrice115() {
        Rental rental = new Rental();
        Movie movie = new Movie();
        Customer customer = new Customer();
        movie.setGenre(Genre.SCI_FI);
        movie.setPriceInCents(100);
        rental.setMovie(movie);
        rental.setDateLent(LocalDate.now());
        List<Rental> returnedList = List.of(rental);
        assertEquals(115, simplePriceService.getTotalPriceInCents(customer, returnedList, LocalDate.now()));
    }

    @Test
    public void testMovieFantasy_shouldReturnPrice125() {
        Rental rental = new Rental();
        Movie movie = new Movie();
        Customer customer = new Customer();
        movie.setGenre(Genre.FANTASY);
        movie.setPriceInCents(100);
        rental.setMovie(movie);
        rental.setDateLent(LocalDate.now());
        List<Rental> returnedList = List.of(rental);
        assertEquals(125, simplePriceService.getTotalPriceInCents(customer, returnedList, LocalDate.now()));
    }
}
