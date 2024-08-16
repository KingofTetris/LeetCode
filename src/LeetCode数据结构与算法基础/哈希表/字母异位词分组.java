package LeetCode数据结构与算法基础.哈希表;

import org.junit.Test;

import java.util.*;

/**
 * @author KingofTetris
 * @File 字母异位词分组
 * @Time 2021/10/17  11:29
 */
/*给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
         组成字母相同，但是顺序不同
        字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母都恰好只用一次。

        简单来说就是字母相同的放一组，无所谓字母顺序，
        示例 1:
        输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
        输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
        示例 2:
        输入: strs = [""]
        输出: [[""]]
        示例 3:
        输入: strs = ["a"]
        输出: [["a"]]
         

        提示：

        1 <= strs.length <= 104
        0 <= strs[i].length <= 100
        strs[i] 仅包含小写字母*/

public class 字母异位词分组 {
    List<List<String>> list = new LinkedList<>();
    @Test
    public void test(){
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat","fish","perish","cherish","science","erishch","ihfs"};
//          String[] strs = {};
//        String[] strs = {"a"};
//        System.out.println(isAnagrams(strs[0], strs[5]));
        list = groupAnagrams(strs);
        for(List<String> group:list){
            for (int i = 0; i < group.size(); i++) {
                System.out.print(group.get(i) + "\t");
            }
            System.out.println();
        }
    }


    //排序 由于互为字母异位词的两个字符串包含的字母相同，
    // 因此对两个字符串分别进行排序之后得到的字符串一定是相同的，
    // 故可以将排序之后的字符串作为哈希表的键。

    //说一下法一中踩得坑： 在内容相同情况下，String会hash得到相同的key，
    // 由于char[]特殊机制，相同内容的在hash后值不会相同。
    // 因此Map中必须使用String作为key
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length ; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars); //给字母排序
            String key = String.valueOf(chars);//然后作为key

            //不包含这个key就put,否则直接get，然后add进去即可
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(strs[i]);
        }

        //ArrayList<>(ArrayList<String>)
        //直接用map中的values 生成List 就这样写 List<>(map.values)
        /**
         * 最后我们只需要map中的value
         * 可以直接List<>(放个集合进来)
         */

        return new ArrayList<>(map.values());
    }
}
