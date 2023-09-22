package 校招面试真题;

/**
 * @author by KingOfTetris
 * @date 2023/9/6
 */


//join是最简单的方法。
public class join_定制化线程的处理顺序 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "执行");
            try {
                Thread.currentThread().join(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            try {
                t1.join();//保证t1先执行
                System.out.println(Thread.currentThread().getName() + "执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");

        Thread t3 = new Thread(() -> {
            try {
                t2.join();
                System.out.println(Thread.currentThread().getName() + "执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3");


        t1.start();
        t2.start();
        t3.start();
    }
}
