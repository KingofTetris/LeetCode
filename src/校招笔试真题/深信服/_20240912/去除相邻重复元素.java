package 校招笔试真题.深信服._20240912;

import java.util.*;

public class 去除相邻重复元素 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        int res = 0;
        char[] arr = s.toCharArray();

        char pre = arr[0];
        for(int i = 1; i< n;i++){
           if(arr[i] == pre){
             res++;
           }
           else{
            pre = arr[i];
           }
        }
        System.out.println(res);
    }
}
