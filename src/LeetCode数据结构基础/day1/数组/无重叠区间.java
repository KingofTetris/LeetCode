package LeetCode数据结构基础.day1.数组;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author KingofTetris
 * @File 无重叠区间
 * @Time 2021/10/14  17:55
 */
/*
给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。

        注意:

        可以认为区间的终点总是大于它的起点。
        区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
        示例 1:

        输入: [ [1,2], [2,3], [3,4], [1,3] ]

        输出: 1

        解释: 移除 [1,3] 后，剩下的区间没有重叠。
        示例 2:

        输入: [ [1,2], [1,2], [1,2] ]

        输出: 2

        解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
        示例 3:

        输入: [ [1,2], [2,3] ]

        输出: 0

        解释: 你不需要移除任何区间，因为它们已经是无重叠的了。*/

public class 无重叠区间 {

    @Test
    public void test(){
        int[][] intervals = {{1,4},{4,8}};
        System.out.println(eraseOverlapIntervals(intervals));
    }

/*    贪心算法：按照每个区间结尾从小到大进行升序排序，优先选择结尾最短的区间，
    在它的后面才可能连接更多的区间（若两个区间有重叠部分，则应该优先保留结尾小的）。*/

   /* 初始化：记录排序后第一个区间的右边界大小（此时的右边界一定是最小的，该区间后面能够连接的区间更多），
   即 int rightEnd = intervals[0][1]。

    从排序后的第二个区间开始遍历：

    若当前遍历的区间的左边界的值intervals[i][0]>= 之前保存的 rightEnd 的值，
    则说明当前遍历的区间与右边界为 rightEnd 的区间没有重叠部分（满足条件），
    此时将当前遍历的区间的右边界的值赋值给 rightEnd 继续往后寻找与它不重叠的区间；

    若当前遍历的区间左边界的值 intervals[i][0] 之前保存的 rightEndrightEnd 的值，
    则说明当前遍历的区间与右边界为 rightEnd 的区间有重叠部分（不满足条件）并且重叠区间个数 +1，
    即 count++。

    对于第一个区间 [1,2]：intervals[0][0]=1；intervals[0][1]=2。*/


    /**
     * Arrays.sort 时间复杂度O(nlogn)  栈空间O(logn)
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {

        int n = intervals.length;
        if (n == 0) return 0;

      /*  Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });*/

        //用lamda表达式直接代替上面的匿名内部类
        //按照o[1]也就是左端点升序排序，如果前面加个负号就是降序排序 o -> -o[1]
        Arrays.sort(intervals,Comparator.comparingInt(o -> o[1]));

        int count = 0; // 用来记录重叠区间的个数
        int rightEnd = intervals[0][1]; //记录上一个区间的右端点
        for (int i = 1;i < n;i++) {

            if (intervals[i][0] >= rightEnd) { //如果下个区间的左端点大于等于上个区间的右端点，说明这两个区间是不重叠的
                rightEnd = intervals[i][1];//合并取下新的右端点，重复这个过程。
            } else { //如果下个区间的左端点小于上个区间的右端点，那必然是重叠的，count++;
                count++;
            }
        }
        return count;
    }

}
