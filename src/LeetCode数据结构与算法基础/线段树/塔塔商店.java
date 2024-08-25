package LeetCode数据结构与算法基础.线段树;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/8/24
 */

/**
 * 美团20240824 第三题 线段树
 * 塔塔商店里只卖两种商品，“塔可乐“和“塔马克”。
 * 现在有 n 个人来到商店购物，第 i 个人有一个喜好区间[Li,Ri]和购买目标商品，
 * 他只看货架上位于区间里的商品，
 * 并从中挑选 ki 个保质期最长的塔可乐或者塔马克买走(如果有多个商品保质期相同，他会拿走区间中靠前的那个)。
 * 你能告诉塔塔每个人买走的商品编号吗。
 * <p>
 * 第一行输入两个整数 n 和 m 代表来塔塔商店购物的人数和商品数量。
 * <p>
 * 第二行输入个m整数 a1...am 代表商品的保质期。
 * 第三行输入m个整数 b1...bm 代表商品的种类，其中，bi=0 代表"塔可乐"、bi = 1代表"塔马克''。
 * <p>
 * 此后 n 行，第 i 行输入四个整数 Li,Ri,ti和k;
 * 和代表第i个人的喜好区间、购买商品和购买件数。其中，ti=0代表他想要购买“塔可乐”、ti=1代表他想要购买“塔马克”。
 * <p>
 * 输出n行，第i行包含至多ki个整数，代表第i个人购买的商品编号(如果有多个商品保质期相同，输出编号较小的那个)，
 * 你需要按照从小到大的顺序依次输出;如果没有买到足够的商品，使用一个 -1 替代。
 *
 * 注
 * 1 <= Li <= Ri <= m ,0 <= ti <= 1,1 <= ki <= m
 *
 * <p>
 * eg:
 * 输入
 * 5 5 （ 5个顾客 5个商品）
 * 2 3 4 5 6 (商品保质期)
 * 1 1 0 1 1 (商品序列)
 * 1 3 1 2 ([1,3]买1，买两个，商品一样选保质期长的，也就是先买2再买1，此时商品序列变成 x x 0 1 1 ）
 * 1 3 1 2 ( [1,3]买1，买两个,已经 没得买了 -1)
 * 1 3 0 5 ( [1,3]买0，买5个 只能买到一个3，后面就不能再买了 返回 3 -1 ,序列变成 x x x 1 1)
 * 1 5 1 1 ( [1,5]买1，买1个。他会选保质期长的，也就是5号，返回5，序列变成 x x x 1 x)
 * 1 5 0 1 ( [1,5]买0，买1个，没得买，返回 -1)
 * <p>
 * 输出：
 * 2 1
 * -1
 * 3 -1
 * 5
 * -1
 * <p>
 * 说明
 * ●第一次询问，货架上的情况为{2,3,*,5,6}(*代表非目标商品，下划线代表喜好区间)，先买第二件，再买第一件;
 * ●第二次询问，货架上的情况为{x,x,*,5,6}(x代表已售罄商品)，此时无法购买，直接输出;
 * ●第三次询问，货架上的情况为{*,*,4,5,6}由于只剩一件商品，故先购买第三件，后输出代表没有买到足够数量的商品。
 */

//总之意思就是顾客会从他中意的区间[L,R]里面取走他想要的ti，一共取ki个
//如果有多个，那么他会首先选保质期长的
//如果保质期一样长，那么他选序号在前面吧
//请你返回顾客购买的商品序号，从小大到排序。
//如果买不了了，返回-1
public class 塔塔商店 {
    /**
     * 思路： 维护线段树区间最大值的下标，每次查询将最大下标对应的值删去并输出，直到没有最大值为止。
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOps = scanner.nextInt();
        int numElems = scanner.nextInt();

        //保质期和商品序列
        ArrayList<Integer>[] arr = new ArrayList[2];
        arr[0] = new ArrayList<>(numElems + 1);
        arr[1] = new ArrayList<>(numElems + 1);

        for (int i = 1; i <= numElems; i++) {
            arr[0].add(-1);
            arr[1].add(-1);
        }

        ArrayList<Integer> A = new ArrayList<>(numElems);//设置初始大小为numElems
        ArrayList<Integer> B = new ArrayList<>(numElems);

        for (int i = 0; i < numElems; i++) {
            A.add(scanner.nextInt());
        }

        for (int i = 0; i < numElems; i++) {
            B.add(scanner.nextInt());
            //建立物品对质保期的映射
            arr[B.get(i)].set(i + 1, A.get(i));
        }

        // 初始化并构建两个线段树
        SegmentTree[] segment = new SegmentTree[2];
        for (int i = 0; i < 2; i++) {
            segment[i] = new SegmentTree();
            segment[i].init(numElems, arr[i]);
            segment[i].build(1, 1, numElems);
        }

        // 处理查询操作

        while (numOps-- > 0) {
            int left = scanner.nextInt();
            int right = scanner.nextInt();
            int type = scanner.nextInt();
            int numQueries = scanner.nextInt();

            while (numQueries-- > 0) {
                int index = segment[type].getMaxIndex(left, right);
                int value = segment[type].data.get(index);
                segment[type].remove(index);// 删除找到的最大值
                System.out.print((value != -1 ? index : -1) + " ");
                if (value == -1) break;// 如果没有找到有效值，停止
            }
            System.out.println();
        }

        scanner.close();
    }
}
