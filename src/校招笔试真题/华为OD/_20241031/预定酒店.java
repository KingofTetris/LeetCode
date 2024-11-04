package 校招笔试真题.华为OD._20241031;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;

public class 预定酒店 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        int x = sc.nextInt();

        int[][] A = new int[n][2];

        for (int i = 0; i < n; i++) {
            A[i][0] = sc.nextInt(); // 酒店价格
            A[i][1] = Math.abs(A[i][0] - x); // 酒店价格和 x 的差距，差距越小，则越接近x
        }

        // 优先选择价格最接近心理价位的酒店，若两家酒店距离心理价位差价相同，则选择价格较低的酒店
        Arrays.sort(A, (a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);

        // 选择前k个
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = A[i][0];
        }

        // 由低到高打印酒店的价格
        Arrays.sort(res);

        StringJoiner sj = new StringJoiner(" ");
        for (int re : res) {
            sj.add(re + "");
        }

        System.out.println(sj);
    }
}
