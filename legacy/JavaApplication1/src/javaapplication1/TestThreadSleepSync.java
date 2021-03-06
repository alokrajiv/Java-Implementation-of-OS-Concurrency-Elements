
package javaapplication1;

/*
 MIT-LICENSE
 */

/**
 *
 * @author alokrajiv
 * @author-mail mail@alokrajiv.com
 */
public class TestThreadSleepSync {
    public static void main(String[] args){
        MyProg1 obj1 = new MyProg1("Bob");
        MyProg1 obj2= new MyProg1("Mary");
        MyThread1 run1 = new MyThread1(obj1);
        MyThread1 run2 = new MyThread1(obj2);
    }
}

class MyThread1 implements Runnable{
    MyProg1 obj;
    Thread t;
    public MyThread1(MyProg1 iObj){
       obj = iObj;
       t = new Thread(this);
       t.start();
    }
    @Override
    public void run() {
        if(obj.name.equals("Mary")){
            try{
                Thread.sleep(1000);
                obj.display();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
    
}

class MyProg1{
    String name;
    public MyProg1(String s){
        name = s;
    }
    public void display(){
        System.out.println(name);
    }
}
