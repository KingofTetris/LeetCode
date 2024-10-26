package LeetCode数据结构与算法基础.day4.栈和队列;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author by KingOfTetris
 * @date 2024/10/26
 */

/**大厂特别喜欢考的算法题TOP1，经常有这兄弟，属于背都要背下来的超级重点**/
public class 基本计算器 {
    //解法1，中缀表达式转后缀表达式然后计算就很简单了
    //但是问题变成了中缀转后缀该怎么做，又是一个难题。

    //解法2.因为只有加法和减法，所以可以不用上边通用的的方法，可以单独分析一下
    //首先，将问题简单化，如果没有括号的话，该怎么做？



    @Test
    public void test(){
        String s = "1 + 3 + 4 - 5 -13 + 99";
        int calculate1 = calculate1(s);
        System.out.println(calculate1);
    }
    /**
     * 1 + 2 - 3 + 5
     *
     * 我们把式子看成下边的样子。
     *
     * + 1 + 2 - 3 + 5
     *
     * 用一个变量 op 记录数字前的运算，初始化为 +。
     * 然后用 res 进行累加结果，初始化为 0。用 num 保存当前的操作数。
     *
     * 从上边第二个加号开始，每次遇到操作符的时候，根据之前保存的 op 进行累加结果 res = res op num，
     * 然后 op 更新为当前操作符。
     *
     * 作者：windliang
     * 链接：https://leetcode.cn/problems/basic-calculator/solutions/93396/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--47/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     */

    //无()的情况
    public int calculate1(String s) {
        char[] array = s.toCharArray();
        int n = array.length;
        int res = 0;
        int num = 0;
        char op = '+';

        for (int i = 0; i < n; i++) {
            if (array[i] == ' ') {
                continue;
            }
            //因为会有多位数例如 123 的情况，所以需要一个num进行转化
            if (array[i] >= '0' && array[i] <= '9') {
                num = num * 10 + array[i] - '0';
            }
            //如果遇到操作符，进行判断
            else {
                if (op == '+') {
                    res = res + num;
                }
                if (op == '-') {
                    res = res - num;
                }
                //操作完成，num重新置0.
                num = 0;
                op = array[i];//操作符是双目运算，还能再用一次。
            }
        }
        //计算完最后的num 还需要进行最后一次计算。
        if (op == '+') {
            res = res + num;
        }
        if (op == '-') {
            res = res - num;
        }
        return res;

      /*  作者：windliang
        链接：https://leetcode.cn/problems/basic-calculator/solutions/93396/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--47/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }

    //带()的情况
    //可能是这样 1 - (2 + 4) + 1，可能括号里包含括号 2 + (1 - (2 + 4)) - 2

    /**
     * 做法也很简单，当遇到左括号的时候，我们只需要将当前累计的结果，
     * 以及当前的 op 进行压栈保存，然后各个参数恢复为初始状态，继续进行正常的扫描计算。
     *
     * 当遇到右括号的时候，将栈中保存的结果和 op 与当前结果进行计算，
     * 计算完成后将各个参数恢复为初始状态，然后继续进行正常的扫描计算。
     *
     * 举个例子，对于 2 + 1 - (2 + 4) + 1，遇到左括号的时候，
     * 我们就将已经累加的结果 3 和左括号前的 - 放入栈中。也就是 3 - (...) + 1。
     *
     * 接着如果遇到了右括号，括号里边 2 + 4 的结果是 6，已经算出来了，
     * 接着我们从栈里边把 3 和 - 取出来，也就是再计算 3 - 6 + 1 就可以了。
     *
     * 结合代码再看一下。
     *
     * 作者：windliang
     * 链接：https://leetcode.cn/problems/basic-calculator/solutions/93396/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--47/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public int calculate2(String s) {
        char[] array = s.toCharArray();
        int n = array.length;
        int res = 0;
        int num = 0;
        Stack<Character> opStack = new Stack<>();
        Stack<Integer> resStack = new Stack<>();
        char op = '+';
        for (int i = 0; i < n; i++) {
            if (array[i] == ' ') {
                continue;
            }
            if (array[i] >= '0' && array[i] <= '9') {
                num = num * 10 + array[i] - '0';
            } else if (array[i] == '+' || array[i] == '-') {
                if (op == '+') {
                    res = res + num;
                }
                if (op == '-') {
                    res = res - num;
                }
                num = 0;
                op = array[i];
            }
            //遇到左括号，将结果和括号前的运算保存，然后将参数重置
            else if (array[i] == '(') {
                resStack.push(res);
                opStack.push(op);
                //将参数重置
                op = '+';
                res = 0;
            }
            //如果遇到了右括号
            else if (array[i] == ')') {
                //将右括号前的当前运算结束
                //比如 (3 + 4 - 5), 当遇到右括号的时候, - 5 还没有运算
                //(因为我们只有遇到操作符才会进行计算)
                if (op == '+') {
                    res = res + num;
                }
                if (op == '-') {
                    res = res - num;
                }
                //将之前的结果和操作取出来和当前结果进行运算
                char opBefore = opStack.pop();
                int resBefore = resStack.pop();
                if (opBefore == '+') {
                    res = resBefore + res;
                }
                if (opBefore == '-') {
                    res = resBefore - res;
                }
                //将参数重置
                op = '+';
                num = 0;
            }
        }

        //把最后的num加上即可
        if (op == '+') {
            res = res + num;
        }
        if (op == '-') {
            res = res - num;
        }
        return res;

     /*   作者：windliang
        链接：https://leetcode.cn/problems/basic-calculator/solutions/93396/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--47/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
