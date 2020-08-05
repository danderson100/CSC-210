import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class Main {

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.clear();
        System.out.println(stack);
//        StackInterface stack = new ArrayStack();
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        System.out.println(stack.pop());
//        System.out.println("Should look like: { 1, 2, }. Got: " + stack.toString());
//
//        StackInterface stack2 = new ArrayStack();
//        stack2.push(2);
//        stack2.push(4);
//        stack2.push(6);
//        stack2.pop();
//        System.out.println("Should get: 4. Got: " + stack2.peek());
//
//        QueueInterface q = new ArrayQueue();
//        q.enqueue(1);
//        q.enqueue(2);
//        q.enqueue(3);
//        System.out.println(q.dequeue());
//        System.out.println("Expected { 2, 3, }. Got: " + q.toString());
//
//        StackInterface listStack = new ListStack();
//        listStack.push(1);
//        listStack.push(2);
//        listStack.push(3);
//        listStack.pop();
//        System.out.println("Expected 2, got " + listStack.size());
//
//        StackInterface listStack2 = new ListStack();
//        listStack2.push(1);
//        listStack2.push(2);
//        listStack2.push(3);
//        System.out.println(listStack2.toString());
//        System.out.println(listStack2.peek());
//        listStack2.pop();
//        System.out.println(listStack2.toString());
//        System.out.println("Expected { 1, 2, }, got:  " + listStack2.toString());
//
//        QueueInterface q = new ListQueue();
//        q.enqueue(1);
//        q.enqueue(2);
//        q.enqueue(3);
//        q.dequeue();
//        System.out.println("Expected { 2, 3, }, got: " + q.toString());
//
//        StackInterface stack = new ArrayStack();
//        for (int i = 0; i < 5; i++) {
//            stack.push(i);
//        }
//        for (int j = 0; j < 5; j++) {
//            stack.pop();
//        }
//        System.out.println("Size should be 0. Got " + stack.size());
//
//    }
    }
}
