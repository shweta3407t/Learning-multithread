
class Sharedresource{
    private int data;

    private  boolean hasData;

    public synchronized void  produce(int value){
         while(hasData){
             try {
                 wait();
             } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
              }
         }
         data=value;
         hasData=true;
         notify();
        System.out.println("produce " + value);
    }

    public  synchronized int   consumer(int value){
         while (!hasData){
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
         hasData=false;
        notify();
        System.out.println("consumed " + value);
          return data;


    }
}

class Producer implements Runnable{
    private Sharedresource  resource;

    public Producer( Sharedresource resource){
        this.resource=resource;
    }

    @Override
    public void run() {
        for(int i=0 ; i<5 ; i++){
            resource.produce(i);

        }
    }
}


class  Consumer implements Runnable{

    private Sharedresource  resource;

    public  Consumer( Sharedresource resource){
        this.resource=resource;
    }

    @Override
    public void run() {
        for(int i=0 ; i<5 ; i++){
           resource.consumer(i);

        }
    }
}



public class ThreadCommunnnication {
    public static void main(String[] args) {
        Sharedresource  resource=new Sharedresource();

         Thread producerThread  =new Thread(new Producer (resource) , "thread1 ");
         Thread  consumerThread  =new Thread(new Consumer (resource) , "thread2 ");

          producerThread.start();
          consumerThread.start();

    }
}
