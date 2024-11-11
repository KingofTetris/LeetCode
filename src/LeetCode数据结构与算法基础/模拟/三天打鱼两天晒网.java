package LeetCode数据结构与算法基础.模拟;

import java.util.Scanner;


/**
 * 一、问题描述
 * 中国有句俗话叫“三天打鱼，两天晒网”，某人从2010年1月1日开始“三天打鱼，两天晒网”，
 * 问这个人在以后的某天是“打鱼”还是“晒网”
 * 二、问题分析
 * 1、开始日期已经确定，需要用键盘输入往后的一个日期
 * 2、输入的年份是否是闰年，输入的年份是否大于2010年，输入月份2月是否是闰年2月
 * 3、需要求出输入的日期与2010,01,01相差的总天数，用总天数来判断是打鱼还是晒网
 * 三、算法设计
 * 1、判断年份是否大于2010，是否是闰年，利用循环来返回相差的年份所差的天数
 * ————————————————
 版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
 *
 * 原文链接：https://blog.csdn.net/weixin_50878338/article/details/115049397
 */
public class 三天打鱼两天晒网 {
    public static void main(String[] args) {
        goFishing();
    }

    public static int days() {
        int totalYear = 0;
        int totalMonth = 0;
        int totalDays = 0;
        Scanner scanner = new Scanner(System.in);
        int year;
        do {
            System.out.println("请输入年份");
            year = scanner.nextInt();
            if (year < 2010) {
                System.out.println("请输入大于2010的年份");
            }
        } while (year < 2010);

        for (int i = 2010; i < year; i++) {
            if ((i % 4 == 0 && i % 100 != 0) || (i % 400 == 0)) {
                totalYear += 366;
            } else {
                totalYear += 365;
            }
        }
//        System.out.println(totalYear);

        System.out.println("请输入月份");
        int month = scanner.nextInt();
        for (int i = 1; i < month; i++) {
            if (i == 1 || i == 3 || i == 5 || i == 7 || i == 8 || i == 10 || i == 12) {
                totalMonth += 31;
            } else if (i == 2) {
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    totalMonth += 29;
                } else {
                    totalMonth += 28;
                }
            } else {
                totalMonth += 30;
            }
        }
//        System.out.println(totalMonth);

        System.out.println("请输入天数");
        int day = scanner.nextInt();
        totalDays = day;
//        System.out.println(totalDays);
        int sumDays = totalYear + totalMonth + totalDays;
        System.out.println(sumDays);

        scanner.close();
        return sumDays;
    }

    public static void goFishing() {
        int sumDays = days();
        System.out.println("相隔的总天数 " + sumDays);

        if (sumDays % 5 == 1 || sumDays % 5 == 2 || sumDays % 5 == 3) {
            System.out.println("他在打鱼");
        } else {
            System.out.println("他在晒网");
        }
    }
}
