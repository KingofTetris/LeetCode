package LeetCode数据结构与算法基础.双指针;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 反转字符串
 * @Time 2021/9/21  11:15
 */

/*编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。

        不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。

        你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。

         

        示例 1：

        输入：["h","e","l","l","o"]
        输出：["o","l","l","e","h"]
        示例 2：

        输入：["H","a","n","n","a","h"]
        输出：["h","a","n","n","a","H"]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/reverse-string
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class 反转字符串 {

    @Test
    public void test(){
        char[] s  = {'h','e','l','l','o'};
        reverseString(s);
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i] + "\t");
        }

    }

    public void reverseString(char[] s){
        //用双指针交换就可以了，left和right互换 双指针逻辑简单点
//        int left,right;
//        left = 0;right = s.length - 1;
//        while(left < right){
//            char temp;
//            temp = s[left];
//            s[left] = s[right];
//            s[right] = temp;
//            left++;
//            right--;
        //单指针更简单 一个for i也可以， n-1 - i和 i 互换就行了，停止条件可以设置成 i <  n/2;
        int n = s.length;
        for (int i = 0; i < n/2; i++) {
            char temp;
            temp = s[i];
            s[i] = s[n - 1 - i];
            s[n - 1 - i] = temp;
        }
    }
}

