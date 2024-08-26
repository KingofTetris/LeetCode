package 校招笔试真题.米哈游._20240803;

import java.util.*;

class Stock {
    long weight, value;

    Stock(long weight, long value) {
        this.weight = weight;
        this.value = value;
    }
}

public class _01背包互斥物品 {

    //判断当前物品能不能放进背包
    private static boolean isCompatible(int stockIndex, boolean[] selected, boolean[][] mutexes) {
        for (int i = 0; i < mutexes.length; i++) {
            if (selected[i] && mutexes[stockIndex][i]) {
                return false;
            }
        }
        return true;
    }

    private static void dfs(int index, long curValue, long curWeight, boolean[] selected,
                            int n, int m, Stock[] stocks, boolean[][] mutexes, long[] maxValue) {
        if (index == n) {
            maxValue[0] = Math.max(maxValue[0], curValue);
            return;
        }

        // 不选择当前股票
        dfs(index + 1, curValue, curWeight, selected, n, m, stocks, mutexes, maxValue);

        // 选择当前股票
        if (isCompatible(index, selected, mutexes) && curWeight + stocks[index].weight <= m) {
            selected[index] = true;
            dfs(index + 1, curValue + stocks[index].value, curWeight + stocks[index].weight,
                    selected, n, m, stocks, mutexes, maxValue);
            selected[index] = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        Stock[] stocks = new Stock[n];
        for (int i = 0; i < n; i++) {
            long weight = sc.nextLong();
            long value = sc.nextLong();
            stocks[i] = new Stock(weight, value);
        }

        boolean[][] mutexes = new boolean[n][n];
        for (int i = 0; i < k; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            mutexes[a][b] = true;
            mutexes[b][a] = true;
        }

        sc.close();

        // Solve
        boolean[] selected = new boolean[n];
        long[] maxValue = new long[1];  // 使用数组来模拟引用传递
        dfs(0, 0, 0, selected, n, m, stocks, mutexes, maxValue);

        System.out.println(maxValue[0]);
    }
}
