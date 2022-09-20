package 每日一题;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author KingofTetris
 * @Date 2022/9/6 14:19
 * 我们定义了一个函数 countUniqueChars(s) 来统计字符串 s 中的唯一字符，并返回唯一字符的个数。
 * 例如：s = "LEETCODE" ，则其中 "L", "T","C","O","D" 都是唯一字符，因为它们只出现一次，所以 countUniqueChars(s) = 5 。
 * <p>
 * 本题将会给你一个字符串 s ，我们需要返回 countUniqueChars(t) 的总和，其中 t 是 s 的子字符串。输入用例保证返回值为 32 位整数。
 * <p>
 * 注意，某些子字符串可能是重复的，但你统计时也必须算上这些重复的子字符串（也就是说，你必须统计 s 的所有子字符串中的唯一字符）。
 * 示例 1：
 * <p>
 * 输入: s = "ABC"
 * 输出: 10
 * 解释: 所有可能的子串为："A","B","C","AB","BC" 和 "ABC"。
 * 其中，每一个子串都由独特字符构成。
 * 所以其长度总和为：1 + 1 + 1 + 2 + 2 + 3 = 10
 * 示例 2：
 * <p>
 * 输入: s = "ABA"
 * 输出: 8
 * 解释: 除了 countUniqueChars("ABA") = 1 之外，其余与示例 1 相同。
 * 示例 3：
 * <p>
 * 输入：s = "LEETCODE"
 * 输出：92
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 10^5
 * s 只包含大写英文字符
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/count-unique-characters-of-all-substrings-of-a-given-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 统计子串中的唯一字符_2022_09_06 {

    @Test
    public void test() {
        String s = "ABCADEFA";
        int i = uniqueLetterString2(s);
        System.out.println(i);
    }

    /**
     * 方法一：考虑每一个字符其对总得分的贡献值，而不用去考虑字符对每一个子串的得分
     * 如AXXXAXXA，对于中间的A字符而言，我们要找到它左边最远不重复字符的位置，和右边最远不重复字符的位置，
     * 左边有a个字符，右边有b个字符，那么可以构成子串数量为(a + 1) * (b + 1)
     * 比如 BC A EDF 前面有2个字符，后面有3个
     * 实际上 A对这个字符串的贡献是 BC/A/E/D/F 也就是4种，C/A/E/D/F 也是4种
     * 前半部分的贡献就是 a*(b+1) 后半部分实际上是 /A/E/D/F  4种，实际上是 (b+1)种
     * 那么总共也就是 a*(b+1)+(b+1) = (a + 1) * (b + 1)

     * 因此我们需要记录每个位置，其左边第一个相同字符的位置 l和右边第一个相同字符的位置 r，
     * 贡献值可以直接写为：( a + 1 ) * ( b + 1 ) = (i - l) * (r - i)
     * <p>
     * <p>
     * 作者：zealous-volhardrla
     * 链接：https://leetcode.cn/problems/count-unique-characters-of-all-substrings-of-a-given-string/solution/-by-zealous-volhardrla-rfuf/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int uniqueLetterString2(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, ans = 0;
        int[] l = new int[n], r = new int[n];//每个字母第一次出现和最后一次出现的位置
        int[] idx = new int[26];//idx记录每个数字最后出现的索引位置

        /**
         * 用特殊情况 AAAAA 去考虑两个为什么填1和n就明白了
         * 另外如果是唯一的字符，那么就把-1记为他的左边第一个相同字符出现位置
         * 把n记为他的右边第一个相同的字符出现位置
         * 这样不重复的元素left[i] = -1 ,right[i] = n 那么贡献就是  ( i - (-1)) * ( n - i)
         */
        Arrays.fill(l, -1);//先假设每个元素左边第一个相同字符出现位置都标为-1
        Arrays.fill(r, n);//先假设每个元素右边第一个相同的字符出现位置标为n

        Arrays.fill(idx, -1);//把idx都标记为-1
        //去算左右第一次出现和最后一次出现的位置
        /**
         * 下面的代码是最重要的精华，怎么求出left和right一定要看懂
         * 例子 ABCADEFA
         * idx[] = {7,1,2,4,5,6,-1,-1,.....,-1}
         * left[] = {-1,-1,-1,0,-1,-1,-1,3}
         * right[] ={3, 8, 8, 7, 8, 8, 8,8}
         *
         * //这样left和right就记录了每个元素的前后一个相同元素出现的位置
         */
        for (int i = 0; i < n; i++) {
            if (idx[cs[i] - 'A'] != -1) { // 如果这个字母出现过了(!=1)
                int last = idx[cs[i] - 'A'];//那么我们把上一次相同元素出现的位置last记录下来
                r[last] = i;//last的下一个相同元素位置就是i
                l[i] = last;//反过来 i 的上一个相同元素位置就是last
            }
            //如果这个字母第一次出现 就把对应的索引位置标为其下标i 记录他出现的位置
            idx[cs[i] - 'A'] = i;
        }

        for (int i = 0; i < n; i++)  ans += (i - l[i]) * (r[i] - i);//计算贡献
        return ans;
    }

    /**
     * 串需要连续，序列不用
     *
     * @param s
     * @return
     */
    public int uniqueLetterString(String s) {
        ArrayList<String> strings = subsOfString(s);
        int count = 0;
        for (int i = 0; i < strings.size(); i++) {
//            System.out.println(strings.get(i));
            count += uniqueCount(strings.get(i));
        }
        return count;
    }

    public int uniqueCount(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);//初始为0，每次给出现的字母+1
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                count++;
            }
        }
        return count;
    }

    //利用substring求出所有的子串 这个所需的空间和时间都很大。
    public ArrayList<String> subsOfString(String s) {
        ArrayList<String> strs = new ArrayList<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            for (int j = 1; j <= length - i; j++) {
                strs.add(s.substring(i, i + j));
            }
        }
        return strs;
    }
}
