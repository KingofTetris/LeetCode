package 每日一题;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2023/7/12
 */
public class 交替数字和_20230712 {

    public int alternateDigitSum(int n) {
        //最高有效位，但是题目限制了正整数，就相当于最高位为+，其他为-
        char[] numChars = String.valueOf(n).toCharArray();
        int flag = 1;
        int sum = 0;
        for (char numChar : numChars) {
            //字符型int强转 和使用包装类 Integer.valueOf是一样的效果
            //字符型数字转int型 直接用 char - '0' 就行了
            int num = (numChar - '0') * flag;
            sum += num;
            flag = flag * -1;
        }
        return sum;
    }

    @Test
    public void test(){
        System.out.println(alternateDigitSum(521));
    }
}
