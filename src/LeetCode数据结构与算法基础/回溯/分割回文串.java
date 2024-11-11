package LeetCode数据结构与算法基础.回溯;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/9/9
 */
public class 分割回文串 {

    //分割字符串ss使得所有子串都是回文串，有多少种方案。
    public static void main(String[] args) {
        分割回文串 ss = new 分割回文串();
        String s = "aabasdsd";
        List<List<String>> partition = ss.partition(s);
        for (List<String> stringList : partition) {
            System.out.println(stringList);
        }
    }

    List<List<String>> res = new ArrayList<>();
    List<String> path = new LinkedList<>();

    public List<List<String>> partition(String s) {
        backTracking(s, 0);
        return res;
    }

    private void backTracking(String s, int startIndex) {
        //停止条件
        //如果起始位置大于s的大小，说明找到了一组分割方案
        if (startIndex >= s.length()) {
            //每次把当前满足条件的path放到结果中，不能直接add(path)，不然只有最后一个。因为path指向的永远是
            //同一个对象，path每次会回溯，这个地址指向的全是空列表，只能new保留他的中间状态。
            res.add(new LinkedList<>(path));
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            //如果是回文子串，则记录
            if (isPalindrome(s, startIndex, i)) {
                //取下[start,i]
                String str = s.substring(startIndex, i + 1);
                path.add(str);
            } else {
                //如果不是回文子串，就直接剪枝，跳过这条分支。
                //直接i++
                continue;
            }
            //起始位置后移，保证不重复
            backTracking(s, i + 1);
            //条件重置
            path.remove(path.size() - 1);
        }
    }
    //判断是否是回文串
    private boolean isPalindrome(String s, int startIndex, int end) {
        for (int i = startIndex, j = end; i < j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

}
