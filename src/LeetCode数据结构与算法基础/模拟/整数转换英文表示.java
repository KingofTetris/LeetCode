package LeetCode数据结构与算法基础.模拟;


//0 <= num <= 2^31 - 1

/**
 * 笔者在去年夏天的微软面试中碰到过原题。面试官要求用递归和迭代都实现一遍。这里讲解我更喜欢的递归写法。
 *
 * 英语世界里数字喜欢用逗号分隔，每三位一组，有一个对应的单位。
 * 从低到高分别是千(Thousand),百万(million),十亿(billion)；
 *
 * 每个单位前最多有一个小于1000的数做量词，
 * 我们要做的只是找到一个计算量词的方式（1000以下的数字如何转化成英文），并拼接这些量词和单位即可。
 *
 * 比如 1234567891 可以写成 1,234,567,891
 * 代表
 * 1 个 亿
 * 234 个 百万
 * 567 个 千
 * 891
 *
 * 1000 以下的数字如何表示呢？
 * 比如517
 * 代表
 * 5个100 + 17
 *
 * 我们知道英语20以下都有直接对应的单词。
 * 20以上写成几十+几。
 * 我们用vector记录不同的数字对应的单词是什么 同样拼接即可。
 *
 * 彩蛋
 * 我所喜欢的美剧《硅谷》中有一个投资人，就有一个酒的logo是三个逗号，
 * 象征着billionaire(十亿富翁)；祝愿大家的公司也可以早日上市，收获一笔财富。
 *
 * 作者：微扰理论
 * 链接：https://leetcode.cn/problems/integer-to-english-words/solutions/1041311/wei-rao-li-lun-mo-ni-ti-ying-wen-shu-zi-1sc7b/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 * 微软，滴滴 面试手撕。
 */
public class 整数转换英文表示 {
    //1-9需要表示
    String[] singles = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    //英文的11-19也要表示
    String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    //10-90还要表示
    String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        String ans = recur(num);
        return ans.trim();
    }

    public String recur(int n) {
        int billion = n / 1000000000;
        n %= 1000000000;
        int million = n / 1000000;
        n %= 1000000;
        int thousand = n / 1000;
        n %= 1000;
        String ans = "";
        if (billion > 0) {
            ans += recur(billion) + "Billion ";
        }
        if (million > 0) {
            ans += recur(million) + "Million ";
        }
        if (thousand > 0) {
            ans += recur(thousand) + "Thousand ";
        }
        ans += comma(n);
        return ans;
    }

    public String comma(int n) {
        String ans = "";

        int hundred = n / 100; //每3个一组

        if (hundred > 0) {
            ans += singles[hundred] + " Hundred ";
        }
        n = n % 100;
        if (n < 20 && n >= 10) {
            ans += teens[n % 10] + " ";
            return ans;
        }
        if (n >= 20) {
            ans += tens[n / 10] + " ";
        }
        n = n % 10;
        if (n == 0) return ans;

        ans += singles[n % 10] + " ";
        return ans;
    }
}
        /*    作者：微扰理论
            链接：https://leetcode.cn/problems/integer-to-english-words/solutions/1041311/wei-rao-li-lun-mo-ni-ti-ying-wen-shu-zi-1sc7b/
            来源：力扣（LeetCode）
            著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
