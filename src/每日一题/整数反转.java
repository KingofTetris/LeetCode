package 每日一题;

import java.util.LinkedList;

/**
 * 深信服测开算法题。
 */
class 整数反转_07 {
    public static void main(String[] args) {
        System.out.println(reverse(214748648));
    }

    public static int reverse(int x) {
        if (x == 0) return 0;
        //你想让LinkedList当双端队列，前面就必须是LinkedList，不能是List,List没有双端队列的功能， 就没有offer函数
        LinkedList<Integer> q = new LinkedList<>();
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
        //把list里面的数字先拼接成字符串
        StringBuilder res = new StringBuilder();
        for (Integer temp : q) {
            res.append(temp);
        }

        //字符串转Long Long.parseLong
        long resValue = 0;
        if (flag == -1) {
            resValue = -Long.parseLong(res.toString());
        } else {
            resValue = Long.parseLong(res.toString());
        }
        if (resValue > Integer.MAX_VALUE || resValue < Integer.MIN_VALUE) return 0;
        return (int) resValue;
    }
}
