import at.ac.tuwien.qs.movierental.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestValidator {

    private final Validator validator = new Validator();

    @Test
    public void testMovieTitleSizeSmallerThan3_shouldReturnErrorMessage() {
        Movie movie = new Movie();
        movie.setTitle("a");
        movie.setSubtitle("Revenge");
        movie.setAgeRating(AgeRating.FSK_0);
        movie.setGenre(Genre.FANTASY);
        movie.setLanguage("Deutsch");
        movie.setRating(2f);
        movie.setYearPublished(Year.now());
        movie.setDirector("Director");
        movie.setStock(25);
        assertEquals("Der Filmtitel muss zwischen 3 und 250 Zeichen lang sein.\n", validator.validateMovie(movie));
    }

    @Test
    public void testCustomerFirstNameStartWithWhitespace_shouldReturnErrorMessage() {
        Customer customer = new Customer();
        customer.setFirstName(" Karl");
        customer.setLastName("Meier");
        customer.setEmail("karl.meier@gmail.com");
        customer.setBirthday(LocalDate.now());
        customer.setPatron(false);
        customer.setAddress("Teststraße 14");
        customer.setCity("Vienna");
        customer.setZipCode("1010");
        customer.setPhone("+43384957834957");
        assertEquals("Der Vorname darf nicht mit einem Leerzeichen beginnen oder enden.\n", validator.validateCustomer(customer));
    }

}
