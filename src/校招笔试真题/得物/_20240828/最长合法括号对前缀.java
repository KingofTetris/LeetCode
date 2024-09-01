package 校招笔试真题.得物._20240828;

import java.util.*;

public class 最长合法括号对前缀 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        char[] arr = s.toCharArray();
        Deque<Character> stack = new LinkedList<>();
        int res = 0;
        for(char c : arr){
            if(c == '('){
                stack.push(c);
            }
            else{
                if(!stack.isEmpty()) {
                    stack.pop();
                    res += 2;
                }
                // 如果栈为空直接退出循环，后面的就算合法也不是前缀了。
                else{
                    break;
                }
            }
        }
        System.out.println(res);
    }
}
