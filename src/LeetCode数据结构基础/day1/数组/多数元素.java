package LeetCode数据结构基础.day1.数组;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 多数元素
 * @Time 2021/10/10  22:58
 */

/*169. 多数元素
        给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。

        **你可以假设数组是非空的，并且给定的数组总是存在多数元素。**
        这个条件给了思路！
        思路

如果将数组 nums 中的所有元素按照单调递增或单调递减的顺序排序，那么下标为n/2的元素（下标从 0 开始）一定是众数。




        示例 1：

        输入：[3,2,3]
        输出：3
        示例 2：

        输入：[2,2,1,1,1,2,2]
        输出：2*/
public class 多数元素 {
    @Test
    public void test(){
        int[] a = {1,1,3,3,2,3,2,2};
        System.out.println(majorityElement(a));
    }
    //法一法二和只出现一次的数字一样。要么暴力要么hashmap 都没啥意思
    //法三 因为这个数一定出现的次数一定大于n/2 所以排个序 返回nums[n/2]就可以了
    //这个Arrays.sort  对基本类型是双基准快速排序
    //对非基本类型是 TimSort
    //时间复杂度O(nlog n)
    //只能说也是比较土味的办法
//    public int majorityElement(int[] nums) {
//        Arrays.sort(nums);
//        return nums[nums.length/2];
//    }

    //来点有意思的，摩尔投票法
    //就和打仗一样，谁人多谁赢
    //时间复杂度O(n)
    public int majorityElement(int[] nums){
        int count = 0;
        //候选人登场
        Integer candidate = null;

        //遍历数组
        for (int num : nums) {
            //没人了就把 num当作候选人
            if (count == 0) {
                candidate = num;
            }

            //后面的数要是自己人就+1，不是自己人就一换一，泪目。
            count += (num == candidate) ? 1 : -1;
        }

        //最后得到的一定是众数
        return candidate;
    }

}
