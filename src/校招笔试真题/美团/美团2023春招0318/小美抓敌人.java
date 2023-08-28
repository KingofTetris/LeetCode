package 校招笔试真题.美团.美团2023春招0318;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * @Author KingofTetris
 * @Date 2023/3/29 14:43
 * Problem Description
 * 小美在玩一项游戏。该游戏的目标是尽可能抓获敌人。 敌人的位置将被一个二维坐标(x,y)所描述。
 * 小美有一个全屏技能，该技能能一次性将若干敌人一次性捕获。捕获的敌人之间的横坐标的最大差值不能大于A，纵坐标的最大差值不能大于B
 * 现在给出所有敌人的坐标，你的任务是计算小美一次性最多能使用技能捕获多少敌人。
 *
 * input
 * 第一行三个整数N,A,B，表示共有N个敌人，小美的全屏技能的参数A和参数B。 接下来N行，每行两个数字x,y，描述一个敌人所在的坐标。
 * 1≤N≤500，1≤A,B≤1000，1≤x,y≤1000
 *
 * ouput
 * 一行,一个整数表示小美使用技能单次所可以捕获的最多数量。
 *
 * Sample Input 1
 * 3 1 1
 * 1 1
 * 1 2
 * 1 3
 *
 * Sample Output 1
 * 2
 *
 * Sample Input 2
 * 5 1 2
 * 1 1
 * 2 2
 * 3 3
 * 1 3
 * 1 4
 *
 * Sample Output 2
 * 3
 * ————————————————
 * 版权声明：本文为CSDN博主「新时代原始人」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/weixin_40735291/article/details/129670825
 */
public class 小美抓敌人 {

    public static void main(String[] agrs){
        int i = catchEnemy();
        System.out.println(i);
    }
    public static int catchEnemy(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int A = sc.nextInt();
        int B = sc.nextInt();
        LinkedList<Enemy> enemies = new LinkedList();
        for (int i = 0; i < n; i++) {
            Enemy enemy = new Enemy(sc.nextInt(), sc.nextInt());
            enemies.add(enemy);
        }

        sc.close(); //关闭输入流

        int max = -1;
        //初始化以后先排序x,再排y
        //前面是要比较的容器，后面是Comparator对象，比较的是int型就用comparingInt，括号里面放要比较的字段
        //不加负号就是升序，加负号就是降序

        //另外Comparator.compareXX方法要求比较的对象字段要实现get方法。
        //这就很要命了，考试的IDE没有自动生成的功能，你自己去写GET SET就要多花时间
        Collections.sort(enemies, Comparator.comparingInt(Enemy::getX).thenComparingInt(Enemy::getY));
        //找到x坐标距离<=A的敌人集合
        LinkedList<LinkedList<Enemy>> groups = new LinkedList<>();
        for (int i = 0; i < n;i++) {
            LinkedList<Enemy> group = new LinkedList<>();
            int start = i; //每轮窗口更新从start出发
            group.add(enemies.get(start));
            while (i + 1 < n && enemies.get(i + 1).x - enemies.get(start).x <= A){
                i++;
                group.add(enemies.get(i));
            }
            groups.add(group);
        }

        //从这些集合里面再去判断是否满足y之差<=B
        int res = 1;//每个能抓到的敌人数目
        for (LinkedList<Enemy> group : groups) {
            if (group.size() == 1 && groups.size() == 1) return res; //如果只有一个组，且这个组的数目就是1，那就不用比了，全屏一定能抓到它

            res = 1; //每个组的res初始都是1
            //其他情况比较每个组的情况
            for (int i = 0; i < group.size(); i++) {
                int start = i;//同样也是从start出发
                while (i + 1 < group.size() && group.get(i + 1).y - group.get(start).y <= B){
                    res++;
                    i++;
                }
                if (res > max){
                    max = res;
                }
            }
        }

        return max;

    }
}
//敌人的坐标
class Enemy{
    int x;
    int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    Enemy(int x, int y){
        this.x = x;
        this.y = y;
    }
}
