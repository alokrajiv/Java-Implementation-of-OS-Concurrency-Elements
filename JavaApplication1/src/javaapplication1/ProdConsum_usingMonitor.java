/* 
 * author: alokrajiv
 * author-mail: mail@alokrajiv.com
 */
package javaapplication1;

//Synchronise using Monitor
class Queue0
{
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
    public Queue0()
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
 
 class Producer0 implements Runnable
{
   Queue0 q;
   
    Producer0(Queue0 queobject)
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
 class Consumer0 implements Runnable
{
   Queue0 q;
   Consumer0(Queue0 queobject)
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
public class ProdConsum_usingMonitor
{

    public static void main(String[]args)
    {
        Queue0 qobject= new Queue0();
        Consumer0 consumerthread= new Consumer0(qobject);
        Producer0 prodThread=new Producer0(qobject);

    }



}