package LeetCode数据结构与算法基础.数学;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


/**
 * 1-9这九个数字，写成三个三位数，不能有重复数字，
 * 第二个数是第一个数的二倍，第三个数是第一个数的三倍，例如327，654，981，输出这三个数。有多少种方案？
 */
public class 多倍数字 {
    private static ArrayList<ArrayList<Integer>> res = new ArrayList<>();

    public static void main(String[] args) {
        findTriplets();
        for (ArrayList<Integer> triplet : res) {
            System.out.println(triplet);
        }
        System.out.println("总共有 " + res.size() + " 种方案。");
    }

    private static void findTriplets() {
        //因为不能重复那么，3N最大就是987 那么对应的N最大就只能是329
        //那么N最小可以是多少?那当然就是123了。
        //因此下面的范围才是从123到329
        for (int a = 123; a <= 329; a++) {
            int b = a * 2;
            int c = a * 3;
            //然后我们去判断combine有没有重复元素
            String combined = String.valueOf(a) + String.valueOf(b) + String.valueOf(c);
            if (isUnique(combined) && !combined.contains("0")) {
                //如果符合要求就可以添加到结果了。
                ArrayList<Integer> triplet = new ArrayList<>();
                triplet.add(a);
                triplet.add(b);
                triplet.add(c);
                res.add(triplet);
            }
        }
    }

    private static boolean isUnique(String str) {
        //str.chars()是JDK9的写法。
//        return str.chars().distinct().count() == str.length();
        //不使用8以上的特性，手动完成。是否有重复元素
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (set.contains(c)) {
                return false; // 如果字符重复，返回 false
            }
            set.add(c);
        }
        return true; // 字符串中的字符都是唯一的
    }
}
