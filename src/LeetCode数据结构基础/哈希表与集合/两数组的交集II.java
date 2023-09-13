package LeetCode数据结构基础.哈希表与集合;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2023/9/11
 */

//https://leetcode.cn/problems/intersection-of-two-arrays-ii/description/
//Python可以直接转化成集合用 & 取交集。
public class 两数组的交集II {
    public static void main(String[] args) {

    }

    public static int[] solution(int[] nums1,int[] nums2){
        //排序，然后比较。
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length;
        int m = nums2.length;
        int[] intersection = new int[Math.min(n,m)];
        int p1 = 0;
        int p2 = 0;
        int index = 0;
//        这里用且因为短的走完，必然不可能再有交集了，没必要继续走了
        while(p1 < n && p2 < m){
            //因为已经从小打大排序，谁小了 谁后移
            if(nums1[p1] < nums2[p2]){
                p1++;
            }
            else if(nums1[p1] > nums2[p2]){
                p2++;
            }
            //相等就添加到集合里去
            else{
                intersection[index] = nums1[p1];
                p1++;
                p2++;
                index++;
            }
        }
        return Arrays.copyOfRange(intersection,0,index);
    }
}
