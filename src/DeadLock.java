class Pen{
    public  synchronized  void writeWithPenPaper(Paper paper){
        System.out.println(Thread.currentThread().getName() + " is using pen and  trying to use paper");
        paper. finishWriting();
    }



    public void finishWriting(){
        System.out.println(Thread.currentThread().getName() + " finish using pen");
    }
}

class Paper{
    public synchronized  void  writeWithPaperPen(Pen pen ){
        System.out.println(Thread.currentThread().getName() + " is using paper and trying to use pen");
        pen. finishWriting();
    }

    public void  finishWriting(){
        System.out.println(Thread.currentThread().getName() + " finish using paper");
    }
}

class Task1 implements  Runnable {
    private  Paper paper=new Paper();
    private  Pen pen = new Pen();

    Task1(Pen  pen ,Paper paper){
        this.paper=paper;
        this.pen=pen;
    }

    @Override
    public void run() {
        pen.writeWithPenPaper(paper);

    }
}

class  Task2 implements  Runnable {
    private  Paper paper=new Paper();
    private  Pen pen = new Pen();

     Task2(Pen  pen ,Paper paper){
        this.paper=paper;
        this.pen=pen;
    }

    @Override
    public void run() {
        synchronized ( pen){
            paper. writeWithPaperPen(pen);
        }

    }
}


public class DeadLock {
    public static void main(String[] args) {
        Pen pen = new Pen();
        Paper paper = new Paper();

        Thread t1=new Thread(new Task1(pen ,paper)) ;
        Thread t2= new Thread(new Task2( pen ,paper));

         t1.start();
        t2.start();
    }
}
