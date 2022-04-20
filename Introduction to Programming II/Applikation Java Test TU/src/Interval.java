import java.util.*;

// # 30 von 30 Punkten für Interval

// This class implements 'Set' and represents an interval of Integer numbers, specified by the smallest and
// largest number of the interval.
//
public class Interval implements Set {

    private int smallest;
    private int largest;

    // Initializes this 'Interval' with the bounds of the interval.
    public Interval(int smallest, int largest) {
        this.smallest = smallest;
        this.largest = largest;
    }

    // Returns the union of 'this' and 'other'. If the result can be represented by a single
    // interval (i.e., 'this' and 'other' are connected), the result
    // is of type 'Interval', otherwise it is a 'Union'.
    // Examples:
    // The union of 0-5 and 3-8 is 0-8  (type 'Interval'),
    // the union of 0-5 and 6-7 is 0-7  (type 'Interval'),
    // the union of 0-5 and 7-8 is [0-5, 7-8] (type 'Union').
    public Set union(Interval other) {
        if((smallest-1 > other.getLargest()) || largest+1 < other.getSmallest()){
            return new Union(this, other);
        }else{
            return new Interval(Math.min(smallest, other.getSmallest()), Math.max(largest, other.getLargest()));
        }
    }

    @Override
    public boolean isContinuous() {
        return true;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int cur = smallest;

            @Override
            public boolean hasNext() {
                return (cur <= largest);
            }

            @Override
            public Integer next() {
                if(cur > largest){
                    throw new NoSuchElementException("no element!");
                }
                return cur++;
            }
        };
    }

    @Override
    public String toString() {
        return smallest + "-" + largest;
    }

    public int getSmallest() {
        return smallest;
    }

    public void setSmallest(int smallest) {
        this.smallest = smallest;
    }

    public int getLargest() {
        return largest;
    }

    public void setLargest(int largest) {
        this.largest = largest;
    }
}

