package LeetCode_HOT100题;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/10/13 14:22
 */
public class 最长有效括号 {

    @Test
    public void test(){
        String s =  "()(())";
        int i = longestValidParentheses(s);
        System.out.println(i);
    }
    /**
     * 给出一串只包含左右括号的字符串
     * 判断有效括号的最长长度
     * 找出所有子串O(n^2) 然后判断是否有效O(n) 总体 O(n^3) TLE
     * 我们另外想办法。用left right来作为( 和 )的计数器。
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int left = 0,right = 0,maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            else  right++;
            if (left == right)
                //如果左右相等了，那么更新当前最大有效长度。
                maxLength = Math.max(maxLength,2 * right);
            //如果right > left了
            //因为是从左边开始算的,左括号数量不够抵消右括号 那必然是无效的括号
            //那就把left right都置为0
            else if (right > left){
                left = right = 0;//重新置0
            }
        }
        //为了避免左括号始终比右括号多的情况
        // 比如 (((() 上面的代码没法算出maxLenth 因为left 始终大于 right
        //这个时候我们需要反过来从右往左算
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') right++;
            else left++;
            if (left == right){
                maxLength = Math.max(maxLength,left * 2);
            }
            //如果left > right了
            //因为现在反过来算,右括号数量不够抵消左括号 那必然是无效的括号
            //那就把left right都置为0
            else if (left > right){
                left = right = 0;
            }
        }
        //这样左右都算了一遍 就解决了
        return maxLength;
    }
}
