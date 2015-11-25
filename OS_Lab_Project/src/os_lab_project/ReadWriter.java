
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
public class ReadWriter {

    public static void main(String[] args) {
        Database qobject = new Database();
        Thread consumerthread = new Thread(new ReadWriter_Reader(qobject));
        Thread producerThread = new Thread(new ReadWriter_Writer(qobject));
        consumerthread.start();
        producerThread.start();
    }
}

class Database {
    
    int bufferFilledCounter = 0,
            producerIndex = 0,
            consumerIndex = 0,
            bufferSize = 3;

    int[] boundedBuffer = new int[bufferSize];
    
    
    Semaphore binary_mutex = new Semaphore(1);
    Semaphore filled_slots = new Semaphore(0);
    Semaphore empty_slots = new Semaphore(bufferSize);
    
    public void put(int i) {
        boundedBuffer[producerIndex] = i;
        bufferFilledCounter++;
        producerIndex++;
        producerIndex %= bufferSize;
    }

    public int get() {
        int readItem = boundedBuffer[consumerIndex];
        bufferFilledCounter--;
        consumerIndex++;
        consumerIndex %= bufferSize;
        return readItem;
    }

}

class ReadWriter_Writer implements Runnable {

    Database queue_obj;

    ReadWriter_Writer(Database queobject) {
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

class ReadWriter_Reader implements Runnable {

    Database queue_obj;

    ReadWriter_Reader(Database queobject) {
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
