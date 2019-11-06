package com.code.immutable.ds.impl;

import com.code.immutable.ds.Stack;
import org.junit.Assert;
import org.junit.Test;

public class PersistentStackTest {


    @Test
    public void testImmutableStackBasicOperations() {
        Stack<Integer> immutableStack = new PersistentStack<>(1,PersistentStack.getEmptyStack());
        Assert.assertTrue(immutableStack.isEmpty() == false);
        Assert.assertTrue(immutableStack.peek() == 1);
        Stack<Integer> popped = immutableStack.pop();
        Assert.assertTrue(immutableStack.isEmpty() == false);
        Assert.assertTrue(immutableStack.peek() == 1);
        Assert.assertTrue(popped.isEmpty() == true);
    }


    @Test
    public void testImmutableStacksEmptyStackPop(){
        Stack<Integer> emptyStack = PersistentStack.getEmptyStack();
        Assert.assertTrue(emptyStack.isEmpty());
        try {
            emptyStack.pop();
            Assert.assertFalse(true);
        }catch(IllegalArgumentException e){
            Assert.assertNotNull(e);
        }
    }


    @Test
    public void testImmutableStackEmptyStackPeek(){
        Stack<Integer> emptyStack = PersistentStack.getEmptyStack();
        Assert.assertTrue(emptyStack.isEmpty());
        try {
            emptyStack.peek();
            Assert.assertFalse(true);
        }catch(IllegalArgumentException e){
            Assert.assertNotNull(e);
        }
    }

    @Test
    public void testImmutableStackEmptyStackPush(){
        Stack<Integer> emptyStack = PersistentStack.getEmptyStack();
        Assert.assertTrue(emptyStack.isEmpty());
        Stack<Integer> pushed = emptyStack.push(1);
        Assert.assertFalse(pushed.isEmpty());
        Assert.assertTrue(pushed.peek() == 1);
        Assert.assertTrue(emptyStack.isEmpty());
    }

    @Test
    public void testAlternatePushPop(){
        Stack<Integer> emptyStack = PersistentStack.getEmptyStack();
        Assert.assertTrue(emptyStack.isEmpty());
        Stack<Integer> pushed = emptyStack.push(1);
        Assert.assertFalse(pushed.isEmpty());
        Assert.assertTrue(pushed.peek() == 1);
        Stack<Integer> popped = pushed.pop();
        Assert.assertTrue(popped.isEmpty());
        Stack<Integer> pushed1 = popped.push(2);
        Assert.assertFalse(pushed1.isEmpty());
        Assert.assertTrue(pushed1.peek() == 2);
        Stack<Integer> popped1 = pushed1.pop();
        Assert.assertTrue(popped1.isEmpty());
    }

    @Test
    public void testImmutablityPropertyOfStack(){
        Stack<Integer> emptyStack = PersistentStack.getEmptyStack();
        Assert.assertTrue(emptyStack.isEmpty());
        Stack<Integer> pushed = emptyStack.push(1);
        Assert.assertFalse(pushed.isEmpty());
        Assert.assertFalse(pushed == emptyStack);
        Stack<Integer> pushed1 = pushed.push(2);
        Assert.assertFalse(pushed1.isEmpty());
        Assert.assertFalse(pushed1 == pushed);
    }

    @Test
    public void testLargePushAndPop(){
        Stack<Integer> stack = PersistentStack.getEmptyStack();
        Assert.assertTrue(stack.isEmpty());
        for(int i = 0 ; i < 15000 ; i++){
            stack = stack.push(i);
        }
        for(int i = 14999; i >= 0 ; i--){
            Assert.assertTrue(stack.peek() == i);
            stack = stack.pop();
        }
    }

}
