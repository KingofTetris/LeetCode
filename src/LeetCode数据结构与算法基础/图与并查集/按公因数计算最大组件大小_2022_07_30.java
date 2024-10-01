package LeetCode数据结构与算法基础.图与并查集;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author KingofTetris
 * @Date 2022/7/30 14:21
 * 给定一个由不同正整数的组成的非空数组 nums ，考虑下面的图：
 * <p>
 * 有 nums.length 个节点，按从 nums[0] 到 nums[nums.length - 1] 标记；
 * <p>
 * 只有当 nums[i] 和 nums[j] 共用一个大于 1 的公因数时，nums[i] 和 nums[j]之间才有一条边。
 * <p>
 * 返回 图中最大连通组件的大小 。
 * <p>
 * 示例 1：
 * 4-6-15-35
 * 输入：nums = [4,6,15,35]
 * 输出：4
 * 示例 2：
 * <p>
 * 20-50 9-63
 * 输入：nums = [20,50,9,63]
 * 输出：2
 * 示例 3：
 * <p>
 * 下面的图很复杂，自己点进去看
 * 输入：nums = [2,3,6,7,4,12,21,39]
 * 输出：8
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i] <= 105
 * nums 中所有值都 不同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/largest-component-size-by-common-factor
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 按公因数计算最大组件大小_2022_07_30 {

    @Test
    public void test(){
        int[] nums = new int[]{20,50,9,63};
        System.out.println(largestComponentSize(nums));
    }
    /**
     * 暴力建图(实际上是建立邻接表)然后用邻接表DFS
     * 时间复杂度O(n^2logC)，其中C指的是nums中的每一个数，超时，超时原因主要在建图过程
     * @param nums
     * @return
     */
    public int largestComponentSize(int[] nums) {
        int n = nums.length;
        List<Integer> path[] = new List[n + 5];
        for (int i = 0; i < n; i++) {
            path[i] = new ArrayList<>();
        }
        /**
         * 建立邻接表 存入的是下标索引
         */
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (gcd(nums[i], nums[j]) > 1) {
                    path[i].add(j);
                    path[j].add(i);
                }
            }
        }


        int max = 0;//最大连同分量

        boolean used[] = new boolean[n + 5]; //visited数组

        for (int i = 0; i < n; i++) { //最外层这个保证每个点都可以访问到，就避免了多个组件的情况
            if (!used[i]) { //从节点a出发 如果a还没有被访问过
                int count = 1;
                used[i] = true;
                List<Integer> list = new ArrayList<>();
                list.add(i); //list存储这个连通分量
                for (int j = 0; j < list.size(); j++) { //list.size是动态变化的，每次加入一个邻居都会继续DFS下去
                    int a = list.get(j);//依次按顺序取出连通分量的节点
                    for (int k = 0; k < path[a].size(); k++) {
                        /**
                         * 遍历这个点的邻接表
                         */
                        int b = path[a].get(k);//节点a的邻居b
                        if (!used[b]) { //如果b没有被访问过
                            used[b] = true;
                            list.add(b);//添加进连通分量
                            count++;//同时count++
                        }
                    }
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }

    /**
     * 图论常用到的数据结构-------并查集
     * 它经常用于求连通分量，求最小生成树，求最近公共祖先LCA等等
     * 什么是并查集？https://www.bilibili.com/video/BV1jv411a7LK?spm_id_from=333.337.search-card.all.click&vd_source=299caa32bd4dc5f5ad17129611289250
     * @param nums
     * @return
     */
    public int largestComponentSize2(int[] nums){
        return 0;
    }
    int gcd(int a, int b) {
        int reminder = a % b;
        while (reminder != 0) {
            a = b;
            b = reminder;
            reminder = a % b;
        }
        return b;
    }
}


