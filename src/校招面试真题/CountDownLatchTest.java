package 校招面试真题;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author by KingOfTetris
 * @date 2023/10/26
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(10);
        //固定10个线程，使用FixedThreadPool
        ExecutorService service = Executors.newFixedThreadPool(10);
        String[] all = new String[10];//显示10个玩家的加载进度。
        Random random = new Random();

        //10个线程
        for (int j = 0; j < 10; j++) {
            int finalJ = j;//常量
            //只要i <= 100 就进行累加
            //submit提交任务，让线程池执行
            service.submit(() -> {
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(random.nextInt(100));	//随机休眠 造成速度不一致
                        all[finalJ] = i + "%";
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //用新的加载进度覆盖老的加载进度，就会搞得好像是实时加载一样酷炫。
                    System.out.print("\r" + Arrays.toString(all));	// \r代表覆盖
                }
                latch.countDown();//一个线程加载完成，countDown。
            });
        }
        latch.await();//让当前线程等待，必须 down 完初始化的数字才可以被唤醒，否则进入无限等待
        System.out.println("\n游戏开始");
        service.shutdown();//关闭线程池
    }
}
