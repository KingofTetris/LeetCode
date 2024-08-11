package LeetCode数据结构与算法基础.day7.优先队列;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author KingofTetris
 * @File 前K个高频元素
 * @Time 2021/10/30  10:35

 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * k 的取值范围是 [1, 数组中不相同的元素的个数]
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的

 * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


public class 前K个高频元素 {

    @Test
    public void test(){
        int[] nums = {1,1,1,2,2,3};
        int[] ints = topKFrequent(nums, 2);
        for(int i : ints){
            System.out.print(i + "\t");
        }
    }
    //hashmap一个数一个频率
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer,Integer> frequency = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            frequency.put(nums[i], frequency.getOrDefault(nums[i],0) + 1);
        }
        //lamda表达式构造优先队列，按照value降序排列
        PriorityQueue<Map.Entry<Integer,Integer>> queue = new PriorityQueue<>(
                //使用Comparator定制化排序，用lambda表达式简略了匿名内部类。
                //本来匿名内部类需要写一个compare(Object o1,Object o2)方法
                //lambda表达式直接省略了方法名和参数类型和返回语句，单括号，直接写成下面的样子。
                (o1, o2) -> -(o1.getValue() - o2.getValue()) //表示用参数的value，从大到小排序。
        );
        //把map集合放入优先队列中，addAll方法。就可以得到一个按照频率从大到小排序的集合了。
        queue.addAll(frequency.entrySet());
        int[] ans = new int[k];
        for (int i = 0; i < k && !queue.isEmpty(); i++) {
            ans[i] = queue.poll().getKey();
        }
        return ans;
    }
}
