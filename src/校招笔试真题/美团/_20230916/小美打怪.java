package 校招笔试真题.美团._20230916;

import java.util.*;
// P3

/**
 * 小美在玩游戏，游戏中有 n 个怪物，怪物的血量为hi， 攻击力为ai
 * ​
 *  ， 小美的血量为H，攻击力为A， 小美可以击败血量和攻击力都小于自己的怪物，
 *  并且打败怪物后血量降为怪物的血量，攻击力降为怪物的攻击力，小美想知道最多可以打败多少怪物。
 *
 输入描述
 第一行三个整数 n,H，A，分别表示怪物的数量，小美的血量，小美的攻击。

 第二行n个整数hi, 表示怪物的血量。

 第三行n个整数ai，表示怪物的攻击力。
 示例1
 输入：
 3 4 5
 1 2 3
 3 2 1

 输出：
 1

 说明：
 最多只能击败一个怪物。
 */
public class 小美打怪 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), H = in.nextInt(), A = in.nextInt();
        Monster[] list = new Monster[n]; //n个怪物
        for (int i = 0; i < n; i++) list[i] = new Monster();
        for (int i = 0; i < n; i++) list[i].h = in.nextInt();
        for (int i = 0; i < n; i++) list[i].a = in.nextInt();
        Solution solution = new Solution();
        System.out.println(solution.solve(list, H, A));
    }
}

class Monster {
    public int h, a;
    // 最多可以打败多个其他怪物
    public int max;

    @Override
    public String toString() {
        return "Monster{" +
                "h=" + h +
                ", a=" + a +
                ", max=" + max +
                '}';
    }
}


class Solution {
    public int solve(Monster[] list, int H, int A) {
        List<Monster> monsters = new ArrayList<>();
        for (Monster monster : list) {
            if (monster.a < A && monster.h < H) {
                //可击败的怪物列表。
                monsters.add(monster);
            }
        }
        Monster xm = new Monster();
        xm.a = A;
        xm.h = H;
        monsters.add(xm);//把小美也看作是怪物，加入到可击败列表中，先按照a从小到大排序，再按照h从大到小排序
        monsters.sort((o1, o2) -> {
            if (o1.a != o2.a) {
                return o1.a - o2.a;
            } else {
                return -(o1.h - o2.h);
            }
        });
        System.out.println(monsters);
        for (int i = 1; i < monsters.size(); i++) {
            Monster cur = monsters.get(i);
            for (int l = 0; l < i; l++) {
                Monster prev = monsters.get(l);
                //如果前一个怪物的a和h都小于当前怪物的a和h则取cur.max和prev.max+1的较大者。
                if (prev.a < cur.a && prev.h < cur.h) {
                    cur.max = Math.max(cur.max, prev.max + 1);
                }
            }
        }
        return xm.max;//最后xm的max就是结果。
    }
}

/*作者：code5bug
        链接：https://www.nowcoder.com/discuss/534003042790780928?urlSource=sitemapApi
        来源：牛客网*/
