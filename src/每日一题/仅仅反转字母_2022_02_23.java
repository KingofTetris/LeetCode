package 每日一题;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 仅仅反转字母_2022_02_23
 * @Time 2022/2/23  14:17
 *
 *
 *
 * 给你一个字符串 s ，根据下述规则反转字符串：
 *
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的 s 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：s = "ab-cd"
 * 输出："dc-ba"
 * 示例 2：
 *
 * 输入：s = "a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 * 示例 3：
 *
 * 输入：s = "Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 *  
 *
 * 提示
 *
 * 1 <= s.length <= 100
 * s 仅由 ASCII 值在范围 [33, 122] 的字符组成
 * s 不含 '\"' 或 '\\'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-only-letters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 仅仅反转字母_2022_02_23 {
    @Test
    public void test() {
        String s = "Test1ng-Leet=code-Q!";
        System.out.println(reverseOnlyLetters(s));
    }

    public String reverseOnlyLetters(String s) {
        char[] ch = s.toCharArray();
        int left = 0;
        int right = ch.length - 1;
        while (left < right){
            if ( isCharacter(ch[left]) && isCharacter(ch[right]) ){  //两个都是字母互换，然后挪动指针
                char temp = ch[left];
                ch[left] = ch[right];
                ch[right] = temp;

                left++;
                right--;
            }

            //如果左边不是字母 左边++ 右边不动
            else if ( !isCharacter(ch[left]) ){
                left++;
            }

            //右边不是字母 右边-- 左边不动
            else {
                right--;
            }
        }
        String s1 = String.valueOf(ch);
        return s1;
    }

    public boolean isCharacter(char c){
        if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))){
            return true;
        }
        return false;
    }
}
