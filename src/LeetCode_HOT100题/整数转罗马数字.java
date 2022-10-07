package LeetCode_HOT100题;

/**
 * @Author KingofTetris
 * @Date 2022/10/4 15:29
 */
public class 整数转罗马数字 {
    /**
     * I 1
     * V 5
     * X 10
     * L 50
     * C 100
     * D 500
     * M 1000
     * 1 <= num <= 3999 //标准罗马数字的范围就到3999。阿拉伯人牛逼。
     */
    /**
     * 罗马数字是有唯一性的，左边放尽量大的。
     * 还以为有什么特殊的解法，结果也是先打表，把特殊的字符先列出来
     */
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman(int num) {
       StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; i++) {
            int value = values[i];
            String symbol = symbols[i];
            while(num >= value){
                num = num - value;
                roman.append(symbol);
            }
            if (num == 0) break;//等于0推出
        }

        return roman.toString();
    }
}
