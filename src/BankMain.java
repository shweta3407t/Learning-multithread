public class BankMain {
    static void main() {
        //BankAccount bankAccount=new BankAccount();

        BankAccountLock bankAccountLock=new BankAccountLock();
        Runnable task=new Runnable() {
            @Override
            public void run() {
                try {
                     bankAccountLock.withDraw(50);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };


        Thread  t1=new Thread( task , "Thread 1");

        Thread t2=new Thread( task , "Thread 2");

        t1.start();
        t2.start();
    }
}
