package LeetCode数据结构与算法基础.day7.堆;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

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
        int[][] kClosest = kClosest2(points, 2);
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

    /**
     * 上面直接排序的方法，面试官肯定不会满意，会让你再想想还有没有其他的方法
     * @param points
     * @param k
     * @return
     */
    public int[][] kClosest2(int[][] points, int k) {
        //按照左端点，建立最大堆。
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        //注意左端点是欧几里得距离
        //维护一个大小为K的大根堆
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{points[i][0] * points[i][0] + points[i][1] * points[i][1], i});
        }
        //那么其实就变成了从N个数里面选K个最小的。
        int n = points.length;
        //前面已经塞了从0到K-1个距离，现在继续往里面赛后面的N-K个距离
        for (int i = k; i < n; ++i) {
            int dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            //如果当前dist比堆顶距离小，那么就把他放进来，堆顶poll掉。
            if (dist < pq.peek()[0]) {
                pq.poll();
                pq.offer(new int[]{dist, i});
            }
        }
        int[][] ans = new int[k][2];
        for (int i = 0; i < k; ++i) {
            //最后把points[i]取出来就行了。
            ans[i] = points[pq.poll()[1]];
        }
        return ans;
    }

   /* 作者：力扣官方题解
    链接：https://leetcode.cn/problems/k-closest-points-to-origin/solutions/477916/zui-jie-jin-yuan-dian-de-k-ge-dian-by-leetcode-sol/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
