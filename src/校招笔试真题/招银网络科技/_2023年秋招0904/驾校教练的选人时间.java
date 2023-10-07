package 校招笔试真题.招银网络科技._2023年秋招0904;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author by KingOfTetris
 * @date 2023/9/4
 */

//某校教练有很多学员，但是只有一辆车，每天练车的时间是5-19
    //学员们前一天会提前将练车时间发给教练，教练需要让最多人练到车，请问他最多能选多少个人？
    //比如{5,11}, {6,9}, {8,10}, {8,13},{10,12},{10,14},{11,15},{13,16},{13,17},{17,19}这个安排。
    //他有很多种方案，但是每种都最多只能选择4个人。所以答案是4
//给你一组区间，请问这组区间,从5到19,最多能选出多少个区间包含在5-19内，且无重叠?
    //其实就是无重叠区间，按照以前的题目，我们要找出重叠的部分，然后把这些重叠部分删除就是不重叠的部分，就是答案。
    //TMD的为什么没想到？？！
public class 驾校教练的选人时间 {
    public static void main(String[] args) {
        int[][] intervals = {{5,11}, {6,9},
                {8,10}, {8,13},{10,12},{10,14},{11,15},{13,16},{13,17},{17,19}};
        int n = intervals.length;
        System.out.println(n - eraseOverlapIntervals(intervals));
    }
    public static int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        if (n == 0) return 0;
        //TODO 20240904 数组排序!!!! 他妈的，为什么想不起来。
        //按照右端点排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
      /*  for (int i = 0; i < intervals.length; i++) {
            for (int j = 0; j < intervals[i].length; j++) {
                System.out.print(intervals[i][j] + " ");
            }
            System.out.println();
        }*/
        int count = 0; // 用来记录重叠区间的个数
        int rightEnd = intervals[0][1]; //记录上一个区间的右端点,初始是最小的右端点，才能保证找到最长的非重叠区间
        for (int i = 1;i < n;i++) {
            if (intervals[i][0] > rightEnd) { //如果下个区间的左端点大于上个区间的右端点，说明这两个区间是不重叠的
                rightEnd = intervals[i][1];//取新的右端点，重复这个过程即可
            }else { //如果下个区间的左端点小于上个区间的右端点，那必然是重叠的，count++;
                for (int i1 = 0; i1 < 2; i1++) {
                    System.out.print(intervals[i][i1] + "\t");
                }
                System.out.println();
                count++;
            }
        }
        return count;
    }

}
