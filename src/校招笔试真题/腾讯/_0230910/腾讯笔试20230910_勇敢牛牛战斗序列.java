package 校招笔试真题.腾讯._0230910;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/8/21
 */
public class 腾讯笔试20230910_勇敢牛牛战斗序列 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] monsters = new int[n];
        for (int i = 0; i < n; i++) {
            monsters[i] = sc.nextInt();
        }
        //从小到大排序
        Arrays.sort(monsters);
        int attack = 0;//初始攻击力=0
        //courage会突破int上限
        long courage = 0;
        //每次贪心地选，
        //最小->最大->最小->最大...
        //这样累计的勇气值就最大
        for(int left = 0,right = n - 1;left <= right;){

            if (attack < monsters[right]){
                //选最大
                courage += monsters[right] - attack;
                attack = monsters[right];
                //这个怪物不能再打了
                right--;
            }
            else{
                //选最小
                attack = monsters[left];
                //这个怪物不能再打了
                left++;
            }
        }
        System.out.println(courage);
    }
}
