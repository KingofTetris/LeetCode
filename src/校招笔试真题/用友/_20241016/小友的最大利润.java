package 校招笔试真题.用友._20241016;

import java.util.Scanner;

public class 小友的最大利润 {
    //50%
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //n件商品
        int[] startTime = new int[n]; //每件商品的预计销售开始时间
        int[] sellTime = new int[n]; //每件商品预计销售时长
        int[] profits = new int[n];  //每件商品销售对应时长以后可以获得的利润
        for (int i = 0; i < n; i++) {
            startTime[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            sellTime[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            profits[i] = sc.nextInt();
        }
        //同一时间只能销售一样商品，如果超过了商品的预计销售开始时间则不允许销售该商品
        //但如果是在K时刻下完成商品的销售工作，则可以立即在K时刻开始下一份商品的销售。
        //按照以上规则，请你计算本次销售计划可以获得的最大利润是多少?

        /**
         * 例如下面的用例，输入5件商品，以及对应的商品开始销售时间，预计销售时长，预计获得利润。
         * 5
         * 1 2 3 4 6
         * 2 3 7 2 3
         * 20 20 100 70 60
         * 这个用例应该是选择商品1，4，6。
         * 从时刻1开始销售商品1，销售商品1 2个时刻 至时刻3，获得利润20
         * 时刻4，销售商品4 2个时刻 至时刻6，获得利润70
         * 时刻6，销售商品5 3个时刻 至时刻9，获得利润60
         * 则利润最大值为20+70+60 = 150
         *
         */
        int maxProfit = getMaxProfit(n, startTime, sellTime, profits);
        System.out.println(maxProfit);
    }

    public static int getMaxProfit(int n, int[] startTime, int[] sellTime, int[] profits) {
        int[] dp = new int[10001]; // dp[i]表示在第i个时刻可以获得的最大利润
        for (int i = 0; i < n; i++) {
            for (int j = 10000; j >= startTime[i] + sellTime[i]; j--) {
                dp[j] = Math.max(dp[j], dp[startTime[i]] + profits[i]);
            }
        }
        int maxProfit = 0;
        for (int i = 0; i <= 10000; i++) {
            maxProfit = Math.max(maxProfit, dp[i]);
        }
        return maxProfit;
    }
}
