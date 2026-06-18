public class myThread extends Thread {
    public  myThread(String name){
        super((name));
    }
    @Override
    public void run() {

         while(true){
            System.out.println(Thread.currentThread().getName()+"THREAD IS RUNNING");
        }

    }

    static void main(String[] args) throws InterruptedException {

      myThread  myThread=new myThread( " demon thread" );
      myThread.setDaemon(true);
        myThread.start();

        myThread t2=new myThread( "t2");
        t2.start();


        System.out.println("main done");

    }
}
