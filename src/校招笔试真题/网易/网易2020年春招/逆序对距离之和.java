package 校招笔试真题.网易.网易2020年春招;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/8/30
 */


//小易给定一个1 - n的排列，希望你能求出这个序列中所有逆序对的距离和。
    //逆序对的距离是指∑(j-i)

    //暴力 AC 60%     100% 需要归并。
public class 逆序对距离之和 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][2];
        for(int i=0;i<n;i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = i;
        }
        long res = getAns(arr,n);
        System.out.println(res);
    }

    public static long getAns(int[][] arr,int n){
        int[][] tmp = new int[n][2];
        return reverse(arr,0,n-1,tmp);
    }

    public static long reverse(int[][] arr,int left,int right,int[][] tmp){
        if(left>=right)
            return 0;
        int mid = (left+right)>>>1;
        long leftDistance = reverse(arr,left,mid,tmp);
        long rightDistance = reverse(arr,mid+1,right,tmp);
        if(arr[mid][0]<=arr[mid+1][0])
            return leftDistance+rightDistance;
        long crossDistance = reverseCross(arr,left,mid,right,tmp);
        return crossDistance+leftDistance+rightDistance;
    }

    public static long reverseCross(int[][] arr,int left,int mid,int right,int[][] tmp){
        for(int i=left;i<=right;i++){
            tmp[i][0] = arr[i][0];
            tmp[i][1] = arr[i][1];
        }
        int i = left,j = mid+1;
        long count = 0;
        for(int k=left;k<=right;k++){
            if(i==mid+1){
                arr[k][0] = tmp[j][0];
                arr[k][1] = tmp[j][1];
                j++;
            }else if(j==right+1){
                arr[k][0] = tmp[i][0];
                arr[k][1] = tmp[i][1];
                i++;
            }else if(tmp[i][0]<=tmp[j][0]){
                arr[k][0] = tmp[i][0];
                arr[k][1] = tmp[i][1];
                i++;
            }else if(tmp[i][0]>tmp[j][0]){
                arr[k][0] = tmp[j][0];
                arr[k][1] = tmp[j][1];
                for(int l=i;l<=mid;l++){
                    count+=tmp[j][1]-tmp[l][1];
                }
                j++;
            }
        }
        return count;
    }

   /* 作者：paperman201910261445664
    链接：https://www.nowcoder.com/exam/test/72859958/submission?examPageSource=Company&pid=20790475&testCallback=https%3A%2F%2Fwww.nowcoder.com%2Fexam%2Fcompany%3FcurrentTab%3Drecommand%26jobId%3D100%26keyword%3D%E7%BD%91%E6%98%932020%26selectStatus%3D0&testclass=%E8%BD%AF%E4%BB%B6%E5%BC%80%E5%8F%91
    来源：牛客网*/
}
