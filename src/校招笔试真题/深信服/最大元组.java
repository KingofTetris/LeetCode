package 校招笔试真题.深信服;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/9/16
 */

//给你n个元组，每个元组有两个值，等级lv和价值value
    //等级差值不超过x的元组能够进行组队。
    //请你返回这些元组按照规则组队后能得到价值的最大值。
//下面这个分组 只能过66.7%? 为什么
public class 最大元组 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int num = n;
        int x = sc.nextInt();
        int[][] yz = new int[n][2];
        int index = 0;
        while (n-- > 0) {
            yz[index][0] = sc.nextInt();
            yz[index][1] = sc.nextInt();
            index++;
        }
        //按等级从小到大排序
        Arrays.sort(yz, Comparator.comparingInt(o -> o[0]));
        int[] flags = new int[num];
        //分组
        List<List<int[]>> groups = new ArrayList<>();
        for (int i = 0; i < yz.length; i++) {
            List<int[]> group = new ArrayList<>();
            if (flags[i] != 1){
                group.add(yz[i]);
                flags[i] = 1;
            }
            for (int j = i + 1; j < yz.length; j++) {
                if (flags[j] != 1 && Math.abs(yz[i][0] - yz[i + 1][0]) <= x )
                {
                    group.add(yz[j]);
                    flags[j] = 1;
                } else {
                    break;
                }
            }
            groups.add(group);
        }
        int max = 0;
        for (List<int[]> group : groups) {
            int sum = 0;
            for (int[] temp : group) {
              sum += temp[1];
            }
            max = Math.max(sum,max);
        }
        System.out.println(max);
    }
}
