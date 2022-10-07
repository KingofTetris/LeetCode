package 每日一题;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author KingofTetris
 * @Date 2022/10/4 9:44
 * https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid/
 */
public class 使括号有效的最少添加_2022_10_04 {

    @Test
    public void test(){
        String s = "()))((";
        System.out.println(minAddToMakeValid(s));
    }

    /**
     * 贪心加栈
     * 遇到 ')' 并且栈顶是 '(' 才pop。
     * 其他情况就push。最后返回栈的大小
     * @param s
     * @return
     */
    public int minAddToMakeValid2(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == ')' && !stk.isEmpty() && stk.peek() == '(') {
                stk.pop();
            } else {
                stk.push(c);
            }
        }
        return stk.size();

     /*   作者：lcbin
        链接：https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid/solution/by-lcbin-t2d4/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
    /**
     * 贪心+变量计数 空间优化为O(1)
     * @param s
     * @return
     */
    public int minAddToMakeValid(String s) {
        int ans = 0;
        int leftCount = 0;//记录左括号的数量
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftCount++;
            } else { //如果是右括号并且leftCount>0 那么--
                if (leftCount > 0) {
                    leftCount--;
                } else { //如果=0 那么ans++;
                    ans++;
                }
            }
        }
        ans += leftCount; //最后ans = ans + leftCount
        return ans;
/*
        作者：LeetCode-Solution
        链接：https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid/solution/shi-gua-hao-you-xiao-de-zui-shao-tian-ji-gcxu/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/


    }
}
