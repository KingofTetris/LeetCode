package 校招笔试真题.华为.华为od_0408;

/**
 * @author by KingOfTetris
 * @date 2023/4/13
 * <p>
 * 要求将一批箱子按从上到下以‘之’字形的顺序摆放在宽度为 n 的空地上，输出箱子的摆放位置，
 * 例如：箱子ABCDEFG，空地宽为3。
 * 如输入：
 * ABCDEFG 3
 * 输出：
 * AFG
 * BE
 * CD
 * 注：最后一行不得输出额外的空行
 * str只包含数字和数字，1<=len(str)<=1000，1<=n<=1000。
 * 解题思路：
 * 主要的技巧点在于处理奇数列和偶数列的时候，正序和倒序的问题；
 * 1.将输入的字符串按空格分割为两部分，分别为箱子的字符串和空地的宽度；
 * 2.创建一个HashMap，用于存储每行的字符串；Key为行的下标，Value为对应的字符串；
 * 3.遍历箱子的字符串，根据空地的宽度确定每个字符应该放置的行和列；
 * 计算当前字符所在的列，即columnIdx = i / num，其中i为当前字符的索引；
 * 如果columnIdx是偶数，则表示从左往右摆放，行索引不变，列索引为i % num；
 * 如果columnIdx是奇数，则表示从右往左摆放，行索引不变，列索引为num - 1 - (i % num)，即倒序摆放；
 * 将当前字符添加到对应行的字符串中；
 * 过程推演：
 * i:                   0 1 2 3 4 5 6
 * columnIdx = i / num: 0 0 0 1 1 1 2
 * i % num:             0 1 2       0
 * num - 1 - (i % num)        2 1 0
 * 4.遍历HashMap map，按行输出箱子的摆放位置；
 * 5.输出结果。
 * ————————————————
 * <p>
 * 版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
 * <p>
 * 原文链接：https://blog.csdn.net/Baoge_leopard/article/details/135974879
 */


/**
 * 要求将一批箱子按从上到下以‘之’字形的顺序摆放在宽度为 n 的空地上，输出箱子的摆放位置，
 * 例如：箱子ABCDEFG，空地宽为3。
 * 如输入：
 * ABCDEFG 3
 * 输出：
 * AFG
 * BE
 * CD
 * 注：最后一行不得输出额外的空行
 * str只包含数字和数字，1<=len(str)<=1000，1<=n<=1000。
 * 解题思路：
 * 主要的技巧点在于处理奇数列和偶数列的时候，正序和倒序的问题；
 * 1.将输入的字符串按空格分割为两部分，分别为箱子的字符串和空地的宽度；
 * 2.创建一个HashMap，用于存储每行的字符串；Key为行的下标，Value为对应的字符串；
 * 3.遍历箱子的字符串，根据空地的宽度确定每个字符应该放置的行和列；
 * 计算当前字符所在的列，即columnIdx = i / num，其中i为当前字符的索引；
 * 如果columnIdx是偶数，则表示从左往右摆放，行索引不变，列索引为i % num；
 * 如果columnIdx是奇数，则表示从右往左摆放，行索引不变，列索引为num - 1 - (i % num)，即倒序摆放；
 * 将当前字符添加到对应行的字符串中；
 *    过程推演：
 *    i:                   0 1 2 3 4 5 6
 *    columnIdx = i / num: 0 0 0 1 1 1 2
 *    i % num:             0 1 2       0
 *    num - 1 - (i % num)        2 1 0
 * 4.遍历HashMap map，按行输出箱子的摆放位置；
 * 5.输出结果。
 * ————————————————
 *
 * 版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
 *
 * 原文链接：https://blog.csdn.net/Baoge_leopard/article/details/135974879
 */

import java.util.*;

public class 箱子之形摆放 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = sc.nextInt();

        ArrayList<Character>[] res = solution(s, n);

        for (ArrayList<Character> chars : res) {
            String str = "";
            for (int i = 0; i < chars.size(); i++) {
                str += chars.get(i);
            }
            if (str.length() != 0) System.out.println(str);
        }
    }

    public static ArrayList<Character>[] solution(String s, int n) {
        //这个只是初始化了一个ArrayList[]数组，但是里面的元素还全部都是null
        //邻接表也是一样的。
        //ArrayList<Integer>[] adj = new ArrayList[n]
        //然后adj[i]每个去初始化
        ArrayList<Character>[] res = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            res[i] = new ArrayList<>();
        }
        //i % n     结果是0->n-1
        //n-1 - (i%n)  结果就是n-1->0
        boolean flag = true;
        for (int i = 0; i < s.length(); i++) {
            //每一轮取反
            //每次 i % n 为0 就换个方向
            if (i != 0 && i % n == 0) flag = !flag;
            if (flag == true) res[i % n].add(s.charAt(i));
            else res[(n - 1) - (i % n)].add(s.charAt(i));
        }
        return res;
    }
}
