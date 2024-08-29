package LeetCode数据结构与算法基础.day2.字符串;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/8/28
 */
public class 替换数字 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String replaceNumber = replaceNumber(s);
        System.out.println(replaceNumber);
    }

    public static String replaceNumber(String s) {

        StringBuilder sb = new StringBuilder();
        char[] charArray = s.toCharArray();
        for (char c : charArray) {
            /**
             * Character.isAlphabetic()‌ 判断的范围更广，
             * 包括了一些特殊字符和其他具有字母属性的字符，而‌Character.isLetter()‌ 的判断范围相对狭窄，
             * 排除了带有字母属性的数字和其他一些特殊字符。
             * 做题基本上isLetter就行了。
             */
//            Character.isAlphabetic(c);
//            Character.isLetter(c);
            if (Character.isDigit(c)){
                sb.append("number");
            }
            else {
                sb.append(c);
            }
        }
        return sb.toString();

        /**
         * 你疯了？？你在写什么，一个替换数字这么简单的操作？？你在干嘛??
         */
       /* char[] charArray = s.toCharArray();
        StringBuilder resStr = new StringBuilder();
        int pre = 0;//记录上一个字母出现的位置
        int end = 0;//记录最后一个数字出现的位置
        for (int i = 0; i < charArray.length; i++) {
            //判断哪些字符是数字
            if (Character.isDigit(charArray[i])) {
                //然后每次截取[0,flags[i]],flags[i],flags[i+1]..之间的非数字部分加入到resStr中
                    String subString = s.substring(pre, i);
                    resStr.append(subString).append("number");
                    pre = i + 1;//i不能取
                    end = i;
            }
        }
        if (end != 0 && end < charArray.length - 1){
            String subString = s.substring(end + 1);//添加上最后一个数字之后的子串
            resStr.append(subString);
        }
        //如果没有数字 直接返回s
        if (resStr.toString().equals("")) return s;
        return resStr.toString();*/
    }
}
