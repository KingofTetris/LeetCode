package 校招真题.美团.美团2023春招0318;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @Author KingofTetris
 * @Date 2023/3/29 17:34
 * 商店
 *
 * 题目描述：
 *
 * 现在商店里有N个物品，每个物品有原价和折扣价。
 *
 * 小美想要购买商品。小美拥有X元，一共Y张折扣券。
 *
 * 小美需要最大化购买商品的数量，并在所购商品数量尽量多的前提下，尽量减少花费。
 *
 * 你的任务是帮助小美求出最优情况下的商品购买数量和花费的钱数。
 *
 * 输入描述
 *
 * 第一行三个整数，以空格分开，分别表示N,X,Y。
 *
 * 接下来N行，每行两个整数，以空格分开，表示一个商品的原价和折扣价。
 *
 * 1≤N≤100, 1≤X≤5000, 1≤Y≤50，每个商品原价和折扣价均介于[1,50]之间。
 *
 * 输出描述
 *
 * 一行，两个整数，以空格分开。第一个数字表示最多买几个商品，第二个数字表示在满足商品尽量多的前提下所花费的最少的钱数。
 *
 * 样例输入
 *
 * 3 5 1
 *
 * 4 3
 *
 * 3 1
 *
 * 6 5
 *
 * 样例输出
 *
 * 2 5
 */
public class 商店 {

    public static void main(String[] args) {
        int[] res = store();
        System.out.print(res[0] + " " + res[1]);
    }


    /**
     * 贪心没办法过全部的用例 只能过9%
     * @return
     */
    //static方法只能用外部类
    private static int[] store() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(),x = sc.nextInt(),y = sc.nextInt();
        ArrayList<item> items = new ArrayList<>(); //能不用数组就别用数字，不然没法用集合类排序
        for (int i = 0; i < n; i++) {
            item it = new item(sc.nextInt(),sc.nextInt());
            items.add(it);
        }
        sc.close();

        //贪心买，把券用完再去买本价,
        //先按照折后价升序，如果折后价相同，用原价升序
        //另外每件商品只能买一次
        // ::是java8的lamda表达式，简化了 item -> item.getDp() 这个匿名函数
        //后面的Comparator只是比较器，相当于给出用什么规则升序还是降序排列
        //具体怎么排还是按照Collections.sort来
        Collections.sort(items, Comparator.comparing(item::getDp).thenComparing(item::getOp));

        int max = 0,total = 0;
        for(int i = 0;i < items.size();i++){
            while (y != 0 && x >= items.get(i).dp){
                 y--;//用掉一张优惠券
                 x = x - items.get(i).dp;
                 max++;
                 total = total + items.get(i).dp;
                 i++;//买完就要买下一个
            }
            //如果y == 0了，只能原价买
            while (x >= items.get(i).op){
                x = x - items.get(i).op;
                max++;
                total = total + items.get(i).op;
                i++;
            }
        }
        return new int[]{max,total};
    }
}

class item{
    int op,dp;

    item(int op,int dp){
        this.op = op;
        this.dp = dp;
    }

    public int getOp() {
        return op;
    }

    public int getDp() {
        return dp;
    }
}
