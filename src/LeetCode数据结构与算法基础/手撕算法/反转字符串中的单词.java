
package LeetCode数据结构与算法基础.手撕算法;

/**
 * @author by KingOfTetris
 * @date 2024/10/24
 */
public class 反转字符串中的单词 {

    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        int n = words.length;
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            if (i > 0) {
                sb.append(words[i]).append(" ");
            } else {
                sb.append(words[i]);
            }
        }
        return sb.toString();
    }

    //优化写法
    public String reverseWords2(String s) {
        //源字符数组
        char[] initialArr = s.toCharArray();
        //新字符数组
        char[] newArr = new char[initialArr.length + 1];//下面循环添加"单词 "，最终末尾的空格不会返回
        int newArrPos = 0;
        //i来进行整体对源字符数组从后往前遍历
        int i = initialArr.length - 1;
        while (i >= 0) {
            while (i >= 0 && initialArr[i] == ' ') {
                i--;
            }  //跳过空格
            //此时i位置是边界或!=空格，先记录当前索引，之后的while用来确定单词的首字母的位置
            int right = i;
            while (i >= 0 && initialArr[i] != ' ') {
                i--;
            }
            //指定区间单词取出(由于i为首字母的前一位，所以这里+1,)，取出的每组末尾都带有一个空格
            for (int j = i + 1; j <= right; j++) {
                newArr[newArrPos++] = initialArr[j];
                if (j == right) {
                    newArr[newArrPos++] = ' ';//空格
                }
            }
        }
        //若是原始字符串没有单词，直接返回空字符串；若是有单词，返回0-末尾空格索引前范围的字符数组(转成String返回)
        if (newArrPos == 0) {
            return "";
        } else {
            return new String(newArr, 0, newArrPos - 1);
        }
    }
}
