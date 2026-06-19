import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteCounter {
    int count=0;

    private  final ReadWriteLock lock=new  ReentrantReadWriteLock();

    private final Lock  readLock = lock. readLock();

    private  final Lock writeLock=lock. writeLock();

    public void   increment(){
        writeLock.lock();

        try {
            count++;

         } catch (Exception e) {
            Thread.currentThread().interrupt();
        }finally {
            writeLock.unlock();
        }
    }

    public    int  getCounter(){
        readLock.lock();
        try {
             return count;
        }finally {
            readLock.unlock();
        }
    }


       void main() throws InterruptedException {
         ReadWriteCounter  counter =new ReadWriteCounter();

         Runnable readTask= new Runnable() {
             @Override
             public void run() {
                 for(int i=0 ; i<5 ;i++){
                     counter.increment();
                     System.out.println(Thread.currentThread().getName() + " : read " + counter. getCounter());
                 }

             }
         };



           Runnable  writeTask= new Runnable() {
               @Override
               public void run() {
                   for(int i=0 ; i<5 ;i++){
                       System.out.println(Thread.currentThread().getName() + "  incremented " );
                   }

               }
           };


           Thread readThread1 =new Thread(readTask , "t1");

           Thread readThread2 =new Thread(readTask, "t2");

           Thread writeThread =new Thread( writeTask ,"t3");


           readThread1.start();
           readThread2.start();
           writeThread.start();

           writeThread.join();
           readThread1.join();
           readThread2.join();


    }
}
