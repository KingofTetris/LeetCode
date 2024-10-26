package LeetCode数据结构与算法基础.哈希表;

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



//还真不算是道简单题。
public class 单词规律 {
    @Test
    public void test(){
        String pattern = "aabb";
        String s = "d d e e";
        boolean b = wordPattern(pattern, s);
        System.out.println(b);
    }

    //不懂可以看这篇题解，从最简单的版本，到最终的版本都给你写出来了。
    //https://leetcode.cn/problems/word-pattern/solutions/192714/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--53/

    //我个人比较喜欢两个翻译的解法
    //例如A说的是中文，B说的是法文，我要怎么才能知道A和B说的是不是同一个意思呢?
    //当然可以直接中文->法文，也可以法文->中文比较
    //但还可以中文->英文<-法文。通过一个中介来实现
    public boolean wordPattern(String pattern, String s) {
        String[] arr = s.split(" ");
        if (pattern.length() != arr.length) return false;
        //直接用""空字符串分割就能得到，各个字符组成的string数组
        return wordPatternHelper(pattern.split("")).equals(wordPatternHelper(arr));
    }
    //不管你是aabb 还是 快快乐乐
    //最后都会被翻译成0011
    private String wordPatternHelper(String[] arr) {
        HashMap<String,Integer> map = new HashMap<>();
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            //已经翻译过了
            if (map.containsKey(arr[i])){
                sb.append(map.get(arr[i]));
            }
            else {
                //arr[i] -> count
                sb.append(count);
                map.put(arr[i],count);
                count++;
            }
        }
        return sb.toString();
    }
    //上面的方法是我个人比较喜欢的，但是效率并不高，需要重新构造两个字符串进行比较

    //现在对他进行改进。
    //为了方便，我们也可以将当前单词（字母）直接映射为当前单词（字母）的下标，省去 count 变量。
    //也就变成21年那会你不明白的这段代码。

    /*public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
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
            *//**
             * 21年刚入学的时候，我居然连HashMap都不懂吗?太夸张了。
             *//*
            //在java中,Map里的put方法,如果key值不存在,则返回值是null,但是key值如果存在,
            // 则会返回原先的value值.
            // (当然,map中的key和value都允许是null). 详细例子看最下面的注释

            //实时更新结构对应的下标。
            if(map.put(pattern.charAt(i),i) != map.put(words[i],i) ){
                return false;
            }
        }
        return true;
    }*/
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
