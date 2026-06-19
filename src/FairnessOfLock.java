import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairnessOfLock {

    private  final Lock lock=new ReentrantLock(true);

    public void accessResources(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired lock");
            Thread.sleep(1000);
        }
        catch (Exception e){
            Thread.currentThread().interrupt();
        }finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " releasing lock");
        }



    }

    static void main() throws InterruptedException {
        FairnessOfLock example=new FairnessOfLock();
          Runnable task= example::accessResources;

        Thread t1=new Thread(task ,"thread 1 :");
        Thread  t2=new Thread(task ,"thread 2 :");
        Thread  t3=new Thread(task ,"thread 3 :");

        t1.start();
        Thread.sleep(50);
        t2.start();
        Thread.sleep(50);
        t3.start();
        Thread.sleep(50);


    }
}
