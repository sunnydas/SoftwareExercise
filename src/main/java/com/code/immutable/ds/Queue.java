package com.code.immutable.ds;

public interface Queue<T> {
    public Queue<T> enQueue(T t);
    //Removes the element at the beginning of the immutable ds, and returns the new ds.
    public Queue<T> deQueue();
    public T head();
    public boolean isEmpty();
}