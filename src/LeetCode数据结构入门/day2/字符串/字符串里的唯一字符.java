package LeetCode数据结构入门.day2.字符串;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author KingofTetris
 * @File 字符串里的唯一字符
 * @Time 2021/10/1  21:57
 */

/*387. 字符串中的第一个唯一字符
        给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。



        示例：

        s = "leetcode"
        返回 0

        s = "loveleetcode"
        返回 2


        提示：你可以假定该字符串只包含小写字母。*/
public class 字符串里的唯一字符 {

    @Test
    public void test(){
        String s = "I Love Leet Code!i love lol!";
        System.out.println(firstUniqChar(s));
    }


    public int firstUniqChar(String s) {

        //HashMap不保证插入顺序一定是从前往后，但是循环遍历时，输出顺序是不会改变的。
        //Hashtable也是，所以最后输出不能拿第一个不是-1的直接输出还得比一下谁最小 但其实要实现很麻烦
        //用LinkedHashmap 的特点是put进去的对象位置未发生变化,而HashMap会发生变化.
        HashMap<Character,Integer> dict = new LinkedHashMap<>();
        char[] sArray = s.toCharArray();

        //出现一次就是置为下标i
        //出现多次就置为-1
        for (int i = 0; i < sArray.length; i++) {
            if (dict.containsKey(sArray[i])){
                dict.put(sArray[i],-1);
            }else {
                dict.put(sArray[i],i);
            }
        }

        //遍历dict的方法Map.Entry
        for(Map.Entry<Character,Integer> map:dict.entrySet()){
//            System.out.println(map.getKey() + ":" + map.getValue());
            if(map.getValue() != -1)
                return map.getValue();
        }
        return -1;

    }


}
