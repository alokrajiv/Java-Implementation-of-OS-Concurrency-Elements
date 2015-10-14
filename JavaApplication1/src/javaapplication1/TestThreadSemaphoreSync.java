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
public class TestThreadSemaphoreSync {
    public static void main(String[] args){
        MyProg2 obj1 = new MyProg2("Bob");
        MyProg2 obj2= new MyProg2("Mary");
        MyThread2 run1 = new MyThread2(obj1);
        MyThread2 run2 = new MyThread2(obj2);
    }
}

class MyThread2 implements Runnable{
    MyProg2 obj;
    Thread t;
    final Semaphore smphObj = new Semaphore(1);
    public MyThread2(MyProg2 iObj){
       obj = iObj;
       t = new Thread(this);
       try{
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
       t.start();
    }
    @Override
    public void run() {
        if(obj.name.equals("Bob")){
            try{
                
           smphObj.acquire();
                Thread.sleep(1000);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        
        obj.display();
       try{
           smphObj.release();
       }catch(Exception e){
           System.out.println(e.getMessage());
       }
        }
        else{
            obj.display();
        }
    }
    
}

class MyProg2{
    String name;
    public MyProg2(String s){
        name = s;
    }
    public void display(){
        System.out.println(name);
    }
}
