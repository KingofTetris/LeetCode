package LeetCode数据结构与算法基础.双指针与滑动窗口;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author KingofTetris
 * @File 两数之和ii_输入有序数组
 * @Time 2021/9/21  10:07
 */

// 关闭了 help-->find action-->registry-->editor.show.special.chars选项
    //特殊字符就不会显示出unicode编码了
/*给定一个已按照 非递减顺序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。

        函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。

        你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。

         
        示例 1：

        输入：numbers = [2,7,11,15], target = 9
        输出：[1,2]
        解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
        示例 2：

        输入：numbers = [2,3,4], target = 6
        输出：[1,3]
        示例 3：

        输入：numbers = [-1,0], target = -1
        输出：[1,2]
         

        提示：

        2 <= numbers.length <= 3 * 104
        -1000 <= numbers[i] <= 1000
        numbers 按 非递减顺序 排列
        -1000 <= target <= 1000
        仅存在一个有效答案

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class 两数之和ii_输入有序数组 {

    @Test
    public void test(){
        int[] numbers = {2,7,11,15,22,311};
        int target = 9;
        int[] result = twoSum(numbers,target);
        for (int i : result) {
            System.out.println(i);
        }
    }

    //法一：最简单就是暴力破解，两重循环。但是没利用到有序这个特征 不写出来了
    //法二：哈希表法，用target - nums[i] 找对应的数 是否在表中 比暴力破解快很多 但也没考虑有序
    public int[] twoSum(int[] numbers,int target){
        Map<Integer,Integer> hashTable = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            if (hashTable.containsKey(target - numbers[i])){
                //map的get(key)方法会返回对应的value，我们把value设为数组下标i
                //实际上就是把数组map化
                return new int[]{hashTable.get(target - numbers[i]),i + 1};
            }
            //不包含就把当前元素和下标保存起来。
            hashTable.put(numbers[i],i + 1);
        }
        return new int[0];//没有就返回空数组
    }

    //法三：利用有序这个特征，实际上我们是要找 target - nums[i] 对应的数，所以可以用二分查找提高效率
    //实际上和法二比也没有提高多少。
    public int[] twoSum2(int[] numbers,int target){
        //fori相当于先固定一个numbers[i],然后去右边找对应的数字
        for (int i = 0; i < numbers.length; i++) {
            int low = i + 1,high = numbers.length - 1;
            while (low <= high){
                //记住mid = (high - low) / 2 + low 和 mid = (low + high) /2 一般情况下其实效果一样
                //但前一种写法可以防溢出
                //具体详见下面博文
                // https://blog.csdn.net/weixin_44556968/article/details/110288725?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.no_search_link&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-1.no_search_link
                int mid = (high - low) / 2 + low;
                if(numbers[mid] == target - numbers[i]){
                    return new int[]{i + 1,mid + 1};
                }
                else if(numbers[mid] > target - numbers[i]){
                    high = mid - 1;
                }
                else{
                    low = mid + 1;
                }
            }
        }
        return new int[0];//失败返回空数组
    }

    //法四，双指针 直接一个头指针，一个尾指针
    //两数相加，如果小了就把前面的调大
    //如果大了就把后面的调小。其实比较好想吧。。。
    //而且最快。这就是利用有序可以做出的操作。
    public int[] twoSum3(int[] numbers,int target){
        int low = 0, high = numbers.length - 1;
        while (low < high) {
            int sum = numbers[low] + numbers[high];
            if (sum == target) {
                return new int[]{low + 1, high + 1};
            } else if (sum < target) {
                ++low;
            } else {
                --high;
            }
        }
        return new int[]{-1, -1};
    }
}
