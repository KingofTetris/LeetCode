package 校招笔试真题.虾皮._20240401;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/23
 */
public class 移动链表 {

    //其实直接移动就行了，还反转什么啊。
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
        }
        int idx = n - k % n;
        for (int i = idx; i < n; i++) {
            System.out.print(num[i] + " ");
        }
        for (int i = 0; i < idx; i++) {
            System.out.print(num[i] + " ");
        }
    }
}
