package LeetCode数据结构与算法基础.二分查找专题;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/10/15
 */
public class 求根号N {

    public double calSqrtN(int N){
        double low = 0, high = N;
        double eps = 1e-10; // 设置精度要求
        while (high - low > eps) {
            double mid = (low + high) / 2;
            if (mid * mid > N) {
                high = mid;
            } else {
                low = mid;
            }
        }
        return Double.parseDouble(String.format("%.20f",(high + low) / 2));
    }

    @Test
    public void test(){
        System.out.println(calSqrtN(12));
    }
}
