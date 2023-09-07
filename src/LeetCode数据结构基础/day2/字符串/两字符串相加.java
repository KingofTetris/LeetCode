package LeetCode数据结构基础.day2.字符串;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 两字符串相加
 * @Time 2021/10/15  14:50
 */

/*415. 字符串相加
        给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。

        你不能使用任何內建的用于处理大整数的库（比如 BigInteger），
         也不能直接将输入的字符串转换为整数形式。
        示例 1：

        输入：num1 = "11", num2 = "123"
        输出："134"
        示例 2：

        输入：num1 = "456", num2 = "77"
        输出："533"
        示例 3：

        输入：num1 = "0", num2 = "0"
        输出："0"

        提示：

        1 <= num1.length, num2.length <= 104
        num1 和num2 都只包含数字 0-9
        num1 和num2 都不包含任何前导零*/

public class 两字符串相加 {
    @Test
    public void test(){
        String num1 = "11211232133311111";
        String num2 = "132213212112";
        System.out.println(addStrings(num1, num2));
    }

    //模拟竖式相加就行了
    public String addStrings(String num1, String num2) {
        //add是进位，i,j是个位
        int i = num1.length() - 1,j = num2.length() - 1,add = 0;
        StringBuffer ans = new StringBuffer();
        //只要有一个还没截完就继续
        //或者优化一下，如果两个字符串长度相差过大，这样的循环会显得没意义。
        while(i >= 0 || j >= 0 || add != 0){
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result  = x + y + add;
            //StringBuffer的append方法可以直接append数字
            //直接append进去，最后翻转过来就是正的了。
            ans.append(result%10);
            add = result / 10;
            i--;
            j--;
        }
        //StringBuffer.reverse 会把字符串倒过来
        ans.reverse();
        return ans.toString();
    }
}
