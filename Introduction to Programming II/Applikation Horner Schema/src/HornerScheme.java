import java.util.*;

// The class implements 'Polynomial' and represents the form (a + x*p), where 'a' is an Integer
// and 'p' is another polynomial. 'p' must not be 'null' (objects of 'Constant' shall be used to represent constants).
// This class implements 'Polynomial' by using Horner's scheme.
// Example:
// a₀ + a₁*x + a₂*x² + a₃*x³ + a₄*x⁴
// The polynomial above is represented in Horner's scheme as:
// a₀ + x*(a₁ + x*(a₂ + x*(a₃ + x*a₄)))
//
public class HornerScheme implements Polynomial {
    //      # 7 von 7 Punkten für 'create'
    //      # 8 von 8 Punkten für 'equals' und 'hashCode'
    //      # 5 von 5 Punkten für 'toString'
    //      # 10 von 10 Punkten für 'iterator'
    //      # 8 von 8 Punkten für 'coefficients'
    //      # 7 von 7 Punkten für 'degree' und 'eval'

    private int number;
    private Polynomial polynomial;

    // Initializes this object.
    // Precondition: 'p' must not be 'null'.
    public HornerScheme(int a, Polynomial p) {
        this.number = a;
        this.polynomial = p;
    }

    // Creates a polynomial from the coefficients specified by the array coeffs = {a₀, a₁, a₂, ... a𝘥}.
    // Precondition: coeffs != null and coeffs has at least one element.
    public static Polynomial create(int[] coeffs) {
        Polynomial p = new Constant(coeffs[coeffs.length - 1]);
        for (int c = coeffs.length - 2; c >= 0; c--) {
            p = new HornerScheme(coeffs[c], p);
        }
        return p;
    }

    @Override
    public int degree() {
        List<Integer> coefficients = coefficients();
        return coefficients.size() - 1 ;
    }

    @Override
    public List<Integer> coefficients() {
        ArrayList<Integer> coefficients = new ArrayList<>();
        for (int i : this) {
            coefficients.add(i);
        }
        return coefficients;
    }

    @Override
    public double eval(double x) {
        return (double) number + x * polynomial.eval(x);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private boolean hasNext = true;
            private Iterator<Integer> next = polynomial.iterator();

            @Override
            public boolean hasNext() {
                return hasNext || next.hasNext();
            }

            @Override
            public Integer next() {
                if (hasNext) {
                    hasNext = false;
                    return number;
                } else
                    return next.next();
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HornerScheme)) return false;
        HornerScheme integers = (HornerScheme) o;
        return number == integers.number &&
                Objects.equals(polynomial, integers.polynomial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(polynomial, number);
    }

    @Override
    public String toString() {
        return "(" + number + " + x*" + polynomial.toString() + ")";
    }
}





