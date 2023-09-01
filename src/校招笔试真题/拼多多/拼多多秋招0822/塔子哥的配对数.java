package 校招笔试真题.拼多多.拼多多秋招0822;

/**
 * @author by KingOfTetris
 * @date 2023/8/29
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class 塔子哥的配对数 {
    //这个做法 AC 7 WA 3 TLE 5.也就是 7/15。不知道哪错了。。
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            System.out.println(solve(nums,m));
        }
    }

    private static int solve(int[] nums, int m) {
        int count = 0;
       //每个数字只能配对一次
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num,map.getOrDefault(num,0) + 1);
        }
        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            while (entry.getValue() >= 2){
                entry.setValue(entry.getValue() - 2);//每有一对就+1
                count++;
            }
            //如果他们的和是m的倍数也可以配对
            //弄错了不是key + target = m 而是key + target = n*m.只要是m的倍数都可以。n = 1,2,3,...,4
            for(Map.Entry<Integer,Integer> nextEntry: map.entrySet()){
                if (entry.getKey() != nextEntry.getKey()){//不相同的key去相加
                    int nm = entry.getKey() + nextEntry.getKey();
                    //如果是m的整数倍
                    //并且两边都有剩余，那么
                    if (nm % m == 0 && entry.getValue() != 0 && nextEntry.getValue() != 0) {
                        entry.setValue(entry.getValue() - 1);
                        nextEntry.setValue(nextEntry.getValue() - 1);
                        count++;
                    }
                }
            }
        }
        return count;
    }

}
