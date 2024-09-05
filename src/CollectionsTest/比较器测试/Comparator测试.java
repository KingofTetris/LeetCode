package CollectionsTest.比较器测试;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author KingofTetris
 * @File Comparator测试
 * @Time 2021/10/17  15:34
 */
public class Comparator测试 {
    @Test
    public void test(){
        int[][] intervals = {{1,2},{3,4},{5,9},{1,8},{1,4}};

        //lambda表达式可以自动推测出o1,o2的类型就是int[]
        Arrays.sort(intervals, (o1, o2) -> {
            //直接按右端点从大到小排序
            //从大到小加个-号就行
            if (o1[0] != o2[0])
            return (o1[0] - o2[0]);
            //如果右端点相同，再把左端点从小到大排。
            else
                return -(o1[1] - o2[1]);
        });

        System.out.println(Arrays.deepToString(intervals));
    }
}
