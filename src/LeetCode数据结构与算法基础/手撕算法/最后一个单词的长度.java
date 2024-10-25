package LeetCode数据结构与算法基础.手撕算法;

/**
 * @author by KingOfTetris
 * @date 2024/10/25
 */
public class 最后一个单词的长度 {

    //简单使用API
    public int lengthOfLastWord(String s) {
        String[] split = s.trim().split("\\s+");
        return split[split.length - 1].length();
    }

    //不使用API
    //因为要找最后一个单词，那么前面的单词其实根本无所谓，我们从末尾开始找就行了。
    public int lengthOfLastWord2(String s) {
        int index = s.length() - 1;
        while (s.charAt(index) == ' ') {
            index--;
        }
        //找到不为空格字符以后开始计算长度。
        int length = 0;
        while (index >= 0 && s.charAt(index) != ' ') {
            length++;
            index--;
        }
        return length;
    }


}
