package LeetCode数据结构与算法基础.双指针与滑动窗口;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**
 * 给定字符串 s 和字符串数组 words, 返回  words[i] 中是s的子序列的单词个数 。
 * <p>
 * 字符串的 子序列 是从原始字符串中生成的新字符串，可以从中删去一些字符(可以是none)，而不改变其余字符的相对顺序。
 * <p>
 * 例如， “ace” 是 “abcde” 的子序列。
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcde", words = ["a","bb","acd","ace"]
 * 输出: 3
 * 解释: 有三个是 s 的子序列的单词: "a", "acd", "ace"。
 * Example 2:
 * <p>
 * 输入: s = "dsahjpjauf", words = ["ahjpjau","ja","ahbwzgqnuk","tnmlanowax"]
 * 输出: 2
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length <= 5 * 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 50
 * words[i]和 s 都只由小写字母组成。
 */
class 匹配子序列的单词数 {

    //做暴力的做法就是双指针，来一个单词遍历一次word和s,看看匹不匹配，这样的复杂度是O(m*n)
    //按题目的数据大概是 O(10^8) 一定超时


    //那么换个做法，我们去记录每个字母在s里面的出现位置
    //eg: a :[1,3,19] b :[2,6] c:[4,5] d:[7,10]
    //那么求 abbc 这个子序列是否存在
    //可以找到一个1,2,6,4的组合，显然是不合法的
    //那么找 bcd 可以找到 2,4,7 是合法的
    //那么显然，合法的就是一个递增序列，不合法的就是一个非递增序列。
    //按照这个思路，好像可行，但是对这题还是会超时

    //那么还有什么做法？
    //我们存放以某个元素开头的子序列
    //a : ["a","ac","ad","ade"..]
    //b : ["b","bc","be"]..
    //c : ["c","ce"..]
    //e : ["e]

    /**
     * 我们在s字符串上进行移动，我们给每一个word里面的字符串都加上一个指针，
     * 指针最开始的字符。若s当前的字符为c,那么所有words里面指针指向c这个字符的指针都应该往后移动，若
     * 移动到末尾，那么代表找到一个子序列。
     *
     *
     * 但是，我们发现每次去查找指向c字符的指针都遍历了整个words数组的长度，这个操作非常耗时，
     * 其实当一些指针指向末尾后，我们就不用再进行判断，因此可以从这进行优化
     * 使用一个队列来保存当前指向某个字符的所有指针，这时候每次移动时只会遍历当前字符c对应的指针，节省了许多遍历次数。
     *
     * @param s
     * @param words
     * @return
     */
    public int numMatchingSubseq(String s, String[] words) {
        int ans = 0;
        Queue<int[]>[] q = new Queue[26];//模拟26个字符指针
        for (int i = 0; i < 26; i++) q[i] = new LinkedList<>();
        for (int i = 0; i < words.length; i++) q[words[i].charAt(0) - 'a'].add(new int[]{i, 0});
        for (char c : s.toCharArray()) { // O(n)
            //目前指针位于c子母的字符串进行移动
            int size = q[c - 'a'].size();
            while (size-- > 0) {
                int[] tem = q[c - 'a'].poll();
                int i = tem[0], len = tem[1];
                if (len + 1 == words[i].length()) {
                    ans++; continue;
                }
                char t = words[i].charAt(len + 1);
                q[t - 'a'].add(new int[]{i, len + 1}); //指针移动
            }
        }
        return ans;
    }
  /*  作者：Tizzi
    链接：https://leetcode.cn/problems/number-of-matching-subsequences/solutions/1975263/java-liang-chong-jie-fa-er-fen-cha-zhao-wnyb0/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}

/*作者：力扣官方题解
链接：https://leetcode.cn/problems/number-of-matching-subsequences/solutions/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
