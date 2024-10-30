package LeetCode数据结构与算法基础.常用工具;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/10/30
 */
public class IsPrime {


    @Test
    public void test(){
        for (int i = 1; i < 100; i++) {
            if (isPrime(i))
                System.out.println(i + "是质数");
            else
                System.out.println(i + "不是质数");
        }
    }
    /**
     * 判断一个数是否是素数
     * i < Math.sqrt(number) 这个写法是错的！ 这样写4都是素数
     * 注意一定是 <= Math.sqrt(number)
     * @param number
     * @return
     */
    static public boolean isPrime(int number) {
        //质数必须是大于1的自然数。这意味着1本身就不在质数的考虑范围内。
        //1既不是质数，也不是合数。
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
