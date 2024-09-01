package 校招笔试真题.得物._20240828;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/8/29
 */

//给你两个数a,b，均为4位数
//现在密码只能从9->1,1->0,0->9->1->0这样往下转。
//请问从a转到b最少要几次操作?

/**
 * 如果没有指定a,b是几位。
 * 密码锁两个方向都能转呢?
 * 也就是下面这样，又该怎么解决？
 * 0<->9<->1<->0
 */
public class 密码锁 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        //a -> b
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        int[] nums1 = new int[4];
        int[] nums2 = new int[4];
        for(int i = 0;i<4;i++){
            if(arr1[i] == '0'){
                nums1[i] = 10;
            }
            else{
                nums1[i] = arr1[i] - '0';
            }

            if(arr2[i] == '0'){
                nums2[i] = 10;
            }
            else{
                nums2[i] = arr2[i] - '0';
            }
        }
        int min = 0;
        for(int i = 0;i < 4;i++){
            int temp = nums1[i];
            if(nums1[i] < nums2[i]){
                temp += 10;
            }
            int diff = Math.abs(temp - nums2[i]);
            min += diff;
        }
        System.out.println(min);
    }
}

