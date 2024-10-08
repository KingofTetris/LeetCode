package 校招笔试真题.京东.秋招20230826;

import java.util.HashMap;
import java.util.Scanner;
import java.util.HashSet;

public class 塔子哥的构造 {
    //10 AC 5TLE.暴力循环还是差点意思。 //下面有15AC的
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];

        //从1开始到n
        for(int i = 1; i<= n;i++){
            a[i] = sc.nextInt();
        }
        //构造数组b使得 (ai + bi) % i == 0
        //且b中元素各不相同
        //为了去重先来个set
        HashSet<Integer> set  = new HashSet<>();
        //遍历a[i]
        for(int i = 1; i <= n;i++){
            //i从1到1e9
            int bi = 1;
            //如果已经出现过就bi++;
            //暴力法还是会TLE 5个用例。
            while(set.contains(bi)|| (bi + a[i]) % i != 0){
                //如果已经重复了或者不满足mod条件则继续++
                bi++;
            }
            //不重复又满足条件了。ok
            b[i] = bi;
            set.add(bi);//添加到set
        }
        for(int i = 1;i<=n;i++){
            if(i != n)
            System.out.print(b[i] + " ");
            else
            System.out.print(b[i]);
        }
    }
    //这个(a / i + 1) * i - a;是怎么设计的？
    public static void test(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int a = sc.nextInt();
            //下面这两段话就是本题的关键
            int b = (a / i + 1) * i - a;
            while (hashMap.containsKey(b)) {
                b += i;
            }
            hashMap.put(b, 1);
            System.out.print(b + " ");
        }
        System.out.println();
    }
}
