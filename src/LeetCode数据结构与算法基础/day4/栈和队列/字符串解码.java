package LeetCode数据结构与算法基础.day4.栈和队列;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class 字符串解码 {

    @Test
    public void test(){
        String s = "21[2[a]]";
        String s1 = decodeString2(s);
        System.out.println(s1);
    }

    /**
     * 这题的难点就在于嵌套字符
     *
     * 思想上就非常类似递归，用到递归其实就可以联系到栈。
     * 都是先计算后面的再返回给前面
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>(); // 存储数字
        Stack<String> stringStack = new Stack<>(); // 存储字符串
        String currentString = ""; // 当前解码字符串，初始为""空字符串，很重要。
        int k = 0; // 当前的倍数
        for (char ch : s.toCharArray()) {
//            Character.isDigit(char c)判断字符是不是数字
            if (Character.isDigit(ch)) {
                //比如21，如果是直接ch - '0' 那就是一个2，一个1。就不对了
                //要按顺序处理，每次让前面的数*10 + 个位才能得到正确的k
                k = k * 10  + (ch - '0'); // 处理多位数
            } else if (ch == '[') {
                // 遇到 '['，将当前的字符串和数字推入各自的栈
                countStack.push(k);
                stringStack.push(currentString);
                /**
                 * 这两个重置非常重要
                 */
                currentString = ""; // 重置当前字符串
                k = 0; // 重置倍数
            } else if (ch == ']') {
                // 遇到 ']'，解码
                StringBuilder temp = new StringBuilder(stringStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(currentString); // 重复当前字符串
                }
                currentString = temp.toString(); // 更新当前字符串
            } else if (Character.isLetter(ch)){
                // 如果是字母，直接加到当前字符串
                currentString += ch;
            }
        }
        return currentString;
    }

    public String decodeString2(String s){
        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        String currentString = "";
        int k = 0;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (Character.isDigit(c)){
                k = k * 10 + (c - '0');
            }
            else if (c == '['){
                countStack.push(k);
                stringStack.push(currentString);
                //重置k和string
                k = 0;
                currentString = "";
            }
            else if (c == ']'){ //解码
                //为什么这里一定要初始化为pop的string builder？
                StringBuilder sb = new StringBuilder(stringStack.pop());
                int repeat = countStack.pop();//取出k
                for (int i = 0; i < repeat; i++) {
                    sb.append(currentString);//重复k次
                }
                //更新当前字符串
                currentString = sb.toString();
            }
            else if (Character.isLetter(c)){
                currentString += c;//拼接到当前字符串
            }
        }
        //最后返回字符串即可
        return currentString;
    }
}
