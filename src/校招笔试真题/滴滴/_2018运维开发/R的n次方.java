package 校招笔试真题.滴滴._2018运维开发;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/8
 */
public class R的n次方 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String r;
        int n;
        String s;
        while (sc.hasNext()){
            r = sc.next();// 0.0<R<99.999
            //用string来存储，因为double和float都是不能准确的表示小数的，只是以概数来表示
            n =sc.nextInt();
            //计算浮点数就需要用BigDecimal
            BigDecimal base = new BigDecimal(r);
            BigDecimal ans = new BigDecimal(r);
            for (int i = 1; i < n; i++) {
                ans = ans.multiply(base);
            }
            s = ans.stripTrailingZeros().toPlainString();//去除不必要的零，转换为字符串，防止科学记数法
            System.out.println(s);
        }
    }

}
