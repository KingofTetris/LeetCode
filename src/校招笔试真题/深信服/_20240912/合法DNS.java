package 校招笔试真题.深信服._20240912;

import java.util.*;

public class 合法DNS {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        //如果长度大于255
        if(s.length() > 255){
            System.out.println(false);
            return;
        }

        //split里面放的是正则表达式，如果是. + ? 这种特殊含义的字符作为分割符，要先转义。
        String[] ss = s.split("\\.");
        //如果只有一个标签
        if(ss.length <= 1){
            System.out.println(false);
            return;
        }
        //如果有标签长度大于63
      for (int i = 0; i < ss.length; i++) {
         if(ss[i].length() > 63){
             System.out.println(false);
            return;
         }
         //如果开头结尾是连接符也不行。
         if(ss[i].startsWith("-") || ss[i].endsWith("-")){
             System.out.println(false);
            return;
         }
         //如果ss[i]出现其他除了指定符号以外的字符也不行
         int m = ss[i].length();
         char[] arr2 = ss[i].toCharArray();
        for (int j = 0; j < m; j++) {
            //字母，数字，连接符才行，其他的都非法。
            if(Character.isDigit(arr2[j]) || Character.isLetter(arr2[j])
            || arr2[j] == '-'){
                continue;
            }
            else{
                System.out.println(false);
               return;
            }
        }
      }
      //都通过才是合法DNS域名
      System.out.println(true);
    }
}
