package LeetCode数据结构与算法基础.day5.树;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
//https://www.nowcoder.com/practice/5b80ab166efa4551844657603227caeb
public class 完全二叉树数组存储的LCA {
    public static void main(String[] args) {
        //根据题意这是棵完全二叉树
        //而且他刚好就是按顺序的数组排序，那么按照完全二叉树的性质
        //节点i，左孩子为2i，右孩子为2i+1
        //左右孩子的父节点都是i 也就是直接除以2即可。
        //不停地让x,y中较大者/2 直到两者相等即LCA
            Scanner scanner = new Scanner(System.in);
            int a = 0, b = 0;
            //如果Scanner还有输入，就继续判断。
            while (scanner.hasNextInt()) {
                a = scanner.nextInt();
                b = scanner.nextInt();
                while (a != b) {
                    if (a > b) {
                        a /= 2;
                    } else {
                        b /= 2;
                    }
                }
                System.out.println(a);
            }
            scanner.close();
    }
}
