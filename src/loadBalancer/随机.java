package loadBalancer;

import org.junit.Test;

import java.util.Random;

/**
 * @author by KingOfTetris
 * @date 2024/10/14
 */
public class 随机 {

        public static String getserver() {
            Random random = new Random();
            int rand = random.nextInt(ServerIps.LIST.size());
            return ServerIps.LIST.get(rand);
        }

        @Test
        public void test(){
            for (int i = 0; i < 10; i++) {
                System.out.println(getserver());
            }
        }
}
