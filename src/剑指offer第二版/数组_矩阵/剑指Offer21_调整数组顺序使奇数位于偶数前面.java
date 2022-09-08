package 剑指offer第二版.数组_矩阵;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @author by KingOfTetris
 * @date 2022/7/22
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
 * 示例：
 *
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *  
 *
 * 提示：
 *
 * 0 <= nums.length <= 50000
 * 0 <= nums[i] <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer21_调整数组顺序使奇数位于偶数前面 {
    @Test
    public void test(){
        int[] nums = {1,2,3,4,8,9,10,21,33};
        int[] exchange = exchange3(nums);
        for (int i = 0; i < exchange.length; i++) {
            System.out.printf(exchange[i] + " ");
        }
    }

    /**
     * 遍历数组用两个列表一个装奇数一个装偶数，然后用这装奇数的列表先填回数组。
     * 时间空间都挺垃圾的
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        LinkedList<Integer> odds = new LinkedList<>();
        LinkedList<Integer> evens = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) != 0){
                odds.add(nums[i]);
            }
            else {
                evens.add(nums[i]);
            }
        }
        for (int i = 0; i < odds.size(); i++) {
                nums[i] = odds.get(i);
        }
        for (int i = odds.size(); i < nums.length; i++) {
            nums[i] = evens.get(i - odds.size());
        }
        return nums;
    }


    /**
     * 双指针，一头一尾，如果偶数在前，奇数在后
     * 如果两个都是奇数就让头指针后移，直到发现偶数，然后互换
     * 如果两个都是偶数让尾指针前移，直到发现奇数，然后互换
     * @param nums
     * @return
     */
    public int[] exchange2(int[] nums){
        if (nums == null) return null;
        int i = 0, j = nums.length - 1, tmp;
        while(i < j) {
            while(i < j && (nums[i] & 1) == 1) i++; //这个i < j还是要加进去，不然会出错
            while(i < j && (nums[j] & 1) == 0) j--;
            tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }

    /**
     * 也可以快慢指针。快指针往后去探索奇数。慢指针始终指向第一个偶数，
     * 快指针遇到奇数就和慢指针互换。直到fast到结尾
     * @param nums
     * @return
     */
    public int[] exchange3(int[] nums){
        if (nums.length <= 1) return nums;//小于等于1直接返回就行了
        int slow = 0,fast = 0;

        while (fast < nums.length ){
            if ((nums[fast] & 1) == 1){ //搜索到奇数就直接互换
                int temp;
                temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;
                slow++;
            }
            fast++;
        }
        return nums;
    }
}
