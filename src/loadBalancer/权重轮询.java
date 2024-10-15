package loadBalancer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * @author by KingOfTetris
 * @date 2024/10/14
 */
public class 权重轮询 {

    //权重轮询就是生成一个等长的List，随机去取，但是这种方法需要一个非常长的List，非常不好。
    //而且有另外一个问题就是权重最大的IP会一直被访问
    //例如A的权重是50，那么他会被先访问50次，再去访问BC。


    //因此这种普通的权重轮询算法基本上可以直接弃用了。
    //使用平滑权重轮询算法

    static int cur = 0;

    public static String getServer(){
        ArrayList<String> list = new ArrayList<>();
        //构造list
        for (Map.Entry<String, Integer> entry : ServerIps.WEIGHT_LIST.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                list.add(entry.getKey());
            }
        }
        cur = cur % list.size();
        return list.get(cur);
    }

    @Test
    public void test(){
        for (int i = 0; i < 100; i++) {
            System.out.println(getServer());
            cur++;
        }
    }
}
