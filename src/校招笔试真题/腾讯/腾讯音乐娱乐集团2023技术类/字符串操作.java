package 校招笔试真题.腾讯.腾讯音乐娱乐集团2023技术类;

import java.util.HashMap;

/**
 * @Author KingofTetris
 * @Date 2023/3/30 10:35
 * <p>
 * 1.
 * 字符串操作
 * 给定一个只包含小写字母字符串，每次可以选择两个相同的字符删除，并在字符串结尾新增任意一个小写字母。
 * 请问最少多少次操作后，所有的字母都不相同？
 * 时间限制：C/C++ 1秒，其他语言2秒
 * 空间限制：C/C++ 256M，其他语言512M
 * 示例1
 * 输入例子：
 * "abab"
 * 输出例子：
 * 2
 * 例子说明：
 * 第一次操作将两个'a'变成一个'f'，字符串变成"bbf"。
 * 第二次操作将两个'b'变成一个'b'，字符串变成"fb"。
 * 操作方式不是唯一的，但可以证明，最少操作次数为2。
 */
public class 字符串操作 {
    //每次选两个相同的字母合并为任意一个字母添加到末尾
    //请问至少几次操作可以让这个字符串的所以字母都不同
    public int minOperations(String str) {
        // write code here
        int res = 0;
        int len = str.length();
        int aa = 0; //最多26个独特字母
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
        }

        //遍历字符串的字母，及其出现次数
        for (char i : map.keySet()) {
            int count = map.get(i);
            if (count == 1) { //如果出现一次
                aa++;//消耗掉一个独特字母
            }
            if (count % 2 == 0) { //如果出现偶数次
                res += count / 2; //消除n个字符，需要n/2次操作
                aa += count / 2; //消耗n/2个独特字母
            }
            if (count > 1 && count % 2 != 0) { //如果大于1且是奇数，那么可以合并 (count - 1) / 2次
                res += ( count - 1 )/ 2;//消除n个字符，需要n/2次操作
                aa += count / 2 + 1;//消耗n/2个字母加上余出的1个字母
            }
        }
        //超出26个字母的部分，需要额外的操作
        //每2个字母，需要多一次合并操作
        //例如aa=29 表示 a-z 已经占满了，可能多出来的是a b c 那么就要对这三个字母继续合并成一个a,b,c。也就多了3次
        //所以最后的结果是aa-26
        if (aa > 26) {
            res += aa - 26;
        }
        return res;
    }
}
