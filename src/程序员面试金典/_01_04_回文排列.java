package 程序员面试金典;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author by KingOfTetris
 * @date 2022/9/22
 *
 * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
 *
 * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
 *
 * 回文串不一定是字典当中的单词。
 *
 * 示例1：
 *
 * 输入："tactcoa"
 * 输出：true（排列有"tacocat"、"atcocta"，等等）
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/palindrome-permutation-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _01_04_回文排列 {


    /**
     * 其实就是判断这个字符串里面的字符能不能组成回文串
     * @param s
     * @return
     */
    public boolean canPermutePalindrome(String s) {
        //条件就是 所有出现的字符都刚好出现偶数次，或者其他字符都是偶数次，仅有一个字符出现奇数次
        HashMap<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i),map.getOrDefault(s.charAt(i),0) + 1);
        }
        int count = 0;
        for (Map.Entry<Character, Integer> characterIntegerEntry : map.entrySet()) {
            if ((characterIntegerEntry.getValue() & 1) == 1) {
                count++;
                if (count > 1) return false;
            }

            if ((characterIntegerEntry.getValue() & 1) != 1){
                continue;//偶数次跳过就行了
            }

        }

        return true;//经过上面的判断，就是回文串的排列
    }

    /**
     * 同样的思路，不一样的写法。没啥太大区别
     * 甚至还多用了一点空间。
     */
    public boolean canPermutePalindrome2(String s) {
        if(s == null){
            return false;
        }
        char[] chars = s.toCharArray();
        Set<Character> set = new HashSet<>();
        for(char c : chars){
            if(set.contains(c)){
                set.remove(c);
            }else{
                set.add(c);
            }
        }
        return set.size() <= 1;
    }

    /**
     * 没想到吧，这也能位运算
     * 这样就是双百的做法
     *
     * 因为题目没有说明字符限制，所有 ASCII 马里面的128匹都可以是
     * 所以用位运算就要有128位来代表各个字符的出现次数
     * 但是Java最多的long也就64位，这怎么办呢？
     * 那我们就拿2个long 来记录呗，一个前一个后
     * 如果大于等于 64 那么就放后面 end
     * 如果小于 64 就放前面 front
     *
     * @param s
     * @return
     */
    public boolean canPermutePalindrome3(String s) {
        long front = 0, end = 0;//初始都是64个0
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 64) { //奇数次异或为1 偶数次异或为0
                end ^= 1L << s.charAt(i) - 64; //每次把 1 左移 i - 64 个位置赋给end
            } else {
                front ^= 1L << s.charAt(i);  //每次把 1 左移 i 个位置赋给front
            }
        }

        return Long.bitCount(front) + Long.bitCount(end) <= 1;
        //最后只要记录二进制里面1的个数，也就是字符出现奇数次的个数是否 <= 1就可以了。
    }

  /*  作者：dc3a2nLETu
    链接：https://leetcode.cn/problems/palindrome-permutation-lcci/solution/wei-yun-suan-ji-hu-shuang-100-by-dc3a2nl-x0q6/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
