package LeetCode数据结构与算法基础.排序;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 运动员和训练师的最大匹配数 {

    //训练师：比我强的运动员我可降不住啊！
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        Arrays.sort(players);
        Arrays.sort(trainers);
        int m = players.length, n = trainers.length;
        int count = 0;
        for (int i = 0, j = 0; i < m && j < n; i++, j++) {
            while (j < n && players[i] > trainers[j]) {
                j++;
            }
            if (j < n) {
                count++;
            }
        }
        return count;
    }

   /* 作者：力扣官方题解
    链接：https://leetcode.cn/problems/maximum-matching-of-players-with-trainers/solutions/1850596/yun-dong-yuan-he-xun-lian-shi-de-zui-da-3icgd/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
