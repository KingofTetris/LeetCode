package loadBalancer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * @author by KingOfTetris
 * @date 2024/10/14
 */
public class 权重随机 {

    //权重随机 就是一道算法题了，得动下脑子

    //其实所有权重的值累加起来就是一个区间。
    //例如权重为[4,3,5]
    //0__3 __6 ____11
    //就可以分成这样3个区间 0-3,4-6,7-11
    //那你随机生成一个0到11之间的数字
    //根据区间就能知道他落在哪一段上。

    public static String getServer() {
        //stream流求和
        int weightSum = ServerIps.WEIGHT_LIST.values().stream().mapToInt(Integer::intValue).sum();
        Random random = new Random();
        int pos = random.nextInt(weightSum);

        for (String ip : ServerIps.WEIGHT_LIST.keySet()) {
            Integer weight = ServerIps.WEIGHT_LIST.get(ip);
            //如果随机数已经小于权重了，说明已经落在了这个ip上
            if (pos < weight){
                return ip;
            }
            //如果大于当前权重，那么你就需要减去当前weight
            pos = pos - weight;
        }

        return "";
    }

    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            System.out.println(getServer());
        }
    }
}
