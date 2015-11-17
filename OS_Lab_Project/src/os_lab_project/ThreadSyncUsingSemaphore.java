/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package os_lab_project;

import java.util.concurrent.Semaphore;

/**
 *
 * @author alokrajiv
 * @author-mail mail@alokrajiv.com
 */
public class ThreadSyncUsingSemaphore {
    public static void main(String[] args) {
        
        
        Semaphore sema1 = new Semaphore(0);
        ThreadSyncUsingSemaphore_MyThread obj1 = new ThreadSyncUsingSemaphore_MyThread("Alok", sema1);
        ThreadSyncUsingSemaphore_MyThread obj2 = new ThreadSyncUsingSemaphore_MyThread("Rajiv", sema1);
        new Thread(obj1).start();
        new Thread(obj2).start();
    }
}
class ThreadSyncUsingSemaphore_MyThread implements Runnable{
    
    Semaphore sem;
    String name;

    public ThreadSyncUsingSemaphore_MyThread(String str, Semaphore ctrl) {
        name = str;
        sem = ctrl;
    }
    
    
    @Override
    public void run() {
        try{
            if(name.equals("Rajiv"))
                sem.acquire();
            System.out.println(name);
            if(name.equals("Alok"))
                sem.release();
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
    }
}
    