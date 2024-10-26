package LeetCode数据结构与算法基础.哈希表;

/**
 * @author by KingOfTetris
 * @date 2023/9/11
 */


import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * <p>
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 * <p>
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 * <p>
 * 示例：
 * <p>
 * 输入：19
 * 输出：true
 * 解释：
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 */
public class 快乐数 {


    @Test
    public void test() {

    }

    public static boolean isHappy(int num) {
        int lastRes = num; //记录上一次的num
        //怎么避免无限循环？
        //无限循环，一定是sum重复出现吗？
        //用map来记录是否重复出现。
        //你怎么证明快乐数陷入无限循环，一定是因为出现周期性无解的变化而不是无限地增大下去呢？？
        //因为num的MAX = Integer.MAX_VALUE
        //这个数字第一次计算快乐值仅仅是260。是不可能无限增大下去的。
        HashMap<Integer, Integer> map = new HashMap<>();
        while (true) {
            int curRes = 0;
            while (lastRes > 0) {
                int n = lastRes % 10; //每次取个位
                lastRes = lastRes / 10;
                curRes += n * n;
            }
            if (curRes == 1) {
                return true;
            }
            lastRes = curRes;
            System.out.println(lastRes);
            map.put(lastRes, map.getOrDefault(lastRes, 0) + 1);
            //如果有值重复出现了，那么就会无限循环。
            //为什么会慢，就是因为这里的containValue，这里要一个一个遍历。！
            if (map.containsValue(2)) {
                return false;
            }
        }
    }

    //2优化一下写法，代码看起来清爽点。
    // 计算一个n的各个位数平方和

    // 判断快乐数
    public boolean isHappy2(int n) {
        Set<Integer> record = new HashSet<>();
        while (n != 1 && !record.contains(n)) {
            record.add(n);
            n = getNextNum(n);
        }
        return n == 1;
    }

    private int getNextNum(int n) {
        int res = 0;
        while (n > 0) {
            int temp = n % 10;
            res += temp * temp;
            n = n / 10;
        }
        return res;
    }


}
