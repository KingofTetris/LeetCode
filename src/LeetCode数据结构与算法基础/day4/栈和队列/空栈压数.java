package LeetCode数据结构与算法基础.day4.栈和队列;

import java.util.*;

public class 空栈压数 {
    public static void push(int num, Stack<Integer> stack) {
        int total = num;
        List<Integer> temp = new ArrayList<>();

        // 从栈顶开始，尝试合并数字
        while (!stack.isEmpty()) {
            int top = stack.pop();
            temp.add(top);
            total -= top;

            // 如果找到了可以合并的组合
            if (total == 0) {
                // 将合并后的新数字压入栈中
                push(num * 2, stack);
                return;
            }
            // 如果和变成负数，说明无法合并，退出循环
            else if (total < 0) {
                break;
            }
        }

        // 如果无法合并，恢复栈的状态
        Collections.reverse(temp);
        stack.addAll(temp);
        // 将新数字压入栈顶
        stack.push(num);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(" ");
        Stack<Integer> stack = new Stack<>();

        // 处理所有输入的数字
        for (String s : input) {
            push(Integer.parseInt(s), stack);
        }

        // 输出结果（从栈底到栈顶）
        List<Integer> result = new ArrayList<>(stack);
        Collections.reverse(result);
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

/*作者：程序员A先生
链接：https://www.nowcoder.com/discuss/676752798968520704?sourceSSR=search
来源：牛客网*/
