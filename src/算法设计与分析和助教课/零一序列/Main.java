package 算法设计与分析和助教课.零一序列;

import java.util.Scanner;

/**
 * @author KingofTetris
 * @File 第二题_01序列
 * @Time 2021/10/12  14:42*/



/*先计算01序列长度和n大小的关系

长度：0 1 3 7 15 31.........

0  0
1  1
2  101
3  111
4  1010101
5  1011101
6  1110111
7  1111111
8  101010101010101
9  101010111010101
10 101110101011101
11 101110111011101
12 111011101110111
13 111011111110111
14 111111101111111
15 111111111111111
16 1010101010101010101010101010101

所以长度关系为
	L=2^0+2^1+ ……+2^t，2^t≤n

并且实际上这个序列是关于 n%2 对称的*/


public class Main {

    static int N,left,right;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        left = sc.nextInt();
        right = sc.nextInt();
        sc.close();

        //化为01序列的长度
        double len = 0;
        for (int i = 0; Math.pow(2,i) <= N; i++) {
            len = len + Math.pow(2,i);
        }
// System.out.println(len);
// position就是当前01序列的中间位置（从1开始）
        System.out.println(function1(N, len, (1 + len) / 2));
    }

    public static int function1(int n,double len,double position) {
        if (right < position - len / 2 || position + len / 2 < left)
            return 0;
        if (len == 1) return n % 2;

        //dis下一次分裂的长度
        double dis = (len + 1) / 4;
        //left right在区间内
        if (left <= position && position <= right)
            return function1(n / 2, len / 2, position - dis) + n % 2 + function1(n / 2, len / 2, position + dis);
        if (right < position)
            return function1(n / 2, len / 2, position - dis);
        if (position < left)
            return function1(n / 2, len / 2, position + dis);
        return 1;
        }
}
