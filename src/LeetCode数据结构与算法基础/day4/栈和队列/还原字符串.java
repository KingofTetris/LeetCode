package LeetCode数据结构与算法基础.day4.栈和队列;

import java.util.Stack;


/**
 * 有这样一种缩写规则
 * aa 缩写为a<2>
 * abb 缩写为ab<2>
 * abab 缩写为(ab)<2>
 * adefefdefefdefefr 缩写为a(d(ef)<2>)<3>r
 * 输入一个缩写字符串，要求你还原字符串.
 */
public class 还原字符串 {
    public static void main(String[] args) {
        String abbreviation = "a(d(ef)<2>)<3>r";
        String expandedString = expandAbbreviation(abbreviation);
        System.out.println(expandedString);
    }

    public static String expandAbbreviation(String abbreviation) {
        Stack<Character> stack = new Stack<>();

        for (char c : abbreviation.toCharArray()) {
            if (c == '>') {
                StringBuilder substringBuilder = new StringBuilder();
                while (stack.peek() != '<') {
                    substringBuilder.insert(0, stack.pop());
                }
                stack.pop(); // 弹出 '<'
                int num = Character.getNumericValue(stack.pop()); // 弹出数字

                String substring = substringBuilder.toString();
                for (int i = 0; i < num; i++) {
                    for (char ch : substring.toCharArray()) {
                        stack.push(ch);
                    }
                }
            } else {
                stack.push(c);
            }
        }

        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }

        return result.toString();
    }
}
