import java.util.Stack;

public class Main {

    public static void main(String[] args) {
//
//        Stack<Integer> javaStack = new Stack<>();
//        javaStack.push(1);
//        javaStack.push(2);
//        System.out.println(javaStack.toString());
//        javaStack.clear();
//        System.out.println(javaStack.toString());
//
//        ListStack<String> stack = new ListStack<>();
//        stack.push("hello");
//        stack.push("goodbye");
//        stack.push("hello");
//        stack.clear();
//        System.out.println(stack.toString());

//        Stack<Integer> stack2 = new Stack<>();
//        System.out.println(stack2.pop());
//        //put tests here

//        StackInterface<Integer> stack = new ListStack<>();
//        stack.push(100);
////        stack.push(10);
////        stack.push(0);
//        int x = stack.pop();
//        System.out.println(x);

        Stack<String> stack = new Stack<>();
        stack.push("hello");
        stack.push("what is going on?");
        stack.push("goodbye");
        String s = stack.toString();
        System.out.println(s);
    }
}
