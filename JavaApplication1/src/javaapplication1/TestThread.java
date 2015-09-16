/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author alokrajiv
 */
class RunnableDemo implements Runnable {
   private Thread t;
   private String threadName;
   
   RunnableDemo( String name){
       threadName = name;
       //System.out.println("Creating " +  threadName );
   }
   @Override
   public void run() {
      System.out.println("Running " +  threadName );
      try {
        // Let the thread sleep for a while.
        if(threadName.equals("Mary"))
            Thread.sleep(50);
     } catch (InterruptedException e) {
         System.out.println("Thread " +  threadName + " interrupted.");
     }
     System.out.println("Thread " +  threadName + " exiting.");
   }
   
   public void start ()
   {
      System.out.println("Starting " +  threadName );
      if (t == null)
      {
         t = new Thread (this, threadName);
         t.start ();
      }
   }

}

public class TestThread {
   public static void main(String args[]) {
   
      RunnableDemo R1 = new RunnableDemo( "Bob");
      R1.start();
      
      RunnableDemo R2 = new RunnableDemo( "Mary");
      R2.start();
   }   
}