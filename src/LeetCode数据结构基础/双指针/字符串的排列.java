package LeetCode数据结构基础.双指针;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author KingofTetris
 * @File 字符串的排列
 * @Time 2021/9/23  0:06
 */

/*567. 字符串的排列
        给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。

        换句话说，s1 的排列之一是 s2 的 子串 。
        示例 1：

        排列之一:abc 的排列就是A33 abc acb bac bca cab cba 六种
        包含六种其中之一就可以，实际上找出只要原字符串的长度也为n的子串sub
        判断sub是否和s有一样的字符个数即可 eg:a b c各1个就可以


        输入：s1 = "ab" s2 = "eidbaooo"
        输出：true
        解释：s2 包含 s1 的排列之一 ("ba").
        示例 2：

        输入：s1= "ab" s2 = "eidboaoo"
        输出：false

        提示：

        1 <= s1.length, s2.length <= 104
        s1 和 s2 仅包含小写字母*/


//    使用两个数组  cnt1 和 cnt2记录 s1和s2中各个字符的个数
    //用一个固定长度为n的滑动窗口来滑动字符串s，右移一格就把出去的字符--，进来的字符++
    //然后判断 cnt1 和cnt2 是否相等
    //相等自然 s包含s1的排列

public class 字符串的排列 {
    //S1是排列串，S2是长字符串

    @Test
    public void test(){
        String s1 = "oooa";
        String s2 = "eidboaoo";
        System.out.println(checkInclusion(s1, s2));

    }


    //比较两数组是否相等的API Arrays.equals(a,b)
//    Arrays.equals(a,b)
//    Arrays.equals(a,b)
//    Arrays.equals(a,b)
    public boolean checkInclusion(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();
        if(n > m){
            //大于s1当然不可能是s1的排列
            return false;
        }
        //因为26个字符，当然用26位数组，从用字符-'a'就是在字母表中的排位0-25
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];


        //初始化cnt1 cnt2
        //cnt1就取s1的前n位记录 注意s2是比较长的 s1是排列子串
        //cnt2也去取n位
        for (int i = 0; i < n; i++) {
            cnt1[s1.charAt(i) - 'a']++;
            cnt2[s2.charAt(i) - 'a']++;
        }
        //先比一次，运气好，找到就直接返回true
        if(Arrays.equals(cnt1,cnt2))
            return true;


        //窗口来辣 在s2上滑动 从n开始向又移动
        for (int i = n; i < m; i++) {
            cnt2[s2.charAt(i) - 'a']++;//进窗口字符++
            cnt2[s2.charAt(i - n) - 'a']--;//出窗口的字符--
            if(Arrays.equals(cnt1,cnt2))
                return true;
        }

        //都没找到就无了
        return  false;
    }

//    优化  注意到每次只进出一个字符，但却比较了两个完全的数组
    //
}
