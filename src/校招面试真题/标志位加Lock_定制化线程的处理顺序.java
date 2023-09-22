package 校招面试真题;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程的定制化处理顺序有很多种解决方案，尚硅谷用的下面这个
 * 通过标志位 + Lock 定制化线程的处理顺序
 *
 * 还可以用线程的join方法，等谁就用谁的join.
 * @author by KingOfTetris
 * @date 2023/6/28
 */

class ShareResource{
    //标志位
    private int flag = 1; // 1AA 2BB 3CC
    //创建Lock锁
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //打印5次，第几轮
    public void print5(int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try {
            while (flag != 1){
                 c1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println("第" + loop + "轮:" + Thread.currentThread().getName() + "::" + i);
            }
            //修改标志为让B去运行
            flag = 2;
            c2.signal();//通知谁，就用谁的signal把谁唤醒
        }finally {
            lock.unlock();
        }
    }


    //打印10次，第几轮
    public void print10(int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try {
            while (flag != 2){
                c2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println("第" + loop + "轮:" + Thread.currentThread().getName() + "::" + i);
            }
            //修改标志为让C去运行
            flag = 3;
            c3.signal();
        }finally {
            lock.unlock();
        }
    }


    //打印10次，第几轮
    public void print15(int loop) throws InterruptedException {
        //上锁
        lock.lock();
        try {
            while (flag != 3){
                c3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println("第" + loop + "轮:" + Thread.currentThread().getName() + "::" + i);
            }
            //修改标志为让A去运行
            flag = 1;
            c1.signal();
        }finally {
            lock.unlock();
        }
    }
}
public class 标志位加Lock_定制化线程的处理顺序 {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(()->{
            for (int i = 1; i <= 3 ; i++) {
                try {
                    shareResource.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i = 1; i <= 3 ; i++) {
                try {
                    shareResource.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(()->{
            for (int i = 1; i <= 3 ; i++) {
                try {
                    shareResource.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
    }
}
