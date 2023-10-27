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
        ExecutorService service = Executors.newFixedThreadPool(10);
        String[] all = new String[10];//10个玩家的加载进度。
        Random random = new Random();
        for (int j = 0; j < 10; j++) {
            int finalJ = j;//常量
            service.submit(() -> {
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(random.nextInt(100));	//随机休眠
                        all[finalJ] = i + "%";
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //用新的加载进度覆盖。
                    System.out.print("\r" + Arrays.toString(all));	// \r代表覆盖
                }
                latch.countDown();//一个线程加载完成，countDown。
            });
        }
        latch.await();//让当前线程等待，必须 down 完初始化的数字才可以被唤醒，否则进入无限等待
        System.out.println("\n游戏开始");
        service.shutdown();
    }
}
