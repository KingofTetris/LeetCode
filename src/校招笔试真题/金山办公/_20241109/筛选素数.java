package 校招笔试真题.金山办公._20241109;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 筛选素数 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n - 1];
        boolean[] isQC = new boolean[n - 1];
        for (int i = 2; i <= n; i++) {
            nums[i - 2] = i;
            isQC[i - 2] = false;
        }
        //只要还有数字能被 sqrt(n) 清零，就继续
        int count = 0;
        int k = 2;
        int start = 1;
        while(k <= Math.sqrt(n)){
            for (int i = start; i < n - 1; i++) {
                //如果能被整除，且还没被清零过
                if(nums[i] % k == 0 && !isQC[i]){
                    nums[i] = 0;
                    count++;
                    isQC[i] = true;
                }
            }
            k++;
            start++;
        }
        //最后返回不为0的数字和count
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) list.add(nums[i]);
        }
//        System.out.println(list);
        for (int i = 0; i < list.size(); i++) {
            if (i == (list.size() - 1)){
                System.out.print(list.get(i));
            }
            else {
                System.out.print(list.get(i) + " ");
            }
        }
        System.out.println();
        System.out.print(count);
    }
}
