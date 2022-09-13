package 剑指offer第二版.字符串;

/**
 * @Author KingofTetris
 * @Date 2022/9/13 16:23
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 * 示例 2：
 *
 * 输入: s = "lrloseumgh", k = 6
 * 输出: "umghlrlose"
 *  
 *
 * 限制：
 *
 * 1 <= k < s.length <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer58_II_左旋转字符串 {
    public String reverseLeftWords(String s, int n) {

        /**
         * 这是数据结构书里的解法。
         * 但是JAVA不支持基本数据类型交换。
         * 不如直接切片拼接
         *
         * 下面是基本思路。
         * 左右先左转
         *
         * 整体再左转
         *
         * 就相当于整体左移了n位
         */
        String left = s.substring(0, n);
        String right = s.substring(n, s.length());
        StringBuffer sb1 = new StringBuffer(left);
        StringBuffer sb2 = new StringBuffer(right);
        sb1.reverse();
        sb2.reverse();
        StringBuffer whole = sb1.append(sb2);
        return whole.reverse().toString();
    }

    /**
     * 直接切片拼接更快
     */
    public String reverseLeftWords2(String s ,int n){
        String left=s.substring(0,n);
        String right=s.substring(n,s.length());
        String res=right+left;
        return res;
    }


    /**
     * 下面挂一个不用String自带API 自己写API的方法
     */

    public String reverseLeftWords3(String s, int n) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] ch = s.toCharArray();
        reverse(ch,0,n - 1);
        reverse(ch,n, s.length() - 1);
        reverse(ch,0,s.length() - 1);
        return new String(ch);
    }

    /**
     * 逆序调整start到end之间的元素顺序
     * 数组元素交换才能写到函数里面
     */
    public void reverse(char[] ch, int start, int end) {
        if (start >= end) {
            return;
        }
        while (start < end) {
            char tmp = ch[start];
            ch[start] = ch[end];
            ch[end] = tmp;
            start++;
            end--;
        }
    }

/*    作者：T_chen
    链接：https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/solution/ni-xu-by-t_chen-m5na/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
