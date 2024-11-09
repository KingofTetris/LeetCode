package LeetCode数据结构与算法基础.哈希表;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 *
 * 给你一个字符串 paragraph 和一个表示禁用词的字符串数组 banned ，
 *
 * 返回出现频率最高的非禁用词。题目数据 保证 至少存在一个非禁用词，且答案 唯一 。
 *
 * paragraph 中的单词 不区分大小写 ，答案应以 小写 形式返回。
 *
 *
 *
 * 示例 1：
 *
 * 输入：paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.", banned = ["hit"]
 * 输出："ball"
 * 解释：
 * "hit" 出现了 3 次，但它是禁用词。
 * "ball" 出现了两次（没有其他单词出现这么多次），因此它是段落中出现频率最高的非禁用词。
 * 请注意，段落中的单词不区分大小写，
 * 标点符号会被忽略（即使它们紧挨着单词，如 "ball,"），
 * 并且尽管 "hit" 出现的次数更多，但它不能作为答案，因为它是禁用词。
 * 示例 2：
 *
 * 输入：paragraph = "a.", banned = []
 * 输出："a"
 *
 *
 * 提示：
 *
 * 1 <= paragraph.length <= 1000
 * paragraph 由英文字母、空格 ' '、和以下符号组成："!?',;."
 * 0 <= banned.length <= 100
 * 1 <= banned[i].length <= 10
 * banned[i] 仅由小写英文字母组成
 *
 */
public class 最常见的单词 {

    @Test
    public void test(){
        String s = "Bob hit a ball, the hit BALL flew far after it was hit.";
        String[] banned = {"hit"};
        String commonWord = mostCommonWord(s, banned);
        System.out.println(commonWord);
    }
    public String mostCommonWord(String paragraph, String[] banned) {
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> cache = new HashMap<>();
        // \\w是一个非常常见的字符类，它用于匹配基本的单词构成字符。在大多数编程语言和文本编辑器中，单词由字母、数字以及下划线组成。
        // \\W正好相反，他匹配的是哪些非字母的符号。
        String[] words = paragraph.replaceAll("\\W+", " ")
                .toLowerCase().split("\\s+");
        for (String str : words) {
            if (!bannedSet.contains(str)) {
                cache.put(str, cache.getOrDefault(str, 0) + 1);
            }
        }
        //找出有最大value的entry，学到一招。
        //Collections.max(xx.entrySet(),Map.Entry.comparingByValue());
        return Collections.max(cache.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

/*    作者：Kvicii
    链接：https://leetcode.cn/problems/most-common-word/solutions/1427651/rustgo-by-kyushu-3eng/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
