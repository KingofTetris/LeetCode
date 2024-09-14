package 校招笔试真题.FunPlus._20240913;

import org.junit.Test;

import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/9/13
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


    //回溯尝试所有可能
    public boolean judgePoints(int[] cards, int points) {
        double[] cardsCopy = new double[cards.length];
        for (int i = 0; i < cardsCopy.length; i++) {
            cardsCopy[i] = cards[i];
        }

        return dfsCardToPoints(cardsCopy, points);
    }

    private boolean dfsCardToPoints(double[] cardCpy, int points) {
        //终止条件 如果cardCpy的长度已经等于1 说明算出了一个结果。
        int n = cardCpy.length;
        if (n == 1) {
            //如果误差足够小，说明找到了一个符合的方案。那么return true;
            return Math.abs(cardCpy[0] - points) < 0.0001;
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                //每次计算 cardsCpy就少一个数
                double[] cardsRes = new double[n - 1];
                int cur = 0;
                for (int k = 0; k < n; k++) {
                    //从四个数里面选择2个不同的数进行四则运算
                    if (k != i && k != j) {
                        cardsRes[cur++] = cardCpy[k];
                    }
                }

                double[] temp = compute(cardCpy[i], cardCpy[j]);
                //遍历每一种可能。
                for (double t : temp) {
                    cardsRes[cardsRes.length - 1] = t;
                    if (dfsCardToPoints(cardsRes, points)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private double[] compute(double i, double j) {
        //两个数组合的所有可能
        return new double[]{i + j, i - j, j - i, i * j, i / j, j / i};
    }
}
