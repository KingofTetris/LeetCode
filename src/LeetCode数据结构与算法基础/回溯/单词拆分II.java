package LeetCode数据结构与算法基础.回溯;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/10/29
 */


//I是问能不能组成，II是问有多少种组成方案。并给出具体的方案。中间用空格隔开
public class 单词拆分II {


    @Test
    public void test() {
        String s = "catsanddog";
        String[] words = {"cat", "cats", "and", "sand", "dog"};
        List<String> wordDict = new ArrayList<>(Arrays.asList(words));
        List<String> stringList = wordBreak(s, wordDict);
        System.out.println(stringList);
    }

    //那么问具体的方案你就没得选了，只能回溯
    List<String> res = new ArrayList<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordSet = new HashSet<>();
        for (String word : wordDict) {
            wordSet.add(word);
        }
        backTracking(s, wordDict, new LinkedList<>(), 0);
        return res;
    }

    private void backTracking(String s, List<String> wordDict, LinkedList<String> path, int startIndex) {
        //如果len == s.length() 结束
        if (startIndex == s.length()) {
            //把单词用" " 连起来
            res.add(String.join(" ", path));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            String word = s.substring(startIndex, i + 1);
            //如果包含这个单词 sb添加word，从i+1继续往下找
            if (wordDict.contains(word)) {
                path.add(word);
                backTracking(s, wordDict, path, i + 1);
                //删除单词还是用集合比较合适。
                //StringBuilder delete只能根据索引下标来删除 比较麻烦。
                //删掉最后一个单词，不是删掉所有的word 还是得用LinkedList来实现。
                path.removeLast();
            }
        }
    }


}
