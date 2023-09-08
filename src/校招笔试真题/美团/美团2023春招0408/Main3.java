package 校招笔试真题.美团.美团2023春招0408;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/7
 */

//55%
public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int l = sc.nextInt();
            int r = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            solution(nums, l, r);
        }
    }
    //一个数加1，一个数-1 保证所有的数都在l,r的范围内。
    private static void solution(int[] nums, int l, int r) {
        //L-R之间有多少个数。这些数字是可以动+-1的
        LinkedList<Integer> listSmall = new LinkedList<>();
        LinkedList<Integer> listBig = new LinkedList<>();
        LinkedList<Integer> listMid = new LinkedList<>();
        for (int num : nums) {
            if (num > r) {
                listBig.add(num);
            } else if (num < l) {
                listSmall.add(num);
            } else if (num > l && num < r) {
                listMid.add(num);
            }
        }
        int diff1 = 0, diff2 = 0;
        for (Integer integer : listSmall) {
            diff1 += l - integer;
        }
        for (Integer integer : listBig) {
            diff2 += integer - r;
        }
        if (diff1 == diff2) {
            //刚好调整diff次就可以了
            System.out.println(diff1);
        } else { //如果两者不相等，那么就需要拿中间的来补
            int bq = 0;
            for (Integer integer : listMid) {
                bq += integer - l;
            }
            int need = Math.abs(diff1 - diff2);
            //如果补全的次数够 就加上返回两者需要调整的较大者就行了。
            if (bq >= need) {
                System.out.println(Math.max(diff1, diff2));
            }
            //如果不够，那么就不可能了。
            else {
                System.out.println(-1);
            }
        }
    }
}


    /*int n = nums.length;
    //如果num全都大于r,或者全都<l 那么不可能。
    int count = 0;
        for (int num : nums) {
                if ( num < l ){
        count++;
        }
        }
        if (count == n) {
        System.out.println(-1);
        return;
        }
        count = 0;
        for (int num : nums) {
        if ( num > r ){
        count++;
        }
        }
        if (count == n) {
        System.out.println(-1);
        return;
        }*/
