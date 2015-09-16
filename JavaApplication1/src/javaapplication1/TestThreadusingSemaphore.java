/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
   private final Semaphore semaphoreObj = new Semaphore(1);
   
   RunnableDemo2( String name){
       threadName = name;
       //System.out.println("Creating " +  threadName );
   }
   @Override
   public void run() {
      try {
          semaphoreObj.acquire();
          System.out.println("Running " +  threadName );
          semaphoreObj.release();
          
     } catch (Exception e) {
         
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
   
      RunnableDemo2 R1 = new RunnableDemo2( "Bob");
      R1.start();
      
      RunnableDemo2 R2 = new RunnableDemo2( "Mary");
      R2.start();
   }   
}