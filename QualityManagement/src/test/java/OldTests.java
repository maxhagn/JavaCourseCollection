import at.ac.tuwien.qs.movierental.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


/**
 * These are some old tests. I refactored the name of the class.
 * If anyone has the time please refactor these tests.
 */
public class OldTests {

    private final RentalController rentalController = new RentalController();

    private Rental rental;

    @BeforeEach
    public void initEach() {
        rental = new Rental();
        rental.setMovie(null);
        rental.setMovie(null);
        rental.setDateLent(null);
    }

    @Test
    public void testValidMovieAndCustomerNull_shouldThrowValidationException() {
        rental.setMovie(new Movie());
        assertThrows(ValidationException.class, ()->rentalController.validateRental(rental));
    }

    @Test
    public void testMovieNullAndCustomerNull_shouldThrowValidationException() {
        assertThrows(ValidationException.class, ()->rentalController.validateRental(rental));
    }

    @Test
    public void testMovieNullAndValidCustomer_shouldThrowValidationException() {
        rental.setCustomer(new Customer());
        assertThrows(ValidationException.class, ()->rentalController.validateRental(rental));
    }

    @Test
    public void testInvalidMovieAndValidCustomerAndDateLendNull_shouldThrowValidationException() {
        rental.setMovie(new Movie());
        rental.setCustomer(new Customer());
        assertThrows(ValidationException.class, ()->rentalController.validateRental(rental));
    }

    @Test
    public void testInvalidMovieAndValidCustomerAndValidDateLent_shouldThrowValidationException() {
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().minusYears(12));
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.FSK_18);
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertThrows(ValidationException.class, ()->rentalController.validateRental(rental));
    }

    @Test
    public void testValidMovieAndValidCustomerAndDateLentNull_shouldThrowValidationException() {
        rental.setMovie(new Movie());
        rental.setCustomer(new Customer());
        assertThrows(ValidationException.class, ()->rentalController.validateRental(rental));
    }

    @Test
    public void testValidMovieAndValidCustomerAndValidDateLent_shouldNotThrowException() {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.FSK_0);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().minusYears(12));
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertDoesNotThrow(()->rentalController.validateRental(rental));
    }
}
