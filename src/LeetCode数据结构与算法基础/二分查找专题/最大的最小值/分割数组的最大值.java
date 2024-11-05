package LeetCode数据结构与算法基础.二分查找专题.最大的最小值;

public class 分割数组的最大值 {
    public int splitArray(int[] nums, int k) {
        int sum = 0;
        int mx = 0;
        for (int x : nums) {
            sum += x;
            mx = Math.max(mx, x);
        }
        int left = Math.max(mx - 1, (sum - 1) / k);
        int right = sum;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (check(nums, k, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private boolean check(int[] nums, int k, int mx) {
        int cnt = 1;
        int s = 0;
        for (int x : nums) {
            if (s + x <= mx) {
                s += x;
            } else { // 新划分一段
                if (cnt == k) {
                    return false;
                }
                cnt += 1;
                s = x;
            }
        }
        return true;
    }
}

/*作者：灵茶山艾府
链接：https://leetcode.cn/problems/split-array-largest-sum/solutions/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
