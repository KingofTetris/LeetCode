package 每日一题;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/9/7 15:56
 * 给你一个字符串 text ，该字符串由若干被空格包围的单词组成。每个单词由一个或者多个小写英文字母组成，并且两个单词之间至少存在一个空格。题目测试用例保证 text 至少包含一个单词 。
 *
 * 请你重新排列空格，使每对相邻单词之间的空格数目都 相等 ，并尽可能 最大化 该数目。如果不能重新平均分配所有空格，请 将多余的空格放置在字符串末尾 ，这也意味着返回的字符串应当与原 text 字符串的长度相等。
 *
 * 返回 重新排列空格后的字符串 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：text = "  this   is  a sentence "
 * 输出："this   is   a   sentence"
 * 解释：总共有 9 个空格和 4 个单词。可以将 9 个空格平均分配到相邻单词之间，相邻单词间空格数为：9 / (4-1) = 3 个。
 * 示例 2：
 *
 * 输入：text = " practice   makes   perfect"
 * 输出："practice   makes   perfect "
 * 解释：总共有 7 个空格和 3 个单词。7 / (3-1) = 3 个空格加上 1 个多余的空格。多余的空格需要放在字符串的末尾。
 * 示例 3：
 *
 * 输入：text = "hello   world"
 * 输出："hello   world"
 * 示例 4：
 *
 * 输入：text = "  walks  udp package   into  bar a"
 * 输出："walks  udp  package  into  bar  a "
 * 示例 5：
 *
 * 输入：text = "a"
 * 输出："a"
 *  
 *
 * 提示：
 *
 * 1 <= text.length <= 100
 * text 由小写英文字母和 ' ' 组成
 * text 中至少包含一个单词
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/rearrange-spaces-between-words
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 重新排列单词间的空格_2022_09_07 {

    @Test
    public void test(){
     String s = "";
     String s1 = reorderSpaces(s);
     System.out.println(s1);
    }

    /**
     * 好像没有什么好办法 就是简单模拟。
     * 没什么复杂逻辑 就是编码
     * @param text
     * @return
     */
    public String reorderSpaces(String text) {
        //先判断边界条件，如果长度<=1 就没有必要去做了
        if (text.length() <= 1){
            return text;
        }

        int spaceCount = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' '){
                spaceCount++;
            }
        }
        text = text.trim();//统计出原来的空格数量后，把头尾空格去掉 注意去掉以后要重新赋值
        String[] split = text.split("\\s+");// \s表示space 空格 所以这个正则就是按任意多个空格把text分割成单词
        int n = split.length;//单词的个数
        if (n - 1 == 0){
            //如果只有一个单词,直接在这里处理，避免下面除0
            sb.append(split[0]);
            for (int i = 0; i < spaceCount; i++) {
                sb.append(" ");
            }
            return sb.toString();
        }
        int spaceGap = spaceCount / (n - 1);//每个单词之间的空格
        int rest = spaceCount - spaceGap * (n - 1);//还剩多少个空格
        int count = 0;
        for (int i = 0; i < n; i++) {
            sb.append(split[i]);
            while (i != n - 1 && count < spaceGap ){ //最后一个单词不要继续加固定空格
                sb.append(" ");
                count++;
            }
            count = 0;//每轮后记得清零
        }

        //多出来的放后面
        for (int i = 0; i < rest; i++) {
            sb.append(" ");
        }

        return sb.toString();
    }
}
