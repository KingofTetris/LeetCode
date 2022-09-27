package 程序员面试金典;

/**
 * @author by KingOfTetris
 * @date 2022/9/22
 * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
 *
 * 示例 1：
 *
 * 输入："Mr John Smith    ", 13
 * 输出："Mr%20John%20Smith"
 * 示例 2：
 *
 * 输入："               ", 5
 * 输出："%20%20%20%20%20"
 *  
 *
 * 提示：
 *
 * 字符串长度在 [0, 500000] 范围内。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/string-to-url-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _01_03_URL化 {


    /**
     *
     * @param S
     * @param length 指定字符串的限定长度为 0 - length-1
     * @return
     */
    public String replaceSpaces(String S, int length) {

        String substring = S.substring(0,length);
        char[] chars = new char[substring.length() * 3]; //极端情况全是空白则一个空白顶%20 3个字符

        int index = 0;
        for (int i = 0; i < substring.length(); i++) {
            if (S.charAt(i) == ' ') {
                chars[index++] = '%';
                chars[index++] = '2';
                chars[index++] = '0';
            }
            else {
                chars[index++] = substring.charAt(i);//普通情况，直接赋值
            }
        }

        return new String(chars).trim();//最后把多余的空格去掉
    }


    /**
     * StringBuffer 生成新字符串
     * 不要用String 因为String 不可变，你每次 += 都会生成新的String
     * @param S
     * @param length
     * @return
     */
    public String replaceSpaces2(String S, int length) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            if (S.charAt(i) == ' ')
                stringBuffer.append("%20");
            else
                stringBuffer.append(S.charAt(i));
        }
        return stringBuffer.toString();
    }
}
