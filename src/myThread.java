public class myThread extends Thread {
    public  myThread(String name){
        super((name));
    }
    @Override
    public void run() {
        System.out.println("THREAD IS RUNNING");
        for(int i=1 ;i<=5 ;i++){
                 System.out.println( Thread.currentThread().getName() +"  is running ");
//                    Thread.yield();
        }

    }

    static void main(String[] args) throws InterruptedException {

      myThread t1=new myThread( "t1" );
      myThread t2=new myThread( "t2");
        t1.start();
        t2.start();

    }
}
