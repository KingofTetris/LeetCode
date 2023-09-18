package 校招笔试真题.美团._20230916;

import java.util.*;
class Monster{
    public int h;
    public int a;
    public Monster(int h, int a) {
        this.h = h;
        this.a = a;
    }
    public int getH() {
        return h;
    }
    public int getA() {
        return a;
    }
}

/**
 * 以后什么任务，怪物，组合，区间，等等多个值的东西
 * 建议你还是抽出来用一个特定的类表示。
 * 这样排序的思路清晰一点。
 */
public class 小美打怪 {
    public static void main(String[] args) {
        int H = 10;  // 初始血量
        int A = 5;   // 初始攻击力
        int originH = H;
        int originA = A;
        int[] h = {6, 4, 5, 7, 2,8};  // 怪物血量数组
        int[] a = {4, 6, 2, 4, 3,5};  // 怪物攻击力数组
        int n = h.length;
        int monsters1 = 0;
        int monsters2 = 0;
        // 创建一个怪物对象数组，用于存储怪物的血量和攻击力
        Monster[] monsters = new Monster[n];
        for (int i = 0; i < n; i++) {
            monsters[i] = new Monster(h[i], a[i]);
        }
        //其实这里的思路是错的。比如只按照攻击力降序
        //那么上面就会先选择能打得过的最大的[8,5]，然后打[6,4] 导致没法打[8.4]
        //所以对A降序以后，如果A一样，我们要选择H大的排前面
        //也就是先按照A降序，再按照H降序。
        //这样才能做到最贪
        //下面的写法要记住！JDK17能用，
      /*  Arrays.sort(monsters, Comparator
                .comparingInt(Monster::getH).reversed()
                .thenComparing(Comparator.comparingInt(Monster::getA).reversed()));*/

//        但是JDK8不行，老的lambda来
        //直接省略new Comparator这个过程。直接拿m1,m2比较
        //但是还是不对啊，比如这个排序完
        //是[4,6]排第一个，贪心先取了最大攻击力，但是血量很低
        //后面的[8,5],[7,4]都打不了。
        //那没办法了，那就排2次，一次先A后H，一次先H后A，两次比较谁能打更多。
        Arrays.sort(monsters, (m1, m2) -> {
            //先按照a降序
            if (m1.a != m2.a){
                return -(m1.a - m2.a);
            }
            //再按照h降序。
            else {
               return -(m1.h - m2.h);
            }
        });

        for (int i = 0; i < n; i++) {
            if (H > monsters[i].getH() && A > monsters[i].getA()) {
                H = monsters[i].getH();
                A = monsters[i].getA();
                monsters1++;
            }
        }

        //还原H,A
        H = originH;
        A = originA;
        //再按照先H后A排一次。
        Arrays.sort(monsters, (m1, m2) -> {
            //先按照a降序
            if (m1.h != m2.h){
                return -(m1.h - m2.h);
            }
            //再按照h降序。
            else {
                return -(m1.a - m2.a);
            }
        });
        for (int i = 0; i < n; i++) {
            if (H > monsters[i].getH() && A > monsters[i].getA()) {
                H = monsters[i].getH();
                A = monsters[i].getA();
                monsters2++;
            }
        }
        //最后取较大者
        System.out.println("小美最多能击败的怪物数量为: " + Math.max(monsters1,monsters2));
    }
}
