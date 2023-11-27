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
    private final int initialCapacity;
    private int capacity;
    private int size = 0;
    private RingBuffer<Object> buffer;

    public RingBufferModelWithAdapter(int capacity) {
        this.initialCapacity = capacity;
        this.capacity = initialCapacity;
        this.buffer = new RingBuffer<>(capacity);
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
        this.buffer = new RingBuffer<>(initialCapacity);
        capacity = initialCapacity;
        size = 0;
    }

    @Action
    public void enqueue() {
        buffer.enqueue(new Object());
        if (size < capacity) {
            size++;
        } else {
            size = capacity;
        }
        assertEquals(size, buffer.size(), "Sut size should be equal to model size after enqueue.");
    }

    @Action
    public void setCapacityLargerThanCurrentSize() {
        int newCapacity = capacity + 1;
        if (newCapacity >= size) {
            this.buffer.setCapacity(newCapacity);
            capacity = newCapacity;
            assertEquals(capacity, this.buffer.capacity(), "Sut capacity should be equal to model capacity.");
        }
    }


    @Action
    public void setCapacitySmallerThanCurrentSize() {
        int newCapacity = size > 0 ? (size - 1) : 0;
        try {
            if (newCapacity < size) {
                this.buffer.setCapacity(newCapacity);
            }
        } catch (RuntimeException runtimeException) {
            if (runtimeException.getMessage().equals("New capacity can not be smaller than the current size")) {
                return;
            }
        }
    }

    @Action
    public void peek() {
        if (size > 0) {
            buffer.peek();
            assertEquals(size, buffer.size(), "Sut size should be equal to model size after peek.");
        }
    }

    @Action
    public void peekOnEmptyBuffer() {
        try {
            if (size == 0) {
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
        if (size > 0) {
            buffer.dequeue();
            size--;
            assertEquals(size, buffer.size(), "Sut size should be equal to model size after dequeue.");
        }
    }

    @Action
    public void dequeueFromEmptyBuffer() {
        try {
            if (size == 0) {
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
        assertEquals(size == 0, buffer.isEmpty(), "Sut should be in state empty if model is in state empty.");
    }

    @Action
    public void isFull() {
        assertEquals(size == capacity, buffer.isFull(), "Sut should be in state full if model is in state full.");
    }
}
