package 剑指offer第二版.字符串;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author KingofTetris
 * @Date 2022/8/30 11:16
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组.
 * 示例:
 *
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 * 限制：
 * 1 <= s 的长度 <= 8
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer38_字符串的排列 {



    @Test
    public void test(){
        String s = "abc";
        List<String> allpy = getAllpy(s);
        System.out.println(allpy);
    }
    /**
     * 字符串的全排列，如果不存在重复字符，则n个字符有n!种排列方式
     * 如果存在重复字符，则要排除掉重复排列
     * @param s
     * @return
     */
    //不就是一个简单的回溯吗。有什么复杂的
    List<String> res = new LinkedList<>();
    boolean[] used;
    public List<String> getAllpy(String s){
        char[] charArray = s.toCharArray();
        used = new boolean[s.length()];
        StringBuffer sb = new StringBuffer();
        //排列是不需要startIndex的。只需要used数组标记一下树层去重就行了。
        backTracking(charArray,sb);
        return res;
    }

    private void backTracking(char[] arr,StringBuffer sb) {
        //找到一个排列
        if (sb.length() == arr.length){
            res.add(sb.toString());
        }

        for (int i = 0; i < arr.length; i++) {
            if (used[i] == true){
                continue;
            }
            used[i] = true;
            sb.append(arr[i]);
            backTracking(arr,sb);
            sb.deleteCharAt(sb.length() - 1);
            used[i] = false;
        }
    }
}
