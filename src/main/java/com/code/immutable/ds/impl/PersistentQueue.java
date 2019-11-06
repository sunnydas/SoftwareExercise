package com.code.immutable.ds.impl;

import com.code.immutable.ds.Queue;
import com.code.immutable.ds.Stack;
import com.code.immutable.ds.util.Util;

/**
 * Loosely based on Persistent queues and reuses double Persistence stacks.
 * If we implement immutability by cloning the queue everytime a mutation operation is performed, it will
 * be inefficient.
 *
 * @param <T>
 */
public final class PersistentQueue<T> implements Queue<T> {

    private final Stack<T> frontTracker;

    private final Stack<T> reverseTracker;

    private static final class EmptyQueue<T> implements Queue<T>{

        private static final String emptyQueueMessage = "The queue is empty";

        @Override
        public Queue<T> enQueue(T t) {
            return new PersistentQueue(PersistentStack.getEmptyStack().push(t),PersistentStack.getEmptyStack());
        }

        @Override
        public Queue<T> deQueue() {
            throw new IllegalArgumentException(emptyQueueMessage);
        }

        @Override
        public T head() {
            throw new IllegalArgumentException(emptyQueueMessage);
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    }

    public static EmptyQueue getEmptyQueue() {
        return emptyQueue;
    }

    private static EmptyQueue emptyQueue = new EmptyQueue();

    public PersistentQueue(final Stack<T> frontTracker, final Stack<T> reverseTracker) {
        this.frontTracker = frontTracker;
        this.reverseTracker = reverseTracker;
    }


    @Override
    public Queue<T> enQueue(T t) {
        return new PersistentQueue<>(this.frontTracker,this.reverseTracker.push(t));
    }

    @Override
    public Queue<T> deQueue() {
        Stack<T> dqed = frontTracker.pop();
        if(!dqed.isEmpty()){
            return new PersistentQueue<>(dqed,this.reverseTracker);
        } else if(reverseTracker.isEmpty()){
            return getEmptyQueue();
        } else{
            return new PersistentQueue(Util.reverseStack(this.reverseTracker),PersistentStack.getEmptyStack());
        }
    }

    @Override
    public T head() {
        return this.frontTracker.peek();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
