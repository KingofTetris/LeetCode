package 每日一题;

import LeetCode数据结构基础.day3.链表.TwoWayListNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author KingofTetris
 * @Date 2022/7/20 13:08
 * 1260. 二维网格迁移 (所有元素右移一位)
 * 给你一个 m 行 n 列的二维网格 grid 和一个整数 k。你需要将 grid 迁移 k 次。
 *
 * 每次「迁移」操作将会引发下述活动：
 *
 * 位于 grid[i][j] 的元素将会移动到 grid[i][j + 1]。
 * 位于 grid[i][n - 1] 的元素将会移动到 grid[i + 1][0]。
 * 位于 grid[m - 1][n - 1] 的元素将会移动到 grid[0][0]。
 * 请你返回 k 次迁移操作后最终得到的 二维网格。
 *
 * 示例 1：
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * 输出：[[9,1,2],[3,4,5],[6,7,8]]
 * 示例 2：
 * 输入：grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
 * 输出：[[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
 * 示例 3：
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
 * 输出：[[1,2,3],[4,5,6],[7,8,9]]
 *
 * 提示：
 *
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 50
 * 1 <= n <= 50
 * -1000 <= grid[i][j] <= 1000
 * 0 <= k <= 100
 */
public class 二维网格迁移_2022_07_20 {

    @Test
    public void test(){
        int[][] grid = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        List<List<Integer>> lists = shiftGrid(grid, 3);

        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }
    }

    /**
     * 用双端循环链表模拟出来的屎山代码
     * 执行用时：5 ms, 在所有 Java 提交中击败了74.04%的用户
     * 内存消耗：42 MB, 在所有 Java 提交中击败了60.27%的用户
     * @param grid
     * @param k
     * @return
     */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {  //List<List<Integer>>

        List<List<Integer>> rows = new LinkedList<>(); //返回结果
        TwoWayListNode root = new TwoWayListNode();
        TwoWayListNode head ;
        head = root;//保证在头部
        int n = grid.length; //n行
        int m = grid[0].length;//m列

   /*      * 位于 grid[i][j] 的元素将会移动到 grid[i][j + 1]。
 * 位于 grid[i][n - 1] 的元素将会移动到 grid[i + 1][0]。
 * 位于 grid[m - 1][n - 1] 的元素将会移动到 grid[0][0]。
 *
 * 所有元素左移一位如何实现呢？借助循环链表
 * */

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                TwoWayListNode node = new TwoWayListNode(grid[i][j]);
                root.next = node;//root指向新节点
                node.pre = root;//node指向node
                root = root.next;//后移
            }
        }
        root.next = head.next;//尾节点的next指向头
        head.next.pre = root;//让头节点的pre指向尾 形成闭环

        //现在要左移k位,相当于用tail指定链表末尾，把tail像前移动k-1位。然后从这前k-1开始往后读就是左移了k位
        TwoWayListNode tail = root;//现在root就是尾巴

       /* int iii = 0;
        while (iii < n *m){
            System.out.print(tail.val + " ");
            tail = tail.next;
            iii++;
        }
        System.out.println();

        验证了一下，确实是9 1 2 3 4 5 6 7 8 双向链表搭建成功
        */

        k = k % (n*m);//首先用k对n*m取余 eg:k = 15和k=6在效果上是一样的。每移动n*m次等于没动。
        while ( k-1 > 0){//tail前移k-1位
            tail = tail.pre;
            k--;
        }//实际上这里K最小是1。
        if (k == 0){
            tail = tail.next;//这里是处理n*m倍的情况，等于没动
        }
        //现在tail指向的就是左移k位后的开头。然后从tail开始每m位一组塞入List就行了

        //TODO 为什么下面这段clear会出Bug??最后整个rows变成空
        /*int count = 0;
        for (int i = 0; i < n * m; i++) { //n*m次
            row.add(tail.val);
            count++;
            if (count == m){ //每次达到m个就清空一次
                rows.add(row);//每行添加进rows
                //然后把row清空
                row.clear();
                count = 0;
            }
            tail = tail.next;//指针后移
        }*/
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = tail.val;
                tail = tail.next;
            }
            int[] ints = grid[i];
            List<Integer> list = new LinkedList<>();
            for (int anInt : ints) {
                list.add(anInt);
            }
            rows.add(list);
        }

        return rows;
    }


    /**
     * 其实你都想到转化成一维的链表了。。
     * 为什么没想到旋转数组。。
     * k次右移后，元素grid[i][j]在该一维数组的下标变为 index_1 = (index + k) mod (m * n)
     * 对于的矩阵位置就是 [index_1/m][index_1%n]
     * 74.04%
     * 48.98%
     * @param grid
     * @param k
     * @return
     */
    public List<List<Integer>> shiftGrid2(int[][] grid, int k){
        int m = grid.length, n = grid[0].length;
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<Integer>();
            for (int j = 0; j < n; j++) {
                row.add(0);
            }
            ret.add(row);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index1 = (i * n + j + k) % (m * n);
                ret.get(index1 / n).set(index1 % n, grid[i][j]);
            }
        }
        return ret;
    }
}
