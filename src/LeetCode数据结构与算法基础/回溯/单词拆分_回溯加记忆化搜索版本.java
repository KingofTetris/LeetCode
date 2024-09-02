package LeetCode数据结构与算法基础.回溯;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author by KingOfTetris
 * @date 2024/8/21
 */


/**
 * 给定一个非空字符串 s 和一个包含非空单词的列表 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 *
 * 你可以假设字典中没有重复的单词。
 *
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 * 注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 */
public class 单词拆分_回溯加记忆化搜索版本 {

    private boolean backtracking1(String s, Set<String> wordSet, int startIndex) {
        //结束条件
        if (startIndex >= s.length()) {
            return true;
        }

        //单层逻辑
        for (int i = startIndex; i < s.length(); i++) {
            String word = s.substring(startIndex, i + 1);
            //如果字典里面有这个子串
            //继续往下判断下一个子串,startIndex 到 n-1 所有子串
            if (wordSet.contains(word) && backtracking1(s, wordSet, i + 1)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 其实这题只是为了求能不能，不是让你把方案全部列出来，没必要回溯。
     * DP就行了。
     */
    @Test
    public void test(){
        String s = "catsandog";
        String[] strings = {"cats", "dog", "sand", "and", "cat"};
        Set<String> wordDict = new HashSet<>();
        for (String string : strings) {
            wordDict.add(string);
        }
        boolean[] memory = new boolean[strings.length];
        boolean backtracking = backtracking(s, wordDict, memory, 0);
        System.out.println(backtracking);
    }
    private boolean backtracking(String s, Set<String> wordSet,
                                 boolean[] memory, int startIndex) {
        //都搜到最后一位了，说明前面都符合。return true
        if (startIndex >= s.length()) {
            return true;
        }
        // 如果 memory[startIndex] 不是初始值了，直接使用 memory[startIndex] 的结果
        if (!memory[startIndex]) return memory[startIndex];
        for (int i = startIndex; i < s.length(); i++) {
            String word = s.substring(startIndex, i + 1);
            if (wordSet.contains(word)
                    && backtracking(s, wordSet, memory, i + 1)) {
                return true;
            }
        }
        memory[startIndex] = false; // 记录以 startIndex 开始的子串是不可以被拆分为字典单词的。
        return false;
    }


    //回溯+记忆化搜索，其实什么记忆化就是那个集合，数组记录一下算过的东西
    // 如果算过了，直接到里面取就叫记忆化
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] memory = new boolean[s.length()]; // false 表示初始化状态
        Arrays.fill(memory, true);
        return backtracking(s, wordSet, memory, 0);
    }

    /*public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        return backtracking(s, wordSet, 0);
    }*/
}
