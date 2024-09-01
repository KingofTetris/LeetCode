package 校招笔试真题.腾讯._0230910;

import java.util.*;


public class 腾讯20230910_最大化密码复杂度 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        //如果长度为1，那不管怎么写复杂度都是0
        if (n == 1) {
            System.out.println(0);
            return;
        }

        /**
         * 下面简述一下next() 与 nextLine() 区别：
         * next()： next()会自动消去有效字符前的空格，只返回输入的字符，不能得到带空格的字符串。
         * nextLine()： nextLine()方法返回的是Enter键之前的所有字符，
         * 它是可以得到带空格的字符串的，可以得到空格。
         *
         * 用nextLine()读取空白的时候，可以用next代替。
         */
        String pwd = sc.next();

        //你需要解析pwd哪些位置可以填字符进去
        char[] s = pwd.toCharArray();
        // 统一处理包括左右字符的情况
        for (int i = 0; i < n; ++i) {
            if (s[i] == '?') {
                //去找在 'a' + m的范围内有没有和 s[i-1] s[i + 1]都不一样的字符
                //这样能+2 复杂度
                boolean found = false;
                for (char j = 'a'; j < 'a' + m; ++j) {
                    // 避免第一个字符 和 最后一个字符，
                    // 因为两个字符只有一个相邻字符，没有左右相邻字符
                    if ((i == 0 || s[i - 1] != j)
                            && (i == n - 1 || s[i + 1] != j)) {
                        //找到这个字符，found = true
                        s[i] = j;
                        found = true;
                        break;
                    }
                }
                // 如果没有找到合适的字符，就和附近字符保持一致
                if (!found) {
                    if (i > 0) s[i] = s[i - 1];
                    else s[i] = s[i + 1];
                }
            }
        }
        // 计算结果
        int result = 0;
        for (int i = 0; i < n - 1; ++i) {
            if (s[i] != s[i + 1]) result++;
        }
        System.out.println(result);
    }
}
