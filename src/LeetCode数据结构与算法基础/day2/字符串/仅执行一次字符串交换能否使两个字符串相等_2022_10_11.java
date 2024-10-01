package LeetCode数据结构与算法基础.day2.字符串;

import java.util.LinkedList;

/**
 * @Author KingofTetris
 * @Date 2022/10/11 9:38
 */
public class 仅执行一次字符串交换能否使两个字符串相等_2022_10_11    {

    //判断
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1.equals(s2)) return true;//一定非空 长度相等
        int len = s1.length();
        LinkedList<Character> characters = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i)){
                characters.add(s1.charAt(i));
                characters.add(s2.charAt(i));
            }
        }
        //如果不同字母长度不是4 那么一定不是两个字母不同，一次交换是办不到的。
        if (characters.size() != 4) return false;
        //最后比较两个需要交换的字符 是否相同
        return characters.get(0) == characters.get(3)
                && characters.get(1) == characters.get(2);
    }
}
