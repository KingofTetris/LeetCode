package 校招真题.美团.美团2023春招0408;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/4/10
 * <p>
 * 小团班级的座位排成了 n 行（行从 1 到 n 编号），共有 m 个大列（大列从 1 到 m 编号），
 * 每个大列中有 a 个小列（小列从 1 到 a 编号），
 * 大列与大列之间有一个过道。小团的班级每周会换一次座位，
 * 首先所有同学都换到后一行，最后一行的同学换到第一行，
 * 然后所有同学都移动到自己右边的那个大列的相同小列上，
 * 在最右大列的同学移动到最左大列。换句话说，
 * 对于坐在第 i<n 行的同学，新位置在第 i+1 行，如果 i=n，那么新位置在第一行；
 * 对于坐在第 j<m 大列的同学，新位置在第 j+1 大列，如果 j=m，那么新位置在第一大列；
 * 对于坐在第 k 小列的同学，新位置仍然在第 k 小列。
 * <p>
 * 小团的学校最近换了一批学生桌椅。
 * 这批学生桌椅的优点在于可以调节桌子的高度，
 * 一些同学调整了桌子高度，但是另一些没有。
 * 这样换座就变得麻烦了起来，
 * 如果一位调整了桌子高度的同学换到了未调整桌子高度同学的位置，
 * 他就会调整新位置的桌子到他想要的高度，而一位没有调整桌子高度的同学换到了调整过桌子高度同学的位置，
 * 他也会调整新位置的桌子高度，使其恢复原高度。
 * 现在小团的班级要进行换座位了，给出换座位前班级所有桌子的情况，小团想知道，
 * 换一次位置后，有多少同学需要重新调整桌子高度。
 * <p>
 * 输入描述
 * 输入第一行包含三个数 n, m, a，意义如题目所示。
 * <p>
 * 接下来 n 行，每行 m 个长度为 a 的 01 字符串，
 * 表示目前小团班上的桌子情况。其中 0 表示这个位置未调节桌子高度，1 表示已调节桌子高度。
 * <p>
 * 对于全部数据，1 ≤ n, m ≤ 200, n × m ≥ 2, 1 ≤ a ≤ 5。 数据量很小直接模拟就行了
 * <p>
 * 输出描述
 * 输出一行一个整数，表示换座位后有多少同学需要重新调整桌子高度。
 * <p>
 * <p>
 * 样例输入
 * 3 3 2
 * 01 10 00
 * 10 00 11
 * 01 00 00
 * 样例输出
 * 8
 */
public class 换座位 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), a = sc.nextInt();
        String[][] tables = new String[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tables[i][j] = sc.next(); //next()也是用空格隔开
            }
        }
        //TODO 记住这个打印多维数组 Arrays.deepToString，直接一行一行打印多维数组
//        System.out.println(Arrays.deepToString(tables));
        int solution = solution(tables);
        System.out.println(solution);
    }

    public static int solution(String[][] tables) {
        int n = tables.length;
        int m = tables[0].length;
        int a = tables[0][0].length();

        /**
         * 开始模拟，每个位置和自己的右下角进行对比
         * 要注意边界是 n - 1 , m - 1不是n,m
         *
         *                 //正常情况 i,j小于n - 1,m - 1
         *                 if (i < n - 1 && j < m - 1)  later = tables[i + 1][j + 1];
         *                 //边界情况 i == n - 1
         *                 else if (i == n - 1){
         *                     //j < m
         *                     //切换到第0行，j + 1列
         *                     if (j < m - 1) later = tables[0][j + 1];
         *                     //j == m
         *                     //切换到第0行，第0列
         *                     if (j == m - 1) later = tables[0][0];
         *                 }
         *                 //边界情况 j == m - 1
         *                 else if(j == m - 1){
         *                     //i < n
         *                     //切换到第i + 1行，0列
         *                     if (i < n - 1) later = tables[i + 1][0];
         *                     //i == n
         *                     //切换到第0行，第0列
         *                     if (i == n - 1) later = tables[0][0];
         *                 }
         * 另外这种写边界的条件太恶心了，直接取余其实有可以实现循环
         *
         *  [(i+1)%n,(j+1)%m]
         */
        int res = 0;
        String later;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                String now = tables[i][j];
                //有了now 和 later 两者字符串进行比较
                //如果对应的字符不一样，就需要调整
                later = tables[(i + 1) % n][(j + 1) % m];
                for (int k = 0; k < a; k++) {
                    if (now.charAt(k) != later.charAt(k)) res++;
                }
            }
        }
        return res;
    }
}
