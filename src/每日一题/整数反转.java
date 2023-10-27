package 每日一题;

import java.util.LinkedList;

/**
 * 深信服测开算法题。
 */
class 整数反转_07 {
    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
    }

    public static int reverse(int x) {
        if (x == 0) return 0;
        LinkedList<Integer> q = new LinkedList<>();//你想让LinkedList当双端队列，前面就必须是LinkedList，不能是List,List没有双端队列的功能， 就没有offer函数
        int flag = 1;
        if (x < 0) {
            if (x == Integer.MIN_VALUE) return 0; //如果等于Integer最小值，直接返回0
            flag = -1;
            x = x * -1;
        }
        while (x > 0) {
            int yushu = x % 10;
            q.add(yushu);
            x = x / 10;
        }
        //判断是否超过整数范围
        StringBuilder res = new StringBuilder();
        for (Integer temp : q) {
            res.append(temp);
        }
        long resValue = 0;
        if (flag == -1) {
            resValue = -Long.parseLong(res.toString());
        } else {
            resValue = Long.parseLong(res.toString());
        }
        if (resValue > Integer.MAX_VALUE || resValue < Integer.MIN_VALUE) return 0;
        return (int) resValue;
    }

    public static int reverse2(int x) {
        int res = 0;
        while (x != 0) {
            //每次取末尾数字
            int tmp = x % 10;
            //判断是否 大于 最大32位整数 实际上214748364就是 Integer.MAX_VALUE / 10
            //Integer.MAX_VALUE = 2147483647
            //Integer.MIN_VALUE = -2147483648
            //所以如果相等的话，就比较尾数是否大于7或者<-8。
            if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && tmp > 7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            if (res < -Integer.MAX_VALUE / 10 || (res == -Integer.MAX_VALUE / 10 && tmp < -8)) {
                return 0;
            }
            res = res * 10 + tmp;
            x /= 10;
        }
        return res;
    }
}
