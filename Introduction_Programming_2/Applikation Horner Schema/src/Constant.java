import java.lang.reflect.Array;
import java.util.*;

// 'Constant' implements 'Polynomial' and represents a polynomial with degree 0 corresponding
// to a constant value (regardless of the 'x' used for evaluation).
// 'Constant' is used by class 'HornerScheme'.
// The iterator of this class iterates over only one value and therefore behaves as follows:
// The 'next' method returns the constant value, if it is called for the first time.
// 'hasNext' is 'true' only if 'next' has not been called.
//
public class Constant implements Polynomial {

    //      # 5 von 5 Punkten für 'equals' und 'hashCode'
    //      # 3 von 3 Punkten für 'toString'
    //      # 10 von 10 Punkten für 'iterator'
    //      # 4 von 7 Punkten für 'coefficients': leere Liste.
    //      # 5 von 5 Punkten für 'degree' und 'eval'

    private int constant;

    // Initializes this object.
    // Precondition: c != 0.
    public Constant(int c) {
        constant = c;
    }

    @Override
    public int degree() {
        return 0;
    }

    @Override
    public List<Integer> coefficients() {
        ArrayList<Integer> coefficients = new ArrayList<>(); // wurde korrigiert
        coefficients.add(constant);
        return coefficients;
    }

    @Override
    public double eval(double x) {
        return (double)constant;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            private boolean hasNext = true;

            @Override
            public boolean hasNext() {
                return hasNext;
            }

            @Override
            public Integer next() {
                if (!hasNext)
                    throw new NoSuchElementException("no coefficient!");
                hasNext = false;
                return constant;
            }
        };
    }

    @Override
    public String toString() {
        return String.format("%d", constant);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constant integers = (Constant) o;
        return constant == integers.constant;
    }

    @Override
    public int hashCode() {
        return Objects.hash(constant);
    }
}


