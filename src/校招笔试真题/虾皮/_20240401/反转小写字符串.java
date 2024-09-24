package 校招笔试真题.虾皮._20240401;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/23
 */
public class 反转小写字符串 {

    /**
     * 大写不反转，小写反转
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        //先去掉两边的""
        String sub = s.substring(1,s.length() - 1);
        String[] ss = sub.split(" ");
        String[] res = new String[ss.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = changeStr(ss[i]);
        }
        String join = String.join(" ", res);
        //最后加上两个双引号
        System.out.println("\"" + join + "\"");
    }

    public static String changeStr(String s){
        int i = 0,j = s.length() - 1;
        int n = j;
        char[] arr = s.toCharArray();
        while (i < j){
            while (i < n && (Character.isUpperCase(arr[i]) || Character.isDigit(arr[i])) ){
                i++;
            }
            while (j >= 0 && (Character.isUpperCase(arr[j]) || Character.isDigit(arr[j]))){
                j--;
            }
            if (j < i ) break;//如果j都小于i了，就break
            //如果两个都是小写，交换位置
            char temp = ' ';
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            //交换完了，索引改变。
            i++;
            j--;
        }
        return new String(arr);
    }
}
