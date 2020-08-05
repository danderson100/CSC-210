/*
 * This file will hold all of your testcases. Remember, to run all
 * of your tests, right-click on 'RunTests.java' and select 'Run As' ->
 * JUnit Test.
 */

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class RecursionTestClass {

    /*
     * indexOf_test1 tests when s2 is not a
     * substring of s1. This should return -1.
     */
    @Test
    public void test_indexOf_test1() {
        int result = Recursion.indexOf("Hello", "World");
        System.out.println("indexOf(Hello, World), got " + result);
        assertEquals(-1, result);
    }

    /*
     * indexOf_test2 tests when s2 is a substring
     * of s1 and it has a length of 2. Should return index 3.
     */
    @Test
    public void test_indexOf_test2() {
        int result = Recursion.indexOf("Hello", "lo");
        System.out.println("indexOf(Hello, lo), got " + result);
        assertEquals(3, result);
    }

    /*
     * indexOf_test3 tests when s2 the exact same
     * string as s1, in which case it should return
     * index 0.
     */
    @Test
    public void test_indexOf_test3() {
        int result = Recursion.indexOf("Hello", "Hello");
        System.out.println("indexOf(Hello, Hello), got " + result);
        assertEquals(0, result);
    }

    /*
     * indexOf_test4 tests when s2 only length 1 and s2
     * is a substring of s1. This should return index 4.
     */
    @Test
    public void test_indexOf_test4() {
        int result = Recursion.indexOf("Hello", "o");
        System.out.println("indexOf(Hello, o), got " + result);
        assertEquals(4, result);
    }

    /*
     * indexOf_test5 tests a case when s2 is written
     * in all-caps. It contains the word, but since s1 is
     * lowercase, it should return -1.
     */
    @Test
    public void test_indexOf_test5() {
        int result = Recursion.indexOf("Hello how are you", "YOU");
        System.out.println("indexOf(Hello, o), got " + result);
        assertEquals(-1, result);
    }

    /*
     * indexOf_test6 tests when there are multiple s2
     * characters in s1 and s1 is long. This should return
     * the index of the first appearance, so index 15.
     */
    @Test
    public void test_indexOf_test6() {
        int result = Recursion.indexOf("I am happy and love llamas!", "l");
        System.out.println("indexOf(I am happy and love llamas!, l), got " + result);
        assertEquals(15, result);
    }

    /*
     * test_removeEvenNumbers_test1 tests when the
     * stack has more than k even numbers, in which case
     * it should return 2.
     */
    @Test
    public void test_removeEvenNumbers_test1() {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(55);
        stack.push(6);
        stack.push(17);
        stack.push(8);
        int x = Recursion.removeEvenNumbers(stack, 2);
        System.out.println("removeEvenNumbers([2, 3, 4, 55, 6, 17, 8], 2), got " + x);
        assertEquals(2, x);
    }

    /*
     * test_removeEvenNumbers_test2 tests when the
     * stack has 0 even numbers with a k-value of 2. This
     * should return 0.
     */
    @Test
    public void test_removeEvenNumbers_test2() {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        int x = Recursion.removeEvenNumbers(stack, 2);
        System.out.println("removeEvenNumbers([3], 2), got " + x);
        assertEquals(0, x);
    }

    /*
     * test_removeEvenNumbers_test3 tests when there
     * are several even numbers but a k-value of 0. This
     * should return 0.
     */
    @Test
    public void test_removeEvenNumbers_test3() {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(55);
        stack.push(6);
        stack.push(17);
        stack.push(8);
        int x = Recursion.removeEvenNumbers(stack, 0);
        System.out.println("removeEvenNumbers([2, 3, 4, 55, 6, 17, 8], 0), got " + x);
        assertEquals(0, x);
    }

    /*
     * test_removeEvenNumbers_test4. This test is checking to see what happens
     * when k is greater than the total number of
     * even integers in the stack. This should return 4,
     * the number of even integers in the stack.
     */
    @Test
    public void test_removeEvenNumbers_test4() {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(55);
        stack.push(6);
        stack.push(17);
        stack.push(8);
        int x = Recursion.removeEvenNumbers(stack, 5);
        System.out.println("removeEvenNumbers([2, 3, 4, 55, 6, 17, 8], 5), got " + x);
        assertEquals(4, x);
    }

    /*
    * test_removeEvenNumbers_test5 checks what happens
    * when the k-value is greater than the stack size
    * and there are no even numbers. Should return
    * 0.
     */
    @Test
    public void test_removeEvenNumbers_test5() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(3);
        stack.push(5);
        stack.push(7);
        stack.push(9);

        int x = Recursion.removeEvenNumbers(stack, 6);
        System.out.println("removeEvenNumbers([1, 3, 5, 7, 9], 6), got " + x);
        assertEquals(0, x);
    }

    /*
     * test_evenDigits_test1 tests when there are
     * two even digits in the middle of the integer. This
     * should return 84.
     */
    @Test
    public void test_evenDigits_test1() {
        int x = Recursion.evenDigits(3845);
        System.out.println("evenDigits(3845), got " + x);
        assertEquals(84, x);
    }

    /*
     * test_evenDigits_test2 tests what happens when
     * a user enters a negative number with even digits.
     * This should return 404.
     */
    @Test
    public void test_evenDigits_test2() {
        int x = Recursion.evenDigits(-40354);
        System.out.println("evenDigits(-40354), got " + x);
        assertEquals(404, x);
    }

    /*
     * test_evenDigits_test2 tests the case where
     * there are no even digits in the integer. This
     * should return 0.
     */
    @Test
    public void test_evenDigits_test3() {
        int x = Recursion.evenDigits(13579);
        System.out.println("evenDigits(13579), got " + x);
        assertEquals(0, x);
    }

    /*
     * test_evenDigits_test4 tests what happens
     * when n = 0. Since 0 is an even number, it
     * should return 0.
     */
    @Test
    public void test_evenDigits_test4() {
        int x = Recursion.evenDigits(0);
        System.out.println("evenDigits(0), got " + x);
        assertEquals(0, x);
    }

    /*
     * test_evaluate_test1 tests a standard case where
     * we have two integers in parenthesis and one operator and integer
     * outside the parenthesis. The items in the queue look
     * like this: (4+3)*2   Queue [(,4,+,3,),*,2].
     * This should return 14.
     */
    @Test
    public void test_evaluate_test1() {
        Queue<Character> queue = new LinkedList<>();
        queue.add('(');
        queue.add('4');
        queue.add('+');
        queue.add('3');
        queue.add(')');
        queue.add('*');
        queue.add('2');
        int x = Recursion.evaluate(queue);
        System.out.println("evaluate([(4+3)*2], got " + x);
        assertEquals(14, x);
    }

    /*
     * test_evaluate_test2 tests a more complex case where
     * we have many operations and parenthesis wrapped
     * around each other, requiring the algorithm to wait
     * to perform operations.
     */
    @Test
    public void test_evaluate_test2() {
        Queue<Character> queue = new LinkedList<>();
        queue.add('(');queue.add('(');
        queue.add('(');queue.add('1');
        queue.add('+');queue.add('2');
        queue.add(')');queue.add('*');
        queue.add('(');queue.add('3');
        queue.add('+');queue.add('1');
        queue.add(')');queue.add(')');
        queue.add('+');queue.add('(');
        queue.add('1');queue.add('*');
        queue.add('(');queue.add('2');
        queue.add('+');queue.add('2');
        queue.add(')');queue.add(')');
        queue.add(')');
        int x = Recursion.evaluate(queue);
        System.out.println("evaluate[(((1+2)*(3+1))+(1*(2+2)))], got " + x);
        assertEquals(16, x);
    }
    /*
     * test_evaluate_test3 tests an intermediate case where
     * we have four operations and parenthesis wrapped
     * around each other, requiring the algorithm to wait
     * to perform operations.
     */
    @Test
    public void test_evaluate_test3() {
        Queue<Character> queue = new LinkedList<>();
        queue.add('(');queue.add('(');
        queue.add('2');queue.add('+');
        queue.add('1');queue.add(')');
        queue.add('*');queue.add('(');
        queue.add('3');queue.add('+');
        queue.add('5');queue.add(')');
        queue.add('+');queue.add('1');
        int x = Recursion.evaluate(queue);
        System.out.println("evaluate[((2+1)*(3+5)+1)], got " + x);
        assertEquals(25, x);
    }

    /*
     * test_repeatStack_test1 tests a standard case where
     * a user inputs a stack with the values [1, 2, 3]. This should
     * return [1, 1, 2, 2, 3, 3].
     */
    @Test
    public void test_repeatStack_test1() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Original Stack: " + stack);
        Recursion.repeatStack(stack);
        System.out.println("Modified Stack [1, 1, 2, 2, 3, 3], got " + stack);
        assertEquals("[1, 1, 2, 2, 3, 3]", stack.toString());
    }

    /*
     * test_repeatStack_test1 tests a case where we have
     * unordered integers, some of which are greater than 10.
     * This should return [6, 6, 8, 8, 12, 12, 2, 2, 1, 1].
     */
    @Test
    public void test_repeatStack_test2() {
        Stack<Integer> stack = new Stack<>();
        stack.push(6);
        stack.push(8);
        stack.push(12);
        stack.push(2);
        stack.push(1);
        System.out.println("Original Stack: " + stack);
        Recursion.repeatStack(stack);
        System.out.println("Modified Stack [6, 6, 8, 8, 12, 12, 2, 2, 1, 1], got " + stack);
        assertEquals("[6, 6, 8, 8, 12, 12, 2, 2, 1, 1]", stack.toString());
    }

    /*
     * test_repeatStack_test3 tests a case where we have
     * all zeroes in the stack. This should return
     * [0, 0, 0, 0, 0, 0, 0, 0]
     */
    @Test
    public void test_repeatStack_test3() {
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        stack.push(0);
        stack.push(0);
        stack.push(0);
        System.out.println("Original Stack: " + stack);
        Recursion.repeatStack(stack);
        System.out.println("Modified Stack [0, 0, 0, 0, 0, 0, 0, 0], got " + stack);
        assertEquals("[0, 0, 0, 0, 0, 0, 0, 0]", stack.toString());
    }
    /*
    * test_repeatStack_test4 tests a case where the algorithm is
    * passed an empty stack. Since there are no values to repeat,
    * it should return an empty stack [].
     */
    @Test
    public void test_repeatStack_test4() {
        Stack<Integer> stack = new Stack<>();
        System.out.println("Original Stack: " + stack);
        Recursion.repeatStack(stack);
        System.out.println("Modified Stack [], got " + stack);
        assertEquals("[]", stack.toString());
    }

    /*
     * test_doubleElements_test1 tests a simple case where three
     * numbers (2, 5, 12) are added to the Queue to be doubled.
     * The modified queue should return [4, 10, 24].
     */
    @Test
    public void test_doubleElements_test1() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(2);
        queue.add(5);
        queue.add(12);
        System.out.println("Original Queue: " + queue);
        Recursion.doubleElements(queue);
        System.out.println("Modified Queue [4, 10, 24], got " + queue);
        assertEquals("[4, 10, 24]", queue.toString());
    }

    /*
     * test_doubleElements_test2 tests a case where the user
     * inputs a negative number into the queue along with others.
     * This should return [-20, 44, 6].
     */
    @Test
    public void test_doubleElements_test2() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(-10);
        queue.add(22);
        queue.add(3);
        System.out.println("Original Queue: " + queue);
        Recursion.doubleElements(queue);
        System.out.println("Modified Queue [-20, 44, 6], got " + queue);
        assertEquals("[-20, 44, 6]", queue.toString());
    }

    /*
     * test_doubleElements_test3 tests a case where the user
     * inputs a zero and a one into the queue. Since 0 cannot
     * be doubled, this should return [0, 2, 4, 6].
     */
    @Test
    public void test_doubleElements_test3() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println("Original Queue: " + queue);
        Recursion.doubleElements(queue);
        System.out.println("Modified Queue [0, 2, 4, 6], got " + queue);
        assertEquals("[0, 2, 4, 6]", queue.toString());
    }
}