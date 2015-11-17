/*
 MIT-LICENSE
 */
package os_lab_project;

/**
 *
 * @author alokrajiv
 * @author-mail mail@alokrajiv.com
 */
public class ThreadExample {
    public static void main(String[] args){
        ThreadExample_MyThread1 obj = new ThreadExample_MyThread1();
    }
}
class ThreadExample_MyThread1 implements Runnable{
    Thread t; 
    public ThreadExample_MyThread1() {
        t = new Thread(this, "second_thread");
        t.start();
    }
    
    @Override
    public void run() {
        ThreadExample_MyThread2 obj = new ThreadExample_MyThread2();
        System.out.println("Hello World! from Thread : " + Thread.currentThread().getName());
    }
    
    public void hello(){
        //code
    }
    
}
class ThreadExample_MyThread2 implements Runnable{
    Thread t ;
    public ThreadExample_MyThread2() {
        t = new Thread(this, "third_thread");
        t.start();
    }

    @Override
    public void run() {
        System.out.println("HEllo Again! from Thread : " + Thread.currentThread().getName());
    }
    
}