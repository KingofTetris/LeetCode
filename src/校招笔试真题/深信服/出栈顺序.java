package 校招笔试真题.深信服;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author by KingOfTetris
 * @date 2023/9/16
 */


//给你一个字符串比如说abc作为入队顺序
//再给你一个不限制大小的栈，栈顶元素能够随时出栈，
    //请你返回所有的出栈顺序。

    //本质还是回溯。
public class 出栈顺序 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        List<List<Character>> allPopOrder = getAllPopOrder(s);
        for (List<Character> characters : allPopOrder) {
             StringBuilder sb = new StringBuilder();
            for (Character character : characters) {
                sb.append(character);
            }
            System.out.println(sb);
        }
    }

    public static List<List<Character>> getAllPopOrder(String s){
        List<List<Character>> result = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        backtrack(s.toCharArray(),0,new ArrayList<Character>(),stack,result);
        return result;
    }

    private static void backtrack(char[] input, int index,
                                  List<Character> current,
                                  Stack<Character> stack,
                                  List<List<Character>> result) {

        if (index == input.length && stack.isEmpty()){
            result.add(new ArrayList<>(current));
            return;
        }

        if (!stack.isEmpty()){
            char top = stack.pop();
            current.add(top);
            backtrack(input,index,current,stack,result);
            current.remove(current.size() - 1);
            stack.push(top);
        }

        if (index < input.length){
            stack.push(input[index]);
            backtrack(input,index + 1,current,stack,result);
            stack.pop();
        }
    }
}
