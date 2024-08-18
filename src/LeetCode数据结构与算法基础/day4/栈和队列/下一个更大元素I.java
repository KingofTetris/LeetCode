package LeetCode数据结构与算法基础.day4.栈和队列;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/8/17
 */
public class 下一个更大元素I {

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        //单调栈，
        /**
         * 找到右边第一个比自己小的数的下标
         * 那就需要维护一个从栈顶到栈底 单调递增的序列
         * 一旦有元素比栈顶元素大，就要一直弹出到重新单调递增
         *
         * 一般栈维护的都是下标，用nums[i] 去取值
         */
        Deque<Integer> stack = new LinkedList<>();
        //结果，用-1保存初始值
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[0] = -1;
        }
        //用map来存储子数组出现的数字和下标
        //因为nums1是无重复数组
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0 ; i< nums1.length ; i++){
            hashMap.put(nums1[i],i);
        }

        //先把首元素压栈
        stack.push(0);
        for (int i = 1; i < nums2.length; i++) {
            //当前元素 小于等于 栈顶元素
            //直接压栈
            if (nums2[i] <= nums2[stack.peek()]) {
                stack.push(i);
            }
            //当前元素 大于 栈顶元素
            //开始记录
            else {
                while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                    //如果hashMap有这个栈顶元素，就可以去取他的下标了。
                    if (hashMap.containsKey(nums2[stack.peek()])){
                        Integer index = hashMap.get(nums2[stack.peek()]);
                        //然后把对应的下标修改为 对应的数字即可
                        res[index] = nums2[i];
                    }
                    //每次都要把这个数字弹出去
                    stack.pop();
                }
                //别忘了把i push进去。
                stack.push(i);
            }
        }

        return res;
    }

    /**
     * 暴力法，1 <= nums1.length <= nums2.length <= 1000
     * 这个是能过的，但如果飙到 10 ^ 5这样，就不行了。
     * 甚至这个规模暴力 比单调栈+hash还快
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] res = new int[m];
        Arrays.fill(res,-1);
        for (int i = 0; i < m; i++) {
            int cur = nums1[i];
            //不能走回头路，要先找到cur在nums2中的位置
            int j = 0;
            while(j < n && cur != nums2[j]){
                j++;
            }
            //找到以后再去后面的位置找第一个大于cur的
            int k = j + 1;
            while(k < n && cur > nums2[k]){
                k++;
            }
            if (k < n) res[i] = nums2[k];
            //其他情况就全是-1
        }
        return res;
    }

   /* 作者：力扣官方题解
    链接：https://leetcode.cn/problems/next-greater-element-i/solutions/1065517/xia-yi-ge-geng-da-yuan-su-i-by-leetcode-bfcoj/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
