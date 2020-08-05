import org.junit.Test;
import static org.junit.Assert.*;

public class PA9PublicTestCases {

    @Test
    public void testDummyTest() {
        assertFalse(false);
    }

    @Test
    public void testEnqueueGeneral() throws Exception {
        PatientQueue pq = new PatientQueue();
        pq.enqueue("Lisa", 9);
        pq.enqueue("August", 1);
        pq.enqueue("Joe", 3);
        String s = pq.dequeue();
        assertEquals("August", s);
    }

    @Test
    public void testEnqueueThenPeek1() {
        PatientQueue pq = new PatientQueue();
        pq.enqueue("Lisa", 9);
        pq.enqueue("August", 1);
        pq.enqueue("Joe", 3);
        String s = pq.peek();
        assertEquals("August", s);
    }

    @Test
    public void testEnqueueThenPeekPriority1() {
        PatientQueue pq = new PatientQueue();
        pq.enqueue("Tere", 5);
        pq.enqueue("Harry", 12);
        pq.enqueue("Jill", 6);
        pq.enqueue("Javier", 13);
        pq.enqueue("Jamal", 22);
        pq.enqueue("Carolyn", 3);
        pq.enqueue("Razo", 30);
        pq.enqueue("hesster", 55);
        int x = pq.peekPriority();
        assertEquals(3, x);
    }

    @Test
    public void testPriorityQueueSize1() {
        PatientQueue pq = new PatientQueue();
        pq.enqueue("Tere", 5);
        pq.enqueue("Harry", 12);
        pq.enqueue("Jill", 6);
        pq.enqueue("Javier", 13);
        pq.enqueue("Jamal", 22);
        pq.enqueue("Carolyn", 3);
        pq.enqueue("Razo", 30);
        pq.enqueue("hesster", 55);
        int x = pq.size();
        assertEquals(8, x);
    }

    @Test
    public void testPriorityQueueisEmpty1() {
        PatientQueue pq = new PatientQueue();
        pq.enqueue("Tere", 5);
        pq.enqueue("Harry", 12);
        boolean check = pq.isEmpty();
        assertFalse(check);
    }

    @Test
    public void testPriorityQueueisEmpty2() {
        PatientQueue pq = new PatientQueue();
        pq.enqueue("Tere", 5);
        pq.enqueue("Harry", 12);
        pq.clear();
        boolean check = pq.isEmpty();
        assertTrue(check);
    }

    @Test
    public void testEnqueueViaConstructor() {
        PatientQueue pq = new PatientQueue();
        Patient patient1 = new Patient("Lisa", 9);
        Patient patient3 = new Patient("Joe", 3);
        Patient patient2 = new Patient("August", 1);
        pq.enqueue(patient1);
        pq.enqueue(patient2);
        pq.enqueue(patient3);
        String s = pq.peek();
        assertEquals("August", s);
    }

    @Test
    public void testChangePriorityBubbleUp() {
        PatientQueue pq = new PatientQueue();
        pq.enqueue("Tere", 5);
        pq.enqueue("Harry", 12);
        pq.enqueue("Jill", 6);
        pq.enqueue("Javier", 13);
        pq.changePriority("Harry", 3);
        String name = pq.peek();
        assertEquals("Harry", name);
    }

    @Test
    public void testChangePriorityBubbleDown() {
        PatientQueue pq = new PatientQueue();
        pq.enqueue("Tere", 5);
        pq.enqueue("Harry", 12);
        pq.enqueue("Jill", 6);
        pq.enqueue("Javier", 13);
        pq.changePriority("Jill", 14);
        String name = "{Tere (5), Harry (12), Jill (14), Javier (13)}";
        assertEquals(name, pq.toString());
    }
}

