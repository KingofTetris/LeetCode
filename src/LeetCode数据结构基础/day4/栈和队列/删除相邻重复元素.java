package LeetCode数据结构基础.day4.栈和队列;

import java.util.LinkedList;

/**
 * @author by KingOfTetris
 * @date 2023/9/13
 */
public class 删除相邻重复元素 {
    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(removeNext(s));
    }

    public static String removeNext(String s) {
        if (s.length() <= 1) return s;
        char[] ss = s.toCharArray();
        LinkedList<Character> stack = new LinkedList<>();
        LinkedList<Character> res = new LinkedList<>();
        for (int i = 0; i < ss.length; i++) {
            //如果等于空，直接入栈
            if (stack.isEmpty()) {
                stack.push(ss[i]);
            } else if (ss[i] != stack.peek()) {
                stack.push(ss[i]);
            }
            //如果一样就删除
            //也不要入栈。
            else if (ss[i] == stack.peek()) {
                stack.pop();
            }
        }
        //最后把栈反过来即可
        for (Character character : stack) {
            res.push(character);
        }
        StringBuilder sb = new StringBuilder();
        for (Character re : res) {
            sb.append(re);
        }
        return sb.toString();
    }
}
