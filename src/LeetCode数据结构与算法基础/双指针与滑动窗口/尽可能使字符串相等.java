package LeetCode数据结构与算法基础.双指针与滑动窗口;

public class 尽可能使字符串相等 {
    public int equalSubstring(String s, String t, int maxCost) {
        int left = 0;
        int max = 0;
        int ans = 0;
        for (int right = 0; right < s.length(); right++) {
            int c = Math.abs(s.charAt(right) - t.charAt(right));
            max += c;
            while (max > maxCost) {
                max -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }
            ans = Math.max(ans, right - left + 1);
        }
        return ans;
    }
}

/*作者：风起云涌
链接：https://leetcode.cn/problems/get-equal-substrings-within-budget/solutions/2982284/bu-ding-chang-hua-dong-chuang-kou-by-ten-hs5r/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
