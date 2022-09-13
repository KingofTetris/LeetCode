package 剑指offer第二版.数组_矩阵;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author KingofTetris
 * @Date 2022/9/13 14:50
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 *
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 *
 * 示例 1：
 *
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * 示例 2：
 *
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 *  
 *
 * 限制：
 *
 * 1 <= target <= 10^5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer57_II_和为s的连续正数序列 {

    @Test
    public void test(){
        int res = (int) Math.sqrt(16);
        System.out.println(res);
    }
    /**
     * 枚举暴力法
     * 另外List里面放的都是引用数据类型，当然也包括数组类型
     * @param target
     * @return
     */
    public int[][] findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();
        int limit = target / 2; //取target的一半先，因为是升序数组，当数组超过target时，就不可能相加等于target了
        int sum = 0;
        for (int i = 1; i <= limit; i++) { //从1到limit
            for (int j = i; ; j++) {
                sum += j;
                if (sum > target){
                    sum = 0;//重置为0
                    break;//大于target就跳出本次循环
                }
                else if (sum == target){
                    int[] res = new int[j - i + 1]; //如果等于target那么长度就是 j - i + 1
                    for (int k = i; k <= j; ++k) { //从i开始到j填入res中
                        res[k - i] = k;//res的下标就可以写为 k - i 也就是从索引0开始赋值
                    }
                    list.add(res);//把res添加进来
                    sum = 0;
                    break;
                }
            }
        }

        return list.toArray(new int [list.size()][]); //学到了，指定行数就行。不用指定最大列数
    }


    /**
     * 双指针加 求和公式
     * 最简单的方式，也是最高效的方式
     * @param target
     * @return
     */
    public int[][] findContinuousSequence3(int target) {
            List<int[]> vec = new ArrayList<int[]>();
            for (int l = 1, r = 2; l < r;) {
                int sum = (l + r) * (r - l + 1) / 2;
                if (sum == target) {
                    int[] res = new int[r - l + 1];
                    for (int i = l; i <= r; ++i) {
                        res[i - l] = i;
                    }
                    vec.add(res);
                    l++;
                } else if (sum < target) {
                    r++;
                } else {
                    l++;
                }
            }
            return vec.toArray(new int[vec.size()][]);
    }
}
