package 校招面试真题;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author by KingOfTetris
 * @date 2023/10/26
 */
public class CyclicBarrierTest {


    //和countDowntLatch的区别就是一个减
    //一个加CyclicBarrier
    //并且CyclicBarrier是可重用的。

    //应用：可以实现多线程中，某个任务在等待其他线程执行完毕以后触发
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        CyclicBarrier barrier = new CyclicBarrier(2, () -> {
            System.out.println("task1 task2 finish...");
        });

        for (int i = 0; i < 3; i++) { // 循环重用
            service.submit(() -> {
                System.out.println("task1 begin...");
                try {
                    Thread.sleep(1000);
                    barrier.await();    // 2 - 1 = 1
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });

            service.submit(() -> {
                System.out.println("task2 begin...");
                try {
                    Thread.sleep(2000);
                    barrier.await();    // 1 - 1 = 0
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();
    }

    @Test
    public void test(){
        //低精度转化为高精度不会发生精度损失，所以当低精度+高精度时候，会发生低精度到高精度的隐式转换
        byte a = 127;
        System.out.println(a + 1);//a + 1会触发隐式转化，把a转化为int，导致结果是128
        byte b = 1;
        System.out.println(a + b);//那么两个byte相加为什么还是128?
        // 因为两个byte相加，同样会发生类型提升，将它们提升为int类型，并且运算的结果也是int类型。
        byte c = (byte) (a + b);//如果你希望两个byte类型相加后仍然得到byte类型的结果，可以进行显式的类型转换。
        System.out.println(c);
    }
}
