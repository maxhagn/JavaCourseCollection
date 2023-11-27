/**
 * Hagn Maximilian
 * 11808237
 * Exercise 01
 **/

package at.tuwien.swtesting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RingBufferStateTest {

    private static final String ELEMENT_A = "a";
    private static final String ELEMENT_B = "b";
    private static final String ELEMENT_C = "c";
    private static final int BUFFER_SIZE_ONE = 1;
    private static final int BUFFER_SIZE_TWO = 2;
    private static final int BUFFER_SIZE_THREE = 3;
    private static final int BUFFER_CAPACITY_THREE = 3;

    // 1) init(3) -EMPTY- delete() -final
    @Test
    @DisplayName(value = "Delete on buffer in state EMPTY should remove all references to it.")
    void emptyBuffer_delete_final() {
        RingBuffer<String> sut = new RingBuffer<>(BUFFER_CAPACITY_THREE);
        sut = null;
        assertNull(sut, "Since the buffer should be marked for deletion, the references to it should be removed.");
    }

    // 2) init(3) -EMPTY- enqueue(a) -FILLED- dequeue() -EMPTY-
    @Test
    @DisplayName(value = "Enqueuing and dequeuing on empty buffer should result in state EMPTY.")
    void emptyBuffer_enqueueDequeue_emptyBuffer() {
        RingBuffer<String> sut = new RingBuffer<>(BUFFER_CAPACITY_THREE);
        sut.enqueue(ELEMENT_A);
        assertFalse(sut.isEmpty(), "The buffer should not be empty.");
        assertEquals(BUFFER_SIZE_ONE, sut.size(), "The buffer should contain one element.");
        sut.dequeue();
        assertTrue(sut.isEmpty(), "Since testing the transition from filled to empty buffer with dequeue, the buffer should be empty.");
    }

    // 3) init(3) -EMPTY- enqueue(a) -FILLED- peek() -FILLED-
    @Test
    @DisplayName(value = "Enqueueing one element on empty buffer and peek one time should result in state FILLED.")
    void emptyBuffer_enqueuePeek_filledBuffer() {
        RingBuffer<String> sut = new RingBuffer<>(BUFFER_CAPACITY_THREE);
        sut.enqueue(ELEMENT_A);
        assertFalse(sut.isEmpty(), "The buffer should not be empty.");
        assertEquals(BUFFER_SIZE_ONE, sut.size(), "The buffer should contain one element.");
        assertEquals(ELEMENT_A, sut.peek(), "The inserted element should be peeked.");
        assertFalse(sut.isEmpty(), "The buffer should not be empty.");
        assertEquals(BUFFER_SIZE_ONE, sut.size(), "The buffer should contain one element.");
        assertFalse(sut.isEmpty(), "The buffer should not be in state EMPTY.");
        assertFalse(sut.isFull(), "The buffer should not be in state FULL.");
    }

    // 4) init(3) -EMPTY- enqueue(a) -FILLED- enqueue(b) -FILLED- dequeue() -FILLED-
    @Test
    @DisplayName(value = "Enqueuing two elements and dequeuing one element should result in state FILLED.")
    void emptyBuffer_enqueueEnqueueDequeue_filledBuffer() {
        RingBuffer<String> sut = new RingBuffer<>(BUFFER_CAPACITY_THREE);
        sut.enqueue(ELEMENT_A);
        assertEquals(BUFFER_SIZE_ONE, sut.size(), "The buffer should contain one element.");
        sut.enqueue(ELEMENT_B);
        assertEquals(BUFFER_SIZE_TWO, sut.size(), "The buffer should contain two elements.");
        assertEquals(ELEMENT_A, sut.dequeue(), "The first inserted element should be dequeued.");
        assertEquals(BUFFER_SIZE_ONE, sut.size(), "The buffer should contain one element.");
        assertFalse(sut.isEmpty(), "The buffer should not be in state EMPTY.");
        assertFalse(sut.isFull(), "The buffer should not be in state FULL.");
    }

    // 5) init(3) -EMPTY- enqueue(a) -FILLED- enqueue(b) -FILLED- peek() -FILLED-
    @Test
    @DisplayName(value = "Enqueuing two elements and peeking one time should result in state FILLED.")
    void emptyBuffer_enqueueEnqueuePeek_filledBuffer() {
        RingBuffer<String> sut = new RingBuffer<>(BUFFER_CAPACITY_THREE);
        sut.enqueue(ELEMENT_A);
        assertEquals(BUFFER_SIZE_ONE, sut.size(), "The buffer should contain one element.");
        sut.enqueue(ELEMENT_B);
        assertEquals(BUFFER_SIZE_TWO, sut.size(), "The buffer should contain two elements.");
        assertEquals(ELEMENT_A, sut.peek(), "The first inserted element should be peeked.");
        assertEquals(BUFFER_SIZE_TWO, sut.size(), "The buffer should contain two elements.");
        assertFalse(sut.isEmpty(), "The buffer should not be in state EMPTY.");
        assertFalse(sut.isFull(), "The buffer should not be in state FULL.");
    }

    // 6) init(3) -EMPTY- enqueue(a) -FILLED- enqueue(b) -FILLED- enqueue(c) -FULL- peek() -FULL-
    @Test
    @DisplayName(value = "Enqueuing three elements and peeking one time should result in state FULL")
    void emptyBuffer_enqueueEnqueueEnqueuePeek_fullBuffer() {
        RingBuffer<String> sut = new RingBuffer<>(BUFFER_CAPACITY_THREE);
        sut.enqueue(ELEMENT_A);
        assertEquals(BUFFER_SIZE_ONE, sut.size(), "The buffer should contain one element.");
        sut.enqueue(ELEMENT_B);
        assertEquals(BUFFER_SIZE_TWO, sut.size(), "The buffer should contain two elements.");
        sut.enqueue(ELEMENT_C);
        assertEquals(BUFFER_SIZE_THREE, sut.size(), "The buffer should contain three elements.");
        assertEquals(ELEMENT_A, sut.peek(), "The first inserted element should be peeked.");
        assertEquals(BUFFER_SIZE_THREE, sut.size(), "The buffer should contain three elements.");
        assertTrue(sut.isFull(), "The buffer should be in state FULL.");
    }

    // 7) init(3) -EMPTY- peek()
    @Test
    @DisplayName(value = "Peek on buffer in state EMPTY should throw exception.")
    void emptyBuffer_peek_throwsRuntimeException() {
        RingBuffer<String> sut = new RingBuffer<>(BUFFER_CAPACITY_THREE);
        assertThrows(RuntimeException.class, sut::peek, "Peek on empty buffer should throw Runtime exception.");
    }

    // 8) init(3) -EMPTY- dequeue()
    @Test
    @DisplayName(value = "Dequeue on buffer in state EMPTY should throw exception.")
    void emptyBuffer_dequeue_throwsRuntimeException() {
        RingBuffer<String> sut = new RingBuffer<>(BUFFER_CAPACITY_THREE);
        assertThrows(RuntimeException.class, sut::dequeue, "Dequeue on empty buffer should throw Runtime exception.");
    }

}
