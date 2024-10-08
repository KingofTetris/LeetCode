package LeetCode数据结构与算法基础.回溯;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/9/13
 */


/**
 * 给定一个长度为4的整数数组 cards 。你有 4 张卡片，每张卡片上都包含一个范围在 [1,9] 的数字。
 * 您应该使用运算符 ['+', '-', '*', '/'] 和括号 '(' 和 ')' 将这些卡片上的数字排列成数学表达式，以获得值24。
 *
 * 你须遵守以下规则:
 *
 * 除法运算符 '/' 表示实数除法，而不是整数除法。
 * 例如， 4 /(1 - 2 / 3)= 4 /(1 / 3)= 12 。
 * 每个运算都在两个数字之间。特别是，不能使用 “-” 作为一元运算符。
 * 例如，如果 cards =[1,1,1,1] ，则表达式 “-1 -1 -1 -1” 是 不允许 的。
 * 你不能把数字串在一起
 * 例如，如果 cards =[1,2,1,2] ，则表达式 “12 + 12” 无效。
 * 如果可以得到这样的表达式，其计算结果为 24 ，则返回 true ，否则返回 false 。
 *
 *
 *
 * 示例 1:
 *
 * 输入: cards = [4, 1, 8, 7]
 * 输出: true
 * 解释: (8-4) * (7-1) = 24
 * 示例 2:
 *
 * 输入: cards = [1, 2, 1, 2]
 * 输出: false
 *
 *
 * 提示:
 *
 * cards.length == 4
 * 1 <= cards[i] <= 9
 */
public class 扩展版24点 {


    @Test
    public void test(){
//        int[] cards = {1,2,3,8};
//        int[] cards = {2,2,2,3,4};
        int[] cards = {1,1,1,1};
        int points = 24;
        boolean b = judgePoints(cards, points);
        System.out.println(b);
    }
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 判断给定的扑克牌通否通过排列和四则运算得到指定的值
     *
     * @param cards int整型一维数组 扑克牌对应的数字
     * @param points int整型 扑克牌需要通过排列和四则运算得到的值
     * @return bool布尔型
     */

    // /号 代表实数运算，不是整数运算
    // 8 / ( 1 - 2/3 ) = 24 而不是 8
    // - 号也不能当作单独的-号来用。
    // 而且也不限制数字的使用顺序。

    //回溯尝试所有可能
    public boolean judgePoints(int[] cards, int points) {
        double[] nums_1 = new double[cards.length];
        //把int 转成 double 方便实数计算
        for (int i = 0; i < nums_1.length; i++) {
            nums_1[i] = cards[i];
        }
        return dfsCardToPoints(nums_1, points);
    }

    private boolean dfsCardToPoints(double[] nums_1, int points) {
        //终止条件 如果当前cardCpy的长度已经等于1 说明算出了一个结果。
        int n = nums_1.length;
        if (n == 1) {
            //如果误差足够小，说明找到了一个符合的方案。那么return true;
            return Math.abs(nums_1[0] - points) < 0.0001;
        }


        /**
         * 每次先选择两个不同的数。
         */
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                //每次计算两个数的结果以后，nums_1就少一个数
                double[] nums_2 = new double[n - 1];
                //先固定nums_1的前两个数，找出这两个数的所有可能。
                //预备把这个结果放到nums_2的结尾
                double[] temp = compute(nums_1[i], nums_1[j]);

                //把和i,j不同的数放到nums_2中
                int cur = 0;
                for (int k = 0; k < n; k++) {
                    //从nums_1里面选择不同的数放入nums_2
                    if (k != i && k != j) {
                        nums_2[cur++] = nums_1[k];
                    }
                }

                //遍历一开始计算的每一种可能,把他放入nums_
                // 因为前面都有数了，那么就只能放入末尾
                //开始dfs
                for (double t : temp) {
                    nums_2[nums_2.length - 1] = t;
                    if (dfsCardToPoints(nums_2, points)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    //能这样计算是因为1 <= cards[i] <= 9
    //不然可能会存在/0异常。
    private double[] compute(double i, double j) {
        //两个数组合的所有可能
        return new double[]{i + j, i - j, j - i, i * j, i / j, j / i};
    }
}
