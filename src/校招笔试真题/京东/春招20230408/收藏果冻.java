package 校招笔试真题.京东.春招20230408;

import java.util.HashSet;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/4/26
 */
public class 收藏果冻 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(sc.nextInt());
        }

        System.out.println(n - set.size());
    }
}
