import at.ac.tuwien.qs.movierental.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMoviePoints {

    private final int expectedVideopoints_A1_B2 = 1;
    private final int expectedVideopoints_A2_B2 = 0;

    @Test
    public void testPatronAndBillSumNegativeMax_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()->Customer.calculateVideopointsGain(Integer.MIN_VALUE, true));
    }

    @Test
    public void testPatronAndBillSumNegative_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()->Customer.calculateVideopointsGain(-1, true));
    }

    @Test
    public void testPatronAndBillSumZero_shouldReturnVideopoints1() {
        assertEquals(expectedVideopoints_A1_B2, Customer.calculateVideopointsGain(0, true));
    }

    @Test
    public void testPatronAndBillSum199_shouldReturnVideopoints1() {
        assertEquals(expectedVideopoints_A1_B2, Customer.calculateVideopointsGain(199, true));
    }

    @Test
    public void testPatronAndBillSum199_shouldReturnVideopoints2() {
        int expectedVideopoints_A1_B3_200 = (200 / 100) / 2 + 1;
        assertEquals(expectedVideopoints_A1_B3_200, Customer.calculateVideopointsGain(200, true));
    }

    @Test
    public void testPatronAndBillSum399_shouldReturnVideopoints2() {
        int expectedVideopoints_A1_B3_399 = (399 / 100) / 2 + 1;
        assertEquals(expectedVideopoints_A1_B3_399, Customer.calculateVideopointsGain(399, true));
    }

    @Test
    public void testPatronAndBillSumPositiveMax_shouldReturnVideopoints1030792152() {
        int expectedVideopoints_A1_B3_Max = (int) ((Long.MAX_VALUE / 100) / 2 + 1);
        assertEquals(expectedVideopoints_A1_B3_Max, Customer.calculateVideopointsGain(Long.MAX_VALUE, true));
    }

    @Test
    public void testNotPatronAndBillSumNegativeMax_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()->Customer.calculateVideopointsGain(Integer.MIN_VALUE, false));
    }

    @Test
    public void testNotPatronAndBillSumNegative_shouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()->Customer.calculateVideopointsGain(-1, false));
    }

    @Test
    public void testNotPatronAndBillSumZero_shouldReturnVideopoints1() {
        assertEquals(expectedVideopoints_A2_B2, Customer.calculateVideopointsGain(0, false));
    }

    @Test
    public void testNotPatronAndBillSum199_shouldReturnVideopoints1() {
        assertEquals(expectedVideopoints_A2_B2, Customer.calculateVideopointsGain(199, false));
    }

    @Test
    public void testNotPatronAndBillSum199_shouldReturnVideopoints2() {
        int expectedVideopoints_A2_B3_200 = (200 / 100) / 2;
        assertEquals(expectedVideopoints_A2_B3_200, Customer.calculateVideopointsGain(200, false));
    }

    @Test
    public void testNotPatronAndBillSum399_shouldReturnVideopoints2() {
        int expectedVideopoints_A2_B3_399 = (399 / 100) / 2;
        assertEquals(expectedVideopoints_A2_B3_399, Customer.calculateVideopointsGain(399, false));
    }

    @Test
    public void testNotPatronAndBillSumPositiveMax_shouldReturnVideopoints1030792152() {
        int expectedVideopoints_A2_B3_Max = (int) ((Long.MAX_VALUE / 100) / 2);
        assertEquals(expectedVideopoints_A2_B3_Max, Customer.calculateVideopointsGain(Long.MAX_VALUE, false));
    }
}
