package 校招笔试真题.金山办公._20240929;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/29
 */


/**
 * 小红有a个桃子，b个苹果，c个梨。她现在想将这些水果装到篮子里，每个蓝子需要刚好装15个水果，
 * 才能用下一个篮子，并且每个篮子至少要装4个桃子，3个苹果，2个梨。小红想知道最多使用多少个篮子？
 */
//TODO 为什么这题是二分法?
public class 小红的水果篮 {
    /**
     * 能装两个篮子肯定能装1个篮子，显然是满足二分性的，
     * 二分的边界l=0,r=t。check里面判断能不能组成mid个篮子即可
     * 这个居然是二分法，根本没想到二分。唉。
     * <p>
     * 如果可以装 x 个篮子，那么肯定可以装 x-1 个篮子，但可能无法装 x+1 个篮子。这种单调性适合用二分法。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // 输入测试用例的数量

        for (int i = 0; i < t; i++) {
            long a = sc.nextLong(), b = sc.nextLong(), c = sc.nextLong(); // 输入桃子、苹果、梨的数量
            long l = 0, r = t; // 初始化二分搜索的边界

            // 二分搜索
            while (l <= r) {
                long mid = (l + r) / 2; // 计算中间值
                if (check(mid, a, b, c)) { // 判断能否组成 mid 个篮子
                    l = mid + 1; // 可以组成，则尝试组成mid + 1个
                } else {
                    r = mid - 1; // 不能组成，则尝试组成mid - 1个
                }
            }
            System.out.println(l - 1); // 输出最大能够组成的篮子数量
        }
        sc.close();
    }

    // 检查能否组成 x 个篮子
    static boolean check(long x, long a, long b, long c) {
        long a1 = a - 4 * x, b1 = b - 3 * x, c1 = c - 2 * x; // 计算剩余水果
        if (a1 < 0 || b1 < 0 || c1 < 0) return false; // 检查是否有足够的水果
        long sum = a1 + b1 + c1;
        sum /= 6; // 检查剩余的水果,是否能6等分岛x个完整的篮子
        return sum >= x;//如果可以就返回true。
    }
}
