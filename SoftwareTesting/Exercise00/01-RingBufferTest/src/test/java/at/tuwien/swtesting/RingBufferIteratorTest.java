/**
 * Hagn Maximilian
 * 11808237
 * Exercise 00
 **/

package at.tuwien.swtesting;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class RingBufferIteratorTest {

    @Test
    public void testHasNextAndNext_WhenOneElementEnqueued_ShouldReturnHasNextOneTime() {
        RingBuffer<Integer> buffer = new RingBuffer<Integer>(3);
        buffer.enqueue(1);
        Iterator<Integer> iterator = buffer.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testRemove_WhenBufferIsEmpty_ShouldThrowUnsupportedOperationException() {
        RingBuffer<Integer> buffer = new RingBuffer<>(5);
        assertTrue(buffer.isEmpty());
        Iterator<Integer> iterator = buffer.iterator();
        assertThrows(UnsupportedOperationException.class, iterator::remove);
    }

    @Test
    public void testNext_WhenThreeElementsEnqueued_ShouldReturnThreeElementsAndThrowNoSuchElementException() {
        RingBuffer<Integer> buffer = new RingBuffer<>(3);
        buffer.enqueue(1);
        buffer.enqueue(2);
        buffer.enqueue(3);
        assertEquals(3, buffer.size());
        Iterator<Integer> iterator = buffer.iterator();
        assertEquals(1, (int) iterator.next());
        assertEquals(2, (int) iterator.next());
        assertEquals(3, (int) iterator.next());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

}
