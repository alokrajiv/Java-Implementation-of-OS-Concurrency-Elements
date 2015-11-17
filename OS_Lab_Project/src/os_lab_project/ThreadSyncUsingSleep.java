package os_lab_project;

/*
 MIT-LICENSE
 */

/**
 *
 * @author alokrajiv
 * @author-mail mail@alokrajiv.com
 */
public class ThreadSyncUsingSleep{
    
    public static void main(String[] args){
        ThreadSyncUsingSleep_MyThread obj_bobby = new ThreadSyncUsingSleep_MyThread("Bobby goes second.");
        ThreadSyncUsingSleep_MyThread obj_molly = new ThreadSyncUsingSleep_MyThread("Molly goes first.");
        Thread t1 = obj_bobby.startThread();
        Thread t2 = obj_molly.startThread();
        
    }
}
class ThreadSyncUsingSleep_MyThread implements Runnable{
    String name;
    Thread t;
    public ThreadSyncUsingSleep_MyThread(String str) {
        name = str;
        t = new Thread(this);
    }
    
    public Thread startThread(){
        t.start();
        return t;
    }
    @Override
    public void run() {
        try{  
            if(this.name.equals("Bobby goes second.")){
                Thread.sleep(1000);
            }
            System.out.println(name);
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }
    }
    
}