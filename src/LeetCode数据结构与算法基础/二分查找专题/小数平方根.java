package LeetCode数据结构与算法基础.二分查找专题;

//拼多多二面算法
public class 小数平方根 {
    public static double sqrt(double n, double e) {
        if (n < 0) {
            return Double.NaN; // 如果 n 是负数，返回 NaN
        }
        double low = 0;
        double high = n;

        //差别就是这么一句n < 1设置为high = 1就可以。唉。
        if (n < 1) {
            high = 1; // 如果 n 在 0 和 1 之间，将 high 设置为 1
        }

        double mid = 0;
        while (high - low > e) {
            mid = low + (high - low) / 2;
            double square = mid * mid;
            if (square == n) {
                return mid; // 找到精确的平方根
            } else if (square < n) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return mid;
    }

    public static void main(String[] args) {
        double n = 15;
        double e = 0.00001;
        double result = sqrt(n, e);
        System.out.println(n + "的平方根是" + result);
    }
}
