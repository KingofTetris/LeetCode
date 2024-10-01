package LeetCode数据结构与算法基础.动态规划.序列DP;

import org.junit.Test;

import java.util.*;

/**
 * @author KingofTetris
 * @File 最长定差子序列_2021_11_5
 * @Time 2021/11/5  11:00
 * 给你一个整数数组 arr 和一个整数 difference，
 * 请你找出并返回 arr 中最长等差子序列的长度，该子序列中相邻元素之间的差等于 difference 。
 *
 * 子序列 是指在不改变其余元素顺序的情况下，通过删除一些元素或不删除任何元素而从 arr 派生出来的序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [1,2,3,4], difference = 1
 * 输出：4
 * 解释：最长的等差子序列是 [1,2,3,4]。
 * 示例 2：
 *
 * 输入：arr = [1,3,5,7], difference = 1
 * 输出：1
 * 解释：最长的等差子序列是任意单个元素。
 * 示例 3：
 *
 * 输入：arr = [1,5,7,8,5,3,4,2,1], difference = -2
 * 输出：4
 * 解释：最长的等差子序列是 [7,5,3,1]。
 *  
 *
 * 提示：
 *
 * 1 <= arr.length <= 105
 * -104 <= arr[i], difference <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-arithmetic-subsequence-of-given-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


//子序列不要求连续 但要求数字一定是一个比一个后面
public class 最长等差序列_2021_11_5 {
    @Test
    public void test() {
//        int[] arr = {1,5,7,9,3};
//        int[] arr = {1,2,3,4};
//        int[] arr = {1,5,7,8,5,3,4,2,1};
        int[] arr = {3,0,-3,4,-4,7,6};
        int diffenrence = 3;
        int res = longestSubsequence2(arr, diffenrence);
        System.out.println(res);
    }

    //理解错误，写成了，找到序列中能排列成等差数列的数字。
    public int longestSubsequence(int[] arr, int difference) {

        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        Arrays.sort(arr);
        for(int i = 0; i < arr.length;i++){
            for (int j = 1; j < arr.length; j++) {
                if (arr[i] == difference)
                    set.add(arr[i]);
                if (difference >= 0){
                    if (arr[j] - arr[i] == difference){
//                        System.out.println(arr[i]+"\t"+arr[j]);
                        set.add(arr[i]);
                        set.add(arr[j]);
                    }
                }
               if (difference < 0){
                   if (arr[i] - arr[j] == difference){
//                       System.out.println(arr[i]+"\t"+arr[j]);
                       set.add(arr[i]);
                        set.add(arr[j]);
                   }
               }
            }
        }

        //现在set里面就全是相差为difference的数值对了，但是未必是定差子序列
        //比如1 3 2 4 5 7 虽然两个一对 都相差2，但并不是定差子序列
        //应当是1 3 5 7 所以现在问题是筛去2 4.
        System.out.println(set);


        //要注意foreach循环遍历线程不安全的容器的同时是不允许对容器进行操作的
        int[] temp = new int[set.size()];
        int count = 0;
        Iterator<Integer> iterator = set.iterator();

        while (iterator.hasNext()){
            temp[count] = iterator.next();
            if (count != 0){
                int diff = temp[count] - temp[count - 1];
                if(diff != difference && diff != -(difference))
                {
                    //删除当前指针所指向的元素，一般和next方法一起用，这时候的作用就是删除next方法返回的元素
                    iterator.remove();
                    temp[count] = temp[count - 1];
                }
            }
            count++;
        }

        System.out.println(set);
        return set.size();
    }



    //动态规划
    public int longestSubsequence2(int[] arr, int difference) {
        Map<Integer,Integer> map = new HashMap<>();
        int res = 0;
        for(int a:arr){
            int cnt = map.getOrDefault(a-difference,0) + 1;
            res = Math.max(cnt,res);
            map.put(a,cnt);
        }
        return res;
    }
}
