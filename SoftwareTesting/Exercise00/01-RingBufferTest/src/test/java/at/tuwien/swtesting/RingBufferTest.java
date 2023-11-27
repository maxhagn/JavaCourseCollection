/**
 * Hagn Maximilian
 * 11808237
 * Exercise 00
 **/

package at.tuwien.swtesting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RingBufferTest {

    @Test
    void testCapacity_WhenSettingCapacityThree_ShouldReturnCapacityThree() {
        RingBuffer<String> buffer = new RingBuffer<>(3);
        assertEquals(3, buffer.capacity());
    }

    @Test
    void testEnqueueAndSize_WhenEnqueuingThreeElements_ShouldReturnSizeThree() {
        RingBuffer<String> buffer = new RingBuffer<>(3);
        buffer.enqueue("a");
        buffer.enqueue("b");
        buffer.enqueue("c");
        assertEquals(3, buffer.size());
    }

    @Test
    void testEnqueueAndSize_WhenEnqueuingFourElementsForCapacityThree_ShouldReturnSizeThree() {
        RingBuffer<String> buffer = new RingBuffer<>(3);
        buffer.enqueue("a");
        buffer.enqueue("b");
        buffer.enqueue("c");
        buffer.enqueue("d");
        assertEquals(3, buffer.size());
    }

    @Test
    void testIsFull_WhenEnqueuingOneElementsForCapacityThree_ShouldReturnIsFullFalse() {
        RingBuffer<String> buffer = new RingBuffer<>(3);
        buffer.enqueue("a");
        assertFalse(buffer.isFull());
    }

    @Test
    void testIsFull_WhenEnqueuingFourElementsForCapacityThree_ShouldReturnIsFullTrue() {
        RingBuffer<String> buffer = new RingBuffer<>(3);
        buffer.enqueue("a");
        buffer.enqueue("b");
        buffer.enqueue("c");
        buffer.enqueue("d");
        assertTrue(buffer.isFull());
    }

    @Test
    void testEnqueue_WhenEnqueuingOneElementAndBufferIsFull_ShouldOverrideFirstElementByEnqueuedElement() {
        RingBuffer<String> buffer = new RingBuffer<>(3);
        buffer.enqueue("a");
        buffer.enqueue("b");
        buffer.enqueue("c");
        assertEquals("a", buffer.peek());
        buffer.enqueue("d");
        assertEquals("b", buffer.peek());
    }

    @Test
    void testEnqueueAndDequeue_WhenEnqueuingThreeElementsForCapacityThreeAndDequeuingThreeElements_ShouldReturnIsEmptyTrue() {
        RingBuffer<String> buffer = new RingBuffer<>(3);
        buffer.enqueue("a");
        buffer.enqueue("b");
        buffer.enqueue("c");
        assertEquals("a", buffer.dequeue());
        assertEquals("b", buffer.dequeue());
        assertEquals("c", buffer.dequeue());
        assertTrue(buffer.isEmpty());
    }

    @Test
    void testEnqueueAndDequeue_WhenEnqueuingFourElementsForCapacityThreeAndDequeuingThreeElements_ShouldReturnIsEmptyTrue() {
        RingBuffer<String> buffer = new RingBuffer<>(3);
        buffer.enqueue("a");
        buffer.enqueue("b");
        buffer.enqueue("c");
        buffer.enqueue("d");
        assertEquals("b", buffer.dequeue());
        assertEquals("c", buffer.dequeue());
        assertEquals("d", buffer.dequeue());
        assertTrue(buffer.isEmpty());
    }

    @Test
    void testEnqueueAndDequeue_WhenEnqueuingSixElementsForCapacityThreeAndDequeuingThreeElements_ShouldReturnIsEmptyTrue() {
        RingBuffer<String> buffer = new RingBuffer<>(3);
        buffer.enqueue("a");
        buffer.enqueue("b");
        buffer.enqueue("c");
        buffer.enqueue("d");
        buffer.enqueue("e");
        buffer.enqueue("f");
        assertEquals("d", buffer.dequeue());
        assertEquals("e", buffer.dequeue());
        assertEquals("f", buffer.dequeue());
        assertTrue(buffer.isEmpty());
    }

    @Test
    void testDequeue_WhenDequeuingElementsFromEmptyBuffer_ShouldThrowRuntimeException() {
        RingBuffer<String> buffer = new RingBuffer<>(3);
        assertTrue(buffer.isEmpty());
        assertThrows(RuntimeException.class, buffer::dequeue);
    }

    @Test
    void testEnqueueAndPeek_WhenEnqueuingThreeElementsForCapacityThreeAndPeek_ShouldReturnFirstElementTwoTimesWithoutRemovingItFromBuffer() {
        RingBuffer<String> buffer = new RingBuffer<>(3);
        buffer.enqueue("a");
        buffer.enqueue("b");
        buffer.enqueue("c");
        assertFalse(buffer.isEmpty());
        assertEquals("a", buffer.peek());
        assertEquals("a", buffer.peek());
    }

    @Test
    void testEnqueueAndPeek_WhenEnqueuingFourElementsForCapacityThreeAndPeek_ShouldReturnFirstElementTwoTimesWithoutRemovingItFromBuffer() {
        RingBuffer<String> buffer = new RingBuffer<>(3);
        buffer.enqueue("a");
        buffer.enqueue("b");
        buffer.enqueue("c");
        buffer.enqueue("d");
        assertEquals("b", buffer.peek());
        assertEquals("b", buffer.peek());
    }

    @Test
    void testPeek_WhenPeekOnEmptyBuffer_ShouldThrowRuntimeException() {
        RingBuffer<String> buffer = new RingBuffer<>(3);
        assertThrows(RuntimeException.class, buffer::peek);
    }

}
