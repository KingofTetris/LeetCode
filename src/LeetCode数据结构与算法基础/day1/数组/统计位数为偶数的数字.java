package LeetCode数据结构与算法基础.day1.数组;

public class 统计位数为偶数的数字 {
    public int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (getLength(num) % 2 == 0) {
                count++;
            }
        }
        return count;
    }

    public int getLength(int num) {
        int length = 0;
        while (num > 0) {
            length++;
            num /= 10;
        }
        return length;
    }
}

/*作者：Storm
链接：https://leetcode.cn/problems/find-numbers-with-even-number-of-digits/solutions/2491416/1295-tong-ji-wei-shu-wei-ou-shu-de-shu-z-qcaq/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
