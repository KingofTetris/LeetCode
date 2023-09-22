package 校招面试真题;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 使用Lock实现一个线程+1，一个线程-1 交替进行。
 * @author by KingOfTetris
 * @date 2023/6/28
 */
class ShareLock{
    private int num = 0;
    private Lock lock = new ReentrantLock();
    //lock需要配合condition
    private Condition condition = lock.newCondition();
    //不加synchronized关键字，用Lock手动加锁 放锁
    public void incr(){
        lock.lock();
        try{
            while (num != 0){
                condition.await();//Lock的等待
            }
            //如果等于0了
            num++;
            System.out.println(Thread.currentThread().getName() + "::::" + num);
            condition.signalAll();
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            lock.unlock();
        }
    }

    public void decr(){
        lock.lock();
        try{
            while (num != 1){
                condition.await();//Lock的等待
            }
            //如果等于1了
            num--;
            System.out.println(Thread.currentThread().getName() + "::::" + num);
            //记得唤醒其他等待线程
            condition.signalAll();
        }catch (Exception e){
            System.out.println(e);
        }
        finally {
            lock.unlock();
        }
    }
}
public class 手动lock实现线程交替加减1 {
    public static void main(String[] args) {
        ShareLock shareLock = new ShareLock();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareLock.incr();
            }
        },"AA").start();


        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareLock.decr();
            }
        },"BB").start();
    }
}
