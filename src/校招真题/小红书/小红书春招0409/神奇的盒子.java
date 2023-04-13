package 校招真题.小红书.小红书春招0409;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/4/12
 *
 *
 * 假设盒子里有x个红色玩具，他们的标号是i1,i2,...,ix
 * 有y个蓝色玩具，他们的标号是j1,j2,...,jy
 * 当前时间为time
 * 玩具放入盒子的时间各自记录为 in_box_time(ix/jy)
 *
 * 那么time时刻盒子里的玩具编号之和就是
 *
 * v(time) = ∑(a_ik + time - in_box_time_(ik) (k从1到x)
 *          +∑(a_ik - (time - in_box_time_(iy))  (k从1到y)
 *
 * 那么其实把这个公式展开。拿第一项说明
 *  ∑(a_ik + time - in_box_time_(ik) (k从1到x)
 *  = ∑a_ik + ∑time - ∑in_box_time_(ik) (k从1到x)
 * 不难发现，第一项是初始红色的价值总和， 第二项是x∗ti , 第三项是红色玩具各自进入盒子的时间的总和
 * 对于蓝色的玩具也是一样
 *   ∑(a_ik - (time - in_box_time_(iy)) (k从1到y)
 * = ∑a_ik - ∑time + ∑in_box_time_(ik)   (k从1到y)
 * 不过正负号有修改
 *
 * 所以我们要算v(time) 实际上就是算这6个值，都可以各自直接用一个变量进行存储
 *
 */
import java.util.*;
import java.util.stream.Collectors;

//Java要考虑溢出问题
//加和乘都有可能溢出
//这题没告诉你对多少取模，那就把可能溢出的都设置为long

/**
 * 3.数据类型转换
 * 3.1两种方式:数据类型由小到大依次为:byte--short--int--long--float--double 后面两种是浮点型
 *
 * 另外整数类型：long，也被称为长整型,8字节，64位，最大数据存储容量是2的64次方减1，数据范围为负的2的63次方到正的2的63次方减1。
 * PS:1、长整型直接量需要在数字后面加L或l,建议加L 因为更直观
 *    2、运算时若有可能溢出,建议在第一个数字后面加L
 *
 * 3.1.1自动类型转换:由小类型到大类型的转换
 *
 * 3.1.2强制类型转换:由大类型到小类型的转换,强转有可能溢出或丢失精度
 *
 *         语法:(要转换成为的数据类型)变量
 *
 * 3.2两点规则:1.整数直接量可以直接赋值给byte,short,char,但不能超出范围
 *
 *                     2.byte,short,char型数据参与运算时,系统会一律先转成int在运算
 * ————————————————
 * 版权声明：本文为CSDN博主「偷你AD钙」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/m0_57475241/article/details/120475985
 */
public class 神奇的盒子 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); //初始n个玩具
        int[] a = new int[n]; //每个玩具的初始数字
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        String s = sc.next();//n个玩具的颜色
        int m = sc.nextInt();//操作数m
        int[] t = new int[m];//m个时刻 t[m]
        int[] op = new int[m];//具体操作
        for (int i = 0; i < m; i++) {
            t[i] = sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            op[i] = sc.nextInt();
        }

        //6个变量维护6个值
        int sr = 0;//∑a_ik (k从1到x)
        int nr = 0;//ti时刻红色玩具的数量
        int sir = 0;// ∑in_box_time(ix) (k从1到x)

        //下面也是一样
        int sb = 0; //∑a_ik (k从1到y)
        int nb = 0; //ti时刻蓝色玩具的数量
        int sib = 0; //∑in_box_time(iy)) (k从1到y)

        Map<Integer, Integer> in_time = new HashMap<>();//维护每个玩具进入盒子的时刻
        List<Integer> res = new ArrayList<>();//操作为0时，计算盒子内的编号之和

        for (int i = 0; i < m; i++) {
            int ti = t[i];
            int opt = op[i];
            if (opt == 0) {
                int ans = sr + nr * ti - sir
                        + sb - nb * ti + sib;
                res.add(ans);
            }
            else if (opt != 0) {

                //为什么|opt| - 1是对应的颜色下标?
                //因为opt都是时间点以后，保证输入合法
                int p = Math.abs(opt) - 1; //放入玩具的编号对应的颜色下标！所以才要用绝对值 - 1

                //操作数 < 0 则取走编号为p的玩具，消除p的各项贡献
                if (opt < 0) {
                    if (s.charAt(p) == 'R') {
                        sr -= a[p];//红色编号之和 减去拿走的玩具的编号
                        nr--; //红色玩具数量 - 1
                        sir -= in_time.get(p); //in_time之和排除掉这个玩具的in_time
                        a[p] = a[p] + ti - in_time.get(p);//p都已经拿走了为什么还要更新a[p]?
                        in_time.put(p, 0);//把编号为p的玩具 in_time重新设置为0
                    } else if (s.charAt(p) == 'B'){
                        sb -= a[p];
                        nb--;
                        sib -= in_time.get(p);
                        a[p] = a[p] - ti + in_time.get(p);
                        in_time.put(p, 0);
                    }
                }

                //操作数 > 0 则放入编号为p的玩具，更新各项贡献
                if (opt > 0){
                    if (s.charAt(p) == 'R') {
                        sr += a[p];
                        nr++;
                        in_time.put(p, ti);//更新放入时刻为ti
                        sir += in_time.get(p);
                    } else if (s.charAt(p) == 'B'){
                        sb += a[p];
                        nb++;
                        in_time.put(p, ti);
                        sib += in_time.get(p);
                    }
                }
            }
        }

        res.add(0, res.size());
        System.out.println(res.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}