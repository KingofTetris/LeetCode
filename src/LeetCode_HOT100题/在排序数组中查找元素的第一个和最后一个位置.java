package LeetCode_HOT100题;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2022/10/17
 */
public class 在排序数组中查找元素的第一个和最后一个位置 {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int n = random.nextInt(10, 11);
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = random.nextInt(1, 10);
        }
        Arrays.sort(nums);
        for (int num : nums) {
            System.out.print(num + "\t");
        }
        System.out.println();
        int[] ints = searchRange(nums, 8);
        for (int anInt : ints) {
            System.out.print(anInt + "\t");
        }
    }

    //先找>=target的第一个 找到第一个位置
    //再找>target的第一个  找到最后一个位置
    public static int[] searchRange(int[] nums, int target) {
        int l = search(nums, target);
        int r = search(nums, target + 1); //找到比target大的第一个元素，再-1就是target最后出现的序号
        //如果l根本就不等于target或者l==n
        //说明数组中根本不存在target.
        if (l == nums.length || nums[l] != target)
            return new int[]{-1, -1};
        //其实这里还有种变体
        // 如果不存在则插入到它应该在的位置。
        return new int[]{l, r - 1};
    }

    //找>=target的第一个 注意这个是左闭右开区间

    /**
     * 左闭右开的一些小优点
     *
     * 1.代码分支更少。将普通二分查找(实际上是3分，> = & < 3种情况)转为真二分查找
     * 2.边界条件更加简洁。无需在mid分割的左右区间边界上过多地讨论+-1，只需讨论一个分支
     * 3.阅读和编写友好。当查找区间只包含一个元素时，low和high不会重合
     *
     * 作者：9_SooHyun
     * 链接：https://www.jianshu.com/p/88333f1f02a7
     * 来源：简书
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int l = 0, r = nums.length; //左闭右开区间,条件就不是l <= r 而是l < r
        while (l < r) {
            int mid = (r + l) / 2; //简单写法，以前可能会有加法溢出的问题，python其实无所谓。
//            int mid = l + (r - l) / 2 ;//防溢出的写法，c/c++/java还是要这样写
            //当【mid大于或者等于target】时，target必在mid的左侧或mid位置，将high移动至mid
            if (nums[mid] >= target)
                r = mid;
            // 当【mid小于target】时，target必在mid的右侧，将low提高至mid+1d
            else
                l = mid + 1;
        }
        return l;
    }
}
