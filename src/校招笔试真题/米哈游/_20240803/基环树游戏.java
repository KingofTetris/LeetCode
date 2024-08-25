package 校招笔试真题.米哈游._20240803;

import java.util.*;


/**
 * 给你一个基环树(n个节点n条边的图) 现在xiaoyo和Pyrmont在玩这个游戏
 * xiaoyo先删除一个度为1的节点和边，然后Pyrmont再删除一个度为1的节点和边
 *
 * 直到有人删除掉基环树中特殊的X节点，取得获胜。
 * 请你返回获胜的玩家
 *
 * 如果没有赢家，返回Draw
 *
 * 比较基础的博弈论
 *
 * 如果目标节点x在环中，结果必然是平局(Draw)。
 * 如果x不在环中:
 * 计算在删除x之前(包括x)可删除的节点数cnt。
 * cnt为偶数，Xiaoyo无法删除该点，Pyrmont获胜。
 * cnt为奇数，Xiaoyo获胜。
 */
public class 基环树游戏 {
    static class Skt {
        static final int N = 100005;
        static List<Integer>[] adj = new ArrayList[N];
        static boolean[] vis = new boolean[N];
        static int[] past = new int[N];
        static boolean[] isCircle = new boolean[N];
        static int[] d = new int[N];

        public static void work() {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            int x = scanner.nextInt();

            for (int i = 1; i <= n; i++) {
                adj[i] = new ArrayList<>();
                vis[i] = false;
                past[i] = -1;
                isCircle[i] = false;
                d[i] = 0;
            }

            for (int i = 1; i <= n; i++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                adj[a].add(b);
                adj[b].add(a);
                d[a]++;
                d[b]++;
            }

            Queue<Integer> q = new LinkedList<>();
            q.add(1);
            vis[1] = true;
            past[1] = 0;
            boolean flag = false;
            List<Integer> t1 = new ArrayList<>();
            List<Integer> t2 = new ArrayList();

            while (!q.isEmpty() && !flag) {
                int u = q.poll();
                for (int v : adj[u]) {
                    if (v == past[u]) continue;
                    if (vis[v]) {
                        int s1 = u, s2 = v;
                        while (s1 != 0) {
                            t1.add(s1);
                            s1 = past[s1];
                        }
                        while (s2 != 0) {
                            t2.add(s2);
                            s2 = past[s2];
                        }
                        flag = true;
                        break;
                    } else {
                        vis[v] = true;
                        past[v] = u;
                        q.add(v);
                    }
                }
            }

            Set<Integer> mp = new HashSet<>();
            int ex = -1;
            for (int i : t1) {
                mp.add(i);
            }
            for (int i : t2) {
                if (mp.contains(i)) {
                    ex = i;
                    break;
                }
            }
            for (int i : t1) {
                isCircle[i] = true;
                if (i == ex) {
                    break;
                }
            }
            for (int i : t2) {
                isCircle[i] = true;
                if (i == ex) {
                    break;
                }
            }

            //如果x在环里面，一定是平局
            if (isCircle[x]) {
                System.out.println("Draw");
                return;
            }

            while (!q.isEmpty()) {
                q.poll();
            }

            for (int i = 1; i <= n; i++) {
                vis[i] = false;
                if (d[i] == 1) {
                    vis[i] = true;
                    q.add(i);
                }
            }

            int ans = 0;
            while (!q.isEmpty()) {
                int u = q.poll();
                ans++;
                for (int v : adj[u]) {
                    if (!vis[v] && !isCircle[v] && v != x) {
                        d[v]--;
                        if (d[v] == 1) {
                            vis[v] = true;
                            q.add(v);
                        }
                    }
                }
            }

            if (ans % 2 == 0 || d[x] == 1) {
                System.out.println("Xiaoyo");
            } else {
                System.out.println("Pyrmont");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        while (T-- > 0) {
            Skt.work();
        }
    }
}
