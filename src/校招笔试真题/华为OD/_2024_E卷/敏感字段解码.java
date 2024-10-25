package 校招笔试真题.华为OD._2024_E卷;

import java.util.ArrayList;
import java.util.Scanner;

public class 敏感字段解码 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = sc.nextInt();
        String s = sc.next();

        System.out.println(solution(s, k));
    }

    public static String solution(String s, int k) {
        ArrayList<String> commands = new ArrayList<>();

        // 双引号是否处于开启状态
        boolean isQuotaOpen = false;

        // 避免收尾操作, s 尾部追加一个 '_' 不影响结果
        s += "_";

        StringBuilder command = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 如果遇到双引号, 则先将 isQuotaOpen 取反
            if (c == '"') {
                isQuotaOpen = !isQuotaOpen;
            }

            if (c != '_' || isQuotaOpen) {
                // 如果遇到的 c 不是 '_' 或者是双引号里面的 '_', 则此时 c 不是命令字分隔符, 而是命令字一部分
                command.append(c);
            } else if (command.length() > 0) {
                // 如果 c 是命令字分隔符, 且 command 命令字不为空, 则命令字被截断
                commands.add(command.toString());
                // 清空command, 用于下个命令字的收集
                command = new StringBuilder();
            }
        }

        if (k >= commands.size()) {
            // 如果无法找到指定索引的命令字，输出字符串ERROR
            return "ERROR";
        } else {
            // 对指定索引的敏感字段进行加密，替换为******（6个*），并删除命令字前后多余的下划线_
            commands.set(k, "******");
            return String.join("_", commands);
        }
    }
}
