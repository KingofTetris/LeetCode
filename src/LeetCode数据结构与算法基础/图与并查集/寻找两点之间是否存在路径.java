package LeetCode数据结构与算法基础.图与并查集;

import java.util.*;

public class 寻找两点之间是否存在路径 {


    public static void main(String[] args) {
        int N, M;
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        M = scanner.nextInt();
        DisJoint disJoint = new DisJoint(N + 1);
        for (int i = 0; i < M; ++i) {
            disJoint.join(scanner.nextInt(), scanner.nextInt());
        }

        if (disJoint.isSame(scanner.nextInt(), scanner.nextInt())) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

}

//并查集模板
class DisJoint {
    private int[] father;

    public DisJoint(int N) {
        father = new int[N];
        for (int i = 0; i < N; ++i) {
            father[i] = i;
        }
    }

    public int find(int n) {
        return n == father[n] ? n : (father[n] = find(father[n]));
    }

    public void join(int n, int m) {
        n = find(n);
        m = find(m);
        if (n == m) return;
        father[m] = n;
    }

    public boolean isSame(int n, int m) {
        n = find(n);
        m = find(m);
        return n == m;
    }

}
