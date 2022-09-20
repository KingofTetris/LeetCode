package 算法设计与分析.白给题;

/**
 * @author KingofTetris
 * @File Main
 * @Time 2021/10/12  14:02*/




/*
1、
nextInt()只读取数值，读取完后\n没有读取并且光标放在本行
Scanner sc=new Scanner(System.in);
int i=sc.nextInt()；
每次可以只输入一个数字，回车确认，例如：“123”，只能被一个nextInt读取。
也可以输入一组数字，例如：“123 456 789”，每个数字之间用空格（一个或多个空格）隔开，
可被多个nextInt()读取，
空格及回车不会被读取。（注：输入数字中不能夹杂除数字、空格、回车之外的其他字符，否则会报错）
*/



/*public class Main {
    static int n,m;
    static int N[] = new int[200005];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            N[i] = sc.nextInt();
        }
        sc.close();
        //注意N的长度是200005 不是3。其他全是0，sort以后输出前n个当然都是0
        //这里要用sort(N,a,b) a,b是下标
        Arrays.sort(N,0,n);


        int ans = BinarySearch(0,(N[n-1] - N[0])/(m-1));
        System.out.println(ans);
    }

    public static int fun(int res){
        int count = 0, k = 1;
        for (int i = 1; i < n; i++) {
            if (res + N[count] <= N[i]){
                count = i;
                k++;
            }
            if (k>=m) return 1;
        }
        return 0;
    }

    public static int BinarySearch(int left,int right){
        if(left>right) return 0;
        int mid = left + (right - left)/2;
        if(fun(mid) == 1) return Math.max(BinarySearch(mid+1,right),mid);
        else return BinarySearch(left,mid-1);
    }


}*/

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int n,m;
    static int N[] = new int[10005];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        for (int i = 0; i < n; i++) {
            N[i] = sc.nextInt();
        }
        sc.close();
        Arrays.sort(N,0,n);


        int ans = BinarySearch(0,(N[n-1] - N[0])/(m-1));
        System.out.println(ans);
    }

    public static int fun(int res){
        int count = 0, k = 1;
        for (int i = 1; i < n; i++) {
            if (res + N[count] <= N[i]){
                count = i;
                k++;
            }
            if (k>=m) return 1;
        }
        return 0;
    }

    public static int BinarySearch(int left,int right){
        if(left>right) return 0;
        int mid = left + (right - left)/2;
        if(fun(mid) == 1) return Math.max(BinarySearch(mid+1,right),mid);
        else return BinarySearch(left,mid-1);
    }


}
