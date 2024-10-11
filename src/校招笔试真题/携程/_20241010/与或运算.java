package 校招笔试真题.携程._20241010;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/10
 */
//这居然超时??
/**
 *
 * 5 2
 * 1 2 3 4 5
 * 1 2 3
 * 2 2 4
 *
 * 输出
 * 2
 * 0
 */
public class 与或运算 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();
        int[] nums = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            nums[i] = sc.nextInt();
        }


        /**
         * 简单地模拟只能过33.33%
         * 其他会超时，说明不是让你去模拟的。
         */
        while (q-- > 0) {
            int op = sc.nextInt();
            int l = sc.nextInt();
            int r = sc.nextInt();

            //op = 1 先 & 后 |
            if (op == 1){
                long res = nums[l]; //和自己相&等于自己
                boolean change = false;
                for (int i = l; i <= r; i++) {
                    if (!change){
                        res = res | nums[i];
                        change = true;
                    }
                    else {
                        res = res & nums[i];
                        change = false;
                    }
                }
                System.out.println(res);
            }
            //op = 1 先 | 后 &
            if (op == 2){
                long res = nums[l]; //和0相或等于自己
                boolean change = false;
                for (int i = l; i <= r; i++) {
                    if (!change){
                        res = res & nums[i];
                        change = true;
                    }
                    else {
                        res = res | nums[i];
                        change = false;
                    }
                }
                System.out.println(res);
            }
        }
    }
}
