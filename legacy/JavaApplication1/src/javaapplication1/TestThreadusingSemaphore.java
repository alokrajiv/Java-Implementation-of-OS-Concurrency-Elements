/* 
 * author: alokrajiv
 * author-mail: mail@alokrajiv.com
 */
package javaapplication1;

import java.util.concurrent.Semaphore;

/**
 *
 * @author alokrajiv
 */
class RunnableDemo2 implements Runnable {
   private Thread t;
   private String threadName;
   Semaphore semaphoreObj;
   RunnableDemo2( String name, Semaphore semaphoreObj){
       threadName = name;
       semaphoreObj = this.semaphoreObj;
       //System.out.println("Creating " +  threadName );
   }
   @Override
   public void run() {
      try {
          if(threadName.equals("Bob")){
                semaphoreObj.acquire();
          }
          System.out.println("Running " +  threadName );
          if(threadName.equals("Mary")){
                semaphoreObj.release();
          }
          
     } catch (Exception e) {
         System.out.println(e.getMessage());
     }
   }
   
   public void start ()
   {
      //System.out.println("Starting " +  threadName );
      if (t == null)
      {
         t = new Thread (this, threadName);
         t.start ();
      }
   }

}

public class TestThreadusingSemaphore {
   public static void main(String args[]) {
       
      Semaphore semaphoreObj = new Semaphore(1);
      RunnableDemo2 R1 = new RunnableDemo2( "Bob", semaphoreObj);
      R1.start();
      
      RunnableDemo2 R2 = new RunnableDemo2( "Mary", semaphoreObj);
      R2.start();
   }   
}