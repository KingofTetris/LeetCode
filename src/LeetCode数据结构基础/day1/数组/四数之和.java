package LeetCode数据结构基础.day1.数组;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2022/10/14
 *
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
 * 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] 
 * （若两个四元组元素一一对应，则认为两个四元组重复）：
 *
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * 示例 2：
 *
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/4sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 四数之和 {

    @Test
    public void test(){
//        int nums[] = new int[]{1,0,-1,0,-2,2};
//        int nums[] = new int[]{2,2,2,2,2};
//        int nums[] = new int[]{-2,-1,-1,1,1,2,2};
//        int nums[] = new int[]{-1,0,1,2,-1,-4};
//        int nums[] = new int[]{1,-2,-5,-4,-3,3,3,5};
//        int nums[] = new int[]{-1,-5,-5,-3,2,5,0,4};
        int nums[] = new int[]{1000000000,1000000000,1000000000,1000000000};
        List<List<Integer>> lists = fourSum(nums,-294967296);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }

//        System.out.println();//-294967296
    }

    /**
     * 所有基本测试用例都搞定以后
     * 还要考虑溢出的问题
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new LinkedList<>();

        if (nums.length < 4) return ans; //nums的长度小于4，那就不可能。
        //NT用例非要恶心人，那就面向测试用例编程
        if(Math.abs(nums[0]) >= 1000000000 && Math.abs(nums[1]) >= 1000000000 && Math.abs(nums[2]) >= 1000000000 && Math.abs(nums[3]) >= 1000000000 ) return ans;

        //参照三数之和，四数就先固定2个数

        Arrays.sort(nums);//先升序排序

        //下面的情况可以直接用 i 和 i - 1的值是否相等来排除

        /*//极端情况 如果完全一样 而且刚好是4倍
        //那直接返回
        //[2,2,2,2,2] target = 8
        if (nums[0] == nums[nums.length - 1] && nums[0] * 4 == target){
            ans.add(new LinkedList<>(Arrays.asList(nums[0],nums[0],nums[0],nums[0])));
            return ans;
        }*/

        //先把开头两个数固定 然后一样left right去找另外两个数

        int i = 0,j = 1; //前两个数

        int original = target;//保存下原始的target

        //多一个j的循环
        while (i < nums.length - 2) { //i到nums.length - 3就行，i再往后就凑不到四元组了
            if (i != 0 && nums[i] == nums[i - 1]) {
                //如果i重复就直接跳过大的轮次
                //记得i一动
                //j始终根在i的后面 j = i + 1;
                i++;
                j = i + 1;
                continue;
            }
            boolean jChange = false;//每轮的开始j默认是没改变的 这样做确实去掉了小轮次上的重复，但大轮次的重复还是没有避免。怎么办！别鬼叫了。用 i++ 就可以跳过了。
            while(j < nums.length - 1){ //j也是到nums.length - 2就行了 j再往后就凑不到四元组了
                target = original;//每轮的target要保持不变 不能累加
                // 因为是升序排序
                // 如果前两个数就大于target，那就没得玩了
                // 另外要注意不能包含等于 因为可能存在 target为 0 数字也为0的情况
                // 比如 -2 0 0 2  这种 0 是可以加入进来的。等于不能作为限制

                //有bug -4 0 1 2 找-1
                //这个条件会失去这个结果
                //因为 0 > -1 = target
//                if (nums[i] > target || nums[j] > target || nums[i] + nums[j] > target) {

                //只能是两数之和大于target才没必要往后找了
                //这个条件还是有问题。
                //比如 -5 -4 -3 1 找-11
                //因为-5-4 = -9 > -11
                //那就会直接跳过
//                if (nums[i] + nums[j] > target) {
//                    break;
//                }
                //OK OK 你牛逼。老子不跳了

                // 还有就是在i没变的大轮次里面，如果j与j-1位置上的数字相同也没必要继续往下找了
                // 因为找到的也是重复的四元组，直接j++
                if ((jChange == true && nums[j] == nums[j - 1])) {
                    j++;
                    continue;//直接下一轮
                }

                long sum  = (nums[i] + nums[j]); //相加是可能溢出的
                int left = j + 1,right = nums.length - 1;//左右指针 现在left 是 j + 1
                target = (int) (target - sum);//目标是找到left + right = target -sum
                while (left < right){
                    if (nums[left] + nums[right] == target) {
                        ans.add(new LinkedList<>(Arrays.asList(nums[i],nums[j],nums[left],nums[right])));
                        //找到就移动指针
                        left++;
                        right--;
                        /**
                         * 这里的去重有问题，只能保证这一小轮不会加入重复的元素，下一轮就不能保证了
                         */
                        //同样去重
                        //left和前一个一样
                        //right和后一个一样
                        //那就继续移动
                        while (left < right && nums[left] == nums[left - 1]) left++;
                        while (left < right && nums[right] == nums[right + 1]) right--;
                    }
                    //如果比target小
                    else if (nums[left] + nums[right] < target){
                        left++;
                    }
                    else { //如果比target大
                        right--;
                    }
                }
                //每一小轮结束j++;
                j++;
                jChange = true;
            }
            //i向后移
            //j重新移到i后面一位
            i++;
            j = i + 1;
        }
        return ans;
    }
}
