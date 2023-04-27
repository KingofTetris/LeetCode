package 校招真题.小红书.小红书春招0409;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/4/12
 * <p>
 * 塔子哥是一位优秀的化学家，他的研究领域是配制各种化学试剂。
 * 今天，他的研究重点是一种特殊的化学溶液。这种溶液需要通过合并其他的多种溶液来制备，以达到理想的浓度和体积。
 * <p>
 * 在实验室里，塔子哥看到了 n 种溶液，每一种都有无限多，第i种溶液的体积为xi，里面含有yi单位的该物质，
 * 他想要用这些溶液来制备出一个体积恰好为C 的溶液，且尽量浓，使得其中所含有的该物质数量尽可能多。
 * <p>
 * 但是，这个过程并不容易。因为当两个瓶子的体积相等时，他们合并的过程中会发生化学反应，导致物质含量增加 X 单位，
 * 也就是 两个xi融合，物质数量变成了 2yi+X ，所有其实选中n瓶相同溶液那么最好的质量就是 n*yi + (n-1)*X
 * 这也就意味着，如果选择了某两种体积相等的溶液进行合并，可能会获得更高的物质含量。
 * <p>
 * 因此，为了制备出更浓的溶液，塔子哥需要仔细考虑每一步的操作。
 * 最终，经过反复的试验和计算，塔子哥终于成功地制备出了体积恰好为C 的溶液，
 * 并且其中所含有的该物质数量也达到了最大值。他非常开心，因为他的努力得到了回报。
 * 现在，他想请你告诉他，这个溶液中所含有的该物质数量最多是多少。
 * <p>
 * 输入描述
 * 第一行三个整数 n, X , C
 * 第二行n个整数 x1,x2,...,xn 中间用空格隔开
 * 第三行n个整数 y1,y2,...,yn 中间用空格隔开
 * 对于所有数据， 1<= n,X,C,yi <= 1000, 1<= xi <= C数据保证至少存在一种方案能够配制溶液体积恰好等于C 的溶液。
 * <p>
 * 输出描述
 * 输出一个整数，表示物质含量最多是多少。
 * <p>
 * 样例
 * 输入
 * <p>
 * 3 4 16
 * 5 3 4
 * 2 4 1
 * 输出
 * <p>
 * 29
 */
public class 融合试剂 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), X = sc.nextInt(), C = sc.nextInt();
        int[] vol = new int[n];//n种溶液的体积xi
        int[] matter = new int[n];//n种溶液对应的物质数量yi
        for (int i = 0; i < n; i++) {
            vol[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            matter[i] = sc.nextInt();
        }
        //并没有告诉你xi互不相同吧，如果相同就不能用hashmap来存了
        int solution = solution(vol, matter, X, C);
        System.out.println(solution);
    }


    /**
     * 动态规划 其实根据下面的思路你都已经想的差不多了。你能想到方案数
     * 为什么不能想到当前方案对应的质量呢？
     * 让dp[i]表示容积为i的时候最大物质最大数量
     * 那么dp[i]是由最后两步决定的，为什么是两步，是因为题目两瓶一样的溶液会增加单位质量。
     * 也就是  dp[i - j] + dp[j] + (X if j = i - j) //每个方案的公式
     * 那么最大的数量 dp[i] = max(dp[i], dp[i - j] + dp[j] + (X if j = i - j)) 每次都选最大的
     * j代表溶液的体积
     * <p>
     * 有了状态转移方程，那么初始值是多少？
     * dp[0 -> (min - 1)] = 0
     * 其余的 dp[xi] = max( dp[xi] ,yi) 因为xi有可能也需要dp
     * 比如输入的xi是2，3，6
     * 那么6就可以由于2，3去凑
     *
     * @param x
     * @param c
     * @return
     */
    private static int solution(int[] vol, int[] matter, int x, int c) {
        int dp[] = new int[c + 1];
        for (int i = 0; i < vol.length; i++) {
            // 其余的 dp[xi] = max( dp[xi] ,yi) 因为xi有可能也需要dp
            //不能直接dp[xi] = yi
            //但是这个真的有用吗?dp[xi]一开始不都是0，max不一定是yi吗。
            //但是不这样写有两个用例过不去。难顶。
            dp[vol[i]] = Math.max(dp[vol[i]], matter[i]);
        }
        for (int i = 1; i <= c; i++) {
            //枚举j的所有可能性
            for (int j = 1; j < i; j++) {
                //如果j或者i-j凑不出来就直接跳过
                if (dp[j] == 0 || dp[i - j] == 0) continue;
                dp[i] = Math.max(dp[i], dp[j] + dp[i - j] + (j == i - j ? x : 0));//每次选最大的。所以前面加个Math.max
            }
        }
        return dp[c];
    }


    //每种溶液是无限的，怎么合成体积为C的溶液？
    //同时要求物质单位数量也最大
    //问题就变成了先找出凑出体积C的方案，然后对比质量
    //也就是 两个xi融合，物质数量变成了 2yi+X ，所有其实选中n瓶相同溶液那么最好的质量就是 n*yi + (n-1)*X
    //其实问题就变成了让vol[i]凑成C有多少种方法
    //是不是很熟悉青蛙跳台阶，可以跳1步，2步，3步...，那么跳100阶有多少种跳法
    //现在每步的跳法有vol[0],...,vol[i]种，那么跳C阶有多少种跳法?
    //也就变成了确定最后一部是vol[0],...,vol[k] 那么
    //这个状态转移方程就变成了 dp[i] = dp[i - vol[0]] + dp[i - vol[1]] + ... + dp[i - vol[k]]
    //当然条件是 i - vol[i] >= 0,也就是目标起码要不小于能跳的数字。不然就是0
    // dp[0] = 1
    /**
     * //下面是具体实现，但问题是我要得到具体的方案，直接动态归化只能得到方案数
     * //要得到具体方案，只能用回溯去试错。
     *  int[] dp = new int[C + 1];
     *         dp[0] = 1;
     *         for (int i = 1; i <= C; i++) {
     *             for (int j : vol) {
     *                 if (i - j >= 0) {
     *                     dp[i] += dp[i - j];
     *                 }
     *             }
     *         }
     *         return dp[C];
     */

    /**
     * 暴力回溯，列举出所有的可能方案然后计算质量。
     * TLE,MLE 0分。
     *
     * @param map
     * @param X
     * @param C
     * @return
     */
    private static int solution2(HashMap<Integer, Integer> map, int X, int C) {
        int max = Integer.MIN_VALUE;
        List<List<Integer>> res = combinationSum(map, C);
        for (List<Integer> re : res) {
            int matter = 0;
            HashMap<Integer, Integer> mapTemp = new HashMap<>();
            for (Integer integer : re) {
                mapTemp.put(integer, mapTemp.getOrDefault(integer, 0) + 1);
            }
            for (Integer xi : mapTemp.keySet()) {
                int n = mapTemp.get(xi);//每个integer的出现次数
                int yi = map.get(xi);
                matter = matter + n * yi + (n - 1) * X;
            }
            max = Math.max(matter, max);
        }
        return max;
    }

    private static List<List<Integer>> combinationSum(HashMap<Integer, Integer> map, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int[] keys = map.keySet().stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(keys); // 对候选数组排序，方便后面的去重
        backtrack(keys, target, new ArrayList<Integer>(), res, 0);
        return res;
    }

    private static void backtrack(int[] vol, int target, List<Integer> curr, List<List<Integer>> res, int start) {
        if (target < 0) { // 当target小于0时，无解，回溯
            return;
        } else if (target == 0) { // 当target等于0时，说明找到了一组解
            res.add(new ArrayList<>(curr));
            return;
        } else {
            for (int i = start; i < vol.length; i++) {
                curr.add(vol[i]);
                backtrack(vol, target - vol[i], curr, res, i);
                curr.remove(curr.size() - 1); // 回溯
            }
        }
    }

}
