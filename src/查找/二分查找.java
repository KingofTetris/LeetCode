package 查找;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2022/9/6
 */
public class 二分查找 {
    @Test
    public void test(){
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        Arrays.binarySearch(arr,10);
        System.out.println(binarySearch(arr, 10));
    }
    public int binarySearch(int[] arr,int target){ //返回目标在arr中的索引下标

        /**
         *  Arrays.binarySearch(arr,10);源码就是这样写的
         */
        int left = 0;
        int right = arr.length-1;
        while(left <= right){ //结束条件 left > right
            int mid = left + (right - left) /2; //中间元素下标
            if(target < arr[mid]){ //如果比mid小
                //去左侧找
                right = mid - 1;
            }else if(target > arr[mid]){//如果比mid大
                //去右侧找
                left = mid + 1;
            }else{
                //找到了
                return mid;
            }
        }
        //没找到
        return -1;
    }
}

