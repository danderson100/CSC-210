import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws Exception {

        //Patient patient = new Patient("Steve", 2);
//        pq.enqueue(patient);
//        pq.enqueue("Lisa", 9);
//        pq.enqueue("August", 1);
//        Patient patient1 = new Patient("Gloria", 7);
//        pq.enqueue(patient1);
//        pq.enqueue("Tere", 5);
//        pq.enqueue("Harry", 12);
//        pq.enqueue("Jill", 6);
//        pq.enqueue("Javier", 13);
//        System.out.println("queue before same priority....\n");
//        System.out.println(pq.toString());
//        pq.enqueue("Hannah", 1);
//        pq.enqueue("Xavier", 6);
//        System.out.println("after ties...\n");
//        System.out.println(pq.toString());
//        String name1 = pq.dequeue();
//        String name2 = pq.dequeue();
//        System.out.println(name1);
//        System.out.println(name2);
//
//        pq.enqueue("Jamal", 22);
//        pq.enqueue("Carolyn", 3);
//        pq.enqueue("Razo", 30);
//        pq.enqueue("hesster", 55);
//        System.out.println(pq.toString());
//        pq.changePriority("Javier", 4);
//        pq.changePriority("Jill", 10);
//        System.out.println("-----------------");
//        int x = pq.peekPriority();
//        System.out.println("Should be 3, got: " + x);
//        System.out.println(pq.toString());
//        System.out.println(pq.dequeue());
//        System.out.println(pq.dequeue());
//        System.out.println("-------------------");
//        System.out.println(pq.toString());
//
//
//       // System.out.println(patientQueue.toString());

//        PatientQueue pq = new PatientQueue();
//

//        pq.changePriority("Harry", 3);
//        System.out.println(pq.toString());

        PatientQueue pq = new PatientQueue();
        pq.enqueue("Brandon", 3);
        pq.enqueue("Tere", 5);
        pq.enqueue("Tyler", 6);
        pq.enqueue("Andrew", 5);
        pq.enqueue("Harry", 12);
        pq.enqueue("Jill", 6);
        pq.enqueue("Javier", 13);
        System.out.println(pq.toString());
        pq.changePriority("Tyler", 20);
        pq.changePriority("Jill", 1);
        System.out.println(pq.toString());
    }
}
