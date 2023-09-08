package 校招笔试真题.携程._20230907;

/**
 * @author by KingOfTetris
 * @date 2023/9/7
 */
import java.util.*;

public class 游游的排列 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        int[] origin = new int[n]; //origin用来记录初始值
        for (int i = 0; i < n; i++) {
            origin[i] = i + 1;
            nums[i] = i + 1;
        }
        boolean isOriginalPermutation = false;
        int res = 0;
        while (!isOriginalPermutation){
            int[] nextPermutation = nextPermutation(nums);
            //当回到origin 停止排列
            if (Arrays.equals(origin,nextPermutation )) {
                isOriginalPermutation = true;
            } else{
                int flag = 0;
                for (int i = 0; i < n - 1; i++) {
                    if (isPrime(nextPermutation[i] + nextPermutation[i + 1])){
                        flag = 1;
                        break;
                    }
                }
                if (flag == 0) res++;
            }
        }
        System.out.println(res);
    }

    private static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    //下面这个全排列模板要记下来!!!!
    public static int[] nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
        return nums;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
