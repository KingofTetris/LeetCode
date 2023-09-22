package 校招面试真题;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程的定制化处理顺序有很多种解决方案，尚硅谷用的下面这个
 * 通过标志位 + Lock 定制化线程的处理顺序
 * <p>
 * 还可以用线程的join方法，等谁就用谁的join.
 *
 * @author by KingOfTetris
 * @date 2023/6/28
 */

class ShareResource1 {
    //标志位
    private int flag = 1; // 1AA 2BB 3CC
    //创建Lock锁
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //AA打印
    public void printA() throws InterruptedException {
        //上锁
        lock.lock();
        try {
            while (flag != 1) {
                c1.await();
            }
            System.out.println("AA");
            //修改标志为让B去运行
            flag = 2;
            c2.signal();//通知谁，就用谁的signal把谁唤醒
        } finally {
            lock.unlock();
        }
    }

    //BB打印
    public void printB() throws InterruptedException {
        //上锁
        lock.lock();
        try {
            while (flag != 2) {
                c2.await();
            }
            System.out.println("BB");
            //修改标志为让CC去运行
            flag = 3;
            c3.signal();//通知谁，就用谁的signal把谁唤醒
        } finally {
            lock.unlock();
        }
    }

    //CC打印
    public void printC() throws InterruptedException {
        //上锁
        lock.lock();
        try {
            while (flag != 3) {
                c3.await();
            }
            System.out.println("CC");
            //修改标志为让AA去运行
            flag = 1;
            c1.signal();//通知谁，就用谁的signal把谁唤醒
        } finally {
            lock.unlock();
        }
    }
}

public class 标志位加_Lock_定制化线程的处理顺序 {
    public static void main(String[] args) {
        ShareResource1 shareResource = new ShareResource1();
        new Thread(() -> {
            try {
                shareResource.printA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                shareResource.printB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                shareResource.printC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
    }
}
