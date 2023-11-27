import at.ac.tuwien.qs.movierental.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestInvoiceService {

    private final SimpleInvoiceService simpleInvoiceService = new SimpleInvoiceService();


    @Test
    public void testVideopoints10_shouldReturnVideopoints10() {
        assertEquals(0, simpleInvoiceService.getUsedVideopoints(10));
    }

    @Test
    public void testVideopoints11_shouldReturnVideopoints10() {
        assertEquals(10, simpleInvoiceService.getUsedVideopoints(11));
    }

    @Test
    public void testVideopoints20_shouldReturnVideopoints10() {
        assertEquals(10, simpleInvoiceService.getUsedVideopoints(20));
    }

    @Test
    public void testVideopoints21_shouldReturnVideopoints20() {
        assertEquals(20, simpleInvoiceService.getUsedVideopoints(21));
    }

    @Test
    public void testVideopoints100000_shouldReturnVideopoints20() {
        assertEquals(20, simpleInvoiceService.getUsedVideopoints(100000));
    }

}
