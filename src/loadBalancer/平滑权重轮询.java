package loadBalancer;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by KingOfTetris
 * @date 2024/10/14
 * 假设初始A B C三台服务器权重是
 * A:5
 * B:1
 * C:1
 *
 * 如果使用加权轮询那么访问顺序是AAAAABC。非常不好是吧，相当于一段期间让A的压力非常大。
 * 这里就需要平滑加权轮询算法，改为：AABACAA
 *
 * 下面是具体的做法
 *
 * 服务器:
 * 1.ip
 * 2. weight  --- 初始权重，5,1,1
 * 3. currentWeight --- 动态权重，0，0，0
 * 4.totalWeight --- 初始权重之和 5+1+1 = 7
 *
 *
 * 初始动态权重 currentWeight, 0，0，0
 *
 *      currentWeight = currentWeight + weight      max(currentWeight)     returnIP     更新最大权重= max(currentWeight) - totalWeight
 * 1.   5，1，1                                      5                        A          （5-7），1，1 = -2，1，1
 * 2.   (-2+5,1+1,1+1) = 3,2,2                      3                        A           (3-7),2,2   = -4 ,2,2
 * 3.   (-4+5,2+1,2+1) = 1,3,3                      3                        B           1,(3-7),3 =  1,-4,3
 * 4.   (5+1,-4+1,3+1) = 6,-3,4                     6                        A           (6-7),-3,4 = -1,-3,4
 * 5.   (-1+5,-3+1,4+1) = 4,-2,5                    5                        C           4,-2,-2
 * 6.   9,-1,-1                                     9                        A           2,-1,-1
 * 7.   7,0,0                                       7                        A           0,0,0
 *
 * 8.   5,1,1 开始新一轮的轮询
 *
 * 这就是平滑轮询算法的整体流程。构造一个周期函数，不断地轮询，
 */
public class 平滑权重轮询 {

    //https://www.bilibili.com/video/BV1fv411q7n9?spm_id_from=333.788.player.switch&vd_source=299caa32bd4dc5f5ad17129611289250&p=2

    //维护权重的动态变化
    private static Map<String,Weight> weights = new HashMap<>();

    public static String getServer(){
        //先算出初始的总权重
        int totalWeight = ServerIps.WEIGHT_LIST.values().stream().mapToInt(Integer::intValue).sum();

        //如果weights为空，初始化weights
        if (weights.isEmpty()) {
            ServerIps.WEIGHT_LIST.forEach((ip,weight)->{
                weights.put(ip,new Weight(ip,weight,0));//初始动态weight为0
            });
        }

        //更新每个weight的权重,也就是当前动态权重+初始权重
        for (Weight weight : weights.values()) {
            weight.setCurrentWeight(weight.getCurrentWeight() + weight.getWeight());
        }

        //找出最大的权重ip
        Weight maxWeight = null;
        for (Weight weight : weights.values()) {
           if (maxWeight == null || maxWeight.getCurrentWeight() < weight.getCurrentWeight()){
               maxWeight = weight; //更新最大权重
           }
        }
        //返回maxWeight的ip之前，记得减去totalWeight
        maxWeight.setCurrentWeight(maxWeight.getCurrentWeight() - totalWeight);

        //返回maxWeight的ip地址
        return maxWeight.getIp();
    }

    @Test
    public void test(){
        for (int i = 0; i < 50; i++) {
            System.out.println(getServer());
        }
    }
}
