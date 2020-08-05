import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PA6PublicTestCases {

    /*
     * Uncomment the test cases to enable them
     *
     * You can also add a junit test case with:
     * Right click on "(default pacakge)",
     * select new JUnit Test Case,
     * select New JUnit 4 test and give a name,
     * click Finish
     * Click Yes or Ok to include the JUnit 4 libraries
     */

    @Test
    public void testArrayStackGeneral() {
         StackInterface stack = new ArrayStack();
         stack.push(1);
         stack.push(2);
         stack.push(3);
         stack.pop();
         assertEquals("{ 1, 2, }", stack.toString());
    }

    @Test
    public void testArrayStackClear() {
        StackInterface stack = new ArrayStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.clear();
        int size = stack.size();
        boolean flag = true;
        for (int i = 0; i < size; i++) {
            if (stack.peek() != 0) {
                flag = false;
            }
        }
        assertTrue(flag);
    }

    @Test
    public void testArrayStackPopAll() {
        StackInterface stack = new ArrayStack();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        for (int j = 0; j < 5; j++) {
            stack.pop();
        }
        assertEquals(0, stack.size());
    }


    @Test
    public void testArrayStackPeek() {
        StackInterface stack = new ArrayStack();
        stack.push(2);
        stack.push(4);
        stack.push(6);
        stack.pop();
        assertEquals(4, stack.peek());
    }
    @Test
    public void testArrayStackCopyConstructor() {
        StackInterface stack = new ArrayStack();
        stack.push(2);
        stack.push(4);
        stack.push(6);
        StackInterface stack2 = new ArrayStack((ArrayStack) stack);
        assertEquals(stack.toString(), stack2.toString());
    }
    @Test
    public void testArrayStackEquals() {
        StackInterface stack = new ArrayStack();
        stack.push(2);
        stack.push(4);
        stack.push(6);
        StackInterface stack2 = new ArrayStack();
        stack2.push(2);
        stack2.push(4);
        stack2.push(6);
        boolean test = stack.equals(stack2);
        assertTrue(test);
    }

    @Test
    public void testListStackGeneral() {
         StackInterface stack = new ListStack();
         stack.push(1);
         stack.push(2);
         stack.push(3);
         stack.pop();
         assertEquals("{ 1, 2, }", stack.toString());
    }

    @Test
    public void testListStackPeek() {
        StackInterface stack = new ListStack();
        stack.push(2);
        stack.push(4);
        stack.push(6);
        stack.pop();
        assertEquals(4, stack.peek());
    }

    @Test
    public void testListStackPopAll() {
        StackInterface stack = new ListStack();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        for (int j = 0; j < 5; j++) {
            stack.pop();
        }
        assertEquals(0, stack.size());
    }

    @Test
    public void testListStackEquals() {
        StackInterface stack = new ListStack();
        stack.push(2);
        stack.push(4);
        stack.push(6);
        StackInterface stack2 = new ListStack();
        stack2.push(2);
        stack2.push(4);
        stack2.push(6);
        boolean test = stack.equals(stack2);
        assertTrue(test);
    }

    @Test
    public void testArrayQueueGeneral() {
         QueueInterface q = new ArrayQueue();
         q.enqueue(1);
         q.enqueue(2);
         q.enqueue(3);
         q.dequeue();
         assertEquals("{ 2, 3, }", q.toString());
    }

    @Test
    public void testArrayQueuePeek() {
        QueueInterface q = new ArrayQueue();
        q.enqueue(2);
        q.enqueue(4);
        q.enqueue(6);
        q.dequeue();
        assertEquals(4, q.peek());
    }

    @Test
    public void testArrayQueueDequeueAll() {
        QueueInterface q = new ArrayQueue();
        for (int i = 0; i < 5; i++) {
            q.enqueue(i);
        }
        for (int j = 0; j < 5; j++) {
            q.dequeue();
        }
        assertEquals(0, q.size());
    }

    @Test
    public void testListQueueSize() {
         QueueInterface q = new ListQueue();
         q.enqueue(1);
         q.enqueue(2);
         q.enqueue(3);
         q.dequeue();
         assertEquals(2, q.size());
    }

    @Test
    public void testListQueueGeneral() {
        QueueInterface q = new ListQueue();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.dequeue();
        assertEquals("{ 2, 3, }", q.toString());
    }

    @Test
    public void testListQueuePeek() {
        QueueInterface q = new ListQueue();
        q.enqueue(2);
        q.enqueue(4);
        q.enqueue(6);
        q.dequeue();
        assertEquals(4, q.peek());
    }

    @Test
    public void testListQueueDequeueAll() {
        QueueInterface q = new ListQueue();
        for (int i = 0; i < 5; i++) {
            q.enqueue(i);
        }
        for (int j = 0; j < 5; j++) {
            q.dequeue();
        }
        assertEquals(0, q.size());
    }

    @Test
    public void testListQueueCopyConstructGeneral() {
         QueueInterface q = new ListQueue();
         q.enqueue(1);
         q.enqueue(2);
         q.enqueue(3);
         QueueInterface q2 = new ListQueue((ListQueue) q);
         assertEquals(q.toString(), q2.toString());
    }
}

