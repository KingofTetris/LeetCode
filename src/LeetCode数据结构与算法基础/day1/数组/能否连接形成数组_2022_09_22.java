package LeetCode数据结构与算法基础.day1.数组;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author by KingOfTetris
 * @date 2022/9/22
 * 给你一个整数数组 arr ，数组中的每个整数 互不相同 。
 * 另有一个由整数数组构成的数组 pieces，其中的整数也 互不相同 。
 * 请你以 任意顺序 连接 pieces 中的数组以形成 arr 。
 * 但是，不允许 对每个数组 pieces[i] 中的整数重新排序。
 *
 * 如果可以连接 pieces 中的数组形成 arr ，返回 true ；否则，返回 false 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [15,88], pieces = [[88],[15]]
 * 输出：true
 * 解释：依次连接 [15] 和 [88]
 * 示例 2：
 *
 * 输入：arr = [49,18,16], pieces = [[16,18,49]]
 * 输出：false
 * 解释：即便数字相符，也不能重新排列 pieces[0]
 * 示例 3：
 *
 * 输入：arr = [91,4,64,78], pieces = [[78],[4,64],[91]]
 * 输出：true
 * 解释：依次连接 [91]、[4,64] 和 [78]
 *  
 *
 * 提示：
 *
 * 1 <= pieces.length <= arr.length <= 100
 * sum(pieces[i].length) == arr.length
 * 1 <= pieces[i].length <= arr.length
 * 1 <= arr[i], pieces[i][j] <= 100
 * arr 中的整数 互不相同
 * pieces 中的整数 互不相同（也就是说，如果将 pieces 扁平化成一维数组，数组中的所有整数互不相同）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-array-formation-through-concatenation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 能否连接形成数组_2022_09_22 {

    @Test
    public void test(){
        int[] arr = new int[]{15,88};
        int[][] pieces = new int[][]{{88},{15}};

        System.out.println(canFormArray(arr, pieces));

    }

    /**
     * 瞎写的用数字作为key,数组作为value
     * 节点时空都很低。但是好像官解也就是这种写法
     * @param arr
     * @param pieces
     * @return
     */
    public boolean canFormArray(int[] arr, int[][] pieces) {
        Map<Integer,int[]> map = new HashMap<>();

        /**
         * 因为数字各不相同所以才能用开头的数字当作key
         * 把以Key开头的数组作为value
         */
        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (pieces[i][0] == arr[j])
                    map.put(arr[j],pieces[i]);//arr[j]作为KEY，piece数组作为value
            }
        }

        /**
         * 遍历arr,如果根本没有以这个开头的那么就是false。
         * 如果有但是组内不相等那么也是false
         * 遍历完，那就是true
         */
        for (int i = 0; i < arr.length; ) {

            int[] values = map.getOrDefault(arr[i],null);//组间可以无序，符合map的特性
            if (values == null) return false;//如果根本没有以temp开头的数组,那么就null
            for (int j = 0; j < values.length; j++) {
                if (arr[i] == values[j])
                i++;
                else return false; //如果组内不匹配也是false
            }
        }

        return true;
    }
}
