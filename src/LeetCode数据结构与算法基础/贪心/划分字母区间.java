package LeetCode数据结构与算法基础.贪心;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author KingofTetris
 * @File 划分字母区间
 * @Time 2021/10/16  11:12
 */
/*763. 划分字母区间
        字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，
        同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。

        说人话就是把字符串分段，每个段里面的字母都是自己特有的，其他段都没有。
        把这个字符串分段得的尽量多。

        示例：
        输入：S = "ababcbaca defegde hijhklij"
        输出：[9,7,8]
        解释：

        划分结果为 "ababcbaca", "defegde", "hijhklij"。
        每个字母最多出现在一个片段中。
        像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。


        提示：

        S的长度在[1, 500]之间。
        S只包含小写字母 'a' 到 'z' 。*/
public class 划分字母区间 {
    @Test
    public void test(){
        String s = "ababcbacadefegdehijhklij";
        List<Integer> list = partitionLabels(s);
        for(Integer i : list){
            System.out.println(i);
        }
    }

    //说是贪心算法，我也看不出来哪贪心
    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();

        //记录每个字母出现的最后位置
        int[] map = new int[26];
        //初始化全为-1 表示字符串中不存在这个字母
        Arrays.fill(map,-1);

        //找到每个字符的最后出现位置
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a'] = i;
        }

        //每段的起始位置start,结束位置end
        int start = 0,end = 0;

        for (int i = 0; i < s.length(); i++) {
            //当前字母能去的最远位置
            int currentCharMaxPos = map[s.charAt(i) - 'a'];

            //比如abc...........abc
            //a最远可以去len-3 b去len-2 c到len-1 实际上就只能分一段。


            //每次记录当前字符子串中字母出现的最远位置。
            //比如abc...........abcdfe...
            //像这个就可以截取abc...abc
            //记录的就是c这个字母的最远位置。
            end = Math.max(end,currentCharMaxPos);// 更新「已扫描的字符中最远的位置」

            //当i到达这个最远位置以后，就可以切割一段下来了。
            //这一段的长度就是 i - start + 1
            //然后把start移动到c以后，也就是start + 1
            //重复这个过程即可
            if (i == end){ // 正好扫描到「已扫描的字符的最远位置」，到达切割点
                list.add(i - start + 1);
                start = i + 1; // 更新，下一个待切割的字符串的起始位置
            }
        }
        return list;
    }
}
