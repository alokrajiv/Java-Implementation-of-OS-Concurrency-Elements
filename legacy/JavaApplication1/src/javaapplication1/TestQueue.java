/* 
 * author: alokrajiv
 * author-mail: mail@alokrajiv.com
 */
package javaapplication1;

/**
 * 
 * @author alokrajiv
 */
public class TestQueue {
    public static void main(String[] args){
        Queue q =new Queue();
        Producer obj1 = new Producer(q);
        Consumer obj2 = new Consumer(q);
        
    }
}
class Producer implements Runnable{
    Queue q;
    Producer(Queue tstqobj){
        q = tstqobj;
        Thread producerThread = new Thread(this);
        producerThread.start();
    }
    @Override
    public void run() {
        int i = 0;
        while(true){
                q.set(i++);
            System.out.println("The produced value"+ i);
        }
            
    }
    
}
class Consumer implements Runnable{
    Queue q;

    public Consumer(Queue tstqobj){
        q = tstqobj;
        Thread consumerThread = new Thread(this);
        consumerThread.start();
    }
    
    @Override
    public void run() {
        while(true){
            int ret = q.get();
            System.out.println("The consumed value"+ ret);
        }
    }
    
}
class Queue{
    private int x, last;
    synchronized void set(int i){
        x = i;
    }
    synchronized int get(){
        x++;
        try{
            if(last==x)
              wait();
        }catch(Exception e){
            
        }
        
        return x;
    }
    synchronized int last(){
        return last;
    }
}