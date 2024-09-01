package LeetCode数据结构与算法基础.回溯;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author KingofTetris
 * @Date 2022/10/4 17:03
 */
public class 电话号码的字母组合 {
    @Test
    public void test(){
        String digits = "2233";
        List<String> strings = letterCombinations(digits);
        System.out.println(strings);
    }
    /**
     * 无脑的回溯写法。
     * 回溯居然TMD模拟还快。。
     */
    List<String> res = new LinkedList<>();
    //初始map,下标是key，String是value
    String[] numString = {
            //0,1都是空字符串
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"};
    public List<String> letterCombinations(String digits){
        if (digits == null || digits.length() == 0)
            return res;
        backTracking(digits, 0);
        return res;
    }

    //每次迭代都会产生一个字符串，大量字符串的拼接会浪费很多内存
    //因此我们选择高效的StringBuilder
    StringBuilder temp = new StringBuilder();
    public void backTracking(String digits,int startIndex){
        //如果startIndex已经到了digits末尾。就可以返回一个结果了。
        if (startIndex == digits.length()){
            res.add(temp.toString());
            return;
        }
        //数字对应的字符串
        String str = numString[digits.charAt(startIndex) - '0'];
        //遍历字符串
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            //每次startIndex + 1保证不重复选择
            backTracking(digits,startIndex + 1);
            //StringBuilder删除下标为i的字符
            temp.deleteCharAt(temp.length() - 1);
        }
    }

}
