package LeetCode数据结构与算法基础.贪心;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author by KingOfTetris
 * @date 2024/9/10
 */
public class 用最少数量的箭引爆气球 {

    @Test
    public void test() {

        //为什么按照左端点从小到大排序
        //2147483646 反而比 -2147483646 小？？？
//        System.out.println(Integer.MAX_VALUE); 2147483647
//        System.out.println(Integer.MIN_VALUE);-2147483648
        int[][] points = {{-2147483646, -2147483645}, {2147483646, 2147483647}};
        int minArrowShots = findMinArrowShots(points);
        System.out.println(minArrowShots);
    }

    //每个气球都有一定的区间
    //箭从x轴向上射出，请问最少要多少之箭能射爆所有的气球。
    //其实就是在问你，这些区间的公共部分有多少个

    //[10,16],[2,8],[1,6],[7,12]
    //[1,6],[2,8],[7,12],[10,16]
    //显然第二个区间的左端点小于等于第一个区间的右端点，那么他们肯定是有重叠部分的
    //[Math.max(left1,left2),Math.min(right1,right2)]
    //也就是合并成一个[2,6],[7,12],[10,16]
    //同理[7,12],[10,16]
    //也能合并成[10,12]
    //最后剩下[2,6],[10,12]就完全不重叠了，那么就返回2
    public int findMinArrowShots(int[][] points) {
        if (points.length == 1) return 1;
        //先按照左端点从小到大排序
        //然后按照右端点从小达大排序
        Arrays.sort(points, (o1, o2) -> {
            /**
             * 这题有点的恶心的是，他故意整了pvt里面的用例
             * -2147483646 - 2147483646 会下溢出，必须用long类型
             * 最好判断diff 是 > 0 还是 < 0
             */
            long diff = (long) o1[0] - (long) o2[0];
            if (diff == 0) return o1[1] - o2[1];
            return diff > 0 ? 1 : -1;
        });

        //实际上并不需要真的去删除区间，只需要更新重叠区间的右端点就行了。
        int count = 1;  // points 不为空至少需要一支箭
        for (int i = 1; i < points.length; i++) {
            //如果当前区间的左端点>上一个区间的右端点，则不重叠
            if (points[i][0] > points[i - 1][1]) {  // 气球i和气球i-1不挨着，注意这里不是>=
                count++; // 需要一支箭
            }
            //如果重叠
            else {
                //更新右端点为小的那个。
                points[i][1] = Math.min(points[i][1], points[i - 1][1]); // 更新重叠气球最小右边界
            }
        }
        return count;

      /*  LinkedList<int[]> list = new LinkedList<>();
        for (int[] point : points) {
            list.add(point);
        }
        int index = 1;
        while (index < list.size()) {
            //可以合并
            int left1 = list.get(index - 1)[0];
            int right1 = list.get(index - 1)[1];
            int left2 = list.get(index)[0];
            int right2 = list.get(index)[1];
            if (left2 <= right1) {
                int[] overlap = {Math.max(left1, left2), Math.min(right1, right2)};
                //删掉两个区间
                list.remove(index);
                list.remove(index - 1);
                //在index - 1上插入一个重叠区间
                list.add(index - 1, overlap);
                //不能重头遍历，会超时。
                //发生了合并，index不用动，还是保持在原位
            } else {
                index++;
            }
        }
        //返回重叠区间的个数即可。
        return list.size();*/
    }
}
