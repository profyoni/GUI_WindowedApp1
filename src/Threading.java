import java.util.*;


class MyThread extends Thread
{
    private int[] list;
    private int threadNum;
    int total = 0;
    public MyThread(int[] list, int num)
    {
        this.list = list;
        threadNum = num;
    }
    @Override
    public void run()
    {
        System.out.println("Starting Thread #" + threadNum);
        System.out.flush();
        for (int i=threadNum * 100_000_000;i<(threadNum+1)*100_000_000;i++)
            total += list[i];
        System.out.println("Ending Thread #" + threadNum);
    }
}
public class Threading {
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
    public static void main(String[] args) {
        MyThread[] threads = new MyThread[10];
        int list[] = new int[1_000_000_000];
        Arrays.fill(list, 1);
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new MyThread(list, i);
            threads[i].start();
        }
        int grandTotal = 0;
        for (int i = 0; i < threads.length; i++) {
            while (threads[i].isAlive()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
            grandTotal += threads[i].total;
            System.out.println(grandTotal);
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
