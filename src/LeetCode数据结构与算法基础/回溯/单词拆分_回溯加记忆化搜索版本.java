package LeetCode数据结构与算法基础.回溯;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author by KingOfTetris
 * @date 2024/8/21
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
