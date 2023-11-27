import at.ac.tuwien.qs.movierental.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestAgeRating {

    private final RentalController rentalController = new RentalController();

    @Test
    public void testRentalNull_shouldThrowIllegalArgumentException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, ()->rentalController.validateRental(null));
        assertEquals("Rental has to be not null", illegalArgumentException.getMessage());
    }

    @Test
    public void testCustomerNull_shouldThrowValidationException() {
        Movie movie = new Movie();
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(null);
        rental.setDateLent(LocalDate.now());
        ValidationException validationException = assertThrows(ValidationException.class, ()->rentalController.validateRental(rental));
        assertEquals("Kunde muss gesetzt sein.", validationException.getMessage());
    }

    @Test
    public void testDateLentNull_shouldThrowValidationException() {
        Movie movie = new Movie();
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.MAX);
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(null);
        ValidationException validationException = assertThrows(ValidationException.class, ()->rentalController.validateRental(rental));
        assertEquals("Verleihtag muss gesetzt sein.", validationException.getMessage());
    }

    @Test
    public void testMovieNull_shouldThrowValidationException() {
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.MAX);
        Rental rental = new Rental();
        rental.setMovie(null);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        ValidationException validationException = assertThrows(ValidationException.class, ()->rentalController.validateRental(rental));
        assertEquals("Film muss gesetzt sein.", validationException.getMessage());
    }

    @Test
    public void testInvalidAge_shouldThrowValidationException() {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.RATED_21);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().minusYears(20));
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        ValidationException validationException = assertThrows(ValidationException.class, ()->rentalController.validateRental(rental));
        assertEquals("Der Kunde erfüllt die Altersfreigabe nicht!", validationException.getMessage().substring(0, 43));
    }

    @Test
    public void testNegativeAge_shouldThrowIllegalArgumentException() {
        Movie movie = new Movie();
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().plusYears(1));
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, ()->rentalController.validateRental(rental));
        assertEquals("Das Alter des Kunden darf nicht negativ sein.", illegalArgumentException.getMessage());
    }

    @Test
    public void testNegativeMaxAge_shouldThrowIllegalArgumentException() {
        Movie movie = new Movie();
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.MAX);
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, ()->rentalController.validateRental(rental));
        assertEquals("Das Alter des Kunden darf nicht negativ sein.", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 6, 12, 16, 18, 21})
    public void testFSK0AndValidAges_shouldNotThrowIllegalArgumentExceptionOrValidationException(int age) {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.FSK_0);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().minusYears(age));
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertDoesNotThrow(()->rentalController.validateRental(rental));
    }

    @Test
    public void testFSK0AndPositiveMaxAge_shouldNotThrowIllegalArgumentExceptionOrValidationException() {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.FSK_0);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.MIN);
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertDoesNotThrow(()->rentalController.validateRental(rental));
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 12, 16, 18, 21})
    public void testFSK6AndValidAges_shouldNotThrowIllegalArgumentExceptionOrValidationException(int age) {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.FSK_6);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().minusYears(age));
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertDoesNotThrow(()->rentalController.validateRental(rental));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 5})
    public void testFSK6AndInvalidAges_shouldThrowValidationException(int age) {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.FSK_6);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().minusYears(age));
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertThrows(ValidationException.class, ()->rentalController.validateRental(rental));
    }

    @Test
    public void testFSK6AndPositiveMaxAge_shouldNotThrowIllegalArgumentExceptionOrValidationException() {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.FSK_6);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.MIN);
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertDoesNotThrow(()->rentalController.validateRental(rental));
    }

    @ParameterizedTest
    @ValueSource(ints = {12, 16, 18, 21})
    public void testFSK12AndValidAges_shouldNotThrowIllegalArgumentExceptionOrValidationException(int age) {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.FSK_12);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().minusYears(age));
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertDoesNotThrow(()->rentalController.validateRental(rental));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 11})
    public void testFSK12AndInvalidAges_shouldThrowValidationException(int age) {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.FSK_12);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().minusYears(age));
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertThrows(ValidationException.class, ()->rentalController.validateRental(rental));
    }

    @Test
    public void testFSK12AndPositiveMaxAge_shouldNotThrowIllegalArgumentExceptionOrValidationException() {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.FSK_12);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.MIN);
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertDoesNotThrow(()->rentalController.validateRental(rental));
    }

    @ParameterizedTest
    @ValueSource(ints = {16, 18, 21})
    public void testFSK16AndValidAges_shouldNotThrowIllegalArgumentExceptionOrValidationException(int age) {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.FSK_16);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().minusYears(age));
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertDoesNotThrow(()->rentalController.validateRental(rental));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 6, 15})
    public void testFSK16AndInvalidAges_shouldThrowValidationException(int age) {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.FSK_16);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().minusYears(age));
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertThrows(ValidationException.class, ()->rentalController.validateRental(rental));
    }

    @Test
    public void testFSK16AndPositiveMaxAge_shouldNotThrowIllegalArgumentExceptionOrValidationException() {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.FSK_16);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.MIN);
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertDoesNotThrow(()->rentalController.validateRental(rental));
    }

    @ParameterizedTest
    @ValueSource(ints = {18, 21})
    public void testFSK18AndValidAges_shouldNotThrowIllegalArgumentExceptionOrValidationException(int age) {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.FSK_18);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().minusYears(age));
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertDoesNotThrow(()->rentalController.validateRental(rental));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 6, 12, 17})
    public void testFSK18AndInvalidAges_shouldThrowValidationException(int age) {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.FSK_18);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().minusYears(age));
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertThrows(ValidationException.class, ()->rentalController.validateRental(rental));
    }

    @Test
    public void testFSK18AndPositiveMaxAge_shouldNotThrowIllegalArgumentExceptionOrValidationException() {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.FSK_18);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.MIN);
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertDoesNotThrow(()->rentalController.validateRental(rental));
    }

    @Test
    public void testRATED21AndAge21_shouldNotThrowIllegalArgumentExceptionOrValidationException() {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.RATED_21);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().minusYears(21));
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertDoesNotThrow(()->rentalController.validateRental(rental));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 6, 12, 16, 20})
    public void testRATED21AndInvalidAges_shouldThrowValidationException(int age) {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.RATED_21);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().minusYears(age));
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertThrows(ValidationException.class, ()->rentalController.validateRental(rental));
    }

    @Test
    public void testRATED21AndPositiveMaxAge_shouldNotThrowIllegalArgumentExceptionOrValidationException() {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.RATED_21);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.MIN);
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertDoesNotThrow(()->rentalController.validateRental(rental));
    }

    @Test
    public void testNotRatedAndAge21_shouldNotThrowIllegalArgumentExceptionOrValidationException() {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.NOT_RATED);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().minusYears(21));
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertDoesNotThrow(()->rentalController.validateRental(rental));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 6, 12, 16, 20})
    public void testNotRatedAndInvalidAges_shouldThrowValidationException(int age) {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.NOT_RATED);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().minusYears(age));
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertThrows(ValidationException.class, ()->rentalController.validateRental(rental));
    }

    @Test
    public void testNotRatedAndPositiveMaxAge_shouldNotThrowIllegalArgumentExceptionOrValidationException() {
        Movie movie = new Movie();
        movie.setAgeRating(AgeRating.NOT_RATED);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.MIN);
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertDoesNotThrow(()->rentalController.validateRental(rental));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 6, 12, 16, 20, 22})
    public void testRatingNullAndAges_shouldThrowValidationException(int age) {
        Movie movie = new Movie();
        movie.setAgeRating(null);
        Customer customer = new Customer();
        customer.setBirthday(LocalDate.now().minusYears(age));
        Rental rental = new Rental();
        rental.setMovie(movie);
        rental.setCustomer(customer);
        rental.setDateLent(LocalDate.now());
        assertThrows(ValidationException.class, ()->rentalController.validateRental(rental));
    }
}
