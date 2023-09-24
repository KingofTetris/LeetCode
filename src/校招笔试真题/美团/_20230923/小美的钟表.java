package 校招笔试真题.美团._20230923;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/23
 */
public class 小美的钟表 {

    //10
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String timeNow = sc.next();
        int n = sc.nextInt();
        String[] strs = new String[n];
        //先用调用一个next吃掉\n
        String fh1 = sc.next();
        for (int i = 0; i < n; i++) {
            strs[i] = sc.nextLine();
        }
        strs[0] = fh1 + strs[0];
        int res = 0;
        for (int i = 0; i < n; i++) {
            //空格分割
            String[] s = strs[i].split(" ");
            if (s[0].equals("-")) {
                res -= Integer.parseInt(s[1]);
            } else if (s[0].equals("+")) {
                res += Integer.parseInt(s[1]);
            }
        }
        //先把现在的时刻化成分钟
        //比如00:00就是0分钟
        String[] split = timeNow.split(":");
        int hourNow = Integer.parseInt(split[0]);
        int minNow = Integer.parseInt(split[1]);
        int fzNow = hourNow * 60 + minNow;
        //然后拿着现在的分钟数加上res
        res = fzNow + res;//拿到结果的分钟
        //结果有3种，<0 =0 >0
        //hour可能会大于24，继续对24取余
        int hour = res / 60 % 24;
        int minute = res % 60;
        //对于 <0的情况还要特殊处理
        if (hour < 0){
            hour = 24 + hour;
        }
        if (minute < 0){
            minute = minute + 60;
            if (hour == 0){
                hour = 23;
            }else {
                hour--;
            }
        }
        String h = String.valueOf(hour);
        String m = String.valueOf(minute);
        if (hour < 10) {
            h = "0" + h;
        }
        if (minute < 10){
            m = "0" + m;
        }
        System.out.println(h + ":" + m);
    }

  /*  {

        //然后让时钟去调整时间
        //先取小时
        int hourRes = res / 60;
        int minRes = res % 60;
//        System.out.println("hourRes = " + hourRes);
//        System.out.println("minRes = " + minRes);
        String[] split = timeNow.split(":");
        int hourNow = Integer.parseInt(split[0]);
        int minNow = Integer.parseInt(split[1]);
        //小时还要化为00-24
        if (hourRes <= 0 || hourRes >= 24) {
            hourRes = hourRes % 24;
        }
        hourNow += hourRes;
        if (hourNow < 0){
            hourNow = 24 + hourNow; //负数相加。
        }
        if (hourNow >= 24){
            hourNow = hourNow % 24;
        }
        minNow += minRes;
        if (minNow < 0) {
            minNow = 60 + minNow;
            if (hourNow == 0){
                hourNow = 23;
            }else {
                hourNow--;
            }
        }
        if (minNow >= 60){
            minNow = minNow % 60;
            if (hourNow == 23){
                hourNow = 0;
            }else {
                hourNow++;
            }
        }

        String h = String.valueOf(hourNow);
        String m = String.valueOf(minNow);
        if (hourNow < 10){
            h = "0" + h;
        }
        if (minNow < 10){
            m = "0" + m;
        }
        System.out.println(h + ":" + m);
    }*/
}
