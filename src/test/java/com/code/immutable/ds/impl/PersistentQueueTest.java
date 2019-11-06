package com.code.immutable.ds.impl;

import com.code.immutable.ds.Queue;
import org.junit.Assert;
import org.junit.Test;

public class PersistentQueueTest {


    @Test
    public void testPersistentQueueBasic(){
        Queue<Integer> queue = PersistentQueue.getEmptyQueue();
        Queue<Integer> enqueued = queue.enQueue(1);
        Assert.assertFalse(enqueued.isEmpty());
        Assert.assertTrue(enqueued.head() == 1);
        Assert.assertTrue(queue.isEmpty());
    }

    @Test
    public void testEmptyQueueProperty(){
        Queue<Integer> emptyQueue = PersistentQueue.getEmptyQueue();
        Assert.assertTrue(emptyQueue.isEmpty());
    }

    @Test
    public void testEmptyQueueDequeue(){
        Queue<Integer> emptyQueue = PersistentQueue.getEmptyQueue();
        Assert.assertTrue(emptyQueue.isEmpty());
        try {
            emptyQueue.deQueue();
            Assert.assertFalse(true);
        }catch (IllegalArgumentException e){
            Assert.assertNotNull(e);
        }
    }


    @Test
    public void testEmptyQueueHead(){
        Queue<Integer> emptyQueue = PersistentQueue.getEmptyQueue();
        Assert.assertTrue(emptyQueue.isEmpty());
        try {
            emptyQueue.head();
            Assert.assertFalse(true);
        }catch (IllegalArgumentException e){
            Assert.assertNotNull(e);
        }
    }

    @Test
    public void testEmptyQueuePush(){
        Queue<Integer> emptyQueue = PersistentQueue.getEmptyQueue();
        Assert.assertTrue(emptyQueue.isEmpty());
        Queue<Integer> queue = emptyQueue.enQueue(1);
        Assert.assertFalse(queue.isEmpty());
        Assert.assertTrue(queue.head() == 1);
    }

    @Test
    public void testAlternateEnqDq(){
        Queue<Integer> emptyQueue = PersistentQueue.getEmptyQueue();
        Assert.assertTrue(emptyQueue.isEmpty());
        Queue<Integer> queue = emptyQueue.enQueue(1);
        Assert.assertFalse(queue.isEmpty());
        Queue<Integer> queue1 = queue.deQueue();
        Assert.assertTrue(queue1.isEmpty());
    }


    @Test
    public void testLargeEnqAndDq(){
        Queue<Integer> queue = PersistentQueue.getEmptyQueue();
        Assert.assertTrue(queue.isEmpty());
        for(int i = 0 ; i < 10000; i++){
            queue = queue.enQueue(i);
        }
        for(int i = 0 ; i < 10000 ; i++){
            Assert.assertTrue(queue.head() == i);
            queue = queue.deQueue();
        }
        Assert.assertTrue(queue.isEmpty());
    }

    @Test
    public void testImmutabilityProperty(){
        Queue<Integer> queue = PersistentQueue.getEmptyQueue();
        Assert.assertTrue(queue.isEmpty());
        Queue<Integer> queue1 = queue.enQueue(1);
        Assert.assertFalse(queue1.isEmpty());
        Assert.assertFalse(queue == queue1);
    }


    @Test
    public void testHeadFunctionality(){
        Queue<Integer> queue = PersistentQueue.getEmptyQueue();
        for(int i = 0 ; i < 10000; i++){
            queue = queue.enQueue(i);
        }
        for(int i = 0 ; i < 10000; i++){
            Assert.assertTrue(queue.head() == i);
            queue = queue.deQueue();
        }

    }
}
