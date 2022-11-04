package 每日一题;

/**
 * @Author KingofTetris
 * @Date 2022/11/1 13:57
 */
public class 检查两个字符串数组是否相等_2022_11_01 {

    //两个字符串数组比较是否相等。
    //相等指的是从w[0]...w[n]拼接起来是否一样

    /**
     * 一个一个遍历
     * @param word1
     * @param word2
     * @return
     */
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int p1 = 0, p2 = 0, i = 0, j = 0;
        while (p1 < word1.length && p2 < word2.length) {
            if (word1[p1].charAt(i) != word2[p2].charAt(j)) {
                return false;
            }
            i++;
            if (i == word1[p1].length()) {
                p1++;
                i = 0;
            }
            j++;
            if (j == word2[p2].length()) {
                p2++;
                j = 0;
            }
        }
        return p1 == word1.length && p2 == word2.length;
    }

    /*作者：LeetCode-Solution
    链接：https://leetcode.cn/problems/check-if-two-string-arrays-are-equivalent/solution/jian-cha-liang-ge-zi-fu-chuan-shu-zu-shi-9iuo/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
