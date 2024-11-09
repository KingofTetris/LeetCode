package LeetCode数据结构与算法基础.模拟;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class _7进制数 {

    public String convertToBase7(int n) {
        boolean flag = n < 0;
        if (flag) n = -n;
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(n % 7);
            n /= 7;
        } while (n != 0);
        sb.reverse();
        return flag ? "-" + sb : sb.toString();
    }
}
