package 校招笔试真题.携程._20230907;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/7
 */

/*题目：给一个0和1组成的字符串，求子串中有多少“好串”。

        对“好串”的定义是：所有的前缀子串中，0的数量全部严格大于1的数量

        计算好串的个数*/
public class 游游的好串数量 {
    /**
     * 暴力只能5%.
     * 找规律
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        System.out.println(solution1(s));
        System.out.println(countGoodStrings(s));
    }
    public static long solution1(String s){
        int cnt0 = 0;
        long sum = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '0') {
                cnt0++;
                sum += cnt0;
            } else {
                cnt0--;
                if (cnt0 > 0) {
                    sum += cnt0;
                } else {
                    cnt0 = 0;
                }
            }
        }
        return sum;
    }

 /*   作者：织梦呀
    链接：https://www.nowcoder.com/discuss/529398383690137600?sourceSSR=post
    来源：牛客网*/


    //AI
    public static long countGoodStrings(String s) {
        long count = 0;
        int numOfZeros = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') {
                numOfZeros++;
            } else {
                if (numOfZeros >= 1) {
                    count++;
                }
                numOfZeros = 0;
            }
        }
        return count;
    }
}
