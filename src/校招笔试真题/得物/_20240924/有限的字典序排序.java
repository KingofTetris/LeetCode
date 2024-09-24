package 校招笔试真题.得物._20240924;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

//给你一个数组，你可以交换任意相邻的2个数组，但是每个数字只能交换2次
//请返回最大的字典序数组。
public class 有限的字典序排序 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];

        //数组没有重复元素
        HashMap<Integer,Integer> map = new HashMap<>();//记录每个数字的初始位置
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
            map.put(nums[i],i);
        }
        //记录每个数的交换次数
        int[] changeTime = new int[n];
        int swapLeft = 2;
        for (int i = 0; i < n - 1; i++) {
            int ori = map.get(nums[i]);
            //从i + 1到初始ori + swapLeft的最大值，不能超过初始位置后面swapLeft。
            for (int j = i + 1; j < n && j <= ori + swapLeft; j++) {
                //如果后面比前面大 就尝试交换
                if (nums[j] > nums[i]){
                    if (changeTime[i] < 2 && changeTime[j] < 2){
                        //交换两个数
                        int temp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = temp;
                        //前两个数+1，后面的数+2
                        if (j - i == 2 && changeTime[j] == 0 && changeTime[i] == 0){
                            changeTime[i] += 2;
                            changeTime[j] += 2;
                        }
                        else {
                            changeTime[i]++;
                            changeTime[j]++;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (i != n - 1){
                System.out.print(nums[i] + " ");
            }
            else{
                System.out.print(nums[i]);
            }
        }
    }
}
