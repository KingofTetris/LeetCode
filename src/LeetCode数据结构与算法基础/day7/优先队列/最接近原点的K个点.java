package LeetCode数据结构与算法基础.day7.优先队列;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author KingofTetris
 * @File 最接近原点的K个点
 * @Time 2021/10/30  11:06
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 *
 * （这里，平面上两点之间的距离是欧几里德距离。）
 *
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * 示例 2：
 *
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 *  
 *
 * 提示：
 *
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 最接近原点的K个点 {

    @Test
    public void test(){
        int[][] points = {{-2,-2},{3,1},{3,3},{0,1},{0,4}};
        int[][] kClosest = kClosest(points, 2);
        for(int[] i:kClosest){
            for(int ii : i){
                System.out.print(ii+"\t");
            }
            System.out.println();
        }
    }

    //一维数组 T 是int 基本数据类型当然不能降序Sort，要转化成Integer
    //二维数组 T是int[] 数组！是引用数据类型。不用转化了
    public int[][] kClosest(int[][] points, int k) {
        //按照a^2+b^2从小到大排序，然后返回前k个数即可。
        //要注意比较的元素是数组或者集合中存放的东西。
        //二维数组中存放的就是一维数组。也就是这个o
        Arrays.sort(points, Comparator.comparingInt(o -> (o[0] * o[0] + o[1] * o[1])));
        //copyOfRange(array,from,to)  左闭右开
//        这个方法在一些处理数组的编程题里很好用，效率和clone基本一致，
//        都是native method，比利用循环复制数组效率要高得多。
        //实际上底层用到了native方法 System.arraycopy
        //要看native方法的源码 在JDK里面是看不到的，得自行下载openJDK。就会发现底层实际上是c++
        return Arrays.copyOfRange(points,0,k);
    }
}
