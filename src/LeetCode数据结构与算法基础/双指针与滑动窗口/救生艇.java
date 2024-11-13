package LeetCode数据结构与算法基础.双指针与滑动窗口;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/11/13
 */
public class 救生艇 {

    public int numRescueBoats(int[] people, int limit) {
        int ans = 0;
        Arrays.sort(people);
        int light = 0, heavy = people.length - 1;
        while (light <= heavy) {
            if (people[light] + people[heavy] <= limit) {
                ++light;
            }
            --heavy;
            ++ans;
        }
        return ans;
    }

    /*作者：力扣官方题解
    链接：https://leetcode.cn/problems/boats-to-save-people/solutions/958838/jiu-sheng-ting-by-leetcode-solution-0nsp/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
