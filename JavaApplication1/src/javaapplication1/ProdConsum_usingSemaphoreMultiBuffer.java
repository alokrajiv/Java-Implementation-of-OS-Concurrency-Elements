/* 
 * author: alokrajiv
 * author-mail: mail@alokrajiv.com
 */
/*

WARNING NOT COMPLETE!!!
WARNING NOT COMPLETE!!!
WARNING NOT COMPLETE!!!


*/
package javaapplication1;


import java.util.concurrent.Semaphore;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alokrajiv
 */
//Synchronise using MOnitor
class Queue1
{
    private final Semaphore empty  = new Semaphore(3, true);
    private final Semaphore full  = new Semaphore(0, true);
    private final Semaphore mutex  = new Semaphore(1, true);
    // instance variables - replace the example below with your own
    private int x;
    Boolean bufferfullflag=false;;
    int[] boundedbuffer;
    // counter to keep track of how many entries
    //in the buffer
 
    int counter;// no of items addeed into buffer
    int Producerindex;//index into buffer where producer add an item
    int Consumerindex;// index into buffer where consumer can remove an item
 synchronized void put(int i)
    {
       
        try
        {
           
            if(counter==3)// the buffer is full block the producer
            wait();
            boundedbuffer[Producerindex]=i;
            counter=counter+1;
            Producerindex=Producerindex+1;
            if(Producerindex==3)
            Producerindex=0;
           
            notify();
           
           
           
        }
        catch (Exception e)
        {
            System.out.println("exception");
        }
    }
    public Queue1()
    {
        // initialise instance variables
        x = 0;
        counter=0;
        Producerindex=0;
        Consumerindex=0;
        boundedbuffer= new int[3];
    }
   synchronized int get()
    {
        int ReadItem;
        try
        {
            if(counter==0)
            wait();
            ReadItem =boundedbuffer[Consumerindex];
            counter=counter-1;
            Consumerindex=Consumerindex+1;
            if(Consumerindex==3)//reset the consumer index to 0
            Consumerindex=0;
            notify();
            return ReadItem;
           
        }
       
     
         
   
    catch (Exception e)
        {
            System.out.println("exception");
        }
        return x;
    }
 
   
}
 
 class Producer1 implements Runnable
{
   Queue1 q;
   
    Producer1(Queue1 queobject)
    {
        q=queobject;
        Thread ProduceThread= new Thread(this);
        ProduceThread.start();
    }
   public void run()
    {
       
       
     {
            try
        {
               
           for (int i=0;i<10;i++)
           {
           Thread.sleep(1000);
           
            q.put(i);
           
           
            System.out.println("produced =" + i);
            }
        }
            catch(Exception e)
            {
                System.out.println("error");
            }
      }
    }
   
   
}
 class Consumer1 implements Runnable
{
   Queue1 q;
   Consumer1(Queue1 queobject)
    {
        q=queobject;
        Thread consumThread= new Thread(this);
        consumThread.start();
    }
    public void run()
   
    {
        int returnvalue;
        while(true)
        {
            try
            {
                Thread.sleep(1000);
               
                returnvalue=q.get();
               
               
                System.out.println(" The consumed value"+returnvalue);
            }
    catch(Exception e)
        {
       
         System.out.println("error");
        }
    }
}
}
public class ProdConsum_usingSemaphoreMultiBuffer
{

    public static void main(String[]args)
    {
        Queue1 qobject= new Queue1();
        Consumer1 consumerthread= new Consumer1(qobject);
        Producer1 prodThread=new Producer1(qobject);

    }



}