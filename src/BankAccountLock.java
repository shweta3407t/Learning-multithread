import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountLock {
    private int balance = 100;

    //explicit lock (manual lock)
    private final Lock lock=new ReentrantLock();

    public    void  withDraw(int amount) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " ATTEMPTING TO WITHDRAW  " +amount);

        if(lock.tryLock(1000 , TimeUnit.MILLISECONDS)){
            if ( amount <= balance){

                 try {
                     System.out.println(Thread.currentThread().getName() + " PROCEEDING WITH WITHDRAWAL . ");

                     Thread.sleep(5000);
                     balance -=amount;
                     System.out.println(Thread.currentThread().getName() + " COMPLETE WITHDRAWAL .REMAINING BALANCE  " + balance);
                     System.out.println();
                 } catch (Exception e) {
                     Thread.currentThread().interrupt();

                  }finally {
                     lock.unlock();
                 }

            }else{
                System.out.println(" INSUFFICIENT BALANCE !");

            }
        }else {
            System.out.println(Thread.currentThread().getName() + " COULD NOT ACQUIRE LOCK .TRY AGAIN LATER ");
        }
    }
}


























//        if ( amount <= balance){
//            System.out.println(Thread.currentThread().getName() + " PROCEEDING WITH WITHDRAWAL . ");
//
//            Thread.sleep(2000);
//            balance -=amount;
//            System.out.println("COMPLETE WITHDRAWAL .REMAINING BALANCE  " + balance);
//            System.out.println();
//
//        }else{
//            System.out.println(" INSUFFICIENT BALANCE !");
//








