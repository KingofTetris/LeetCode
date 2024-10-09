package 校招笔试真题.招银云创._20241009;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/9
 */
public class 程序员的战斗力 {
    static int MOD = (int) (1e9 + 7);
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] quality = new int[n];
        int[] efficiency = new int[n];
        for (int i = 0; i < n; i++) {
            quality[i] = scanner.nextInt();
        }
        for (int i = 0; i < n; i++) {
            efficiency[i] = scanner.nextInt();
        }
        int result = getMaxEffectiveness(n, k, quality, efficiency);
        System.out.println(result);
    }

    private static int getMaxEffectiveness(int n, int k, int[] quality, int[] efficiency) {
        //请补充完整
        Programmer[] programmers = new Programmer[n];
        for (int i = 0; i < n; i++) {
            programmers[i] = new Programmer(quality[i],efficiency[i]);
        }
        //按照程序员的效率从大到小排序
        Arrays.sort(programmers,(a,b)-> b.efficiecy - a.efficiecy);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int sumSpeed = 0;
        int maxPerformance = 0;

        //遍历程序员数组
        for (Programmer programmer : programmers) {
            //把质量放入最小堆，则堆顶为最小值
            minHeap.offer(programmer.quality);
            //质量之和
            sumSpeed += programmer.quality;
            //如果堆中元素数量 > k
            //
            if (minHeap.size() > k && !minHeap.isEmpty()){
                sumSpeed -= minHeap.poll();
            }

            int performance = (sumSpeed * programmer.efficiecy) % MOD; //本轮战斗力
            maxPerformance = Math.max(performance,maxPerformance);
        }

        return maxPerformance % MOD;
    }
}

class Programmer{
    int quality;
    int efficiecy;

    public Programmer(int speed, int efficiecy) {
        this.quality = speed;
        this.efficiecy = efficiecy;
    }
}
