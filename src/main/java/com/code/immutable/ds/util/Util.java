package com.code.immutable.ds.util;

import com.code.immutable.ds.Stack;
import com.code.immutable.ds.impl.PersistentStack;

public class Util {

    public static <T> Stack<T> reverseStack(Stack<T> stack){
        Stack<T> reversed =  PersistentStack.getEmptyStack();
        while(!stack.isEmpty()){
            reversed = reversed.push(stack.peek());
            stack = stack.pop();
        }
        return reversed;
    }

}
