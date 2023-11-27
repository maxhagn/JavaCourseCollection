/**
 * Hagn Maximilian
 * 11808237
 * Exercise 03
 **/

package at.tuwien.swtesting;

import nz.ac.waikato.modeljunit.Action;
import nz.ac.waikato.modeljunit.FsmModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RingBufferModelWithAdapter implements FsmModel {

    private static int EMPTY_SIZE = 0;
    private static int FULL_SIZE = 1;
    private int capacity;
    private int size = 0;
    private RingBuffer<Object> buffer;

    public RingBufferModelWithAdapter() {
        this.buffer = new RingBuffer<>(FULL_SIZE);
        this.capacity = FULL_SIZE;
    }

    public Object getState() {
        if (size == EMPTY_SIZE) {
            return "EMPTY";
        } else {
            return "FULL";
        }
    }

    public void reset(boolean testing) {
        this.buffer = new RingBuffer<>(FULL_SIZE);
        capacity = FULL_SIZE;
        size = EMPTY_SIZE;
    }

    @Action
    public void enqueue() {
        buffer.enqueue(new Object());
        if (size == EMPTY_SIZE) {
            size = FULL_SIZE;
        }
        assertEquals(size, buffer.size(), "Sut size should be equal to model size after enqueue.");
    }

    @Action
    public void peek() {
        if (size == FULL_SIZE) {
            buffer.peek();
            assertEquals(size, buffer.size(), "Sut size should be equal to model size after peek.");
        }
    }

    @Action
    public void peekOnEmptyBuffer() {
        try {
            if (size == EMPTY_SIZE) {
                buffer.peek();
            }
        } catch (RuntimeException runtimeException) {
            if (runtimeException.getMessage().equals("Buffer is empty")) {
                return;
            }
        }
    }

    @Action
    public void dequeue() {
        if (size == FULL_SIZE) {
            buffer.dequeue();
            size = EMPTY_SIZE;
            assertEquals(size, buffer.size(), "Sut size should be equal to model size after dequeue.");
        }
    }

    @Action
    public void dequeueFromEmptyBuffer() {
        try {
            if (size == EMPTY_SIZE) {
                buffer.dequeue();
            }
        } catch (RuntimeException runtimeException) {
            if (runtimeException.getMessage().equals("Buffer is empty")) {
                return;
            }
        }
    }

    @Action
    public void capacity() {
        assertEquals(capacity, buffer.capacity(), "Sut capacity should be equal to model capacity.");
    }

    @Action
    public void isEmpty() {
        assertEquals(size == EMPTY_SIZE, buffer.isEmpty(), "Sut should be in state empty if model is in state empty.");
    }

    @Action
    public void isFull() {
        assertEquals(size == capacity, buffer.isFull(), "Sut should be in state full if model is in state full.");
    }
}
