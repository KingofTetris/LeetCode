package LeetCode数据结构入门.day2.字符串;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author KingofTetris
 * @File 赎金信
 * @Time 2021/10/2  14:47
 */


/*给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，
判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。
如果可以构成，返回 true ；否则返回 false。

        (题目说明：为了不暴露赎金信字迹，
        要从杂志上搜索各个需要的字母，组成单词来表达意思。
        杂志字符串中的每个字符只能在赎金信字符串中使用一次。)

        示例 1：

        输入：ransomNote = "a", magazine = "b"
        输出：false
        示例 2：

        输入：ransomNote = "aa", magazine = "ab"
        输出：false //因为magazine只有一个a,ransom有2个a
        示例 3：

        输入：ransomNote = "aa", magazine = "aab"
        输出：true
         

        提示：

        你可以假设两个字符串均只含有小写字母。*/

public class 赎金信 {
    @Test
    public void test() {
        String ransom = "aaeaadGM--";
        String magazine = "aaeeacsaGdM--a";
        boolean flag = canConstruct2(ransom, magazine);
        System.out.println(flag);
    }
    //其实就是在比较s1是不是s2的一个子序列而已呗。
    //那就记录一个Map.s2+,s1- 如果出现负数，那么就不行。
    public boolean canConstruct2(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length() ) return false;
        HashMap<Character,Integer> map = new HashMap<>();
        int n = 0;
        for (char c : magazine.toCharArray()) {
            map.put(c,map.getOrDefault(c,0) + 1);
            if (n < ransomNote.length()){
                char temp = ransomNote.charAt(n);
                map.put(temp,map.getOrDefault(temp,0) - 1);//ransom - 1
                n++;
            }
        }
        //最后判断map是否包含负数的value就行了
        for (Integer value : map.values()) {
            if (value < 0) return false;
        }
        return true;
    }

    //用两个map来映射，最后对比一下 这种就什么都能比
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> map_ransom = new HashMap<>();
        HashMap<Character, Integer> map_magazine = new HashMap<>();
        char[] toCharArray1 = ransomNote.toCharArray();
        char[] toCharArray2 = magazine.toCharArray();

        for (int i = 0; i < ransomNote.length(); i++) {
            map_ransom.put(toCharArray1[i], map_ransom.getOrDefault(toCharArray1[i], 0) + 1);
        }
        for (int i = 0; i < magazine.length(); i++) {
            map_magazine.put(toCharArray2[i], map_magazine.getOrDefault(toCharArray2[i], 0) + 1);
        }


        return compareMap(map_ransom, map_magazine);
        //现在要做的就是比较两个map里面相同的key的value值
        //只要map_magazine >= map_ransom就行


        /*for(Map.Entry<Character,Integer> map:map_ransom.entrySet()){
            System.out.println(map.getKey() + ":" + map.getValue());
        }

        System.out.println(); System.out.println();
        for(Map.Entry<Character,Integer> map:map_magazine.entrySet()){
            System.out.println(map.getKey() + ":" + map.getValue());
        }*/

    }

    public boolean compareMap(HashMap<Character, Integer> m1, HashMap<Character, Integer> m2) {
        //比较
        for (Map.Entry<Character, Integer> entry : m1.entrySet()) {
            int value1 = entry.getValue();
            //要注意m2里面没用m1的元素的话 会报空指针异常要处理一下
            int value2 = m2.get(entry.getKey()) == null ? 0 : m2.get(entry.getKey());
            //只要有一个小于就返回false
            if (value2 < value1)
                return false;
        }
        //全都大就返回true
        return true;
    }
}
