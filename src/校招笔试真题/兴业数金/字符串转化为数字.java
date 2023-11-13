package 校招笔试真题.兴业数金;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author by KingOfTetris
 * @date 2023/11/3
 */
public class 字符串转化为数字 {
    @Test
    public void test(){
        String s = "28";
        int res = stringToNumber(s);
        System.out.println(res);
    }
    /**
     * 0-9表示0-9
     * A-Z表示10-35
     * 所以10表示36
     * 11表示37
     * 28表示80 2*36 + 8 = 72+8 = 80
     * 其实就是36进制化为10进制
     * @param inputString
     * @return
     */
    public int stringToNumber (String inputString) {
        // write code here
        //建立映射
        HashMap<Character,Integer> map = new HashMap<>();
        for (char i = '0'; i <= '9'; i++) {
            map.put(i,i - '0');
        }
        for (char i = 'a'; i <= 'z'; i++) {
            map.put(i,i - 'a' + 10);
        }
        char[] chars = inputString.toLowerCase().toCharArray();
        int sum = 0;
        int mc = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            sum += map.get(chars[i]) * Math.pow(36,mc);
            mc++;
        }
        return sum;
    }
}
