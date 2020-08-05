/*
 * There is no requirement for a file header comment for this
 * assignment. Spend your time writing good testcases instead!
 */

import java.util.EmptyStackException;
import java.util.Queue;
import java.util.Stack;

public class Recursion {

    /**
     * Write a recursive function that finds the index of s2 in s1. Do not use any
     * string functions except for .length(), .equals(), and .substring(). Do not use
     * any loops, or any data structures.
     *
     * @param s1
     * @param s2
     * @return Returns the index of the first time that
     * s2 appears in s1 or -1 if s2 is not contained
     * in s1.
     * <p>
     * Example: s1 = "Hello" s2 = "lo"  expected output: 3
     */
    public static int indexOf(String s1, String s2) {
        int index = 0;
        return indexOfHelper(s1, s2, index);

    }

    /*
     * Purpose: This is a helper function that allows me to increment
     * the index by 1 so I can scan s1 for s2. The first condition
     * checks if s2.length is greater than s1, or if s1 has
     * no length, in which case it would be impossible to find s2.
     */
    private static int indexOfHelper(String s1, String s2, int index) {
        int length = s2.length();
        if (s1.length() < 1 || s2.length() > s1.length()) {
            return -1;
        }

        if (s1.substring(0, length).equals(s2)) {
            return index;
        } else {
            return indexOfHelper(s1.substring(1), s2, index + 1);
        }

    }

    /**
     * Write a recursive function that removes the first k even numbers
     * from the stack. If there are less than k even elements in the stack,
     * just remove all even elements. Do not use any loops or data structures
     * other than the stack passed in as a parameter.
     *
     * @param stack
     * @param k
     * @return Returns the number of elements removed from the stack.
     * <p>
     */

    public static int removeEvenNumbers(Stack<Integer> stack, int k) {
        return removeEvenNumbersHelper(stack, k, stack.size());
    }
    /*
    * Purpose: This helper method allows me to count the number of times
    * the algorithm recursively works through the stack. Once count equals
    * the original stack size, it has finished.
     */
    private static int removeEvenNumbersHelper(Stack<Integer> stack, int k, int count) {

        if (count == 0 || k == 0) {
            return 0;
        } else {

            int x = stack.pop();

            if (x % 2 == 0) {
                return 1 + removeEvenNumbersHelper(stack, k - 1, count - 1);

            } else {
                stack.insertElementAt(x, 0);
                return removeEvenNumbersHelper(stack, k, count - 1);
            }
        }
    }

    /**
     * Write a recursive function that accepts an integer and
     * returns a new number containing only the even digits, in the same
     * order. If there are no even digits, return 0. Your function should
     * work for positive or negative numbers. You are NOT allowed
     * to use any data structures. You are not allowed to use Strings to
     * solve this problem either.
     *
     * @param n
     * @return The input with only the even digits remaining in the same
     * order.
     * <p>
     * Example input: n = 3845  expected output: 84
     */
    public static int evenDigits(int n) {
        if (n < 0) {
            return evenDigits(-n);
        } else if (n == 0) {
            return 0;

        } else {
            int digit = evenDigits(n / 10);
            int a = n % 10;
            if (a % 2 == 0) {
                digit *= 10;
                digit += a;
            }

            return digit;
        }
    }

    /**
     * Write a recursive function that evaluates a Queue<Character> as a mathematical
     * expression. This queue can have any of the following characters:
     * { '(', ')', '+', '*'} or any single digit number. Evaluate this expression and
     * return the result. For example, for the expression:
     * "(((1+2)*(3+1))+(1*(2+2)))", each of these characters would be in the
     * q. As you recursively evaluate characters from the expression, you will
     * remove the characters from the q. After evaluating the above expression,
     * you should return 16. You are guaranteed that there are NO two digit numbers,
     * and that the expression is well formed (parenthesis match, etc...). Do not use any
     * loops. Do not use any data structures besides the q passed in as a parameter.
     *
     * @param q
     * @return The result of the mathematical expression.
     */
    public static int evaluate(Queue<Character> q) {
        return evaluateHelper(q, 0);
    }

    /*
     * I completely went down a rabbit hole for this one, and I couldn't
     * figure it out. At one point I had it working for a few
     * test cases, but now I've made it so messy I'm just lost.
     * Sorry!
     */
    private static int evaluateHelper(Queue<Character> q, int result) {

        if (q.isEmpty()) {
            return 0;
        }

        if (Character.isDigit(q.element())) {
            int a = Character.getNumericValue(q.remove());
            switch (q.element()) {
                case '+':
                    result += a;
                    return result + evaluateHelper(q, result);
                case '*':
                    result *= a;
                    return result * evaluateHelper(q, result);
                case ')':
                    q.remove();
                    return evaluateHelper(q, result);
            }
        } else {
            switch (q.element()) {
                case '+':
                    q.remove();
                    if (Character.getNumericValue(q.element()) >= 0) {
                        result += Character.getNumericValue(q.remove());
                        return result + evaluateHelper(q, result);
                    } else {
                        q.remove();
                        return evaluateHelper(q, result);
                    }
                    //add the previous element to the next element
                case '*':
                    q.remove();
                    if (Character.getNumericValue(q.element()) >= 0) {
                        result *= Character.getNumericValue(q.remove());
                        return result * evaluateHelper(q, result);
                    } else {
                        q.remove();
                        return evaluateHelper(q, result);
                    }
            }
        }
        return result;
    }

    /**
     * Write a recursive function that accepts a stack of integers and
     * replaces each int with two copies of that integer. For example,
     * calling repeatStack and passing in a stack of { 1, 2, 3} would change
     * the stack to hold { 1, 1, 2, 2, 3, 3}. Do not use any loops. Do not use
     * any data structures other than the stack passed in as a parameter.
     *
     * @param stack Example: Stack = [1, 2, 3]  output = [1, 1, 2, 2, 3, 3]
     */
    public static void repeatStack(Stack<Integer> stack) {
        repeatStackHelper(stack, stack.size(), 0);

    }

    /*
     * Purpose: This helper method allows me to retain the original size of the stack.
     * I used this to test when the stack's modified size had become twice
     * the original, because that would indicate that it's finished adding. I also
     * added int index so I could increment through the stack.
     */
    private static void repeatStackHelper(Stack<Integer> stack, int originalSize, int index) {
        if (stack.size() < originalSize * 2) {
            int a = stack.get(index);
            stack.insertElementAt(a, index);
            repeatStackHelper(stack, originalSize, index + 2);

        }
    }

    /**
     * Write a recursive function that accepts a Queue<Integer>. It
     * should change every int in this queue to be double its original
     * value. You may NOT use loops or any other data structures besides
     * the queue passed in as a parameter. You may use a helper function.
     *
     * @param q Example: q = [2, 5, 12]   expected output = [4, 10, 24]
     */
    public static void doubleElements(Queue<Integer> q) {

        doubleElementsHelper(q, q.size());
    }

    /*
     * Purpose: This helper method helps to double the elements in the Queue.
     * I added int size to get the original size of the queue because that tells
     * me how many times to perform the operation. I decremented the size int
     * by one after each iteration.
     */
    private static void doubleElementsHelper(Queue<Integer> q, int size) {

        if (size > 0) {
            int x = q.remove();
            x *= 2;
            q.add(x);
            doubleElementsHelper(q, size - 1);

        }
    }
}

