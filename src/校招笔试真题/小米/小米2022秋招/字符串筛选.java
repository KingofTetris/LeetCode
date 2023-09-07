package 校招笔试真题.小米.小米2022秋招;

import java.util.HashSet;

/**
 * @author by KingOfTetris
 * @date 2023/9/2
 */

//给定一个长度为
//n字符串, 需要去除所有之前曾经出现过的字符，只保留第一次出现的字符
public class 字符串筛选 {

    public String unique_string (String s) {
        // write code here
        HashSet<Character> charList = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(charList.add(s.charAt(i))){
                stringBuilder.append(s.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

}
