import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CodeCoverage_11808237_Hagn_Maximilian {

    public List<BigInteger> FibonacciSeries(BigInteger from, BigInteger to) {

        List<BigInteger> result = new ArrayList<>();
        BigInteger previous = BigInteger.ONE;
        BigInteger next = BigInteger.ONE;
        BigInteger sum;

        if (from.compareTo(to) > 0) {
            return result;
        }

        if (BigInteger.ZERO.compareTo(from) >= 0 && BigInteger.ZERO.compareTo(to) <= 0) {
            result.add(BigInteger.ZERO);
        }

        if (BigInteger.ONE.compareTo(from) >= 0 && BigInteger.ONE.compareTo(to) <= 0) {
            result.add(BigInteger.ONE);
            result.add(BigInteger.ONE);
        }

        if (to.compareTo(BigInteger.ONE) > 0) {
            for (BigInteger i = BigInteger.ONE; ; i = i.add(BigInteger.ONE)) {

                sum = previous.add(next);

                if (sum.compareTo(to) > 0) {
                    return result;
                }

                previous = next;
                next = sum;

                result.add(sum);

            }
        }

        return result;
    }

    /**
     * Testfall für die Anweisungsüberdeckung. Alle Anweisungen sollen zumindest einmal ausgeführt werden.
     * Dieser Testfall überprüft alle Anweisungen, außer das return Statement in der ersten Abfrage.
     */
    @Test
    public void TestC0StatementCoverage_ShouldReturnListWith0_1_1_2_3_5_8() {
        List<BigInteger> expectedList = List.of(BigInteger.ZERO, BigInteger.ONE, BigInteger.ONE, BigInteger.TWO, BigInteger.valueOf(3), BigInteger.valueOf(5), BigInteger.valueOf(8));
        assertEquals(expectedList, FibonacciSeries(BigInteger.ZERO, BigInteger.TEN));
    }

    /**
     * Testfall für die Anweisungsüberdeckung. Alle Anweisungen sollen zumindest einmal ausgeführt werden.
     * Da die erste Abfrage auch eine Anweisung enthält, muss auch dieser Pfad in der Anweisungsüberdeckung geprüft werden.
     **/
    @Test
    public void TestC0StatementCoverage_ShouldReturnEmptyList() {
        List<BigInteger> expectedList = new ArrayList<>();
        assertEquals(expectedList, FibonacciSeries(BigInteger.TEN, BigInteger.ZERO));
    }

    /**
     * Testfall für die Zweigüberdeckung. Jede Kante muss zumindest einmal ausgeführt werden.
     **/
    @Test
    public void TestC1BranchCoverage_ShouldReturnListWith0_1_1_2_3_5_8() {
        List<BigInteger> expectedList = List.of(BigInteger.ZERO, BigInteger.ONE, BigInteger.ONE, BigInteger.TWO, BigInteger.valueOf(3), BigInteger.valueOf(5), BigInteger.valueOf(8));
        assertEquals(expectedList, FibonacciSeries(BigInteger.ZERO, BigInteger.TEN));
    }

    /**
     * Testfall für die Zweigüberdeckung. Jede Kante muss zumindest einmal ausgeführt werden.
     **/
    @Test
    public void TestC1BranchCoverage_ShouldReturnEmptyList() {
        List<BigInteger> expectedList = new ArrayList<>();
        assertEquals(expectedList, FibonacciSeries(BigInteger.TEN, BigInteger.ZERO));
    }

    /**
     * Testfall für die Zweigüberdeckung. Jede Kante muss zumindest einmal ausgeführt werden.
     * Überprüft das Verhalten, wenn alle Bedingungen falsch sind.
     **/
    @Test
    public void TestC1BranchCoverage_ShouldReturnListWith0_1_1() {
        List<BigInteger> expectedList = List.of(BigInteger.ZERO, BigInteger.ONE, BigInteger.ONE);
        assertEquals(expectedList, FibonacciSeries(BigInteger.ZERO, BigInteger.ONE));
    }

    /**
     * Testfall für die einfache Bedingungsüberdeckung. Alle atomaren Teilentscheidungen werden getestet.
     * from > to = [true]; other = [false]
     **/
    @Test
    public void TestC2SimpleConditionCoverage_ShouldReturnEmptyListInFirstCondition() {
        List<BigInteger> expectedList = new ArrayList<>();
        assertEquals(expectedList, FibonacciSeries(BigInteger.TEN, BigInteger.ZERO));
    }

    /**
     * Testfall für die einfache Bedingungsüberdeckung. Alle atomaren Teilentscheidungen werden getestet.
     * from > to = [false]; other = [true]
     **/
    @Test
    public void TestC2SimpleConditionCoverage_ShouldReturnListWith0_1_1_2_3_5_8() {
        List<BigInteger> expectedList = List.of(BigInteger.ZERO, BigInteger.ONE, BigInteger.ONE, BigInteger.TWO, BigInteger.valueOf(3), BigInteger.valueOf(5), BigInteger.valueOf(8));
        assertEquals(expectedList, FibonacciSeries(BigInteger.ZERO, BigInteger.TEN));
    }

    /**
     * Testfall für die einfache Bedingungsüberdeckung. Alle atomaren Teilentscheidungen werden getestet.
     * all = [false]
     **/
    @Test
    public void TestC2SimpleConditionCoverage_ShouldReturnEmptyList() {
        List<BigInteger> expectedList = new ArrayList<>();
        assertEquals(expectedList, FibonacciSeries(BigInteger.valueOf(-1), BigInteger.valueOf(-1)));
    }

    /**
     * Testfall für die mehrfache Bedingungsüberdeckung. Alle Wahrheitswertkombinationen werden getestet.
     * from > to = [true]; other = [false]
     **/
    @Test
    public void TestC2MultipleConditionCoverage_ShouldReturnEmptyListInFirstCondition() {
        List<BigInteger> expectedList = new ArrayList<>();
        assertEquals(expectedList, FibonacciSeries(BigInteger.TEN, BigInteger.ZERO));
    }

    /**
     * Testfall für die mehrfache Bedingungsüberdeckung. Alle Wahrheitswertkombinationen werden getestet.
     * from <= to = [true] && from == 0 = [true] && to == 0 = [true]; other = [false]
     **/
    @Test
    public void TestC2MultipleConditionCoverage_ShouldReturnListWith0() {
        List<BigInteger> expectedList = List.of(BigInteger.ZERO);
        assertEquals(expectedList, FibonacciSeries(BigInteger.ZERO, BigInteger.ZERO));
    }

    /**
     * Testfall für die mehrfache Bedingungsüberdeckung. Alle Wahrheitswertkombinationen werden getestet.
     * from <= to = [true] && from == 1 = [true] && to == 1 = [true]; other = [false]
     **/
    @Test
    public void TestC2MultipleConditionCoverage_ShouldReturnListWith1_1() {
        List<BigInteger> expectedList = List.of(BigInteger.ONE, BigInteger.ONE);
        assertEquals(expectedList, FibonacciSeries(BigInteger.ONE, BigInteger.ONE));
    }

    /**
     * Testfall für die mehrfache Bedingungsüberdeckung. Alle Wahrheitswertkombinationen werden getestet.
     * from <= to = [true] && from >= 2 = [true] && to >= 2 = [true]; other = [false]
     **/
    @Test
    public void TestC2MultipleConditionCoverage_ShouldReturnListWith2() {
        List<BigInteger> expectedList = List.of(BigInteger.TWO);
        assertEquals(expectedList, FibonacciSeries(BigInteger.TWO, BigInteger.TWO));
    }

    /**
     * Testfall für die mehrfache Bedingungsüberdeckung. Alle Wahrheitswertkombinationen werden getestet.
     * from <= to = [true] && from == 0 = [true] && to == 1 = [true]; other = [false]
     **/
    @Test
    public void TestC2MultipleConditionCoverage_ShouldReturnListWith0_1_1() {
        List<BigInteger> expectedList = List.of(BigInteger.ZERO, BigInteger.ONE, BigInteger.ONE);
        assertEquals(expectedList, FibonacciSeries(BigInteger.ZERO, BigInteger.ONE));
    }

    /**
     * Testfall für die mehrfache Bedingungsüberdeckung. Alle Wahrheitswertkombinationen werden getestet.
     * from <= to = [true] && from == 1 = [true] && to >= 2 = [true]; other = [false]
     **/
    @Test
    public void TestC2MultipleConditionCoverage_ShouldReturnListWith1_1_2() {
        List<BigInteger> expectedList = List.of(BigInteger.ONE, BigInteger.ONE, BigInteger.TWO);
        assertEquals(expectedList, FibonacciSeries(BigInteger.ONE, BigInteger.TWO));
    }

    /**
     * Testfall für die mehrfache Bedingungsüberdeckung. Alle Wahrheitswertkombinationen werden getestet.
     * from <= to = [true] && from == 0 = [true] && to >= 2 = [true]; other = [false]
     **/
    @Test
    public void TestC2MultipleConditionCoverage_ShouldReturnListWith0_1_1_2() {
        List<BigInteger> expectedList = List.of(BigInteger.ZERO, BigInteger.ONE, BigInteger.ONE, BigInteger.TWO);
        assertEquals(expectedList, FibonacciSeries(BigInteger.ZERO, BigInteger.TWO));
    }

    /**
     * Testfall für die mehrfache Bedingungsüberdeckung. Alle Wahrheitswertkombinationen werden getestet.
     * all = [false]
     **/
    @Test
    public void TestC2MultipleConditionCoverage_ShouldReturnEmptyList() {
        List<BigInteger> expectedList = new ArrayList<>();
        assertEquals(expectedList, FibonacciSeries(BigInteger.valueOf(-1), BigInteger.valueOf(-1)));
    }

    /**
     * Testfall für die Pfadüberdeckung. Alle Pfade müssen abgedeckt werden.
     **/
    @Test
    public void TestC3PathCoverage_ShouldReturnEmptyListInFirstCondition() {
        List<BigInteger> expectedList = new ArrayList<>();
        assertEquals(expectedList, FibonacciSeries(BigInteger.TEN, BigInteger.ZERO));
    }

    /**
     * Testfall für die Pfadüberdeckung. Alle Pfade müssen abgedeckt werden.
     **/
    @Test
    public void TestC3PathCoverage_ShouldReturnEmptyList() {
        List<BigInteger> expectedList = new ArrayList<>();
        assertEquals(expectedList, FibonacciSeries(BigInteger.valueOf(-1), BigInteger.valueOf(-1)));
    }

    /**
     * Testfall für die Pfadüberdeckung. Alle Pfade müssen abgedeckt werden.
     **/
    @Test
    public void TestC3PathCoverage_ShouldReturnListWith0() {
        List<BigInteger> expectedList = List.of(BigInteger.ZERO);
        assertEquals(expectedList, FibonacciSeries(BigInteger.ZERO, BigInteger.ZERO));
    }

    /**
     * Testfall für die Pfadüberdeckung. Alle Pfade müssen abgedeckt werden.
     **/
    @Test
    public void TestC3PathCoverage_ShouldReturnListWith1_1() {
        List<BigInteger> expectedList = List.of(BigInteger.ONE, BigInteger.ONE);
        assertEquals(expectedList, FibonacciSeries(BigInteger.ONE, BigInteger.ONE));
    }

    /**
     * Testfall für die Pfadüberdeckung. Alle Pfade müssen abgedeckt werden.
     **/
    @Test
    public void TestC3PathCoverage_ShouldReturnListWith0_1_1() {
        List<BigInteger> expectedList = List.of(BigInteger.ZERO, BigInteger.ONE, BigInteger.ONE);
        assertEquals(expectedList, FibonacciSeries(BigInteger.ZERO, BigInteger.ONE));
    }

    /**
     * Testfall für die Pfadüberdeckung. Alle Pfade müssen abgedeckt werden.
     **/
    @Test
    public void TestC3PathCoverage_ShouldReturnListWith2() {
        List<BigInteger> expectedList = List.of(BigInteger.TWO);
        assertEquals(expectedList, FibonacciSeries(BigInteger.TWO, BigInteger.TWO));
    }

    /**
     * Testfall für die Pfadüberdeckung. Alle Pfade müssen abgedeckt werden.
     **/
    @Test
    public void TestC3PathCoverage_ShouldReturnListWith1_1_2() {
        List<BigInteger> expectedList = List.of(BigInteger.ONE, BigInteger.ONE, BigInteger.TWO);
        assertEquals(expectedList, FibonacciSeries(BigInteger.ONE, BigInteger.TWO));
    }

    /**
     * Testfall für die Pfadüberdeckung. Alle Pfade müssen abgedeckt werden.
     **/
    @Test
    public void TestC3PathCoverage_ShouldReturnListWith0_1_1_2() {
        List<BigInteger> expectedList = List.of(BigInteger.ZERO, BigInteger.ONE, BigInteger.ONE, BigInteger.TWO);
        assertEquals(expectedList, FibonacciSeries(BigInteger.ZERO, BigInteger.TWO));
    }

}
