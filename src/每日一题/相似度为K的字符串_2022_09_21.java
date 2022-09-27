package 每日一题;

import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * @Author KingofTetris
 * @Date 2022/9/21 10:10
 * 对于某些非负整数 k ，如果交换 s1 中两个字母的位置恰好 k 次，能够使结果字符串等于 s2 ，则认为字符串 s1 和 s2 的 相似度为 k 。
 *
 * 给你两个字母异位词 s1 和 s2 ，返回 s1 和 s2 的相似度 k 的最小值。
 *
 * 示例 1：
 *
 * 输入：s1 = "ab", s2 = "ba"
 * 输出：1
 * 示例 2：
 *
 * 输入：s1 = "abc", s2 = "bca"
 * 输出：2
 *  
 *
 * 提示：
 *
 * 1 <= s1.length <= 20
 * s2.length == s1.length
 * s1 和 s2  只包含集合 {'a', 'b', 'c', 'd', 'e', 'f'} 中的小写字母
 * s2 是 s1 的一个字母异位词
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/k-similar-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 相似度为K的字符串_2022_09_21 {

    @Test
    public void test(){
        String s1 = "abccaacceecdeea";
        String s2 = "bcaacceeccdeaae";
        int i = kSimilarity(s1, s2);
        System.out.println(i);
    }

    //TODO 困难题 看不懂。
    /**
     * 最少交换次数是不能单纯去找后面第一个一样的字符交换就行了
     * 这样不是最少次数。
     *
     * * 提示：
     *  *
     *  * 1 <= s1.length <= 20
     *  * s2.length == s1.length
     *  * s1 和 s2  只包含集合 {'a', 'b', 'c', 'd', 'e', 'f'} 中的小写字母 这条比较关键只包含 6 种不同的字符
     *  * s2 是 s1 的一个字母异位词
     *
     *  我们可以枚举所有可能的交换方案，在搜索时进行减枝从而提高搜索效率，最终找到最小的交换次数。
     *
     */
    public int kSimilarity(String s1, String s2) {
        int n = s1.length();
        Queue<Pair<String, Integer>> queue = new ArrayDeque<Pair<String, Integer>>();
        Set<String> visit = new HashSet<String>();
        queue.offer(new Pair<String, Integer>(s1, 0));
        visit.add(s1);
        int step = 0;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                Pair<String, Integer> pair = queue.poll();
                String cur = pair.getKey();
                int pos = pair.getValue();
                if (cur.equals(s2)) {
                    return step;
                }
                while (pos < n && cur.charAt(pos) == s2.charAt(pos)) {
                    pos++;
                }
                for (int j = pos + 1; j < n; j++) {
                    if (s2.charAt(j) == cur.charAt(j)) {
                        continue;
                    }
                    if (s2.charAt(pos) == cur.charAt(j)) {
                        String next = swap(cur, pos, j);
                        if (!visit.contains(next)) {
                            visit.add(next);
                            queue.offer(new Pair<String, Integer>(next, pos + 1));
                        }
                    }
                }
            }
            step++;
        }
        return step;
    }

    public String swap(String cur, int i, int j) {
        char[] arr = cur.toCharArray();
        char c = arr[i];
        arr[i] = arr[j];
        arr[j] = c;
        return new String(arr);
    }

 /*   作者：LeetCode-Solution
    链接：https://leetcode.cn/problems/k-similar-strings/solution/xiang-si-du-wei-k-de-zi-fu-chuan-by-leet-8z10/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/


    /**
     * DFS，剪枝，递归
     */
    int result = Integer.MAX_VALUE;
    public int kSimilarity2(String s1, String s2) {
        return execute(s1.toCharArray(), s2.toCharArray(), 0, 0);
    }

    public int execute(char[] sc1, char[] sc2, int start, int current) {
        //我们要拿result 记录当前最小值 当current已经大于这个数字的时候就没必要继续下去了
        //这就是剪枝。
        if (current >= result) return result; //当发现继续走下去这一步已经大于某条路的result时，就不必再走了
        if (start == sc1.length - 1) return result = Math.min(current, result); //当遍历到结束的时候 比较两者大小
        for (int i = start; i < sc1.length; i++) {
            if (sc1[i] != sc2[i]) {
                for (int j = i + 1; j < sc2.length; j++) {
                    if (sc2[j] == sc1[i] && sc2[j] != sc1[j]) { //当str2的第j个位置和str1的i位置相同，但又刚好和str1的j不同时，我们才交换
                        //否则 如果只是钱一个条件成立，后面不成立，那么你交换了以后，后面还是要交换回来。没有必要。
                        swap(sc2, i, j); // 交换
                        execute(sc1, sc2, i + 1, current + 1);//交换后，递归。每次i往后移动一个位置
                        swap(sc2, i, j); // 最后要记得回溯
                    }
                }
                return result;
            }
        }
        return result = Math.min(current, result);
    }

    public void swap(char[] sc, int i, int j){
        char temp = sc[i];
        sc[i] = sc[j];
        sc[j] = temp;
    }
}
