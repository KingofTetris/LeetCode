package LeetCode数据结构与算法基础.手撕算法;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/10/25
 */
public class 找出字符串中第一个匹配项的下标 {


    @Test
    public void test(){
        String haystack = "mississippi";
        String needle = "issipi";
        int i = strStr(haystack, needle);
        System.out.println(i);
    }

    //直接调API indexOf 当然面试是不可能的。
    //最简单的就是两重for循环暴力。
    public int strStr(String haystack, String needle) {
        int n1 = haystack.length();
        int n2 = needle.length();
        if (n2 > n1) return -1;
        char[] haystackArray = haystack.toCharArray();
        char[] needleArray = needle.toCharArray();
        //遍历
        for (int i = 0; i < n1; i++) {
            //遇到needle首字符相同，进入下一层循环
            if (haystackArray[i] == needleArray[0]) {
                int j = 0;
                int temp = i;//temp记录第一个匹配的下标
                //字符相同，两个字符串都进到下一位
                while (temp < n1 && haystackArray[temp] == needleArray[j]) {
                    temp++;
                    j++;
                    //匹配字符的数量与needle的数量相同，返回第一个匹配项的下标，并且终止循环
                    if (j == n2) {
                        return i;
                    }
                }
            }
        }
        //没找到返回-1
        return -1;
    }
}
