package LeetCode数据结构与算法基础.模拟;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author KingofTetris
 * @Date 2022/7/28 14:07
 * 给你一个整数数组 arr ，请你将数组中的每个元素替换为它们排序后的序号。
 *
 * 序号代表了一个元素有多大。序号编号的规则如下：
 *
 * 序号从 1 开始编号。
 * 一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。
 * 每个数字的序号都应该尽可能地小。
 *  
 *
 * 示例 1：
 *
 * 输入：arr = [40,10,20,30]
 * 输出：[4,1,2,3]
 * 解释：40 是最大的元素。 10 是最小的元素。 20 是第二小的数字。 30 是第三小的数字。
 * 示例 2：
 *
 * 输入：arr = [100,100,100]
 * 输出：[1,1,1]
 * 解释：所有元素有相同的序号。
 * 示例 3：
 *
 * 输入：arr = [37,12,28,9,100,56,80,5,12]
 * 输出：[5,3,4,2,8,6,7,1,3]
 *  
 *
 * 提示：
 *
 * 0 <= arr.length <= 10^5
 * -10^9 <= arr[i] <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/rank-transform-of-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 数组序号转换_2022_07_28 {

    @Test
    public void test(){
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) Math.floor(Math.random() * 101);// 0-100之间的数
        }
        int[] ints = alogorithmRequirment(arr);
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");
        }
    }

    /**
     * 首先用一个数组保存排序完的原数组，然后用一个哈希表保存各元素的序号，最后将原属组的元素替换为序号后返回。
     * 复杂度是O(nlogn)
     * 和你按照ti排序后，返回原来数组中的下标，有点类似但是不一样。
     * @param arr
     * @return
     */
    public int[] arrayRankTransform(int[] arr){
        int[] sortedArr = new int[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);//优雅的复制代码 arraycopy
        Arrays.sort(sortedArr);
        Map<Integer, Integer> ranks = new HashMap<>();
        int[] ans = new int[arr.length];
        for (int a : sortedArr) {
            if (!ranks.containsKey(a)) {
                ranks.put(a, ranks.size() + 1);//不包含就放进来rank.size() + 1 包含的话，就不动就可以了。
            }
        }
        /**
         *
         */
        for (int i = 0; i < arr.length; i++) {
            ans[i] = ranks.get(arr[i]);
        }
        return ans;
    }

    /**
     * 下面是按照ti从小到大排序后，返回原来数组中的下标
     * 但问题是双重循环 O(n^2) 时间复杂度高得吓人。
     * 你可以测试一下1W个点要排多久。
     * @param arr
     * @return
     */
    public int[] alogorithmRequirment(int[] arr){
        int[] sortedArr = new int[arr.length];
        System.arraycopy(arr, 0, sortedArr, 0, arr.length);//优雅的复制代码 arraycopy
        Arrays.sort(sortedArr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
       /* for (int i = 0; i < arr.length; i++) {
            System.out.print(sortedArr[i] + " ");
        }
        System.out.println();*/


        int[] ans = new int[arr.length];
        boolean[] duplicated = new boolean[arr.length];
        int count = 0;//每次遍历只能对应一个位置
        for (int i = 0; i < arr.length; i++) {//遍历排序数组
            for (int j = 0; j < arr.length;j++) {
                if (sortedArr[i] == arr[j] && duplicated[j] == false && count == 0){
                    ans[i] = j; //如果赋值过一次了就把这个位置标记为true，不要再记录它
                    duplicated[j] = true;//保证前面的先被标记为true
                    count++;
                }
            }
            count = 0;//每轮重新赋为0
        }
        return ans;
    }
}
