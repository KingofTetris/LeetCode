package 校招笔试真题.美团._20230916;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/16
 */

//从数组中选出1个或者多个数字，设他们相与的和为res。
    //假设这个res能被2^m整除,求m的最大值。
    //比如，数组是[21,1,20,28]
    //可以取20，一个数不用和其他数字相与，res就=20，那么m的最大值就是4，以为4能整除20
    //也可以取，20和28，相与结果是28&20=20，res=20，这个结果也一样。m=4.
    //现在请你返回最大的m.


    //下面这个程序应该不对。为什么这个B能AC？
    //https://www.nowcoder.com/discuss/532661796222562304?sourceSSR=search
public class 小美的数组相与 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        scanner.close();
        Arrays.sort(a); // 对数组进行排序
        int ans = get(a[n - 1]); // 初始化ans为最后一个元素的m值,一般最后一个最大元素的m值就可能是最大的。
        int rec = a[n - 1]; // 初始化rec为最后一个元素
        //从尾巴倒着相遇，然后每次比较一次ans和rec
        for (int i = n - 2; i >= 0; i--) {
            rec &= a[i];
            ans = Math.max(ans, get(rec));
        }
        System.out.println(ans);
    }

    // 计算x的最大m值
    private static int get(int x) {
        int ret = 0;
        while (x != 0 && x % 2 == 0) {
            x /= 2;
            ret++;
        }
        return ret;
    }
}
