package 校招笔试真题.腾讯.腾讯音乐2023暑期实习;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/4/18
 * 题目描述
 *
 * 塔子哥是一个年轻的数学家，他热爱研究数学中的各种问题。
 * 他最近在研究如何构造一个大小为 n×(n+1)/2 的数组，
 * 使得数组中有 1 个 1，2 个 2，直到 n 个 n，且任意两个相邻元素都不相等。
 *
 * 他认为这个问题非常有趣，因为在这个问题中，
 * 他不仅需要考虑到数学的知识，还需要考虑到编程的实现方式。
 * 他意识到，这个问题的解法不仅仅在于如何生成这个数组，还要考虑到如何保证相邻的元素不相等。
 *
 * 塔子哥知道这个问题可能有多种解法，
 * 但他仍然想要你帮他解决这个问题。
 * 他希望你能够编写一个程序来生成这个数组，
 * 保证其中任意两个相邻的元素都不相等。
 * 他相信你能够帮他完成这个问题，并期待着你的回复，并且表示只需要任何一个合法答案即可。
 *
 * 输入格式
 *
 * 输入为一个整数 n (1≤n≤500)。
 *
 * 输出格式
 *
 * 输出一个满足条件的合法的数组。
 *
 * 样例输入
 *
 * 3
 *
 * 样例输出
 *
 * 3 2 3 2 3 1
 *
 */
public class 构造数组 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int len = n * (n+1) / 2;
        //构造循环就完了，从尾向前倒过来，就能保证相邻的不同[n...1],[n...2],[n...3],...,[n]
        //外层 n(n+1)/2个数 从1加到n(n+1)/2
        //内层就是每个循环 从n减到0
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < len; i++) { //添加len个元素
            for (int j = n; j > i; j--) { //每轮添加[n,...,i]
                list.add(j);
            }
//          每轮i+1
        }
        for (Integer number : list) {
            System.out.print(number + " ");
        }
    }

}
