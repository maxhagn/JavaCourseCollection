package at.ac.tuwien.qs.movierental;

import java.time.LocalDate;
import java.util.List;

public interface PriceService {

    /**
     * Calculates the total price based on rental and returnDate
     *
     * @param rental which should be given back by customer
     * @param returnDate the date the customer returns the rental
     * @return the total price for the rental in cents
     */
     long calculatePriceForRental(Rental rental, LocalDate returnDate);

    /**
     * Calculates the total price in cents based on customer, returnedList and generationDate
     *
     * @param customer the customer who returns movies
     * @param returnedList a list containing all rentals that should be paid
     * @param generationDate the date the invoice was created
     * @return the total price for all rentals included in the invoice
     */
     long getTotalPriceInCents(Customer customer, List<Rental> returnedList, LocalDate generationDate);

}
