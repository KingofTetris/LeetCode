package 校招笔试真题.华为.华为研发工程师编程题;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/4/4
 * 某商店规定：三个空汽水瓶可以换一瓶汽水，允许向老板借空汽水瓶（但是必须要归还）。
 * 小张手上有n个空汽水瓶，她想知道自己最多可以喝到多少瓶汽水。
 * 1<= n <= 100
 * 注意：本题存在多组输入。输入的 0 表示输入结束，并不用输出结果。
 *
 * 输入描述：
 * 输入文件最多包含 10 组测试数据，每个数据占一行，
 * 仅包含一个正整数 n（ 1<=n<=100 ），表示小张手上的空汽水瓶数。n=0 表示输入结束，你的程序不应当处理这一行。
 *
 * 输出描述：
 * 对于每组测试数据，输出一行，表示最多可以喝的汽水瓶数。如果一瓶也喝不到，输出0。
 *
 * 示例1
 * 输入例子：
 * 3
 * 10
 * 81
 * 0
 * 输出例子：
 * 1
 * 5
 * 40
 * 例子说明：
 * 样例 1 解释：用三个空瓶换一瓶汽水，剩一个空瓶无法继续交换
 * 样例 2 解释：用九个空瓶换三瓶汽水，剩四个空瓶再用三个空瓶换一瓶汽水，
 * 剩两个空瓶，向老板借一个空瓶再用三个空瓶换一瓶汽水喝完得一个空瓶还给老板
 */
public class 汽水瓶 {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        while (true){
            int num = sc.nextInt();
            if (num == 0) break;
            int i = changeBottle(num);
            System.out.println(i);
        }
    }

    public static int changeBottle(int emptyBottle){
        int drink = 0;
        while (emptyBottle >= 3){
            emptyBottle = emptyBottle - 3;
            //每3个空瓶子换一瓶饮料喝完再得到一个空瓶子
            drink++;
            emptyBottle++;
        }

        //如果刚好还剩2个，那可以再借一个。drink++
        if (emptyBottle == 2){
            drink++;
        }

        return drink;
    }
}
