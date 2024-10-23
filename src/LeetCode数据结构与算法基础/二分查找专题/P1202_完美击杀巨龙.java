package LeetCode数据结构与算法基础.二分查找专题;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/5/22
 *
 *
 * 塔子哥是一个有着无限冒险精神的年轻勇士，他在冒险中遇到了一只凶猛的巨龙，这只巨龙是这个领域中的最终Boss，
 * 只有击败了它，他才能获得传说中的宝藏。
 *
 * 巨龙有 a 点血量，而塔子哥的每次攻击可以使其减少 b 点血量。
 *
 * 然而，塔子哥希望在最后一击时完美击杀巨龙，这意味着他必须用最后一击将巨龙的血量降至零，而不能超过。
 *
 * 为了达到这个目标，塔子哥可以花费代价增加或减少自己每次攻击造成的伤害。
 * 然而，这种代价只能在攻击巨龙之前花费，一旦塔子哥开始攻击巨龙，他就无法再进行任何修改。
 * 现在，塔子哥想知道，为了保证最后一击完美击杀巨龙，他需要最少花费多少代价来修改自己的攻击伤害。
 *
 * 请注意，塔子哥只有战前修改自己的伤害需要花费代价，攻击巨龙并不需要花费代价。
 *
 * 输入为两个整数 a 和 b。 (1 ≤ a, b ≤ 10^12)
 *
 * 输出一个整数，代表塔子哥花费的最小代价。
 *
 * 样例:
 * 输入
 * 11 1
 * 输出
 * 0
 * 输入
 * 11 3
 * 输出
 * 2
 */


//要完美击杀巨龙，就必须让b是a的因数。
public class P1202_完美击杀巨龙 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long a = in.nextLong();
        long b = in.nextLong();

        if (a % b == 0) {
            System.out.println(0);
        } else { //如果a % b != 0 就要去找离a因子最近的距离
            List<Long> list = getAll(a);
            //二分
            //为什么他要用两个开区间，这也太少见了。
            int l = -1;
            int r = list.size();
            //去找那个最靠近b的因子
            while (l + 1 < r) {
                int mid = (l + r) >> 1;
                //大于等于b,调整上界，问题是为什么等于的时候不停下来，还要继续？等于不就最靠近了吗
                if (list.get(mid) >= b) {
                    r = mid;
                }
                //小于b，调整下届
                else {
                    l = mid;
                }
            }
            //找到离b最近的那个因子以后
            if (l == -1) { //如果l==-1
                System.out.println(list.get(r) - b);
            } else if (r == list.size()) {
                System.out.println(b - list.get(l));
            }
            //如果l不是-1，r也不是list.size 那么说明b离开list.get(r)或者list.get(l)最近
            else {
                System.out.println(Math.min(list.get(r) - b, b - list.get(l)));
            }
        }
    }


    //求n的所有因数,同时保证这个因数列表是升序的。
    private static List<Long> getAll(long n) {
        List<Long> list1 = new ArrayList<>();
        List<Long> list2 = new ArrayList<>();
        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                //list1存放i list2存放n / i,list2把n/i 放在第一位。
                //这样list1.addAll(list2)的时候就不用再对整个list排序了。非常巧妙。
                list1.add(i);
                list2.add(0, n / i);
            }
        }
        list1.addAll(list2);
        return list1;
    }

}
