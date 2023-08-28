package 校招笔试真题.字节跳动.字节跳动2023秋招0820;

import java.util.PriorityQueue;
import java.util.Scanner;


//大佬个G8.。他也只A了 3/15.。自己写的1/15。没多大差距。
public class 塔子哥的五年击_大佬题解 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (x, y) ->
                        x[2] == y[2] ? x[0] - y[0] : x[2] - y[2]
        );
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{scan.nextInt(), scan.nextInt(), scan.next().charAt(0)});
        }
        long ans = 0;
        while (pq.size() >= 5) {
            int[][] arr = new int[5][];
            arr[0] = pq.poll();
            int min = arr[0][1];
            boolean flag = true;
            for (int i = 1; i < 5 && flag; i++) {
                if (pq.peek()[2] != arr[0][2] || pq.peek()[0] != arr[i - 1][0] + 1) flag = false;
                else {
                    arr[i] = pq.poll();
                    min = Math.min(min, arr[i][1]);
                }
            }
            if (flag) {
                int idx = 0;
                for (int i = 0; i < 5; i++) {
                    arr[i][1] -= min;
                    if (arr[i][1] == 0) idx = i;
                }
                ans += min;
                for (int i = idx + 1; i < 5; i++) pq.offer(arr[i]);
            }
        }
        System.out.println(ans);
    }
}

/*作者：高启盛◎
链接：https://www.nowcoder.com/discuss/522934570560258048?sourceSSR=post
来源：牛客网*/
