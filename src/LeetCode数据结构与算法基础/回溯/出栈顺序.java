package LeetCode数据结构与算法基础.回溯;

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

    //res接收结果，stack是需要回溯的栈。
    static List<List<Character>> result = new ArrayList<>();
    static Stack<Character> stack = new Stack<>();
    public static List<List<Character>> getAllPopOrder(String s){
        backtrack(s.toCharArray(),0,new ArrayList<>());
        return result;
    }

    private static void backtrack(char[] input, int index,
                                  List<Character> current) {

        //如果开始位置已经等于输入的长度，或者stack为空。结束
        //添加结果。
        if (index == input.length && stack.isEmpty()){
            result.add(new ArrayList<>(current));
            return;
        }

        //接下来就是回溯的模板。
        if (!stack.isEmpty()){
            //每个字符入栈。
            char top = stack.pop();
            current.add(top);
            backtrack(input,index,current);
            current.remove(current.size() - 1);
            stack.push(top);
        }

        if (index < input.length){
            stack.push(input[index]);
            backtrack(input,index + 1,current);
            stack.pop();
        }
    }
}
