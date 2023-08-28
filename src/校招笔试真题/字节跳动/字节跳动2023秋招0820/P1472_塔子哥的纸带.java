package 校招笔试真题.字节跳动.字节跳动2023秋招0820;

/**
 * @author by KingOfTetris
 * @date 2023/8/26
 */
import java.util.Scanner;
import java.util.Arrays;

//三个点将环分割成了3段，记录每一段减k后的值，负数之和就是要移动的次数
public class P1472_塔子哥的纸带 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-- > 0 ){
            int n = sc.nextInt();
            int k = sc.nextInt();
            int a1 = sc.nextInt();
            int a2 = sc.nextInt();
            int a3 = sc.nextInt();
            soluiton(n,k,a1,a2,a3);
        }
    }

    public static void soluiton(int n,int k,int a1,int a2,int a3){
        //移动最少的次数达到平衡状态，且首尾相邻。
        //给3位置排序
        int[] nums = new int[]{a1,a2,a3};
        Arrays.sort(nums);
        int gap1 = nums[1] - nums[0];//中间-最小
        int gap2 = nums[2] - nums[1];//最大-中间

        //因为这个带子是循环的，那么就要考虑最小和最大的循环距离
        //比如最小是1 最大是6 带子长度也是6，那么1 6的距离其实就是1
        //如果最小是2 最大是5 带子长度6 那么这个距离就是3
        // 那么这个循环距离就是 n - arr[2] + arr[0];
        int gap3 = n - nums[2] + nums[0]; //

        //如果条的长度/3 都要<k 也就是3个点刚好均分都没法满足条件，
        //那么不管你怎么移动，都不可能满足条件。
        if( n / 3 < k) System.out.println(-1);

            //如果有希望，那么
        else{
            long ans = 0;
            //gap1 < k 移动gap1到k 不管是移动min 还是mid
            if(gap1 < k) ans += k - gap1;
            //gap2 < k 移动gap2到k 不管是移动mid 还是max
            if(gap2 < k) ans += k - gap2;
            //gap3 < k 移动gap3到k 不管是移动max 还是min
            if(gap3 < k) ans += k - gap3;
            System.out.println(ans);
        }
    }
}
