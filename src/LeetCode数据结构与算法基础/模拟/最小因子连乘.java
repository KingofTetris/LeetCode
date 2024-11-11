package LeetCode数据结构与算法基础.模拟;

import java.util.Scanner;

public class 最小因子连乘 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            System.out.println(factorize(n));
        }
        scanner.close();
    }

    public static String factorize(int n) {
        StringBuilder result = new StringBuilder();
        int divisor = 2;//除数
        while (n > 1) {
            if (n % divisor == 0) {
                result.append(divisor);
                n /= divisor;
                if (n > 1) {
                    result.append("*");
                }
            } else {
                divisor++;
            }
        }
        return result.toString();
    }
}
