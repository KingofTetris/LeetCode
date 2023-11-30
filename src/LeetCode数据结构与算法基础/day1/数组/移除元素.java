package LeetCode数据结构与算法基础.day1.数组;

/**
 * @author by KingOfTetris
 * @date 2023/9/8
 */
public class 移除元素 {
    public static void main(String[] args) {

    }

    //不借助额外空间，原地删除val 返回删除后的数组大小
    //最简单的就是边删除边把后面的元素往前面移动，O(n^2)
    public static int removeElement(int[] nums,int val){
        //简单的办法是直接把不等于val的元素直接覆盖到前面 去填充被删除的元素的位置。
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i; //然后返回i即可，这样做的问题就是 i以后的元素都是0。
    }
}
