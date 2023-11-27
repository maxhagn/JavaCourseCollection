/**
 * Hagn Maximilian
 * 11808237
 * Exercise 01
 **/

package at.tuwien.swtesting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RingBufferConstrTest {

    private static final int MY_MAX_ARRAY_INTEGER_SIZE = 2147483645;

    /**
     * This tests refer to the partitions defined in the first step.
     **/

    // [-INF .. -MAX_INT - 1] -> invalid, throws OutOfMemoryError
    @Test
    @DisplayName(value = "[-INF .. -MAX_INT] Initializing a buffer with capacity smaller than the minimum integer value throws an exception.")
    void capacityOutOfNegativeIntRange_initialize_throwsOutOfMemoryError() {
        assertThrows(OutOfMemoryError.class, () -> new RingBuffer<>(Integer.MIN_VALUE - 1), "Since the value is smaller than the minimum integer value, a OutOfMemory Error should be thrown.");
    }

    // [MIN_INT .. -1] -> invalid, throws NegativeArraySizeException
    @ParameterizedTest(name = "[{index}] Initializing buffer with capacity {0}")
    @DisplayName(value = "[MIN_INT .. -1] Initializing a buffer with capacity smaller than zero throws an exception.")
    @ValueSource(ints = {Integer.MIN_VALUE, -1})
    void capacityNegative_initialize_throwsNegativeArraySizeException(int capacity) {
        assertThrows(NegativeArraySizeException.class, () -> new RingBuffer<>(capacity), "Since an array with negative size should be created, an NegativeArraySize exception is thrown.");
    }

    // [0 .. MAX_INT] -> valid, buffer with zero or positive capacity initialized
    @ParameterizedTest(name = "[{index}] Initializing buffer with capacity {0}")
    @DisplayName(value = "[0 .. MAX_INT] Initializing a buffer with zero or positive capacity should create an buffer with the specified capacity.")
    @ValueSource(ints = {0, MY_MAX_ARRAY_INTEGER_SIZE})
    void capacityPositive_initialize_returnsCapacity(int capacity) {
        RingBuffer<Integer> buffer = new RingBuffer<>(capacity);
        assertEquals(capacity, buffer.capacity(), "The buffer should be initialized with any possible zero or positive integer value.");
    }

    // [MAX_INT+1 .. +INF]    -> invalid, throws OutOfMemoryError
    @Test
    @DisplayName(value = "[MAX_INT+1 .. +INF] Initializing a buffer with capacity larger than the maximum integer value throws an exception.")
    void capacityOutOfPositiveIntRange_initialize_throwsOutOfMemoryError() {
        assertThrows(OutOfMemoryError.class, () -> new RingBuffer<>(Integer.MAX_VALUE - 1), "Since the value is larger than the maximum integer value, a OutOfMemory Error should be thrown.");
    }

    /**
     * This tests refer to the refined partitions from the second step.
     * Already defined partitions are not tested again.
     **/

    // [0] -> invalid, buffer initialized, state:  EMPTY && FULL
    @Test
    @DisplayName(value = "[0] Initializing a buffer with capacity zero should technically work, but has one one state.")
    void capacityZero_initialize_initializedBufferWithOneState() {
        RingBuffer<Integer> buffer = new RingBuffer<>(0);
        assertEquals(0, buffer.capacity(), "The buffer should be initialized with the specified capacity of zero.");
    }

    // [1] -> valid, buffer initialized, states: EMPTY || FULL
    @Test
    @DisplayName(value = "[1] Initializing a buffer with capacity one should create buffer which can have two states.")
    void capacityOne_initialize_initializedBufferWithTwoStates() {
        RingBuffer<Integer> buffer = new RingBuffer<>(1);
        assertEquals(1, buffer.capacity(), "The buffer should be initialized with the specified capacity of one.");
    }

    // [2 .. MAX_INT] -> valid, buffer initialized, states: EMPTY || FILLED || FULL
    @ParameterizedTest(name = "[{index}] Initializing buffer with capacity {0}")
    @DisplayName(value = "[1] Initializing a buffer with capacity one should create buffer which can have two states.")
    @ValueSource(ints = {2, MY_MAX_ARRAY_INTEGER_SIZE})
    void capacityLargerOne_initialize_initializedBufferWithThreeStates(int capacity) {
        RingBuffer<Integer> buffer = new RingBuffer<>(capacity);
        assertEquals(capacity, buffer.capacity(), "The buffer should be initialized with the specified capacity positive capacity larger than two.");
    }

}
