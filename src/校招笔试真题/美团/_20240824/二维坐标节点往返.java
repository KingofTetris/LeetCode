package 校招笔试真题.美团._20240824;

import java.util.*;

/**
 * 二维坐标上给你一个起点(a,b)和终点(c,d)，再给你n个瓶子所在坐标(xi,yi)。
 * 你每次只能拿一个瓶子，横纵坐标每走一步代价是1，那么从起点出发把所有瓶子收集齐放到终点需要的最小代价是多少？
 * <p>
 * 除了第一次(a,b)从出发外，后面均是从(c,d)出发再回到(c,d)，
 * 所以需要确定我们去的第一个点是哪个。枚举该点并计算其它点到(c,d)的路径总和即可。
 */
public class 二维坐标节点往返 {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a, b, c, d;
        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();
        d = scanner.nextInt();

        int n = scanner.nextInt();
        int[] X = new int[n];
        int[] Y = new int[n];

        int ans = 0;
        int idx = -1;
        int min_ab = Integer.MAX_VALUE;
        int t_xy_ab = 0;
        for (int i = 0; i < n; i++) {
            X[i] = scanner.nextInt();
            Y[i] = scanner.nextInt();
            int x = X[i];
            int y = Y[i];

            /**
             * 因为只能拿一个瓶子
             * 其实所有瓶子都是要从(C,D)出发，再回去(C,D)的。
             * 除了第一个瓶子从(a,b) 到 (x,y)。
             *
             * 那么其实可以把所有点到(c,d)的距离都算出来*2
             * 然后加上(a,b)到最近(x,y)的距离 减去这个点一次到(c,d)的距离即可
             */
            t_xy_ab = Math.abs(x - a) + Math.abs(y - b);
            //取下距离(a，b)最近的(x,y)
            if (t_xy_ab < min_ab){
                min_ab = t_xy_ab; //取最小的
                idx = i;
            }
        }

        // 计算从 (c, d) 出发到每个点的总距离
        for (int i = 0; i < n; i++) {
            int x = X[i];
            int y = Y[i];
            //不管怎么样，每个点都肯定要回去(C,D)的
            ans += 2 * (Math.abs(x - c) + Math.abs(y - d));
        }
        int t_xy_cd =  Math.abs(X[idx] - c) + Math.abs(Y[idx] - d);
        //其实就是min_ab 为了看得清楚，再算一次
        t_xy_ab =  Math.abs(X[idx] - a) + Math.abs(Y[idx] - b);
        //ans少了xy到ab这一段，多了xy_cd一次。给他补回去
        ans = ans + t_xy_ab - t_xy_cd;

        System.out.println(ans);
        scanner.close();
    }
}
