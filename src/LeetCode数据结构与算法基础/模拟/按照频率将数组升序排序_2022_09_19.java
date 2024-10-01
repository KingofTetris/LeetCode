package LeetCode数据结构与算法基础.模拟;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author KingofTetris
 * @Date 2022/9/19 9:54
 * 给你一个整数数组 nums ，请你将数组按照每个值的频率 升序 排序。
 * 如果有多个值的频率相同，请你按照数值本身将它们 降序 排序。 
 *
 * 请你返回排序后的数组。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2,2,2,3]
 * 输出：[3,1,1,2,2,2]
 * 解释：'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。
 * 示例 2：
 *
 * 输入：nums = [2,3,1,3,2]
 * 输出：[1,3,3,2,2]
 * 解释：'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。
 * 示例 3：
 *
 * 输入：nums = [-1,1,-6,4,5,-6,1,4,1]
 * 输出：[5,-1,4,4,-6,-6,1,1,1]
 *  
 *
 * 提示：
 *
 * //这个长度明显是可以排序的
 * 1 <= nums.length <= 100
 * -100 <= nums[i] <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sort-array-by-increasing-frequency
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 按照频率将数组升序排序_2022_09_19 {

    @Test
    public void test(){
        int[] nums = {1,2,2,3,1,3,4};
        frequencySort(nums);
    }


    /**
     * 比较巧妙的不排序
     * @param nums
     * @return
     */
    public int[] frequencySort2(int[] nums) {
        int n = nums.length;//用于提前跳出的变量
        int[] arr = new int[210]; // -100 <= num <= 100 那么 0 <= num + 100 <= 200
        // 用一个长度201的数组 来存储 num 的频率
        for (int i : nums) {
            arr[i + 100] ++; //自此 arr[i + 100] 就是 num = i 的频率
        }
        int[] res = new int[nums.length];


        int ii = 0;//用于给res赋值的临时变量
        for (int i = 1 ; i <= 100 ;i ++) { //每个数字出现的次数 在 1到 100之间
            for (int j = arr.length -1; j >= 0 ;j--) { //因为要组间从大到小排序 那么就倒过来遍历
                if (arr[j] == i) { //依次从 1 到 100 去遍历频率数组
                    for (int k = 0;k < i ; k++) { //出现几次就添加几次
                        res[ii ++] = j - 100;// j - 100 就是对应的num 数值
                    }
                }
            }
            if (ii == n - 1) break;//填满了就没必要再继续找下去了
        }
        return res;
    }
    //模拟
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        //list存储nums
        List<Integer> list = new ArrayList<Integer>();
        for (int num : nums) {
            list.add(num);
        }
        //存储好nums的list 用Collections 排序
        /**
         * 本题的难点就在于这个排序的写法
         */
        Collections.sort(list, (a, b) -> {
            int cnt1 = cnt.get(a), cnt2 = cnt.get(b);//a,b两个数的频率
            return cnt1 != cnt2 ? cnt1 - cnt2 : b - a;//如果频率不相等 就按照频率从小到大排序
            //如果相等，则按照本身的值从大到小排序
        });
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            nums[i] = list.get(i);
        }
        return nums;
    }
}
