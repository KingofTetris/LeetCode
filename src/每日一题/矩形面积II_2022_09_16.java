package 每日一题;

import java.util.*;

/**
 * @Author KingofTetris
 * @Date 2022/9/16 9:53
 */
public class 矩形面积II_2022_09_16 {

    /**
     * 先看个简单版本，单纯两个矩形的。求两个矩形的覆盖面积
     * @param ax1
     * @param ay1
     * @param ax2
     * @param ay2
     * @param bx1
     * @param by1
     * @param bx2
     * @param by2
     * @return
     */
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        /**
         * 其实根本没必要考虑那么复杂
         * 反正你不重叠就让squre返回0
         * 重叠去算就行了
         */

        //画个四个点不重叠的 看着图求就行了
        int lx = Math.max(ax1, bx1);
        int ly = Math.max(ay1, by1);
        int rx = Math.min(ax2, bx2);
        int ry = Math.min(ay2, by2);

        int s1 = square(ax1,ay1,ax2,ay2);
        int s2 = square(bx1,by1,bx2,by2);
        int s3 = square(lx,ly,rx,ry);

        return s1 + s2 - s3;
    }

    private int square(int x1, int y1, int x2, int y2) {
        if (x1 >= x2 || y1 >= y2) return 0;//非法坐标。
        return (y2 - y1) *(x2 - x1);
    }

    /**
     * 现在难度加大，我们来看看
     * 多个矩形如何求覆盖面积
     * 离散化 + 扫描线 + 使用简单数组实时维护
     * 链接：
     * https://leetcode.cn/problems/rectangle-area-ii/solution/ju-xing-mian-ji-ii-by-leetcode-solution-ulqz/
     * @param rectangles
     * @return
     */
    public int rectangleArea(int[][] rectangles) {
        final int MOD = (int) (1e9 + 7);
        int n = rectangles.length;
        Set<Integer> set = new HashSet<>();
        for (int[] rect : rectangles) {
            // 下边界
            set.add(rect[1]);
            // 上边界
            set.add(rect[3]);
        }
        List<Integer> hbound = new ArrayList<Integer>(set);
        Collections.sort(hbound);
        int m = hbound.size();
        // 「思路与算法部分」的 length 数组并不需要显式地存储下来
        // length[i] 可以通过 hbound[i+1] - hbound[i] 得到
        int[] seg = new int[m - 1];

        List<int[]> sweep = new ArrayList<int[]>();
        for (int i = 0; i < n; ++i) {
            // 左边界
            sweep.add(new int[]{rectangles[i][0], i, 1});
            // 右边界
            sweep.add(new int[]{rectangles[i][2], i, -1});
        }
        Collections.sort(sweep, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else if (a[1] != b[1]) {
                return a[1] - b[1];
            } else {
                return a[2] - b[2];
            }
        });

        long ans = 0;
        for (int i = 0; i < sweep.size(); ++i) {
            int j = i;
            while (j + 1 < sweep.size() && sweep.get(i)[0] == sweep.get(j + 1)[0]) {
                ++j;
            }
            if (j + 1 == sweep.size()) {
                break;
            }
            // 一次性地处理掉一批横坐标相同的左右边界
            for (int k = i; k <= j; ++k) {
                int[] arr = sweep.get(k);
                int idx = arr[1], diff = arr[2];
                int left = rectangles[idx][1], right = rectangles[idx][3];
                for (int x = 0; x < m - 1; ++x) {
                    if (left <= hbound.get(x) && hbound.get(x + 1) <= right) {
                        seg[x] += diff;
                    }
                }
            }
            int cover = 0;
            for (int k = 0; k < m - 1; ++k) {
                if (seg[k] > 0) {
                    cover += (hbound.get(k + 1) - hbound.get(k));
                }
            }
            ans += (long) cover * (sweep.get(j + 1)[0] - sweep.get(j)[0]);
            i = j;
        }
        return (int) (ans % MOD);
    }

    /*作者：LeetCode-Solution
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
