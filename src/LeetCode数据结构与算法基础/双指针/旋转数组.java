package LeetCode数据结构与算法基础.双指针;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 旋转数组
 * @Time 2021/9/20  10:53
 */
/*给定一个数组，将数组中的元素向右移动k个位置，其中k是非负数。



        进阶：

        尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
        你可以使用空间复杂度为O(1) 的原地算法解决这个问题吗？


        示例 1:

        输入: nums = [1,2,3,4,5,6,7], k = 3
        输出: [5,6,7,1,2,3,4]
        解释:
        向右旋转 1 步: [7,1,2,3,4,5,6]
        向右旋转 2 步: [6,7,1,2,3,4,5]
        向右旋转 3 步: [5,6,7,1,2,3,4]
        示例2:

        输入：nums = [-1,-100,3,99], k = 2
        输出：[3,99,-1,-100]
        解释:
        向右旋转 1 步: [99,-1,-100,3]
        向右旋转 2 步: [3,99,-1,-100]


        提示：

        1 <= nums.length <= 2 * 104
        -231 <= nums[i] <= 231 - 1
        0 <= k <= 105

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/rotate-array
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class 旋转数组 {
    @Test
    public void test(){

        //时间复杂度是  全部交换两次 所以是O(2n) 稍微要慢点
        //还要考虑k>nums.length的情况
        //用取余来解决 可以想到 当移动超过数组长n时 每移动n次就变回原数组 无论左右都一样
        //所以用k = k%nums.length 可以完美解决这个问题，就不会有溢出的error了
        int[] nums = {1,2,3,4,5};
        int k = 2;
//        k = k%nums.length;
//        System.out.println(k);
//        reverseInt(nums,k);
        rotate2(nums,k);
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }
    }

  /*  //法一 类似字符串旋转，r1 r2分别转一次，最后整体一起转
    public void  reverseInt(int[] nums,int k){
        int n =nums.length;
        //要注意一下边界
        //n-k-1不能是负数
        reverse(nums,0,n-k - 1); //注意前半部分是 n- k -1
        reverse(nums,n-k,n-1);
        reverse(nums,0,n-1);
    }

    public void reverse(int[] nums,int start,int end ){
        int temp;
        for(;start<end;){
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }*/

//    法2：空间换时间，把nums[i] 放在移动后正确的位置上
//    int newArr[]  i+k % n 放在newArr里，最后复制回nums即可
    public void rotate2(int[] nums,int k){
        int n = nums.length;
        int[] newArr = new int[n];
        for (int i = 0; i < n; i++) {
            newArr[(i+k)%n] = nums[i];
        }
        //arraycopy 从newArr的0位置开始，复制n个元素 给nums，nums同样从0开始
        //其实这个方法也可以用在自己赋值给自己
        System.arraycopy(newArr,0,nums,0,n);
    }
}
