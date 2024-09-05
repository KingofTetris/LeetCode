package 校招笔试真题.得物._20240903;

import java.util.*;


/**
 * 小塔开了一家水果店，己知，一个水果恰好可以切成n块（不论大小），也只能切成n块，
 * 一个顾客，他买一盒水果，要求是：这盒水果中的水果块数必须在闭区间[l,r]中。
 *
 * 小塔只按“个"卖水果，而不是按”块“卖水果，所以，
 * 如果整数个水果并不满足顾客要求，小塔就不会卖给这位顾客；而如果存在整数个水果，使得这些水果切成的块数满足顾客要求，
 * 那么，小塔希望你告诉他，小塔最少需要切多少个水果，以及最多需要切多少个水果。
 */
public class 小塔卖水果 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int left = sc.nextInt();
            int right = sc.nextInt();

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            //一个水果能切成n块，那么分成[left,right]之间块最多最少是多少块
            //切n个满足不了 left
            //切n+1个超过 right返回-1
            for(int i = 1;i <= right;i++){
                int cur = i * n;
                if(cur >= left && cur <= right){
                    min = Math.min(min,i);
                    max = Math.max(max,i);
                }
                //已经大于right就没必要再乘了。
                else if(cur > right){
                    break;
                }
            }

            if(min == Integer.MAX_VALUE){
                System.out.println(-1);
            }
            else{
                System.out.println(min + " " + max);
            }
        }
    }
}
