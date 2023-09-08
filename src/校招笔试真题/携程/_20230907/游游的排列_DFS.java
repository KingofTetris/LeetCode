package 校招笔试真题.携程._20230907;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/8
 */
public class 游游的排列_DFS {
    /**
     * 先找出所有的素数对，然后dfs
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        //还有这种操作我槽。为什么不写成L<L<>>..
        LinkedList<Integer>[] next = new LinkedList[n];
        for (int i = 1; i <= n; ++i) {
            next[i - 1] = new LinkedList<>();
            for (int j = 1; j <= n; ++j) {
                if (i != j && checkPrime(i + j)) {
                    next[i - 1].add(j - 1);
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += dfs(n - 1, i, next, new boolean[n]);
        }
        System.out.println(sum);
    }

    static int dfs(int cnt, int cur, LinkedList<Integer>[] next, boolean[] vis) {
        if (cnt == 0) {
            return 1;
        }
        int sum = 0;
        vis[cur] = true;
        for (int num : next[cur]) {
            if (!vis[num]) {
                sum += dfs(cnt - 1, num, next, vis);
            }
        }
        vis[cur] = false;
        return sum;
    }

    static boolean checkPrime(int n) {
        for (int i = 2; i * i <= n; ++i) {
            if (n % i == 0) return true;
        }
        return false;
    }

/*    作者：织梦呀
    链接：https://www.nowcoder.com/discuss/529398383690137600?sourceSSR=post
    来源：牛客网*/
}
