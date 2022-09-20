package 剑指offer第二版.排序;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author KingofTetris
 * @Date 2022/9/15 16:46
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。
 * 2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,4,5]
 * 输出: True
 *  
 *
 * 示例 2:
 *
 * 输入: [0,0,1,2,5]
 * 输出: True
 *  
 *
 * 限制：
 *
 * 数组长度为 5 
 *
 * 数组的数取值为 [0, 13] .
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/bu-ke-pai-zhong-de-shun-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer61_扑克牌中的顺子 {

    /**
     * 要注意并没有限定是一副牌
     * 排序法 nlogn 1
     * @param nums
     * @return
     */
    public boolean isStraight(int[] nums) {
        int joker = 0;
        Arrays.sort(nums); // 数组排序
        for(int i = 0; i < 4; i++) {
            if(nums[i] == 0) joker++; // 统计大小王数量
            else if(nums[i] == nums[i + 1]) return false; // 若有重复，提前返回 false
        }
        //joker的作用在于记录最小牌的位置
        return nums[4] - nums[joker] < 5; // 最大牌 - 最小牌 < 5 则可构成顺子
    }

    /**
     * HashSet n n
     * 1.牌不重复，除了大小王
     * 2.max - min < 5
     * @param nums
     * @return
     */
    public boolean isStraight2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        //牌的大小在0-13
        int max = -1;
        int min = 14;
        for (int num : nums) {
            if (num == 0) continue;//0跳过
            if (set.contains(num)) return false;//重复则不可能。
            set.add(num);
            max = Math.max(max,num);
            min = Math.min(min,num);
        }

        return max - min < 5;
    }
}
