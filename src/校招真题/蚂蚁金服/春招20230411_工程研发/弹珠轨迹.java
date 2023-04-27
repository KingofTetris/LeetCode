package 校招真题.蚂蚁金服.春招20230411_工程研发;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/4/20
 * 给你一个棋盘和弹珠的起始位置，再给你一个方向
 * 经过多久后，弹珠会弹回到原点?
 *
 * 原题是https://codeforces.com/contest/1807/problem/F
 *
 */
public class 弹珠轨迹 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int x = in.nextInt();
        int y = in.nextInt();

        //方向 DL,DR,UL,UR
        //DL : down left
        //DR : down right
        //UL : up left
        //UR : up right
        char[] chars = in.next().toCharArray();

//        每一步的累加因子
        // 为什么是反过来 down/up 对应x坐标? left/right 对应y坐标?
        int xbase = chars[0] == 'D' ? 1 : -1;
        int ybase = chars[1] == 'L' ? -1 : 1;
//
//        //不应该是y的累加因子对应down/up
//        // x的累加因子对应left/right?
        //用正常思维去做就只能得61,反过来100？
        //都用测试用例测试了，都能通过，为什么最后提交的时候，下面这个就只有61？
//        int ybase = chars[0] == 'D' ? 1 : -1;
//        int xbase = chars[1] == 'L' ? -1 : 1;

        int res = 0;
        int xx = x;//移动轨迹模拟xx,yy  初始就是(x,y)
        int yy = y;

        do {
            res++; //每一步res++
            xx += xbase;
            yy += ybase;
//            碰壁的时候，累加因子取反
            if (xx == m || xx == 1) {
                xbase = -xbase;
            }
            if (yy == 1 || yy == n) {
                ybase = - ybase;
            }
        } while (xx != x || yy != y);
        System.out.println(res);
    }
}
