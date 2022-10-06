package 每日一题;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2022/10/6
 */
public class 三等分_2022_10_06 {


    /**
     * 1.三等分，则三个部分 1 的个数要相等，设 1 的个数为sum 如果 sum 不能被 3 整除，那就不能三等分
     *
     * 2.如果能整除，则每个部分都要有 partial = sum / 3 个 1
     *  那么我们尝试在数组中找 arr的第一个 1 的位置 first
     *  第 partial + 1 个 1 出现的位置 second
     *  第 2*partial + 1 个 1 出现的位置 third
     *  另外 [third,arr.length - 1]这个值是无法改变的。
     *
     *  设 len = arr.length - third,表示二进制的长度，接下来只要判断
     *  [first,first + len),[second,second + len),[third,third + len)是否相等
     *  前提是 first + len <= second,second + len <= third.
     *
     *  如果完全相同，那么答案就是 [ first + len - 1,second + len].
     *  如果sum = 0 (数组中全部都是0) ,那么直接返回 [0,2]。
     * @param arr
     * @return
     */
    public int[] threeEqualParts(int[] arr) {
        //找出1的个数
        int sum = Arrays.stream(arr).sum();
        //1不能3等分
        if (sum % 3 != 0) {
            return new int[]{-1, -1};
        }
        //全是0
        if (sum == 0) {
            return new int[]{0, 2};
        }

        int partial = sum / 3;
        int first = 0, second = 0, third = 0, cur = 0;

        //去找三个组里面有效1的三个位置
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                if (cur == 0) {
                    first = i;
                } else if (cur == partial) {
                    second = i;
                } else if (cur == 2 * partial) {
                    third = i;
                }
                cur++;
            }
        }


        //比较3个组值是否相等
        int len = arr.length - third;
        if (first + len <= second && second + len <= third) {
            int i = 0;
            while (third + i < arr.length) {
                if (arr[first + i] != arr[second + i] || arr[first + i] != arr[third + i]) {
                    return new int[]{-1, -1};
                }
                i++;
            }
            return new int[]{first + len - 1, second + len};
        }
        return new int[]{-1, -1};
    }

//    作者：LeetCode-Solution
//    链接：https://leetcode.cn/problems/three-equal-parts/solution/san-deng-fen-by-leetcode-solution-3l2y/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}
