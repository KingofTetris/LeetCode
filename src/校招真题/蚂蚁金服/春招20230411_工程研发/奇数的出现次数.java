package 校招真题.蚂蚁金服.春招20230411_工程研发;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/4/20
 *
 * 最后输出所有奇数出现奇数次的总次数
 */
public class 奇数的出现次数 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];


        //存放奇数的出现次数
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            if ((nums[i] & 1) == 1) map.put(nums[i],map.getOrDefault(nums[i],0) + 1);
        }
        int res = 0;

        for (Integer value : map.values()) {
            if ((value & 1) == 1) res = res + value;
        }
//        for(Map.Entry<Integer,Integer> entry : map.entrySet()){
//            if ((entry.getValue() & 1) == 1) res++;
//        }
        System.out.println(res);
    }
}
