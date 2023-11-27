package at.ac.tuwien.qs.movierental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class SimplePriceService implements PriceService {

    @Override
    public long calculatePriceForRental(Rental rental, LocalDate returnDate) {
        float factor = rental.getMovie().getGenre().getPriceFactor();
        long days = ChronoUnit.DAYS.between(rental.getDateLent(), returnDate) + 1;
        long priceInCents = rental.getMovie().getPriceInCents();
        return (long) (priceInCents * days * factor);
    }

    @Override
    public long getTotalPriceInCents(Customer customer, List<Rental> returnedList, LocalDate generationDate) {
        long priceInCent = 0;
        for (Rental rental : returnedList) {
            priceInCent = calculatePriceForRental(rental, generationDate);
        }
        priceInCent = (long) (priceInCent - (priceInCent / 100 * customer.calculateDiscount()));
        return priceInCent;
    }
}
