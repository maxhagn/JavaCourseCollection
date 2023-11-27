/**
 * Hagn Maximilian
 * 11808237
 * Exercise 03
 **/

package at.tuwien.swtesting;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;


public class RingBufferModel implements FsmModel {

    private static int EMPTY_SIZE = 0;
    private static int FULL_SIZE = 1;
    private int capacity;
    private int size = 0;

    public Object getState() {
        if (size == EMPTY_SIZE) {
            return "EMPTY";
        } else {
            return "FULL";
        }
    }

    public void reset(boolean testing) {
        size = EMPTY_SIZE;
    }

    @Action
    public void enqueue() {
        size = FULL_SIZE;
    }

    @Action
    public void dequeue() {
        if (size == FULL_SIZE) {
            size = EMPTY_SIZE;
        }
    }

    @Action
    public void peek() {
        // peek doesn't change the state
        return;
    }

    @Action
    public void peekOnEmptyBuffer() {
        if (size == EMPTY_SIZE) {
            // sut should throw exception
            return;
        }
    }

    @Action
    public void dequeueFromEmptyBuffer() {
        if (size == EMPTY_SIZE) {
            // sut should throw exception
            return;
        }
    }
}
