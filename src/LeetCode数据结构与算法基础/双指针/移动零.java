package LeetCode数据结构与算法基础.双指针;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 移动零
 * @Time 2021/9/21  0:35
 */

/*给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

        示例:

        输入: [0,1,0,3,12]
        输出: [1,3,12,0,0]
        说明:

        必须在原数组上操作，不能拷贝额外的数组。
        尽量减少操作次数。

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/move-zeroes
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class 移动零 {

    //最简单就是把零元素全排除，方法就是拿后面的数填上就可以剩下，最后末尾补零就可以
    //但有点钻牛角尖 不是移动0
    @Test
    public void test(){
      int nums[] = {-1,-2,0,0,3,23,3};
      moveZero(nums);
    }

    public void moveZero(int nums[]){

        //法一，最容易想到的覆盖。
        //也是双指针，slowIndex fastIndex只要不是0就不断后移
        //遇到0 则slowIndex停留在nums = 0的位置，等fastIndex遇到不为0的数
        //就直接覆盖掉0，然后一起后移。还能保证相对位置不变。

        //简单说就是，用slow记住0的位置，用fast去找不是0的元素。
        //只要fast不为0，就用fast的值覆盖掉slow，然后slow才++，最后把slow后面的数都改为0就可以了。
        //虽然时间复杂度是O(n) 空间复杂度是O(1) 但实际上两者性能都比较差。
        int slowIndex = 0;
        int fastIndex = 0;
        while(fastIndex < nums.length){
            if (nums[fastIndex] != 0){ //不等于0，把后者覆盖前者，一起后移
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
            ++fastIndex;//等于0 fastIndex向后推，slowIndex不动，记录下0的位置
        }
        for (int i = slowIndex; i < nums.length; i++) {
            nums[i] = 0;
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }

    }



    //法二 交换
    //右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
    public void moveZero2(int[] nums){

        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
