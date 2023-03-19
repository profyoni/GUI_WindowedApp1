import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class MyTask implements Runnable
{
    private String tname;
    public MyTask(String s)
    {
        tname =s;
    }
    @Override
    public void run()
    {
        for (int i=0;i<1_000;i++)
        {
            System.out.print( String.format("%s:%d ", tname,i));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                return;
            }

        }
    }
}

class MyThread extends Thread
{
    private int[] list;
    private int threadNum;
    static int total = 0;
    static int nextThreadNum = 0;

    public MyThread(int[] list)
    {
        this.list = list;
        synchronized (MyThread.class)
        {
            threadNum = nextThreadNum++;
        }
    }
     public  MyThread(int[] list, int num)
    {
        this.list = list;
        threadNum = num;
    }

    synchronized public static void  foo(){
        synchronized ( MyThread.class )
        {

        }
    }


   void addToTotal(int x){
        /**
         * REAL CODE
         */
       synchronized ( "key" ) // specify lock object
        {
            total += x;
       }
    }
    @Override
    public void run()
    {
        System.out.println("Starting Thread #" + threadNum);
        for (int i=threadNum * 100_000_000;i<(threadNum+1)*100_000_000;i++)
        {
            //total += list[i];
            addToTotal(list[i]);
        }
        System.out.println("Ending Thread #" + threadNum);
    }
}


public class Threading {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(4);
        es.submit(new MyTask("A"));
        es.submit(new MyTask("B"));
        es.submit(new MyTask("C"));
        es.submit(new MyTask("D"));
        es.submit(new MyTask("Q"));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        es.shutdown();

        try {
            if (!es.awaitTermination(1_000, TimeUnit.MILLISECONDS))
                es.shutdownNow();
        } catch (InterruptedException e) {
            es.shutdownNow();
        }

//        new Thread( new MyTask("A")).start();
//        new Thread( new MyTask("B")).start();
//        new Thread( new MyTask("C")).start();
//        new Thread( new MyTask("D")).start();
    }
//
//    static class SynchronizedList<T> extends ArrayList<T>
//    {
//        @Override
//        public synchronized boolean add(T t)
//        {
//            return super.add(t);
//        }
//        @Override
//        public synchronized T set(int index, T t)
//        {
//            return super.set(index, t);
//        }
//    }
    public static void main3(String[] args) {
        long start = System.currentTimeMillis();
        MyThread[] threads = new MyThread[10];
        int list[] = new int[1_000_000_000];
        Arrays.fill(list, 1);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(list, i);
            threads[i].start();
        }
        int grandTotal = 0;
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        for (int i = 0; i < threads.length; i++) {
            while (threads[i].isAlive()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
            grandTotal = threads[i].total;
            System.out.println(grandTotal);
            System.out.println(System.currentTimeMillis() - start);
        }
    }

    static int x;

    public static void main2(String[] args) {
        Thread[] threads = new Thread[10];
        final int MAX_ITER = 1_000_000;
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());

        for (int i=0;i<threads.length;i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < MAX_ITER; j++) {
                    list.add(j);
                    //x++;
                }
            });
            threads[i].start();
        }
//
        for (int i=0;i<threads.length;i++)
        {
            //threads[i].start();

            while (threads[i].isAlive()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }

        }
        // All threads ended
        System.out.println(list.size());
    }
}
