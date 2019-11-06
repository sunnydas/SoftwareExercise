package com.code.immutable.ds;

public interface Stack<T> {

    public Stack<T> push(T e);

    public Stack<T> pop();

    public T peek();

    public boolean isEmpty();

}
