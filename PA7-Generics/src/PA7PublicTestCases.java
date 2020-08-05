import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class PA7PublicTestCases {

    /*
     * Uncomment the test cases to enable them
     *
     * You can also add a junit test case with:
     * Right click on "(default pacakge)",
     * select new JUnit Test Case,
     * select New JUnit 4 or 5 test and give a name,
     * click Finish
     * Click Yes or Ok to include the JUnit 4 or 5 libraries
     */

    @Test
    public void testDummyTest() {
        assertFalse(false);
    }

    @Test
    public void testListStackStringGeneral() {
        StackInterface<String> s = new ListStack<>();
        s.push("yeet");
        s.push("teey");
        s.push("etty");
        s.pop();
        assertEquals(2, s.size());
    }

    @Test
    public void testListStackCharGeneral() {
        StackInterface<Character> s = new ListStack<>();
        s.push('s');
        s.push('g');
        s.push('y');
        s.pop();
        assertEquals(2, s.size());
    }

    @Test
    public void testListStackCharEqual() {
        StackInterface<Character> s1 = new ListStack<>();
        s1.push('s');
        s1.push('g');
        s1.push('y');
        StackInterface<Character> s2 = new ListStack<>();
        s2.push('s');
        s2.push('g');
        s2.push('y');
        boolean equals = s1.equals(s2);
        assertTrue(equals);
    }

    @Test
    public void testListStackNotEqual() {
        StackInterface<Character> s1 = new ListStack<>();
        s1.push('s');
        s1.push('g');
        s1.push('y');
        StackInterface<Character> s2 = new ListStack<>();
        s2.push('s');
        s2.push('p');
        s2.push('y');
        boolean equals = s1.equals(s2);
        assertFalse(equals);
    }

    @Test
    public void testListStackSameElement() {
        StackInterface<Character> s = new ListStack<>();
        s.push('s');
        s.push('s');
        s.push('s');
        s.pop();
        assertEquals(2, s.size());
    }

    @Test
    public void testListStackStringNotEmpty() {
        StackInterface<String> s = new ListStack<>();
        s.push("yeet");
        s.push("Helllooooo world");
        boolean check = s.isEmpty();
        assertFalse(check);
    }

    @Test
    public void testListStackStringisEmpty() {
        StackInterface<String> s = new ListStack<>();
        boolean check = s.isEmpty();
        assertTrue(check);
    }

    @Test
    public void testListStackCharacterGeneral() {
        StackInterface<Character> s = new ListStack<>();
        s.push('y');
        s.push('t');
        s.pop();
        s.push('e');
        assertEquals("{ y, e, }", s.toString());
    }


    @Test
    public void testListStackGeneral() {
        StackInterface<Integer> stack = new ListStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.pop();
        assertEquals("{ 1, 2, }", stack.toString());
    }

    @Test
    public void testListStackPeek() {
        StackInterface<Integer> stack = new ListStack<>();
        stack.push(2);
        stack.push(4);
        stack.push(6);
        stack.pop();
        int x = stack.peek();
        assertEquals(4, x);
    }

    @Test
    public void testListStackPop1() {
        StackInterface<Integer> stack = new ListStack<>();
        stack.push(100);
//        stack.push(10);
//        stack.push(0);
        int x = stack.pop();
        assertEquals(100, x);
    }

    @Test
    public void testListStackPopAll() {
        StackInterface<Integer> stack = new ListStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        for (int j = 0; j < 5; j++) {
            stack.pop();
        }
        assertEquals(0, stack.size());
    }

    @Test
    public void testListStackPopAllToString() {
        StackInterface<Integer> stack = new ListStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        for (int j = 0; j < 5; j++) {
            stack.pop();
        }
        String emptyStack = stack.toString();
        assertEquals("{ }", emptyStack);
    }

    @Test
    public void testListStackEquals() {
        StackInterface<Integer> stack = new ListStack<>();
        stack.push(2);
        stack.push(4);
        stack.push(6);
        StackInterface<Integer> stack2 = new ListStack<>();
        stack2.push(2);
        stack2.push(4);
        stack2.push(6);
        boolean test = stack.equals(stack2);
        assertTrue(test);
    }

    @Test
    public void testListStackPrintStringStack() {
        StackInterface<String> stack = new ListStack<>();
        stack.push("hello");
        stack.push("what is going on?");
        stack.push("goodbye");
        String s = stack.toString();
        assertEquals("{ hello, what is going on?, goodbye, }", s);
    }


    @Test
    public void testListQueueIntegerGeneral() {
        QueueInterface<Integer> q = new ListQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.dequeue();
        assertEquals("{ 2, 3, }", q.toString());
    }

    @Test
    public void testListQueueNotEmpty() {
        QueueInterface<Integer> q = new ListQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        boolean check = q.isEmpty();
        assertFalse(check);
    }

    @Test
    public void testListQueueIsEmpty() {
        QueueInterface<Integer> q = new ListQueue<>();
        boolean check = q.isEmpty();
        assertTrue(check);
    }

    @Test
    public void testListQueueDoubleGeneral() {
        QueueInterface<Double> q = new ListQueue<>();
        q.enqueue(1.0);
        q.enqueue(2.0);
        q.dequeue();
        q.enqueue(3.0);
        assertEquals(2, q.size());
    }

    @Test
    public void testListQueueSize() {
        QueueInterface<Integer> q = new ListQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.dequeue();
        assertEquals(2, q.size());
    }

    @Test
    public void testLisQueuePrintStringQueue() {
        QueueInterface<String> q = new ListQueue<>();
        q.enqueue("hello");
        q.enqueue("what is going on?");
        q.enqueue("goodbye");
        String s = q.toString();
        assertEquals("{ hello, what is going on?, goodbye, }", s);
    }

    @Test
    public void testListQueueGeneral() {
        QueueInterface<Integer> q = new ListQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.dequeue();
        assertEquals("{ 2, 3, }", q.toString());
    }

    @Test
    public void testListQueuePeek() {
        QueueInterface<Integer> q = new ListQueue<>();
        q.enqueue(2);
        q.enqueue(4);
        q.enqueue(6);
        q.dequeue();
        int x = q.peek();
        assertEquals(4, x);
    }


    @Test
    public void testListQueueDequeue1() {
        QueueInterface<Integer> q = new ListQueue<>();
        q.enqueue(2);
        int x = q.dequeue();
        assertEquals(2, x);
    }


    @Test
    public void testListQueueClear() {
        QueueInterface<Integer> q = new ListQueue<>();
        q.enqueue(2);
        q.enqueue(4);
        q.enqueue(6);
        q.clear();
        assertEquals(0, q.size());
    }

    @Test
    public void testListQueueEquals() {
        QueueInterface<Integer> q1 = new ListQueue<>();
        q1.enqueue(2);
        q1.enqueue(4);
        q1.enqueue(6);
        QueueInterface<Integer> q2 = new ListQueue<>();
        q2.enqueue(2);
        q2.enqueue(4);
        q2.enqueue(6);
        boolean check = q1.equals(q2);
        assertTrue(check);
    }

    @Test
    public void testListQueueNotEqual() {
        QueueInterface<Integer> q1 = new ListQueue<>();
        q1.enqueue(2);
        q1.enqueue(4);
        q1.enqueue(6);
        QueueInterface<Integer> q2 = new ListQueue<>();
        q2.enqueue(2);
        q2.enqueue(4);
        q2.enqueue(5);
        boolean check = q1.equals(q2);
        assertFalse(check);
    }

    @Test
    public void testListQueueDequeueAll() {
        QueueInterface<Integer> q = new ListQueue<>();
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
        QueueInterface<Integer> q = new ListQueue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        ListQueue q2 = new ListQueue<>((ListQueue) q);
        assertEquals(q.toString(), q2.toString());
    }
}

