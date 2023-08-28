package 校招笔试真题.中国移动;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/5/22
 */
public class P1201_杨辉三角 {


    public static void s1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n  = sc.nextInt();
        //其实有个结论，二项式中奇数的个数
        //等于2^k  其中k代表的是(n-1)的二进制中1的个数Integer.bitCount(n - 1)
        long res = (long) Math.pow(2, Integer.bitCount(n - 1));
    }

    //普通去模拟，就算你用DP省略掉一个rows，实际上复杂度也还是O(n2)，只是省了点空间
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n  = sc.nextInt();
        lastRowEvenNumber(n);
    }

    public static void lastRowEvenNumber(int n){
        List<List<Integer>> rows = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> row = new ArrayList<>();//初始化每行

            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i){
                    row.add(1);
                }
                else {
                    row.add(rows.get(i - 1).get(j-1) + rows.get(i - 1).get(j));
                }
            }
            rows.add(row);
        }
        long res = 0;
        List<Integer> lastRow = rows.get(rows.size() - 1);
        for (Integer integer : lastRow) {
            if ((integer & 1) == 1) res++;
        }
        System.out.println(res);
    }
}
