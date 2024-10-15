package loadBalancer;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/10/14
 */
public class 轮询 {
    static int cur = 0; //全局变量来轮询List
    public static String getserver() {
        cur = cur % ServerIps.LIST.size();
        return ServerIps.LIST.get(cur);
    }

    @Test
    public void test(){
        for (int i = 0; i < 15; i++) {
            System.out.println(getserver());
            cur++; //调用一次 cur++
        }
    }
}
