package com.code.immutable.ds.impl;

import com.code.immutable.ds.Stack;

/**
 * This class is loosely based on Persistent stacks, as implemnting immutability using
 * cloning/copy on write might be expensive.
 *
 *
 * @param <T>
 */
public final class PersistentStack<T> implements Stack<T> {

     static final class EmptyPersistentStack<T> implements Stack<T>{

        private final static String emptyStackException = "Empty stack operation not allowed";

        private EmptyPersistentStack() {
        }

        @Override
        public Stack<T> push(T e){
            return new PersistentStack<>(e,this);
        }

        @Override
        public Stack<T> pop() {
            throw new IllegalArgumentException(emptyStackException);
        }

        @Override
        public T peek() {
            throw new IllegalArgumentException(emptyStackException);
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    }

    private final T currentValue;
    private final Stack<T> previousStack;
    private static EmptyPersistentStack emptyPersistentStack = new EmptyPersistentStack();


    public PersistentStack(final T currentValue, final Stack<T> previousStack) {
        this.currentValue = currentValue;
        this.previousStack = previousStack;
    }

    public static EmptyPersistentStack getEmptyStack(){
        return emptyPersistentStack;
    }

    @Override
    public Stack<T> push(final T e){
        return new PersistentStack(e,this);
    }

    @Override
    public Stack<T> pop() {
        return this.previousStack;
    }

    @Override
    public T peek() {
        return this.currentValue;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
