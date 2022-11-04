package 每日一题;

import java.util.Scanner;

/**
 * @Author KingofTetris
 * @Date 2022/7/19 16:01
 *
 * "差分"是"前缀和"的逆运算，一般与"前缀和"一起使用，用来处理多次区间修改，对于每一次区间修改，复杂度为常数级O(1)。
 * 对"差分数组"求"前缀和数组"，可以还原"原数组"，若后续还要对区间和求解，可再对"原数组"求"前缀和数组"。
 */
public class 差分数组测试 {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
        long start = System.currentTimeMillis();
        int[] chafen = chafen(10);
        for (int i = 0; i < chafen.length; i++) {
            System.out.println(chafen[i]);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时" + (end - start) / 1000 + "s");
    }

    /**
     *
     * @param n 数组的长度
     * @return
     */
    public static int[] chafen(int n){
        int num[] = new int[n];
        int diff[] = new int[n];//差分数组

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            num[i] = (int) Math.floor(Math.random() * 101);//100里面随机取数
        }
       /* for (int i = 0; i < num.length; i++) {
            System.out.print(num[i]+" ");
        }
        System.out.println();*/
        /**
         * 差分数组
         */
        for (int i = 1; i < n; i++) {
            diff[i] = num[i] - num[i - 1];//后一个数减去前一个数
        }

        int left = sc.nextInt();
        int right = sc.nextInt(); // [a,b]操作的区间
        int c = sc.nextInt();//操作数为c

        /**
         * 对差分数组区间的头一个数+opration,对区间后面一个数-operation
         */
        diff[left] += c;
        diff[right + 1] -= c;

        for (int i = 1; i < n; i++) {
            num[i] = num[i - 1] + diff[i];//num[i]等于前一个数加上diff[i]
        }
        sc.close();
        return num;
    }
}
