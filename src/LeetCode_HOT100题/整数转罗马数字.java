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

    //打表比较关键的数字。 I II III IV V VI //放在后面表示加，前面减
    static int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    static String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static void main(String[] args) {
        System.out.println(intToRoman(99));
    }
    public static String intToRoman(int num) {
       StringBuilder roman = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            //从大到小遍历。
            int value = values[i];
            String symbol = symbols[i];

            //每次减去代表符号
            while(num >= value){
                num = num - value;
                roman.append(symbol);
            }
            //一直到0
            if (num == 0) break;//等于0推出
        }

        return roman.toString();
    }
}
