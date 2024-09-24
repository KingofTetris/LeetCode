package 校招笔试真题.得物._20240924;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class 小红的大小写字母 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = sc.nextInt();
        int k = sc.nextInt();
        String s = sc.next();
        char[] arr = s.toCharArray();
        //先记录大小写的数量
        int bigLetter = 0;
        int smallLetter = 0;
        for (int i = 0; i < arr.length; i++) {
            if (Character.isUpperCase(arr[i])){
                bigLetter++;
            }
            else{
                smallLetter++;
            }
        }
        //如果小写的数量大于等于k
        if (smallLetter >= k){
            System.out.println(bigLetter + k);
            return;
        }
        //如果小写数量不够 eg: k = 3,sl = 0
        //那么就会出现大写变小写的情况

        //先把所有字母都变成大写
        int remainTime = k - smallLetter;
        //还剩下remainTime次 如果是偶数
        //那么 A -> a -> A 还是一样
        if (remainTime % 2 == 0){
            System.out.println(bigLetter + smallLetter);
        }
        //奇数 A -> a 少一次。
        else {
            System.out.println(bigLetter + smallLetter - 1);
        }
    }
}
