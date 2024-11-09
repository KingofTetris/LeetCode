package LeetCode数据结构与算法基础.贪心;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 插入后的最大值 {

    /**
     * 对于正数，插入位置左侧的数字都必须 ≥x
     * 对于负数，插入位置左侧的数字都必须 ≤x
     * @param n
     * @param x
     * @return
     */
    public String maxValue(String n, int x) {
        int len = n.length(), i = 0, y = '0' + x;
        char[] cs = n.toCharArray();
        //@formatter:off
        if (cs[0] == '-') for (i = 1; i < len && cs[i] <= y; i++) {}
        else for (; i < len && cs[i] >= y; i++) {}
        //@formatter:on
        return String.valueOf(cs, 0, i) + x + String.valueOf(cs, i, len - i);
    }
}
