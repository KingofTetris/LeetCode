package LeetCode数据结构与算法基础.回溯;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/10/23
 */
public class 复原IP地址 {

    @Test
    public void test() {
        String s = "25525511135";
        List<String> stringList = restoreIpAddresses(s);
        System.out.println(stringList);
    }


    /**
     * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，
     * 这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
     * s 仅由数字组成
     * 每个整数位于 0 到 255 之间组成，且不能含有前导 0
     *
     * @param s
     * @return
     */
    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s.length() > 12) return res;//大于12 那么就不可能有合法的ip地址
        //用StringBuilder来完成字符串的拼接，节省空间。
        //因为我们知道直接用+号拼接字符串，相当于要多创建2个对象
        //+号 底层其实就是用StringBuilder拼接的，然后再用String.toString方法返回就多了两个对象浪费空间和效率
        StringBuilder sb = new StringBuilder(s);
        //这题的难道应该是 回溯函数的参数应该怎么设置，pointNum，这个分割点如果已经有3个了，就说明分成四段了。
        //就要去判断是否合法
        backTracking(sb, 0, 0);
        return res;
    }

    private void backTracking(StringBuilder s, int startIndex, int pointNum) {
        //如果已经有3个. 就是可以结束了
        //但是添加前我们还需要判断第四段子串是否合法
        if (pointNum == 3) {
            if (isValid(s, startIndex, s.length() - 1)) {
                //直接添加s，因为我们会改造s，给s加上.号
                res.add(s.toString());
                return;
            }
        }

        //回溯
        //startIndex就是分割线
        for (int i = startIndex; i < s.length(); i++) {
            //判断.号前面的字符串是否有效
            if(isValid(s, startIndex, i)){
                //如果有效，在后面插入一个.
                s.insert(i + 1, '.');
                backTracking(s, i + 2, pointNum + 1);
                //回溯
                s.deleteCharAt(i + 1);
            }
            //如果非法，那么这条分支就没必要走了。
            else{
                break;
            }
        }
    }


    //左闭右闭
    private boolean isValid(StringBuilder s, int start, int end) {
        //判断字符串是否是0-255，不包含前导零
        if (start > end) return false;
        if (s.charAt(start) == '0' && start != end) return false;//前导零
        //最后判断是否大于255，如果大于255，直接返回false即可
        int num = 0;
        //注意这里可能有溢出问题，如果你要用包装类，要用Long
        for (int i = start; i <= end; i++) {
            int digit = s.charAt(i) - '0';
            num = num * 10 + digit;
            if (num > 255){
                return false;
            }
        }
        return true;
    }


}
