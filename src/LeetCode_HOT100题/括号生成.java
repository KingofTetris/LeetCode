package LeetCode_HOT100题;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2022/10/11
 */

//输入n，代码有3对括号，请你输出所有合法的括号可能。
public class 括号生成 {

    @Test
    public void test() {
        List<String> strings = generateParenthesis2(3);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    //原理遍历每个括号，插入基本元素“()”，然后去重
    public List<String> generateParenthesis(int n) {
        //一个括号就这一种情况
        if (n == 1) {
            return Arrays.asList("()");
        }
        //用set去重
        Set<String> hs = new HashSet<>();
        //
        for (String s : generateParenthesis(n - 1)) {
            for (int i = 0; i < 2 * n - 2; i++) {
                hs.add(s.substring(0, i) + "()" + s.substring(i, s.length()));
            }
        }
        return new ArrayList(hs);
    }

    /**
     * 总体的原理就是给字符串中间加上()。
     * 不管怎么加都不会影响原来字符的有效性
     * 需要注意的点就会有重复，需要去重。选择set作为存储容器
     * 最后返回为List
     * @param n
     * @return
     */
    public List<String> generateParenthesis2(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        Set<String> ans = new HashSet<>();
        ans.add("()");
        for (int i = 1; i < n; i++) { //几个括号套几次
            Set<String> tmp = new HashSet<>();//set去重
            for (String str : ans) {
                for (int j = 0; j <= str.length(); j++) {
                    //最关键的就是下面这三段话，
                    // 在()外面包裹上sub1和sub2
                    //sub1 就是 (0,j) sub2就是(j,s.len)
                    String substring1 = str.substring(0, j);// 0,j j=0时是""空字符串，j=len时，就是字符串本身
                    String substring2 = str.substring(j); // j,len j=0时是字符串本身,j=len时，substring不是报错，而是返回""空字符串
                    tmp.add(substring1 + "()" + substring2);//用set套上前半部分和后半部分，自带去重的作用
                    //比如j = 0时候 sub1 = "" sub2 = "()" 结果是"()()"
                    // j = len时，sub1 = "()",sub2 = "" 结果还是"()()" 这就要去重
                }
            }
            ans = tmp;
        }
        //最后要用List返回
        //Set转List 直接用List的带参构造函数，把容器作为参数传进去就行了
//        List a = new ArrayList<>(ans);
        return new ArrayList<>(ans);
    }
}
