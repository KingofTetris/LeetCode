package LeetCode数据结构基础.day2.字符串;

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
            Arrays.sort(chars);
            String key = String.valueOf(chars);
//            String key = chars.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(strs[i]);
        }

        //ArrayList<>(ArrayList<String>)
        //直接用map中的values 生成List 就这样写 List<>(map.values)
        return new ArrayList<>(map.values());
    }


    //暴力法超时？
   /* public List<List<String>> groupAnagrams(String[] strs) {
        int[] flag = new int[strs.length];//给已经加入group的字符串加上标记
        for (int i = 0; i < strs.length; i++) {
            if (flag[i] == 1) continue;
            List<String> group = new LinkedList<>();
            if (flag[i] != 1){
                group.add(strs[i]);
                flag[i] = 1;
            }
            for (int j = i + 1; j <strs.length ; j++) {
                //长度一样 并且还没被加入group中 才去判断
                if (strs[i].length() == strs[j].length() && flag[j] != 1){
                    if (isAnagrams(strs[i],strs[j])){
                        group.add(strs[j]);
                        flag[j] = 1;
                    }
                }
            }
            //空group不要加入
            if (group != null)
            list.add(group);
        }
        return list;
    }

    public boolean isAnagrams(String s1,String s2){
        HashMap<Character,Integer> map1 = new HashMap<>();
        HashMap<Character,Integer> map2 = new HashMap<>();
        //各用一个MAP来存储各有什么字母，各个字母出现多少次
        for (int i = 0; i < s1.length(); i++) {
             map1.put(s1.charAt(i),map1.getOrDefault(s1.charAt(i),0) + 1);
             map2.put(s2.charAt(i),map2.getOrDefault(s2.charAt(i),0) + 1);
        }
        for(Map.Entry<Character,Integer> item:map1.entrySet()){
            //如果map2和map1包含的字母不同，返回false
            if (!map2.containsKey(item.getKey()))
                return false;
            //如果map2 包含了相同的字母 但是数量不同也返回false
            else if (map2.get(item.getKey()) != item.getValue())
                return false;
        }
        //两个条件都满足就是异位词
        return true;
    }*/
}
