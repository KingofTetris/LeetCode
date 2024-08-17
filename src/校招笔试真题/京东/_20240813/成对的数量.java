package 校招笔试真题.京东._20240813;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/8/13
 */
public class 成对的数量 {

    /*//暴力破解
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(),X = sc.nextInt();
        int[] nums = new int[N];
        for(int i = 0;i<N;i++){
            nums[i] = sc.nextInt();
        }
        //从数组中凑出目标X，ai+aj = X，ij可以相等。
        //双指针暴力找，后面五个TLE。想一下怎么搞。
        int count = 0;
        for(int i = 0;i < N;i++){
            for(int j = 0;j < N;j++){
                if(nums[i] + nums[j] == X){
                    count++;
                }
            }
        }
        System.out.println(count);
    }*/

    //空间换时间，用Map来存储数
    public static void main(String[] args){
        Scanner sc =  new Scanner(System.in);
        int N = sc.nextInt(),X = sc.nextInt();
        int[] nums = new int[N];
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            nums[i] =  sc.nextInt();
            map.put(nums[i],map.getOrDefault(nums[i],0) + 1);
        }
        int count = 0;
        //循环遍历数组
        for (int i = 0; i < N; i++) {
            int element = X - nums[i];
            if (map.containsKey(element)){
                count += map.get(element); //count等于element出现的次数。
            }
        }
        System.out.println(count);
    }
}
