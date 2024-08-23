package LeetCode数据结构与算法基础.day2.字符串;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author KingofTetris
 * @File 最长回文串
 * @Time 2021/10/15  15:43
 */

/*409. 最长回文串
        给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。

        在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。

        注意:
        假设字符串的长度不会超过 1010。

        示例 1:

        输入:
        "abccccdd"

        输出:
        7
        解释:
        //回文串是一个正读和反读都一样的字符串
        以回文中心为分界线，对于回文串中左侧的字符，在右侧对称的位置也会出现同样的字符
        中心可能是明确的某个字符，也可能是| 比如"ba|ab"
        我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。*/
public class 最长回文串 {

    //注意这题和直接去字符串中找回文串不一样，他是要去构造。
    //那就统计各个字符的数量，偶数都加上，奇数只加最大的那个就可以了。
    @Test
    public void test() {
        String s = "civilwartestingwhetherthatnaptionoranynarti" +
                "onsoconceivedandsodedicatedcanlongendureWear" +
                "eqmetonagreatbattlefiemldoftzhatwarWehavecometodedi" +
                "cpateaportionofthatfieldasafinalrestingplaceforthosewhohere" +
                "gavetheirlivesthatthatnationmightliveItisaltogetherfangandpro" +
                "perthatweshoulddothisButinalargersensewecannotdedicatewecannot" +
                "consecratewecannothallowthisgroundThebravelmenlivinganddeadwhos" +
                "truggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractT" +
                "gheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcannev" +
                "erforgetwhattheydidhereItisforusthelivingrathertobededicatedheret" +
                "otheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvance" +
                "dItisratherforustobeherededicatedtothegreattdafskremainingbefor" +
                "eusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcausef" +
                "orwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyre" +
                "solvethatthesedeadshallnothavediedinvainthatthisnationunsderGod" +
                "shallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeop" +
                "leforthepeopleshallnotperishfromtheearth";
        System.out.println(longestPalindrome(s));
    }

    /*    **友情提示**：遇到有提示字符串仅包含小写（或者大写）英文字母的题，
        都可以试着考虑能不能构造长度为26的每个元素分别代表一个字母的数组，来简化计算*/
    public int longestPalindrome(String s) {
        //可以观察到，回文数里面 两边字符一定是偶数次出现。
        //单词出现的若是奇数则一定是分界线
        //否则不可能构成回文数
        //所以只要字符成对出现一定可以构成回文数，或者有一个数只出现奇数次可以拿他当中心轴
        //因为要最长，我们取最大的奇数次

        //有坑！
        //你跟我一样的理解，以为奇数次数的只能取个最大的，
        // 而实际上，如果一个字母出现3次，那么我可以只用它2次，这题的坑在这！
        // 我也是看了他们的代码才想出来的

        //全是偶数次 一定可以
        //奇数次的只能出现1次才可以
        //字符出现偶数次无所谓，多少个都能对称。
        char[] arr = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            //累计一下字符串中各个字符出现的次数
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        //记录奇数次出现的情况
        int oddCount = 0;
        //结果
        int result = 0;
        /**
         * 遍历map
         */
        for (Map.Entry<Character, Integer> item : map.entrySet()) {
            //所有字符都可以选，只是如果我奇数，我们要给他减1,再加上去
            int val = item.getValue();
            if (val % 2 != 0) {
                //奇数-1
                result += (val - 1);
                oddCount++;
            } else {
                result += val;
            }
        }

        //注意最大的那个奇数字符 我们需要全部取 当中心，最后+1
        //判断有没有奇数次
        return oddCount == 0 ? result : result + 1;

    }
}
