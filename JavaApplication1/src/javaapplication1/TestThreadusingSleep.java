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
class RunnableDemo1 implements Runnable {
   private Thread t;
   private String threadName;
   
   RunnableDemo1( String name){
       threadName = name;
       //System.out.println("Creating " +  threadName );
   }
   @Override
   public void run() {
      try {
          if(threadName.equals("Mary"))
            Thread.sleep(50);
          System.out.println("Running " +  threadName );
          
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

public class TestThreadusingSleep {
   public static void main(String args[]) {
   
      RunnableDemo1 R1 = new RunnableDemo1( "Bob");
      R1.start();
      
      RunnableDemo1 R2 = new RunnableDemo1( "Mary");
      R2.start();
   }   
}