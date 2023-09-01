package LeetCode数据结构入门.day1.数组;

/**
 * @author by KingOfTetris
 * @date 2023/8/28
 */

//某个数组中包含了1-n的所有整数，但是消失了一个数。请问这个数是多少？
public class 消失的数字 {
    public static void main(String[] args) {
        int[] nums = {2,3,5,6,1,4,8};
        System.out.println(solution(nums));
    }
    public static int solution(int[] nums){
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int n = nums.length + 1;//因为消失了一个数，别忘了+1
        int sumRight =  (1 + n) * n / 2;
        return sumRight - sum;//这个就是消失的数字
    }
}
