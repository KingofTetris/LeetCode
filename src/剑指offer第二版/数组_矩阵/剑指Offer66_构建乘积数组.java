package 剑指offer第二版.数组_矩阵;

/**
 * @Author KingofTetris
 * @Date 2022/9/19 11:01
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
 * 其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 
 *
 * B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 *
 * 示例:
 *
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 *  
 * 提示：
 *
 * 所有元素乘积之和不会溢出 32 位整数
 * a.length <= 100000 //(10^5)*(10^5) = 10^10 这个长度显然是不可能让你暴力求每个数的乘法的
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer66_构建乘积数组 {


    /**
     * 不让用除法
     * 写个矩阵你就懂了 把主对角线都标为1，因为不和自己相乘相当于把这个数当成1
     *                    A[0] A[1] A[2] A[3] A[4]
     *               B[0]  1     2   3    4    5
     *               B[1]  1     1   3    4    5
     *               B[2]  1     2   1    4    5
     *               B[3]  1     2   3    1    5
     *               B[4]  1     2   3    4    1
     *
     *           现在我们可以先算下三角形的乘积，然后再算上三角形的乘积
     *           这个操作可以用遍历两轮数组完成，所以复杂度是O(n) 不是暴力的O(n^2)
     *           两者相乘就是答案。
     * @param a
     * @return
     */
    public int[] constructArr(int[] a) {
        if (a == null || a.length == 0) return a;
        int len = a.length;
        int[] left = new int[len];
        int[] right = new int[len];
        left[0] = right[len - 1] = 1;

        //下三角
        for (int i = 1; i < len; i++) { //left[0] = 1，从1开始算
            left[i] = left[i - 1] * a[i - 1];//left[i] 下三角每次比上面多乘一个a[i - 1]
        }

        //上三角
        /**
         * 注意上三角倒过来从下面往上面算
         * 对应到right数组 就是从后往前算
         */
        for (int i = len - 2; i >= 0; i--) { //right[len - 1] = 1 从len - 2开始算
            right[i] = right[i + 1] * a[i + 1];  //right[i] 上三角每次比下面多乘一个a[i + 1]
        }

        int[] ans = new int[len];

        //最后把左右相乘就可以了
        for (int i = 0; i < len; i++) {
            ans[i] = left[i] * right[i];
        }
        return ans;

      /*  作者：jyd
        链接：https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/solution/mian-shi-ti-66-gou-jian-cheng-ji-shu-zu-biao-ge-fe/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
