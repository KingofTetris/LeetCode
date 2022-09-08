package 剑指offer第二版.字符串;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/7/7 15:32
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *  
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ti-huan-kong-ge-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer05_替换空格 {
    @Test
    public void test(){
        String s = "  ";
        System.out.println(replaceSpace3(s));
    }

    /**
     * 特殊情况：如果这个情况全是空格
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        return s.replaceAll(" ","%20");
    }

    /**
     * 实现replaceAll。
     * 字符串的底层是char数组，我们假设所有字符都空格，那么我们会把空格换成'%''2''0'三个字符
     * 这样我们初始化一个3倍于原始长度数组
     * 或者直接拿队列来存储，就不用预设长度了。
     * @param s
     * @return
     */
    public String replaceSpace2(String s) {
        char[] charsThree = new char[s.length() * 3];
        char[] chars = s.toCharArray();
        int j = 0; //三倍的指针
        int i = 0;//原始数组的指针
        while(i < chars.length) {
            if (chars[i] != ' '){
                charsThree[j] = chars[i];
                i++;
                j++; //不等于空格就直接赋值，然后一起往后移动
            }
            else { //如果碰到空格
                charsThree[j] = '%';
                charsThree[j + 1] = '2';
                charsThree[j + 2] = '0';
                i++;
                j = j + 3;//j要移动到j+3
            }
        }

        return new String(charsThree,0,j);//新建字符串，取charsThree的0-j这一段 因为后面全是空字符，不要了
    }

    public String replaceSpace3(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' '){
//                stringBuilder.append() 重载了多种类型，所以可以append char,int,double,string等等
                stringBuilder.append('%');
                stringBuilder.append('2');
                stringBuilder.append('0');
            }
            else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }
}
