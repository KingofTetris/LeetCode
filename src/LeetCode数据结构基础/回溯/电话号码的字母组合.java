package LeetCode数据结构基础.回溯;

import org.junit.Test;

import java.util.HashMap;
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
    public List<String> letterCombinations(String digits){
        if (digits == null || digits.length() == 0)
            return res;
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
        backTracking(digits, numString, 0);
        return res;
    }

    //每次迭代都会产生一个字符串，大量字符串的拼接会浪费很多内存
    //因此我们选择高效的StringBuilder
    StringBuilder temp = new StringBuilder();
    public void backTracking(String digits,String[] numString,int num){
        //结果的要求是digits长度
        if (num == digits.length()){
            res.add(temp.toString());
            return;
        }
        //数字对应的字符串
        String str = numString[digits.charAt(num) - '0'];
        //模板
        //for是同层遍历，递归其实就是纵向遍历,DFS
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            backTracking(digits,numString,num + 1);
            //从list的remove(list.size() - 1);
            //改成了StringBuilder的deleteCharAt(sb.length() - 1);
            //如果是栈那就是stack.pop()
            temp.deleteCharAt(temp.length() - 1);
        }
    }

}
