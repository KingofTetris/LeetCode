package 校招笔试真题.字节跳动.字节跳动2023秋招0820;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class P1473_塔子哥的五连击 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<Integer, Integer> map = new HashMap<>();
        char temp = '-';
        long res = 0L;
        while (n-- > 0) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            char c = sc.next().charAt(0);
            //把同一个关卡的音块数量放到一起
            if (temp == '-' || temp == c) {
                temp = c;//更新关卡
                map.put(a, b);
            } else {
                //更新关卡
                temp = c;
                //清空之前，我们先计算一下上次能获得的金币
                res += solution(map);
                map.clear();//如果是新的关卡，那么就要清空map 重新计算
                map.put(a,b);//别忘了把这次的加进去。
            }
        }
        //计算最后一关的金币
        res += solution(map);
        System.out.println(res);
    }

    //一个顺子得到一个金币
    public static long solution(HashMap<Integer, Integer> map) {
        long res = 0L;
        int minKey = Integer.MAX_VALUE;
        int maxKey = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getKey() < minKey) {
                minKey = entry.getKey();
            }
            if (entry.getKey() > maxKey) {
                maxKey = entry.getKey();
            }
        }
        //模拟扣除数字，如果不满足就回溯
        //从最小数开始遍历，一直到map为空
        while (true) {
            if (map.get(minKey) != null && map.get(minKey) >= 1 &&
                    map.get(minKey + 1) != null && map.get(minKey + 1) >= 1 &&
                    map.get(minKey + 2) != null && map.get(minKey + 2) >= 1 &&
                    map.get(minKey + 3) != null && map.get(minKey + 3) >= 1 &&
                    map.get(minKey + 4) != null && map.get(minKey + 4) >= 1) {
                //满足条件，所有key对应的value-1
                for (int i = 0; i <= 4; i++) {
                        map.put(minKey + i, map.get(minKey + i) - 1);
                }
                //满足一次顺子,res++
                res++;
            }
            //不满足顺子，key++
            else {
                minKey++;
            }
            //如果minKey + 4 已经 > maxKey 那么已经不可能组成顺子了。跳出循环
            if (minKey + 4 > maxKey) {
                break;
            }
        }
        return res;
    }
}
