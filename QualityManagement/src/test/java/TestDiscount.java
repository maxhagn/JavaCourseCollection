import at.ac.tuwien.qs.movierental.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDiscount {

    private final float expectedDiscount_A1_B2 = 1.5f;
    private final float expectedDiscount_A1_B3 = 3.5f;
    private final float expectedDiscount_A1_B4 = 7.5f;
    private final float expectedDiscount_A2_B2 = 0.0f;
    private final float expectedDiscount_A2_B3 = 2.0f;
    private final float expectedDiscount_A2_B4 = 6.0f;

    @Test
    public void testPatronAndNegativeMaxVideopoints_shouldThrowIllegalArgumentException() {
        Customer patronCustomer = new Customer();
        patronCustomer.setPatron(true);
        patronCustomer.setVideopoints(Integer.MIN_VALUE);
        assertThrows(IllegalArgumentException.class, patronCustomer::calculateDiscount);
    }

    @Test
    public void testPatronAndNegativeVideopoints_shouldThrowIllegalArgumentException() {
        Customer patronCustomer = new Customer();
        patronCustomer.setPatron(true);
        patronCustomer.setVideopoints(-1);
        assertThrows(IllegalArgumentException.class, patronCustomer::calculateDiscount);
    }

    @Test
    public void testPatronAnd0Videopoints_shouldReturnDiscount1_5() {
        Customer patronCustomer = new Customer();
        patronCustomer.setPatron(true);
        patronCustomer.setVideopoints(0);
        assertEquals(expectedDiscount_A1_B2, patronCustomer.calculateDiscount());
    }

    @Test
    public void testPatronAnd9Videopoints_shouldReturnDiscount1_5() {
        Customer patronCustomer = new Customer();
        patronCustomer.setPatron(true);
        patronCustomer.setVideopoints(9);
        assertEquals(expectedDiscount_A1_B2, patronCustomer.calculateDiscount());
    }

    @Test
    public void testPatronAnd10Videopoints_shouldReturnDiscount3_5() {
        Customer patronCustomer = new Customer();
        patronCustomer.setPatron(true);
        patronCustomer.setVideopoints(10);
        assertEquals(expectedDiscount_A1_B3, patronCustomer.calculateDiscount());
    }

    @Test
    public void testPatronAnd19Videopoints_shouldReturnDiscount3_5() {
        Customer patronCustomer = new Customer();
        patronCustomer.setPatron(true);
        patronCustomer.setVideopoints(19);
        assertEquals(expectedDiscount_A1_B3, patronCustomer.calculateDiscount());
    }

    @Test
    public void testPatronAnd20Videopoints_shouldReturnDiscount7_5() {
        Customer patronCustomer = new Customer();
        patronCustomer.setPatron(true);
        patronCustomer.setVideopoints(20);
        assertEquals(expectedDiscount_A1_B4, patronCustomer.calculateDiscount());
    }

    @Test
    public void testPatronAndPositiveMaxVideopoints_shouldReturnDiscount7_5() {
        Customer patronCustomer = new Customer();
        patronCustomer.setPatron(true);
        patronCustomer.setVideopoints(Integer.MAX_VALUE);
        assertEquals(expectedDiscount_A1_B4, patronCustomer.calculateDiscount());
    }

    @Test
    public void testNotPatronAndNegativeMaxVideopoints_shouldThrowIllegalArgumentException() {
        Customer customer = new Customer();
        customer.setPatron(false);
        customer.setVideopoints(Integer.MIN_VALUE);
        assertThrows(IllegalArgumentException.class, customer::calculateDiscount);
    }
    @Test
    public void testNotPatronAndNegativeVideopoints_shouldThrowIllegalArgumentException() {
        Customer customer = new Customer();
        customer.setPatron(false);
        customer.setVideopoints(-1);
        assertThrows(IllegalArgumentException.class, customer::calculateDiscount);
    }

    @Test
    public void testNotPatronAnd0Videopoints_shouldReturnDiscount0() {
        Customer customer = new Customer();
        customer.setPatron(false);
        customer.setVideopoints(0);
        assertEquals(expectedDiscount_A2_B2, customer.calculateDiscount());
    }

    @Test
    public void testNotPatronAnd9Videopoints_shouldReturnDiscount0() {
        Customer customer = new Customer();
        customer.setPatron(false);
        customer.setVideopoints(9);
        assertEquals(expectedDiscount_A2_B2, customer.calculateDiscount());
    }

    @Test
    public void testNotPatronAnd10Videopoints_shouldReturnDiscount2() {
        Customer customer = new Customer();
        customer.setPatron(false);
        customer.setVideopoints(10);
        assertEquals(expectedDiscount_A2_B3, customer.calculateDiscount());
    }

    @Test
    public void testNotPatronAnd19Videopoints_shouldReturnDiscount2() {
        Customer customer = new Customer();
        customer.setPatron(false);
        customer.setVideopoints(19);
        assertEquals(expectedDiscount_A2_B3, customer.calculateDiscount());
    }

    @Test
    public void testNotPatronAnd20Videopoints_shouldReturnDiscount6() {
        Customer customer = new Customer();
        customer.setPatron(false);
        customer.setVideopoints(20);
        assertEquals(expectedDiscount_A2_B4, customer.calculateDiscount());
    }

    @Test
    public void testNotPatronAndPositiveMaxVideopoints_shouldReturnDiscount6() {
        Customer customer = new Customer();
        customer.setPatron(false);
        customer.setVideopoints(Integer.MAX_VALUE);
        assertEquals(expectedDiscount_A2_B4, customer.calculateDiscount());
    }


}
