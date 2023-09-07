package 校招笔试真题.小米.小米2022秋招;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/2
 */
public class 括号字符串的有效性 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            boolean res = validBracket(input);
            if (res) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    public static boolean validBracket(String input) {
        // Corner case
        if (input == null || input.length() == 0) {
            return true;
        }
        if (input.length() % 2 != 0) {
            return false;
        }
        char[] array = input.toCharArray();
        // Use a stack
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '(') {
                stack.push(array[i]);
            } else if (array[i] == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        // Post Processing
        return stack.isEmpty();
    }

  /*  作者：GreenMonster
    链接：https://www.nowcoder.com/exam/test/73035414/submission?pid=31126126
    来源：牛客网*/
}
