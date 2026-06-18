public class BankAccount  {
     private int balance = 100;

     //synchronize example(intrinsic lock)---types of lock  build-in
      public  synchronized void  withDraw(int amount) throws InterruptedException {
          System.out.println(Thread.currentThread().getName() + " ATTEMPTING TO WITH DRAW  " +amount);
          if ( amount <= balance){
               System.out.println(Thread.currentThread().getName() + " PROCEEDING WITH WITHDRAWAL . ");

              Thread.sleep(2000);
              balance -=amount;
              System.out.println("COMPLETE WITHDRAWAL .REMAINING BALANCE  " + balance);
              System.out.println();

          }else{
              System.out.println(" INSUFFICIENT BALANCE !");
          }
      }
}
