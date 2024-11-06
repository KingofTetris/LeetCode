package 校招面试真题;

import java.util.Date;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author by KingOfTetris
 * @date 2024/11/6
 */
public class 读写锁 {


    //读读并发
    public static void main(String[] args) {
        ReentrantReadWriteLock rw = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock r = rw.readLock();
        ReentrantReadWriteLock.WriteLock w = rw.writeLock();

        new Thread(() -> {
            r.lock();
            try {
                Thread.sleep(2000);
                System.out.println("Thread 1 running " + new Date());
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                r.unlock();
            }
        },"t1").start();

        new Thread(() -> {
            r.lock();
            try {
                Thread.sleep(2000);
                System.out.println("Thread 2 running " + new Date());
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            finally {
                r.unlock();
            }
        },"t2").start();
    }
}
