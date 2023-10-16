package 校招笔试真题.同程.秋招20231012;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/10/12
 */
public class 电话号码组合 {

    @Test
    public void test(){
        String s = "2";
        ArrayList<String> strings = letterCombinations(s);
        System.out.println(strings);
    }
    /**
     * 无脑的回溯写法。
     * 回溯居然TMD比上面的模拟还快。。
     */
    ArrayList<String> res = new ArrayList<>();
    public ArrayList<String> letterCombinations (String digits) {
        // write code here
        if (digits == null || digits.length() == 0)
            return res;
        //初始map
        //key是下标，value就是对应的字符串
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
        //终止条件，num == length
        if (num == digits.length()){
            res.add(temp.toString());
            return;
        }
        //数字对应的字符串
        String str = numString[digits.charAt(num) - '0'];
        //模板
        //i从0开始
        for (int i = 0; i < str.length(); i++) {
            temp.append(str.charAt(i));
            //每次num + 1,不重复。
            backTracking(digits,numString,num + 1);
            //回溯
            //从list的remove(list.size() - 1);
            //改成了StringBuilder的deleteCharAt(sb.length() - 1);
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
