package LeetCode数据结构与算法基础.排序;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

/**
 * 给定一组单词words，编写一个程序，找出其中的最长单词，且该单词由这组单词中的其他单词组合而成。
 * 若有多个长度相同的结果，返回其中字典序最小的一项，若没有符合要求的单词则返回空字符串。
 *
 * 示例：
 *
 * 输入： ["cat","banana","dog","nana","walk","walker","dogwalker"]
 * 输出： "dogwalker"
 * 解释： "dogwalker"可由"dog"和"walker"组成。
 * 提示：
 *
 * 0 <= len(words) <= 200
 * 1 <= len(words[i]) <= 100
 *
 * 作者：阿飞
 * 链接：https://leetcode.cn/problems/longest-word-lcci/solutions/811199/a-fei-suan-fa-mian-shi-ti-1715-zui-chang-c3vr/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 */
public class 最长单词 {

    /**
     * 思路很简单，先把字符串数组按字符串长度进行排序，如果长度相同按字符串的字典序进行倒序
     *
     * 于是我们直接从字符串数组尾部开始寻找结果，对每个字符串直接使用暴力法，例如dogwalker
     * 从第一个字符匹配到最后一个字符，每次往后移一个位置，都去判断当前字符之前的所有字符能否匹配一个字符串
     * 流程如下
     * d ogwalker,d 字典中无匹配，后移
     * do gwalker,do 字典中无匹配，后移
     * dog walker,dog 字典有匹配,递归判断walker
     * ......
     *
     * 如果你理解上面的思路，其实你已经可以不用看代码了，自己试试看
     *
     * 作者：思周
     * 链接：https://leetcode.cn/problems/longest-word-lcci/solutions/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
        //把words数组存入set中当字典用
        HashSet<String> set = new HashSet<>(Arrays.asList(words));
        //把字符串数组按长度进行排序，如果长度相同按字典序倒序
        Arrays.sort(words, (s1, s2) -> {
            if(s1.length() == s2.length()){
                return s2.compareTo(s1);
            }
            return s1.length()-s2.length();
        });
        //从尾部开始遍历
        for(int i = words.length-1; i >= 0; i--){
            //防止整个字符串与自身匹配返回true，且后续不会用到直接删除
            set.remove(words[i]);
            if(check(words[i], set)){
                return words[i];
            }
        }
        return "";
    }
    public boolean check(String s, HashSet<String> set){
        //s的长度为0说明整个字符串被匹配完了
        if(s.length() == 0){
            return true;
        }
        for(int j = 1; j <= s.length();j++){
            //从0~j-1位置的字符组成的字符串是否在字典中
            if(set.contains(s.substring(0,j))){
                //对子串进行递归
                if(check(s.substring(j), set)){
                    return true;
                }
            }
        }
        return false;
    }

 /*   作者：思周
    链接：https://leetcode.cn/problems/longest-word-lcci/solutions/901541/setzi-dian-shu-zu-pai-xu-su-du-93bao-mu-hewl7/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
