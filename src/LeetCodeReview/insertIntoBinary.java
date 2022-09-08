package LeetCodeReview;

/**
 * @author KingofTetris
 * @File insertIntoBinary
 * @Time 2021/10/31  11:15
 */
public class insertIntoBinary {
    public int searchInsert(int[] nums, int target) {
        int n = nums.length - 1;
        int low = 0;
        int high = n;
        int mid = 0;
        while(low <= high){
            mid = (low + high)/2 + low;
            if (nums[mid] == target)
                return mid;
            if (nums[mid] > target)
                high = mid - 1;
            if(nums[mid] < target)
                low = mid + 1;
        }

        //target大于mid就放后面 mid + 1
        //小于就放前面mid
        return nums[mid] < target ? mid + 1 : mid;
    }
}
