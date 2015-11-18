
/*
 MIT-LICENSE
 */
package os_lab_project;

import java.util.concurrent.Semaphore;

/**
 *
 * @author alokrajiv
 * @author-mail mail@alokrajiv.com
 */
public class ProducerConsumerUsingSemaphores {

    public static void main(String[] args) {
        ProducerConsumerUsingSemaphores_Queue qobject = new ProducerConsumerUsingSemaphores_Queue();
        Thread consumerthread = new Thread(new ProducerConsumerUsingSemaphores_Consumer(qobject));
        Thread producerThread = new Thread(new ProducerConsumerUsingSemaphores_Producer(qobject));
        consumerthread.start();
        producerThread.start();
    }
}

class ProducerConsumerUsingSemaphores_Queue {
    
    int bufferFilledCounter = 0,
            producerIndex = 0,
            consumerIndex = 0,
            bufferSize = 3;

    int[] boundedBuffer = new int[bufferSize];
    
    
    Semaphore binary_mutex = new Semaphore(1);
    Semaphore filled_slots = new Semaphore(0);
    Semaphore empty_slots = new Semaphore(bufferSize);
    
    synchronized public void put(int i) {
        boundedBuffer[producerIndex] = i;
        bufferFilledCounter++;
        producerIndex++;
        producerIndex %= bufferSize;
    }

    synchronized public int get() {
        int readItem = boundedBuffer[consumerIndex];
        bufferFilledCounter--;
        consumerIndex++;
        consumerIndex %= bufferSize;
        return readItem;
    }

}

class ProducerConsumerUsingSemaphores_Producer implements Runnable {

    ProducerConsumerUsingSemaphores_Queue queue_obj;

    ProducerConsumerUsingSemaphores_Producer(ProducerConsumerUsingSemaphores_Queue queobject) {
        queue_obj = queobject;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; ; i++) {
                Thread.sleep(1000);
                queue_obj.empty_slots.acquire();
                queue_obj.binary_mutex.acquire();
                queue_obj.put(i);
                queue_obj.binary_mutex.release();
                queue_obj.filled_slots.release();
                System.out.println("produced =" + i);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

class ProducerConsumerUsingSemaphores_Consumer implements Runnable {

    ProducerConsumerUsingSemaphores_Queue queue_obj;

    ProducerConsumerUsingSemaphores_Consumer(ProducerConsumerUsingSemaphores_Queue queobject) {
        queue_obj = queobject;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                queue_obj.filled_slots.acquire();
                queue_obj.binary_mutex.acquire();
                System.out.println(" The consumed value" + queue_obj.get());
                queue_obj.binary_mutex.release();
                queue_obj.empty_slots.release();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
