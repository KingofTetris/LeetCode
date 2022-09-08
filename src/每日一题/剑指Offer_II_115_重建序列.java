package 每日一题;

import org.junit.Test;

import java.util.*;

/**
 * @Author KingofTetris
 * @Date 2022/7/26 12:18
 * 给定一个长度为 n 的整数数组 nums ，其中 nums 是范围为 [1，n] 的整数的排列。
 * 还提供了一个 2D 整数数组 sequences ，其中 sequences[i] 是 nums 的子序列。
 * 检查 nums 是否是唯一的最短 超序列 。最短 超序列 是 长度最短 的序列，
 * 并且所有序列 sequences[i] 都是它的子序列。对于给定的数组 sequences ，可能存在多个有效的 超序列 。
 *
 * 例如，对于 sequences = [[1,2],[1,3]] ，有两个最短的 超序列 ，[1,2,3] 和 [1,3,2] 。
 * 而对于 sequences = [[1,2],[1,3],[1,2,3]] ，唯一可能的最短 超序列 是 [1,2,3] 。[1,2,3,4] 是可能的超序列，但不是最短的。
 * 如果 nums 是序列的唯一最短 超序列 ，则返回 true ，否则返回 false 。
 * 子序列 是一个可以通过从另一个序列中删除一些元素或不删除任何元素，而不改变其余元素的顺序的序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,3], sequences = [[1,2],[1,3]] //判断同时将[1,2],[1,3]作为子序列的最短序列是否唯一就是nums
 * [1,2,3] [1,3,2]明显不唯一。
 * 输出：false
 * 解释：有两种可能的超序列：[1,2,3]和[1,3,2]。
 * 序列 [1,2] 是[1,2,3]和[1,3,2]的子序列。
 * 序列 [1,3] 是[1,2,3]和[1,3,2]的子序列。
 * 因为 nums 不是唯一最短的超序列，所以返回false。
 * 示例 2：
 *
 * 输入：nums = [1,2,3], sequences = [[1,2]]
 * 输出：false
 * 解释：最短可能的超序列为 [1,2]。
 * 序列 [1,2] 是它的子序列：[1,2]。
 * 因为 nums 不是最短的超序列，所以返回false。
 * 示例 3：
 *
 * 输入：nums = [1,2,3], sequences = [[1,2],[1,3],[2,3]]
 * 输出：true
 * 解释：最短可能的超序列为[1,2,3]。
 * 序列 [1,2] 是它的一个子序列：[1,2,3]。
 * 序列 [1,3] 是它的一个子序列：[1,2,3]。
 * 序列 [2,3] 是它的一个子序列：[1,2,3]。
 * 因为 nums 是唯一最短的超序列，所以返回true。
 *  
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 10^4
 * nums 是 [1, n] 范围内所有整数的排列
 * 1 <= sequences.length <= 10^4
 * 1 <= sequences[i].length <= 10^4
 * 1 <= sum(sequences[i].length) <= 10^5
 * 1 <= sequences[i][j] <= n
 * sequences 的所有数组都是 唯一 的
 * sequences[i] 是 nums 的一个子序列
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ur2n8P
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer_II_115_重建序列 {

    @Test
    public void test(){
        int n = 3;
        int[]  nums = new int[n];
        for (int i = 0; i < n; i++) {
         nums[i] = i + 1;//1-n
        }
//        sequenceReconstruction(nums, new int[][]{{1}, {2, 3}});
       int[][] sequences = new int[][]{{1,2},{1,3},{2,3}};
        System.out.println( sequenceReconstruction(nums, sequences));
    }


    /**
     * nums就是从1到n的顺序排列 [1,2,....,n]
     * 子序列只要求前后顺序相同可以，不用连续
     * 因为序列可以确定节点出现的先后顺序，所以可以采用拓扑排序的思想
     * 让先出现的节点指向后出现的节点。构建DAG图
     * 然后让入度为0的点入队，同时检查队列长度是否为1，同时出现2个以上入度为0的则相对顺序就可以调换
     * 则一个nums是不可能满足唯一的。
     * @param nums
     * @param sequences
     * @return
     */
    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        //记录邻接表
        Map<Integer, LinkedList<Integer>> adjacency = new HashMap<>();//用Linked保证加入的顺序
        int[] inDegrees = new int[nums.length + 1];//把所有点的入度初始为0 //n+1只是为了从1开始方便看

        for (int i = 0; i < sequences.length; i++) {
            int[] sequence = sequences[i];
            for (int j = 0; j < sequence.length - 1; j++) {
                int from = sequence[j];
                int to = sequence[j + 1];

                if (adjacency.containsKey(from) && adjacency.get(from).contains(to)){
                    continue;//已经算过了 就不添加了
                }
                adjacency.putIfAbsent(from,new LinkedList<>());//如果还没有这个点的记录，就添加一个空的列表
                adjacency.get(from).add(to);//把to放进去，同时要记得to的入度+1
                inDegrees[to]++;
            }
        }

        //这样邻接表和入读表就建立好了
        Queue<Integer> queue = new LinkedList<>();//建立队列
        for (int i = 1; i < inDegrees.length; i++) { //按顺序入队，如果Sequences里面包含单个顺序[1]，那它虽然没进入上面的循环，但也由于数组的初始化而为0
            if (inDegrees[i] == 0){
                queue.add(i);//添加入度为0的点
            }
        }
        while (!queue.isEmpty()){//如果queue不为空就一直删除入度为0的点
            if (queue.size() > 1) return false;//每次入队后，入度为0的点不止一个那相对顺序就不唯一，直接false;
            int from = queue.poll();//队头元素出队 队列长度-1
            LinkedList<Integer> toList = adjacency.get(from);//取出队头元素指向的点列表，删除from-to的每条边
            if (toList == null) continue; //如果这个点根本没有指向下一个点比如 [1] 那就继续。
            for (Integer to : toList) {
                inDegrees[to]--;//入度-1;
                if (inDegrees[to] == 0){ //减完变0记得入队
                    queue.offer(to);// 入队
                }
            }
        }
        return true;//完全符合
    }


    /**
     * 还可以再优化一点，由于题目的条件做了很限制。其实整个题目背后就是验证DAG里面有没有一条路径刚好是nums
     * @param nums
     * @param sequences
     * @return
     */
    public boolean sequenceReconstruction2(int[] nums, int[][] sequences){
        Map<Integer, Set> d = new HashMap<>();//这里可以不写明是<Integer>类型，等初始化的时候写就行了，多态！

        /**
         * 同样也是初始化邻接表
         */
        for(int[] x : sequences){
            for(int i = 1; i < x.length; i++){
                d.putIfAbsent(x[i-1], new HashSet<Integer>());
                d.get(x[i-1]).add(x[i]);
            }
        }

        for(int i = 1; i < nums.length; i++){
            //从1开始到n,如果[i-1] 没有指向[i]的路 ，那就寄
            if(!d.getOrDefault(nums[i-1], new HashSet<Integer>()).contains(nums[i])) return false;
        }
        return true;
    }
}
