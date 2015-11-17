
package os_lab_project;

/*
 MIT-LICENSE
 */

/**
 *
 * @author alokrajiv
 * @author-mail mail@alokrajiv.com
 */

/*

(1344*292)/(2234*23)
*/
public class ThreadSyncUsingJoin {
    public static void main(String[] args){
        ThreadSyncUsingJoin_NumeratorCalc num_obj = new ThreadSyncUsingJoin_NumeratorCalc(1331, 121);
        ThreadSyncUsingJoin_DenominatorCalc den_obj = new ThreadSyncUsingJoin_DenominatorCalc(72, 57);
        Thread num_thread = new Thread(num_obj, "Denominator-Thread");
        Thread den_thread = new Thread(den_obj, "Numerator-Thread");
        num_thread.start();
        den_thread.start();
        try{
            num_thread.join();
            den_thread.join();
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        double result = num_obj.getRes()/den_obj.getRes();
        System.out.println("The result is "+ result);
    }
}
class ThreadSyncUsingJoin_DenominatorCalc implements Runnable{
    double a,b,res;
    public ThreadSyncUsingJoin_DenominatorCalc(double x, double y) {
        a = x;
        b = y;
    }

    @Override
    public void run() {
        res = a * b;
        System.out.println("The denominator is "+ res +" calculated by Thread: "+ Thread.currentThread().getName());
    }
    
    public double getRes(){
        return res;
    }
    
}
class ThreadSyncUsingJoin_NumeratorCalc implements Runnable{
    double a,b, res;
    public ThreadSyncUsingJoin_NumeratorCalc(double x, double y) {
        a = x;
        b = y;
    }

    @Override
    public void run() {
        res = a * b;
        System.out.println("The numerator is "+ res +" calculated by Thread: "+ Thread.currentThread().getName());
    }
    
    public double getRes(){
        return res;
    }
    
}