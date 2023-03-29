package 校招真题.美团.美团2023春招0318;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Author KingofTetris
 * @Date 2023/3/29 16:01
 * 彩带
 *
 * 题目描述：
 *
 * 小美现在有一串彩带，假定每一厘米的彩带上都是一种色彩。
 *
 * 因为任务的需要，小美希望从彩带上截取一段，使得彩带中的颜色数量不超过K种。
 *
 * 显然，这样的截取方法可能非常多。于是小美决定尽量长地截取一段。
 *
 * 你的任务是帮助小美截取尽量长的一段，使得这段彩带上不同的色彩数量不超过K种。
 *
 * 输入描述
 *
 * 第一行两个整数N,K，以空格分开，分别表示彩带有N厘米长，你截取的一段连续的彩带不能超过K种颜色。
 *
 * 接下来一行N个整数，每个整数表示一种色彩，相同的整数表示相同的色彩。
 *
 * 1≤N,K≤5000，彩带上的颜色数字介于[1, 2000]之间。
 *
 * 输出描述
 *
 * 一行，一个整数，表示选取的彩带的最大长度。
 *
 * 样例输入
 *
 * 8 3
 *
 * 1 2 3 2 1 4 5 1
 *
 * 样例输出
 *
 * 5
 */
public class 彩带 {

    public static void main(String agrs[]){
        int i = colorTie();
        System.out.println(i);
    }

    public static int colorTie(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }
        sc.close();

        //找到最长字串，并且这个字串的重复颜色<=k
        //一样还是滑动窗口
        int max = 0;
        for (int i = 0; i < n; i++) {
            int colorNums = 0; //每轮重置0
            LinkedList<Integer> digits = new LinkedList<>(); //每轮生成新的子串
            int start = i;
            while(start < n && colorNums <= k){
                //子串不包含这种颜色
                if (!digits.contains(nums[start]) ) {
                    colorNums++;//不包含这个颜色就+1
                    if (colorNums > k ){
                        if (digits.size() > max) max = digits.size();
                        break;//如果+1后种类>k 就可以结束这轮循环，开始下一轮了。
                    }
                    digits.add(nums[start]);//不大于k,加入子串
                }
                //包含就直接加入子串就行了
                else if (digits.contains(nums[start])){
                    digits.add(nums[start]);
                }
                start++;
            }
            //如果是start > n的情况就要用下面这个if赋值了。
            if (digits.size() > max) max = digits.size();
        }
        return max;
    }
}
