/**
 * Hagn Maximilian
 * 11808237
 * Exercise 01
 **/

package at.tuwien.swtesting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class RingBufferIteratorTest {

    @Test
    @DisplayName(value = "HasNext on buffer with one enqueued element should return has next one time.")
    void oneElementEnqueued_hasNext_returnsHasNextOneTime() {
        RingBuffer<Integer> buffer = new RingBuffer<>(3);
        buffer.enqueue(1);
        Iterator<Integer> sut = buffer.iterator();
        assertTrue(sut.hasNext(), "Since one element was enqueued, the iterator should have a next element.");
        sut.next();
        assertFalse(sut.hasNext(), "Since only one element was enqueued, the iterator should not have another element.");
    }

    @Test
    @DisplayName(value = "Remove on empty buffer should throw an exception.")
    void emptyBuffer_remove_throwsUnsupportedOperationException() {
        RingBuffer<Integer> buffer = new RingBuffer<>(5);
        assertTrue(buffer.isEmpty(), "Since no element was enqueued, the buffer should be empty.");
        Iterator<Integer> sut = buffer.iterator();
        assertThrows(UnsupportedOperationException.class, sut::remove, "Since buffer is empty, remove method should throw an UnsupportedOperation exception.");
    }

    @Test
    @DisplayName(value = "Next on buffer with three enqueued elements should return three elements.")
    void threeElementsEnqueued_next_returnsThreeElements() {
        RingBuffer<Integer> buffer = new RingBuffer<>(3);
        buffer.enqueue(1);
        buffer.enqueue(2);
        buffer.enqueue(3);
        assertEquals(3, buffer.size(), "Since three elements were enqueued, the buffer size should be three.");
        Iterator<Integer> sut = buffer.iterator();
        assertEquals(1, (int) sut.next(), "First iterator.next() should be the first enqueued element.");
        assertEquals(2, (int) sut.next(), "Second iterator.next() should be the second enqueued element.");
        assertEquals(3, (int) sut.next(), "Third iterator.next() should be the third enqueued element.");
    }

    @Test
    @DisplayName(value = "Next on buffer with three enqueued elements should throw exception on forth call.")
    void threeElementsEnqueued_next_throwsNoSuchElementExceptionOnForthCall() {
        RingBuffer<Integer> buffer = new RingBuffer<>(3);
        buffer.enqueue(1);
        buffer.enqueue(2);
        buffer.enqueue(3);
        assertEquals(3, buffer.size(), "Since three elements were enqueued, the buffer size should be three.");
        Iterator<Integer> sut = buffer.iterator();
        sut.next();
        sut.next();
        sut.next();
        assertThrows(NoSuchElementException.class, sut::next, "Since only three elements were enqueued, the iterator should throw an NoSuchElement exception on the forth next().");
    }

}
