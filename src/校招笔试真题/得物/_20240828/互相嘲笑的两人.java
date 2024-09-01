package 校招笔试真题.得物._20240828;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/8/29
 */
public class 互相嘲笑的两人 {

    //条件:数组前后两个元素比较，如果增量更多，减少更少，或者A增加B减少
    //A都能嘲笑B
    //那其实不都是增量吗?
    //ΔA = Ai - Ai-1
    //ΔB = Bi - Bi-1
    //只要Δ不相等就会发生嘲笑。
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A = new int[n];
        int[] B = new int[n];
        for(int i = 0;i < n;i++){
            A[i] = sc.nextInt();
        }
        for(int i = 0;i < n;i++){
            B[i] = sc.nextInt();
        }
        int deltaA = 0,deltaB = 0;
        int max = 1;//至少一开始是不会嘲笑的。s
        //寻找最长不嘲笑关卡长度
        int res = 1; //至少第一关不会发生嘲笑
        for(int i = 1;i < n;i++){
            deltaA = A[i] - A[i - 1];
            deltaB = B[i] - B[i - 1];
            if(deltaA == deltaB){
                res++;
                max = Math.max(res,max);
            }
            else{
                res = 1;
            }
        }
        System.out.println(max);
    }
}
