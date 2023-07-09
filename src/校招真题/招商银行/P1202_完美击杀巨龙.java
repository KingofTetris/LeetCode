package 校招真题.招商银行;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/5/22
 */
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
            if (l == -1) { //如果l==-1
                System.out.println(list.get(r) - b);
            } else if (r == list.size()) {
                System.out.println(b - list.get(l));
            } else {
                System.out.println(Math.min(list.get(r) - b, b - list.get(l)));
            }
        }
    }

    private static List<Long> getAll(long n) {
        List<Long> list1 = new ArrayList<>();
        List<Long> list2 = new ArrayList<>();
        for (long i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                list1.add(i);
                //这样放也是为了二分
                //使用二分的前提就是有序
                list2.add(0, n / i);
            }
        }
        list1.addAll(list2);
        return list1;
    }

}
