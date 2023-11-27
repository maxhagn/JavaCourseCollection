/**
 * Hagn Maximilian
 * 11808237
 * Exercise 01
 **/

package at.tuwien.swtesting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RingBufferTest {

    @Test
    @DisplayName(value = "Capacity on buffer initialized with capacity three should return capacity three.")
    void capacityThree_capacity_returnsCapacityThree() {
        RingBuffer<String> sut = new RingBuffer<>(3);
        assertEquals(3, sut.capacity(), "Since buffer was initialized with capacity three, the capacity of the buffer should be three.");
    }

    @Test
    @DisplayName(value = "Size on buffer initialized with capacity three and three elements enqueued should return size three.")
    void bufferThreeElementsEnqueued_size_returnsSizeThree() {
        RingBuffer<String> sut = new RingBuffer<>(3);
        sut.enqueue("a");
        sut.enqueue("b");
        sut.enqueue("c");
        assertEquals(3, sut.size(), "Since three elements were enqueued, the size of the buffer should be three.");
    }

    @Test
    @DisplayName(value = "Size on buffer initialized with capacity three and four elements enqueued should return size three.")
    void bufferFourElementsEnqueued_size_returnsSizeThree() {
        RingBuffer<String> sut = new RingBuffer<>(3);
        sut.enqueue("a");
        sut.enqueue("b");
        sut.enqueue("c");
        sut.enqueue("d");
        assertEquals(3, sut.size(), "Since the capacity is three, the last element should override the first element and size is still three.");
    }

    @Test
    @DisplayName(value = "isFull on not completely filled buffer should be false.")
    void bufferNotFull_isFull_returnsFalse() {
        RingBuffer<String> sut = new RingBuffer<>(3);
        sut.enqueue("a");
        assertFalse(sut.isFull(), "Since the buffer is not full, isFull should return false.");
    }

    @Test
    @DisplayName(value = "isFull on full buffer should be true.")
    void bufferFull_isFull_returnsTrue() {
        RingBuffer<String> sut = new RingBuffer<>(3);
        sut.enqueue("a");
        sut.enqueue("b");
        sut.enqueue("c");
        sut.enqueue("d");
        assertTrue(sut.isFull(), "Since the buffer is full, isFull should return true.");
    }

    @Test
    @DisplayName(value = "Enqueue on full buffer should remove the first inserted element and enqueues the new element at the end.")
    void bufferFull_enqueue_removesFirstElementAndAddsNewElementAtTheEnd() {
        RingBuffer<String> sut = new RingBuffer<>(3);
        sut.enqueue("a");
        sut.enqueue("b");
        sut.enqueue("c");
        assertEquals("a", sut.peek(), "Peek should return the first enqueued element without deleting it.");
        sut.enqueue("d");
        assertEquals("b", sut.peek(), "Since enqueue was performed on empty buffer, the first element should have been removed.");
    }

    @Test
    @DisplayName(value = "Enqueuing and dequeuing three elements each should result in empty buffer.")
    void emptyBuffer_enqueueDequeue_returnsIsEmptyTrue() {
        RingBuffer<String> sut = new RingBuffer<>(3);
        sut.enqueue("a");
        sut.enqueue("b");
        sut.enqueue("c");
        assertEquals("a", sut.dequeue(), "First dequeue should remove the first enqueued element.");
        assertEquals("b", sut.dequeue(), "Second dequeue should remove the second enqueued element.");
        assertEquals("c", sut.dequeue(), "Third dequeue should remove the third enqueued element.");
        assertTrue(sut.isEmpty());
    }

    @Test
    @DisplayName(value = "Enqueuing four elements into a buffer with capacity three and dequeuing three should result in empty buffer.")
    void fullBufferCapacityThree_enqueueFourDequeueThree_returnsIsEmptyTrue() {
        RingBuffer<String> sut = new RingBuffer<>(3);
        sut.enqueue("a");
        sut.enqueue("b");
        sut.enqueue("c");
        sut.enqueue("d");
        assertEquals("b", sut.dequeue(), "Since the first element was removed by enqueuing four elements into buffer with capacity three, the second enqueued element should be dequeued.");
        assertEquals("c", sut.dequeue(), "Since the first element was removed by enqueuing four elements into buffer with capacity three, the third enqueued element should be dequeued.");
        assertEquals("d", sut.dequeue(), "Since the first element was removed by enqueuing four elements into buffer with capacity three, the fourth enqueued element should be dequeued.");
        assertTrue(sut.isEmpty(), "Since all elements in the buffer were dequeued, the buffer should be empty.");
    }

    @Test
    @DisplayName(value = "Enqueuing six elements into a buffer with capacity three and dequeuing three should result in empty buffer.")
    void fullBufferCapacityThree_enqueueSixDequeueThree_returnsIsEmptyTrue() {
        RingBuffer<String> sut = new RingBuffer<>(3);
        sut.enqueue("a");
        sut.enqueue("b");
        sut.enqueue("c");
        sut.enqueue("d");
        sut.enqueue("e");
        sut.enqueue("f");
        assertEquals("d", sut.dequeue(), "Since the first three elements were removed by enqueuing six elements into buffer with capacity three, the fourth enqueued element should be dequeued.");
        assertEquals("e", sut.dequeue(), "Since the first three elements were removed by enqueuing six elements into buffer with capacity three, the fifth enqueued element should be dequeued.");
        assertEquals("f", sut.dequeue(), "Since the first three elements were removed by enqueuing six elements into buffer with capacity three, the sixth enqueued element should be dequeued.");
        assertTrue(sut.isEmpty());
    }

    @Test
    @DisplayName(value = "Dequeuing on empty buffer should throw exception.")
    void emptyBuffer_dequeue_throwsRuntimeException() {
        RingBuffer<String> sut = new RingBuffer<>(3);
        assertTrue(sut.isEmpty());
        assertThrows(RuntimeException.class, sut::dequeue, "Since no elements to dequeue are available, a Runtime exception should be thrown.");
    }

    @Test
    @DisplayName(value = "Peek two times on full buffer should return two times the same element.")
    void fullBuffer_peek_returnsFirstElementTwoTimesWithoutRemovingItFromBuffer() {
        RingBuffer<String> sut = new RingBuffer<>(3);
        sut.enqueue("a");
        sut.enqueue("b");
        sut.enqueue("c");
        assertFalse(sut.isEmpty(), "The buffer should be filled with a least one element for the test to work correctly.");
        assertEquals("a", sut.peek(), "Since a was the first element enqueued, peek should return a.");
        assertEquals("a", sut.peek(), "Peek should not remove elements. Therefore, peek should return the same element again.");
    }

    @Test
    @DisplayName(value = "Peek on empty buffer should throw exception.")
    void emptyBuffer_peek_throwsRuntimeException() {
        RingBuffer<String> sut = new RingBuffer<>(3);
        assertThrows(RuntimeException.class, sut::peek, "Since the buffer is empty, peek throws an Runtime exception.");
    }

}
