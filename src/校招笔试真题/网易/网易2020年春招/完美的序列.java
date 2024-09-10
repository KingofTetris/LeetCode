package 校招笔试真题.网易.网易2020年春招;

import java.util.*;


//小易定义一个数字序列是完美的，当且仅当对于任意..即每个数字都要大于等于前面所有数字的和。
//满足这个连续字符串是完美的。
public class 完美的序列 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        while(T-- > 0){
            int n = sc.nextInt();
            int[] nums = new int[n];
            for(int i = 0; i < n; i++) nums[i] = sc.nextInt();
            System.out.println(solve(nums));
        }
    }

    // 直接用滑动窗口计算窗口内的元素之和来判断

    //不要暴力，滑动窗口就能解决。
    private static int solve(int[] nums) {
        int left = 0, right = 1;
        long sum = nums[0];
        int maxLen = 0;
        while(right < nums.length){
            if(nums[right] >= sum){
                maxLen = Math.max(maxLen, right - left + 1);
                sum += nums[right];
                right ++;
            }else{
                sum -= nums[left];
                left ++;
            }
        }
        return maxLen;
    }
}

/*作者：Pein531
链接：https://www.nowcoder.com/exam/test/72859958/submission?examPageSource=Company&pid=20790475&testCallback=https%3A%2F%2Fwww.nowcoder.com%2Fexam%2Fcompany%3FcurrentTab%3Drecommand%26jobId%3D100%26keyword%3D%E7%BD%91%E6%98%932020%26selectStatus%3D0&testclass=%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91
来源：牛客网*/
