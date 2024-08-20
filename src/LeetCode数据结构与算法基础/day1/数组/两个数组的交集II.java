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
    //法一 哈希表(无序有序都能用)
    //由于同一个数字在两个数组中都可能出现多次，因此需要用哈希表存储每个数字出现的次数。
    // 对于一个数字，其在交集中出现的次数等于该数字在两个数组中出现次数的最小值。
    //所以先遍历小数组，再遍历大数组 小数组中每次++
    //大数组中每次--
 /*   public int[] intersect(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;

        //默认num1的长度 小于 num2
        //调正长度小的那个放在前面当nums1
        if(n > m )
            return intersect(nums2,nums1);

        HashMap<Integer,Integer> map = new HashMap<>();
        for (int num:nums1) {
            //getOrDefault() 方法获取指定 key 对应的 value，如果找不到 key
            // ，则返回设置的默认值。在这里一开始为0，所以用个getOrDefault
            int count = map.getOrDefault(num,0)  + 1;
            map.put(num,count);
        }

        //交集肯定不可能超过num1.length
        int[] intersection = new int[nums1.length];
        int index = 0;
        for(int num:nums2){

            //首先要明确长数组里面的数没必要进map
            //只是查一下对应的value值而已
            int count = map.getOrDefault(num,0);
            //count = 0后就不动了 没必要删除
            //上面这句话是放屁，一定要删
            if(count > 0){
                //都有共同的数放入交集，并减去一次
                intersection[index++] = num;
                count--;
                //如果--后还>0
                //更新count值
                if(count > 0){
                    map.put(num,count);
                }
                //如果count为0了 把这个元素给删去，保证是小数组里面的数量
                //不删去就会变成大数组里面的数量
                else {
                    map.remove(num);
                }
            }
        }

//        Arrays.copyOfRange() 返回一个数组
        return Arrays.copyOfRange(intersection,0,index);
    }*/

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
