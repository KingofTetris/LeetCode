package LeetCode数据结构基础.day2.字符串;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author KingofTetris
 * @File 单词规律
 * @Time 2021/10/16  10:30
 */

/*给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。

        这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中
        的每个非空单词之间存在着双向连接的对应规律。

        示例1:

        输入: pattern = "abba", str = "dog cat cat dog"
        输出: true
        示例 2:

        输入:pattern = "abba", str = "dog cat cat fish"
        输出: false
        示例 3:

        输入: pattern = "aaaa", str = "dog cat cat dog"
        输出: false
        示例 4:

        输入: pattern = "abba", str = "dog dog dog dog"
        输出: false
        说明:
        你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。 */

public class 单词规律 {
    @Test
    public void test(){
        String pattern = "ccccccccccccccccccccc" +
                "cccccccccccccccccccccccccccccccc" +
                "ccccccccccccccccccc" +
                "cccccccccccccccccccccccccccccccccc" +
                "ccccccccccccccccccccccdd";
        String s = "s s s s s s s s s s s s s s s s s s s s s s s s s " +
                "s s s s s s s s s s s s s s s s s s s s s s s s s " +
                "s s s s s s s s s s s s s s s s s s s s s s s s s " +
                "s s s s s s s s s s s s s s s s s s s s s " +
                "s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s s t t";
        System.out.println(wordPattern(pattern, s));
    }

    //实际上是一个双射关系，一般用两个map完成
    //这里巧妙地用一个节省空间
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");

        System.out.println(pattern.length());
        System.out.println(words.length);
        if (pattern.length() != words.length){
            return false;
        }
        //设为Object就什么都能放了，Integer记录出现的位置是否一致

        //这题还有一个小细节 涉及到自动装箱
        //自动装箱使用的是Integer.valueOf(int i)方法
        // int值在[-128,127]范围内是使用缓存中的对象，超过这个范围就创建新的Integer对象。
        //这个testcase长130 超过127 使用了新的对象
        //就导致  if(map.put(pattern.charAt(i),i) != map.put(words[i],i) ) 其实是两个不同的对象
        //会判断为不相等，直接return false 所以要注意for里面的i要设为Integer类型
        HashMap<Object,Integer> map = new HashMap<>();
        for (Integer i = 0; i < words.length; i++) {
            //在java中,Map里的put方法,如果key值不存在,则返回值是null,但是key值如果存在,
            // 则会返回原先的value值.
            // (当然,map中的key和value都允许是null). 详细例子看最下面的注释
            if(map.put(pattern.charAt(i),i) != map.put(words[i],i) ){
                return false;
            }
        }
        return true;
    }
}
/*
                如果key不存在，插入成功，返回null；如果key存在，返回之前对应的value。

                以pattern = "abba", str = "dog cat cat dog"为例，
                第1次：map.put('a',0)返回null，map.put("dog",0)返回null，两者相等；
                第2次：map.put('b',1)返回null，map.put("cat",1)返回null，两者相等；
                第3次：map.put('b',2)返回1，map.put("cat",2)返回1，两者相等；
                第4次：map.put('a',3)返回0，map.put("dog",3)返回0，两者相等，
                结果为 true。

                以pattern = "abba", str = "dog cat cat fish"为例，
                第1次：map.put('a',0)返回null，map.put("dog",0)返回null，两者相等；
                第2次：map.put('b',1)返回null，map.put("cat",1)返回null，两者相等；
                第3次：map.put('b',2)返回1，map.put("cat",2)返回1，两者相等；
                第4次：map.put('a',3)返回0，map.put("fish",3)返回null，两者不相等，
                结果为 false。
            */
