package LeetCode数据结构基础.双指针;

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
        //进阶 限制空间复杂度为O(1)
public class 反转字符串中的单词 {

    @Test
    public void test(){
        String s = "let's take leetcode contest";
        String reverse = reverseWords(s);
        System.out.println(reverse);
    }
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


    //进阶，不使用额外空间
    public String reverseWords2(String s){
        //这个和旋转字符串K位是一个思想
        //先让整个字符串进行反转，然后再让单个单词进行反转即可。
        //但是里面还有点细节，要先清楚前后的空格，以及中间的多余空格。
        StringBuilder stringBuilder = removeSpace(s);
        return "";
    }
    private StringBuilder removeSpace(String s) {
        // System.out.println("ReverseWords.removeSpace() called with: s = [" + s + "]");
        int start = 0;
        int end = s.length() - 1;
        while (s.charAt(start) == ' ') start++;
        while (s.charAt(end) == ' ') end--;
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }
        // System.out.println("ReverseWords.removeSpace returned: sb = [" + sb + "]");
        return sb;
    }
}
