/* 
 * author: alokrajiv
 * author-mail: mail@alokrajiv.com
 */
package javaapplication1;

/**
 *
 * @author alokrajiv
 */
public class TestThread {
    public static void main(String[] args)throws Exception {
        MyProg obj1 = new MyProg("Bob");
        MyProg obj2= new MyProg("Mary");
        MyThread run1 = new MyThread(obj1);
        MyThread run2 = new MyThread(obj2);
    }
    
}

class MyThread implements Runnable{
    MyProg obj;
    Thread t;
    public MyThread(MyProg iObj){
       obj = iObj;
       t = new Thread(this);
       t.start();
    }
    @Override
    public void run() {
        obj.display();
    }
    
}

class MyProg{
    String name;
    public MyProg(String s){
        name = s;
    }
    public void display(){
        System.out.println(name);
    }
}
