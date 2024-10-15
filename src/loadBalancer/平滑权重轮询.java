package loadBalancer;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by KingOfTetris
 * @date 2024/10/14
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
               maxWeight = weight;
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
