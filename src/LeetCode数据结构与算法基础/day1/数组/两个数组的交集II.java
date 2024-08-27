package LeetCode数据结构与算法基础.day1.数组;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author KingofTetris
 * @File 两个数组的交集
 * @Time 2021/9/28  10:49
 */

/*350. 两个数组的交集 II
        给定两个数组，编写一个函数来计算它们的交集。
        示例 1：

        输入：nums1 = [1,2,2,1], nums2 = [2,2]
        输出：[2,2]
        示例 2:

        输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
        输出：[4,9]


        说明：

        输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
        我们可以不考虑输出结果的顺序。
        进阶：

        如果给定的数组已经排好序呢？你将如何优化你的算法？
        如果 nums1 的大小比 nums2 小很多，哪种方法更优？
        如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？*/
public class 两个数组的交集II {

    @Test
    public void test(){
        int[] num1 = {4,9,5,4,4}; // 4 4 4 5 9
        int[] num2 = {9,4,9,8,4,6}; // 4 4 6 8 9 9
        int[] test = intersect(num2,num1);
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + "\t");
        }
    }

    @Test
    public void test2(){
        //java 集合的交并补
        int[] num1 = {4,9,5,4,4}; // 4 4 4 5 9
        int[] num2 = {9,4,9,8,4,6}; // 4 4 6 8 9 9
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        for (int i : num1) {
            set1.add(i);
        }
        for (int i : num2) {
            set2.add(i);
        }
        /**
         * 定义两个Integer类型的非空集合set1和set2，
         * 则在使用JDK中的方法，可以实现集合的交集、并集和差集，方法如下：
         *
         * 交集 set1.retainAll(set2)；
         * 并集 set1.addAll(set2)；
         * 差集 or 补集 set1.removeAll(set2)。
         *       温馨提示，它们都是把结果集记录在set1中，使用的时候需要留意。
         */
         set1.retainAll(set2);
         System.out.println(set1);
    }

    //法二：排序+双指针 O(nlogn)
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length;
        int m = nums2.length;
        int[] intersection = new int[Math.min(n,m)];
        int p1 = 0;
        int p2 = 0;
        int index = 0;
//        这里用且 因为短的走完，必然不可能再有交集了，没必要继续走了
        while(p1 < n && p2 < m){
            //因为已经从小到大排序，谁小了 谁后移
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
        //最后返回intersection的0到index部分即可。
        return Arrays.copyOfRange(intersection,0,index);
    }
}
