import java.util.concurrent.locks.Lock;

public class RentrantLock {
    private Lock lock=new java.util.concurrent.locks.ReentrantLock();
    public void outerMethod() throws InterruptedException {
        lock. lockInterruptibly();
        try{
            System.out.println("outer method");
            innerMethod();

        }finally {
            lock.unlock();
        }


    }

    public void  innerMethod() throws InterruptedException {
        lock.lock();
        try{
            System.out.println(" inner  method");
             outerMethod();

        }finally {
            lock.unlock();
        }
    }

    static void main() throws InterruptedException {
        RentrantLock t1=new RentrantLock();
        t1. outerMethod();


    }
}
