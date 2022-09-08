package 剑指offer第二版.数组_矩阵;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author KingofTetris
 * @Date 2022/9/1 15:46
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * 示例 1:
 * 输入: [10,2]
 * 输出: "102"
 * 示例 2:
 * 输入: [3,30,34,5,9]
 * 输出: "3033459"
 * 提示:
 * 0 < nums.length <= 100
 * 说明:
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer45_把数组排成最小的数 {

    @Test
    public void test(){
        int[] nums = {11,12,13};
        String s = minNumber(nums);
        System.out.println(s);
    }
    /**
     * 类似字符串排序，这里用快速排序。
     * 当然其他排序也都可以。只是为了复习一遍快速排序。
     * @param nums
     * @return
     */
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
//        quickSort(strs,0,strs.length - 1);//手写定制的字符串数组快速排序
        //或者使用JDK API
        Arrays.sort(strs,(x,y) -> (x+y).compareTo(y+x) );//比较x+y和y+x的大小，小的排前面。JDK8的新特性 就不用再手写半天。
        String res = "";
        for (int i = 0; i < nums.length; i++) {
            res = res + strs[i];
        }
        return res;
    }

    private void quickSort(String[] strs, int start , int end) {
        /**
         * 快速排序的模板写上来先
         */
        //上来别的先别管，先把结束条件写上去。
        if (start > end) return; //start > end 就结束 比如 11,12,13 i=j=1,但是i - 1等于-1 那么start(0) > end(-1) 就结束了

        /**
         * 两个哨兵，一个基准
         */
        int i = start,j = end;
        //一般基准取为最左边的元素
        while (i < j){
            //定制字符串相加比较大小 还是和快速排序一样，比基准大的放基准后面，小的放前面
            //字符出的compareTo方法，先比较长度，再逐个字符比较，如果两个字符不相同返回 前一个字符减去后一个字符的ASCII码的差。
            while ((strs[j] + strs[start]).compareTo(strs[start] + strs[j]) >= 0 && i < j) j--;
            //从右往左找第一个 x+pivot >= pivot+x的 放基准后面
            while ((strs[i] + strs[start]).compareTo(strs[start] + strs[i]) <= 0 && i < j) i++;
            //从左往右找第一个 y+pivot <= pivot+y 的 放基准前面
            //交换两字符串位置
            swap(strs,i,j);
        }
        swap(strs,start,i);//交换到基准到最终位置。因为快速排序相当一开始给最左边挖了个坑，最后要把坑填上

        quickSort(strs,start, i - 1);
        quickSort(strs,i + 1, end);
    }

    private void swap(String[] strs,int i,int j){
        String tmp = strs[i];
        strs[i] = strs[j];
        strs[j] = tmp;
    }
}
