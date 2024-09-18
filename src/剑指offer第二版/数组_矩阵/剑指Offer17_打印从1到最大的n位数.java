package 剑指offer第二版.数组_矩阵;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/7/20 16:52
 * 剑指 Offer 17. 打印从1到最大的n位数
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * 示例 1:
 *
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *
 *
 * 说明：
 *
 * 用返回一个整数列表来代替打印
 * n 为正整数
 */
public class 剑指Offer17_打印从1到最大的n位数 {
    @Test
    public void test(){
        int[] ints = printNumbers(3);
        for (int anInt : ints) {
            System.out.println(anInt + " ");
        }
    }

    @Test
    public void test2(){
        Solution solution = new Solution();
        String s = solution.printNumbers(7);
        System.out.println(s);
    }

    /**
     * 普通模拟法，不考虑大数越界，极限到6。7就超出内存限制。
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        //最大的n位十进制数 10^n - 1
        int[] list = new int[(int) Math.pow(10,n) - 1];//大数越界问题就出现在这里，
        // 因为要初始化一个int数组，而int数组需要知道长度
        //但由于本题要求返回 int 类型数组，相当于默认所有数字都在 int32 整型取值范围内，因此不考虑大数越界问题。
        for (int i = 0; i < list.length; i++) { //当然如果上面OOM了，这里也会TLE(time limit exceed)
            list[i] = i + 1;
        }
        return list;
    }
}


/**
 *考虑大数越界问题
 *
 * 因为生成的列表无非就是n个位置上0-9的全排列
 * 所以我们避开进位操作，递归生成数字String列表
 * 基于分治算法的思想，先固定高位，向低位递归，当个位已被固定时，
 * 添加数字的字符串。例如当 n = 2 时（数字范围 1 - 99)
 * 固定十位为 0 - 9 ，按顺序依次开启递归，固定个位 0 - 9 ，终止递归并添加数字字符串。
 *
 * */
class Solution {

    char[] loop = {'0','1','2','3','4','5','6','7','8','9'};
    char[] num;
    int n;
    StringBuilder res = new StringBuilder();

    public String printNumbers(int n) {
        this.n = n;
        num = new char[n];
        dfs(0);
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }
    //该方法的作用是固定当前位
    public void dfs(int x) {
        if(x == n) {
            res.append(num).append(",");
            return;
        }
        for(char i : loop) {
            num[x] = i;
            dfs(x + 1);
        }
    }
}
