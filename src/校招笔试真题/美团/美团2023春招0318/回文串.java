package 校招笔试真题.美团.美团2023春招0318;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Author KingofTetris
 * @Date 2023/3/29 16:39
 * 回文串
 *
 * 时间限制： 3000MS
 *
 * 内存限制： 589824KB
 *
 * 题目描述：
 *
 * 现在小美获得了一个字符串。小美想要使得这个字符串是回文串。
 *
 * 小美找到了你。你可以将字符串中至多两个位置改为任意小写英文字符’a’-‘z’
 *
 * 你的任务是帮助小美在当前制约下，获得字典序最小的回文字符串。
 *
 * 数据保证能在题目限制下形成回文字符串。
 *
 * 注：回文字符串：即一个字符串从前向后和从后向前是完全一致的字符串。
 *
 * 例如字符串abcba, aaaa, acca都是回文字符串。字符串abcd, acea都不是回文字符串。
 *
 * 输入描述
 *
 * 一行，一个字符串。字符串中仅由小写英文字符构成。
 *
 * 保证字符串不会是空字符串。
 *
 * 字符串长度介于 [1, 100000] 之间。
 *
 * 输出描述
 *
 * 一行，一个在题目条件限制下所可以获得的字典序最小的回文字符串。
 *
 * 样例输入
 *
 * acca
 *
 * 样例输出
 *
 * aaaa
 */
public class 回文串 {

    public static void main(String[] args) {
        回文串 hwc = new 回文串();
        String s = hwc.GXhwc();
        System.out.println(s);
    }


    /**
     * 下面这个思路还是有问题，不能遇到不一样就修改成小的那个。
     * 最优先的选项应该是先修改成2个'a'，才能保证字典序最小。
     * 但问题来了，题目要求最多改两次一定能改成回文串，但不一定是最小回文串。
     * 如果你把两次都用在改成'a'上，后面可能就没法改成回文串了。
     *
     * 所以必须先统计有几对不一样的字符组。
     * 结果0对，那就可以把第一对不是'a'的回文对都改成'a'
     * 结果1对,还要考虑这里面有没有'a'，有的话，把另一个不是'a'的改成'a'，还要判断回文串长度如果为奇数就要把对称轴上的元素也改成'a'
     * 没有的话都改成'a'即可
     * 结果2对，那么都改成对应最小的字母
     * @return
     */
    public String GXhwc(){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        sc.close();

        char[] chars = s.toCharArray();
        int l = 0,r = chars.length - 1;
        LinkedList<Integer> groups = new LinkedList<>(); //查看有几对不一样的数字，记下标，用下标就可以得到数字
        while (l < r){ //l = r也可以退出了
            //如果不一样，改成字典序小的那个,这样修改次数只能算一次
            if (chars[l] != chars[r]){
               groups.add(l);
               groups.add(r);
            }
            //不管相不相等，指针都是要动的
            l++;
            r--;
        }

        if (groups.size() == 0){ //如果本来就是回文串
            l = 0;
            r = chars.length - 1;
            while (l < r){
                //去找第一个不为'a'的位置，那么等于'a'的时候就要改变指针
                while (chars[l] == 'a'){
                    l++;
                    r--;
                }
                //如果找到的位置满足l<=r,修改lr
                if (l <= r){
                    chars[l] = 'a';
                    chars[r] = 'a';
                    break;//跳出循环
                }
            }
        }
        //如果一对不一样
        //还要分成包不包含'a'
        //1.这对里面有'a'那么把另外一个不是'a'的改成'a'，然后还要修改对称轴也是'a'
        //2.如果都不是'a'，那么都改成'a'
        if (groups.size() == 2){
            if (chars[groups.get(0)] == 'a' || chars[groups.get(1)] =='a'){
                if (chars[groups.get(0)] == 'a') chars[groups.get(1)] = 'a';
                if (chars[groups.get(1)] == 'a') chars[groups.get(0)] = 'a';
                //如果还是奇数长度，对称轴也要改
                if (chars.length / 2 != 0) chars[chars.length / 2] = 'a';
            }
            //如果都不是'a'，则都改成'a'
            if (chars[groups.get(0)] != 'a' && chars[groups.get(1)] !='a'){
                chars[groups.get(0)] = 'a';
                chars[groups.get(1)] = 'a';
            }
        }

        //如果等于4，则说明两对不一样，则各自取为最小值
        if (groups.size() == 4){
            char min = (char) ('a' + Math.min(groups.get(0) - 'a',groups.get(1) - 'a'));
            chars[groups.get(0)] = min;
            chars[groups.get(1)] = min;

            min = (char) ('a' + Math.min(groups.get(2) - 'a',groups.get(3) - 'a'));
            chars[groups.get(2)] = min;
            chars[groups.get(3)] = min;
        }

        String res = new String(chars);
        return res;
    }

}
