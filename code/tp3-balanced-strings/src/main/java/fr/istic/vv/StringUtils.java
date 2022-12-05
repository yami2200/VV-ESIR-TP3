package fr.istic.vv;

import java.util.Stack;

public class StringUtils {

    private StringUtils() {}

    private static char reverseChar(char c) {
        assert(c=='['||c=='{'||c=='(');
        if(c=='[') { return ']'; }
        if(c=='{') { return '}'; }
        //if(str.equals("(")) { return ")"; }
        return ')';
    }

    public static boolean isBalanced(String str) {
        Stack<Character> memory = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i)==']' || str.charAt(i)=='}' || str.charAt(i)==')') {
                if(memory.isEmpty()) { return false; }
                if(!(reverseChar(memory.peek())==str.charAt(i))) { return false; }
                memory.pop();
            }
             else if(str.charAt(i)=='[' || str.charAt(i)=='{' || str.charAt(i)=='(') {
                memory.push(str.charAt(i));
            }
        }
        return memory.isEmpty();
    }

    public static void main(String[] args) {
    }

}
