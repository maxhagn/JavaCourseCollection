/**
 * Hagn Maximilian
 * 11808237
 * Exercise 03
 **/

package at.tuwien.swtesting;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;


public class RingBufferModel implements FsmModel {

    private final int initialCapacity;
    private int capacity;
    private int size = 0;

    public RingBufferModel(int capacity) {
        this.initialCapacity = capacity;
        this.capacity = initialCapacity;
    }

    public Object getState() {
        if (size == 0) {
            return "EMPTY";
        } else if (size == capacity) {
            return "FULL";
        } else {
            return "FILLED";
        }
    }

    public void reset(boolean testing) {
        size = 0;
        capacity = initialCapacity;
    }

    @Action
    public void enqueue() {
        if (size < capacity) {
            size++;
        } else {
            size = capacity;
        }
    }

    @Action
    public void dequeue() {
        if (size > 0) {
            size--;
        }
    }

    @Action
    public void peek() {
        // peek doesn't change the state
        return;
    }

    @Action
    public void setCapacityLargerThanCurrentSize() {
        int newCapacity = capacity + 1;
        if (newCapacity >= size) {
            capacity = newCapacity;
        }
    }


    @Action
    public void setCapacitySmallerThanCurrentSize() {
        int newCapacity = size > 0 ? (size - 1) : 0;
        if (newCapacity < size) {
            // sut should throw exception
            return;
        }
    }

    @Action
    public void peekOnEmptyBuffer() {
        if (size == 0) {
            // sut should throw exception
            return;
        }
    }

    @Action
    public void dequeueFromEmptyBuffer() {
        if (size == 0) {
            // sut should throw exception
            return;
        }
    }
}
