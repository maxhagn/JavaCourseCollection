import java.util.*;

// # 40 von 45 Punkten für Union

// 'Union' implements 'Set' and represents a union of multiple (at least two) unconnected intervals.
// Hint: It is allowed to use the java Collection-framework for the implementation (e.g., List<Interval>).
//
public class Union implements Set {

    private List<Interval> intervals;

    public Union(Interval a, Interval b){
        intervals = new LinkedList<>();
        intervals.add(a);
        intervals.add(b);
    }

    // Helper method: Removes all intervals from this 'Union' which are connected to 'other'
    // (i.e., intervals where the union with 'other' is a single continuous interval).
    // The union of all the removed intervals and 'interval' is returned.
    // Precondition: other != null.
    private Interval removeAllConnectedWith(Interval other) {
        Interval returnIV = null;
        int n = 0;
        for (Interval iv: intervals) {
            if(iv.union(other).isContinuous()){
                intervals.remove(n);
                if(returnIV == null){
                    returnIV = iv;
                }else{
                    returnIV = ((Interval)returnIV.union(iv));
                }
            }
            n++;
        };
        return returnIV;
    }


    @Override
    public Set union(Interval other) {  // Lösung unvollständig, Verwendung von removeAllConnectedWith hätte vieles vereinfacht
        Set h = null;
        int n = 0;
        for (Interval iv: intervals) {
            h = iv.union(other);
            if(h instanceof Interval){
                //this.union((Interval)h);
                intervals.set(n, (Interval)h);
                return this;
            }
            n++;
        }
        intervals.add(other);


        return this;  // this verändert und zurückgegeben, keine Kopie
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int pos = 0;
            Iterator<Integer> cur = intervals.get(pos).iterator();

            @Override
            public boolean hasNext() {
                return (pos < intervals.size() || cur.hasNext());
            }

            @Override
            public Integer next() {
                if(cur.hasNext()){
                    return cur.next();
                }else{

                    if(pos < intervals.size()) {
                        cur = intervals.get(pos++).iterator();

                        if (cur.hasNext()) {
                            return cur.next();
                        } else {
                            this.next();
                        }
                    }
                }

                throw new NoSuchElementException("No more elemets!");
            }
        };
    }


    // 'Union' objects are never continuous.
    @Override
    public boolean isContinuous() {
        return false;
    }

    @Override
    public String toString() {
        String s = "[";
        for (Interval iv: intervals) {
            s += iv.toString() + ", ";
        };
        s = s.substring(0,s.length()-2) + "]";

        return s;
    }
}

