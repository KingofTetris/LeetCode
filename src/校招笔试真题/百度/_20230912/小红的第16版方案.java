package 校招笔试真题.百度._20230912;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/8/22
 */
public class 小红的第16版方案 {


    /**
     * 模拟就过了?
     * @param args
     */
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n =  sc.nextInt();
        int m = sc.nextInt();

        //每个人的愤怒阈值数组
        int[] yz = new int[n];
        for(int i = 0;i < n;i++){
            yz[i] = sc.nextInt();
        }


        //每次修改让[L,R]上的人愤怒+1
        int[][] interval = new int[m][2];
        for(int i = 0;i < m;i++){
            interval[i][0] = sc.nextInt();
            interval[i][1] = sc.nextInt();
        }
        //阈值数组
        int[] angryNow = new int[n];
        int version = 0;
        for(int i = 0;i < m;i++){
            int L = interval[i][0] - 1;
            int R = interval[i][1] - 1;
            boolean flag = false;
            for(int j = L;j <= R ;j++){
                angryNow[j]++;
                if(angryNow[j] > yz[j]){
                    flag = true;
                    break;
                }
            }
            //如果有人怒了，直接提交该版
            if(flag){
                break;
            }
            version++;
        }
        //
        System.out.println(version);
    }
}
