package LeetCode数据结构与算法基础.贪心;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author by KingOfTetris
 * @date 2024/9/7
 */
public class 分发饼干 {

    //s饼干，g孩子
    //思路2：优先考虑胃口，先喂饱大胃口
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int count = 0;
        int start = s.length - 1;
        // 遍历胃口
        for (int index = g.length - 1; index >= 0; index--) {
            if(start >= 0 && g[index] <= s[start]) {
                start--;
                count++;
            }
        }
        return count;
    }
}
