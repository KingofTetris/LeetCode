package LeetCode算法20天.day2.双指针;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 反转字符串中的单词
 * @Time 2021/9/21  11:46
 */


/*给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。

         

        示例：

        输入："Let's take LeetCode contest"
        输出："s'teL ekat edoCteeL tsetnoc"
         

        提示：

        在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class 反转字符串中的单词 {

    @Test
    public void test(){
        String s = "let's take leetcode contest";
        String reverse = reverseWords(s);
        System.out.println(reverse);
    }

    //利用Java APi String spilit,toCharArray,String.valueOf 分割 逆序 重组。
    //非常麻烦 而且时空效率都很低
/*    执行用时： 19 ms, 在所有 Java 提交中击败了12.64%的用户
    内存消耗：39.8 MB
, 在所有 Java 提交中击败了
5.04%
    的用户*/
    /*public String reverseWords(String s) {
        //String底层实际上是个char数组
        //String--->char[] toCharArray()
        //char[] ---> String String.valueOf(char[])
        String[] words = s.split(" ");
        String reverse = "";
        for (int i = 0; i < words.length; i++) {
            char[] toCharArray = words[i].toCharArray();
            reverse(toCharArray);
            if(i == words.length - 1)
                reverse = reverse + String.valueOf(toCharArray);
            else
                reverse = reverse + String.valueOf(toCharArray) + " ";
        }
        return reverse;
    }

    public void reverse(char[] s) {
        int n = s.length;
        for (int i = 0; i < n / 2; i++) {
            char temp;
            temp = s[i];
            s[i] = s[n - 1 - i];
            s[n - 1 - i] = temp;
        }
    }*/

    //当然也有简单的api 但你得知道 会用
    //执行用时：
    //6 ms
    //内存消耗：
    //38.6 MB
    //空间不明显，但是时间消耗大幅缩短！
    public String reverseWords(String s) {
        String[] split = s.split(" ");
        for (int i = 0; i < split.length; i++) {
            //StringBuffer(String s)
            //StringBuffer自带reverse方法
            split[i] = new StringBuffer(split[i]).reverse().toString();
        }
        //新的静态方法String.join方法 可以直接把字符串数组用delimiter连起来
        //后续没空格，爽( •̀ ω •́ )y！
        return String.join(" ",split);
    }
}
