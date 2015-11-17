
/*
 MIT-LICENSE
 */
package os_lab_project;

/**
 *
 * @author alokrajiv
 * @author-mail mail@alokrajiv.com
 */
public class ProducerConsumerUsingMonitor {

    public static void main(String[] args) {
        ProducerConsumerUsingMonitor_Queue qobject = new ProducerConsumerUsingMonitor_Queue();
        Thread consumerthread = new Thread(new ProducerConsumerUsingMonitor_Consumer(qobject));
        Thread producerThread = new Thread(new ProducerConsumerUsingMonitor_Producer(qobject));
        consumerthread.start();
        producerThread.start();
    }
}

class ProducerConsumerUsingMonitor_Queue {

    int bufferFilledCounter = 0,
            producerIndex = 0,
            consumerIndex = 0,
            bufferSize = 3;

    int[] boundedBuffer = new int[bufferSize];

    synchronized public void put(int i) {
        try {
            if (bufferFilledCounter == bufferSize) {
                wait();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        boundedBuffer[producerIndex] = i;
        bufferFilledCounter++;
        producerIndex++;
        producerIndex %= bufferSize;
        notify();
    }

    synchronized public int get() {
        try {
            if (bufferFilledCounter == 0) {
                wait();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        int readItem = boundedBuffer[consumerIndex];
        bufferFilledCounter--;
        consumerIndex++;
        consumerIndex %= bufferSize;
        notify();
        return readItem;
    }

}

class ProducerConsumerUsingMonitor_Producer implements Runnable {

    ProducerConsumerUsingMonitor_Queue queue_obj;

    ProducerConsumerUsingMonitor_Producer(ProducerConsumerUsingMonitor_Queue queobject) {
        queue_obj = queobject;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; ; i++) {
                Thread.sleep(1000);
                queue_obj.put(i);
                System.out.println("produced =" + i);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}

class ProducerConsumerUsingMonitor_Consumer implements Runnable {

    ProducerConsumerUsingMonitor_Queue queue_obj;

    ProducerConsumerUsingMonitor_Consumer(ProducerConsumerUsingMonitor_Queue queobject) {
        queue_obj = queobject;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println(" The consumed value" + queue_obj.get());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
