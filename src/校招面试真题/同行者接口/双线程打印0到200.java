package 校招面试真题.同行者接口;

import java.util.concurrent.locks.LockSupport;

/**
 * @author by KingOfTetris
 * @date 2024/9/27
 */
public class 双线程打印0到200 {
    public static int num = 1;
    static Thread t1 = null,t2 = null;
    public static void main(String[] args) {
        t1 = new Thread(()->{
            while (num <= 200){
                if (num % 2 == 1){
                    System.out.println(Thread.currentThread().getName() + " " + num);
                    num++;
                }
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });
        t2 = new Thread(()->{
            while (num <= 200){
                LockSupport.park();
                if (num % 2 != 1){
                    System.out.println(Thread.currentThread().getName() + " " + num);
                    num++;
                }
                LockSupport.unpark(t1);
            }
        });
        t1.start();
        t2.start();
    }
}
