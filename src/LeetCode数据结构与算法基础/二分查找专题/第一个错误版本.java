package LeetCode数据结构与算法基础.二分查找专题;

import org.junit.Test;

/*你是产品经理，目前正在带领一个团队开发新的产品。
不幸的是，你的产品的最新版本没有通过质量检测。
由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。

        假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
        你可以通过调用bool isBadVersion(version)接口来判断版本号 version 是否在单元测试中出错。
        实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。


        示例 1：

        输入：n = 5, bad = 4
        输出：4
        解释：
        调用 isBadVersion(3) -> false
        调用 isBadVersion(5)-> true
        调用 isBadVersion(4)-> true
        所以，4 是第一个错误的版本。
        示例 2：

        输入：n = 1, bad = 1
        输出：1


        提示：

        1 <= bad <= n <= 2^31 - 1

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/first-bad-version
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
public class 第一个错误版本 {
    /*
    注意到一个性质：当一个版本为正确版本，
    则该版本之前的所有版本均为正确版本；当一个版本为错误版本，
    则该版本之后的所有版本均为错误版本。我们可以利用这个性质进行二分查找。
    具体地，将左右边界分别初始化为 1 和 n，
    其中 n 是给定的版本数量。设定左右边界之后，每次我们都依据左右边界找到其中间的版本，
    检查其是否为正确版本。如果该版本为正确版本，那么第一个错误的版本必然位于该版本的右侧，
    我们缩紧左边界；否则第一个错误的版本必然位于该版本及该版本的左侧，我们缩紧右边界。

    注isBadVersion是父类VersionControl中的方法
    具体的实现不用你操心，下面的方法只是随便写的。

     */
    @Test
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean isBadVersion(int n) {
        if (n % 2 == 0)
            return false;
        return true;
    }
}
