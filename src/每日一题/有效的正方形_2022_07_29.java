package 每日一题;

import org.junit.Test;

import java.util.HashSet;

/**
 * @Author KingofTetris
 * @Date 2022/7/29 12:31
 * 给定2D空间中四个点的坐标 p1, p2, p3 和 p4，如果这四个点构成一个正方形，则返回 true 。
 *
 * 点的坐标 pi 表示为 [xi, yi] 。输入 不是 按任何顺序给出的。
 *
 * 一个 有效的正方形 有四条等边和四个等角(90度角)。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
 * 输出: True
 * 示例 2:
 *
 * 输入：p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
 * 输出：false
 * 示例 3:
 *
 * 输入：p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
 * 输出：true
 *  
 *
 * 提示:
 *
 * p1.length == p2.length == p3.length == p4.length == 2
 * -10^4 <= xi, yi <= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 有效的正方形_2022_07_29 {

    @Test
    public void test(){
      int[] p1 = new int[]{0,0};
      int[] p2 = new int[]{1,1};
      int[] p3 = new int[]{1,0};
      int[] p4 = new int[]{0,1};
      System.out.println(validSquare(p1, p2, p3, p4));
    }

    /**
     * 判断二维空间上，这四个点能否组成一个正方形
     * p1,..,p4的顺序是随机的四个点。
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return
     */
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        /**
         * 首先如果有同样的点那肯定是没法组成四边形的
         */
        if (equals(p1,p2) || equals(p1,p3) || equals(p1,p4) || equals(p2,p3)|| equals(p2,p4) || equals(p3,p4))
            return false;
        /**
         * 反正先判断四条边是否相等。关键是顺序是随机的，你怎么算出4条边？
         * 如果你都算那就会有 4*3。2条邻边，1条对角边 * 4。
         * 那么应该有8个相等，4个相等。
         */
        double[] nums = new double[12];
        nums[0] = distance(p1,p2);
        nums[1] = distance(p1,p3);
        nums[2] = distance(p1,p4);

        nums[3] = distance(p2,p1);
        nums[4] = distance(p2,p3);
        nums[5] = distance(p2,p4);

        nums[6] = distance(p3,p1);
        nums[7] = distance(p3,p2);
        nums[8] = distance(p3,p4);

        nums[9] = distance(p4,p1);
        nums[10] = distance(p4,p2);
        nums[11] = distance(p4,p3);
        int countA = 0;
        int countB = 0;
        boolean used[] = new boolean[12];
        double edgeA = nums[0];
        for (int i = 0; i < 12; i++) {
            if (nums[i] == edgeA){
                countA++;
                used[i] = true;//标记为使用过
            }
        }
        double edgeB = 0;
        for (int i = 0; i < 12; i++) {
            if (used[i] == false){
                edgeB = nums[i];//找到一条没用过的边
            }
        }

        for (int i = 0; i < 12; i++) {
            if (nums[i] == edgeB){
                countB++;
            }
        }
        if ((countA == 8 && countB == 4)|| (countB == 8  && countA == 4)) return true;
        return false;
    }


    /**
     *  使用Map和斜边是普通边的根号2倍这个性质
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return
     */
    public boolean validSquare2(int[] p1, int[] p2, int[] p3, int[] p4) {
        HashSet<Double> set = new HashSet<>();
        //存放点和点之间的距离
        set.add(distance(p1, p2));
        set.add(distance(p1, p3));
        set.add(distance(p1, p4));
        set.add(distance(p2, p3));
        set.add(distance(p2, p4));
        set.add(distance(p3, p4));
        //只有两种结果 并且距离不是0  其他结果全是false
        if (set.size() != 2 || set.contains(0)) {
            return false;
        }
        //正方形的对角线是普通边的根号2倍 ，两边一起平方就是两倍
        Double[] array =  set.toArray(new Double[2]);
        if (array[0] > array[1]) { //如果arr[0]是斜边
            return array[0] * array[0] == 2 * array[1] * array[1];
        } else {//如果arr[1]是斜边
            return array[1] * array[1] == 2 * array[0] * array[0];
        }
        /**
         * 但这个方法实际上是不行的 因为 根号2是无理数 在计算机里面存的数字是有限的 = 1.41.......
         * 平方以后之后，不会等于2
         */
    }

    /**
     * 正方形的任意三个点是等腰直角三角形
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return
     */
    public boolean validSquare3(int[] p1, int[] p2, int[] p3, int[] p4) {
        return isIsoscelesRightTriangle(p1, p2, p3) && isIsoscelesRightTriangle(p1, p2, p4) && isIsoscelesRightTriangle(p2, p3, p4) && isIsoscelesRightTriangle(p1, p3, p4);

    }
    //是否是等腰直角三角形
    public boolean isIsoscelesRightTriangle(int[] p1, int[] p2, int[] p3) {
        int d1 = (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);//求距离，但是不开根号，也就是距离的平方，杜绝无理数的出现
        int d2 = (p1[0] - p3[0]) * (p1[0] - p3[0]) + (p1[1] - p3[1]) * (p1[1] - p3[1]);
        int d3 = (p3[0] - p2[0]) * (p3[0] - p2[0]) + (p3[1] - p2[1]) * (p3[1] - p2[1]);
        return d1 > d2 && d2 == d3 && d1 == d2 + d3 ||
                d2 > d3 && d1 == d3 && d2 == d1 + d3 ||
                d3 > d1 && d2 == d1 && d3 == d2 + d1;
    }


    /**
     * 2维两点距离
     * @param p1
     * @param p2
     * @return
     */
    public double distance(int[] p1,int[] p2){
        int a = p1[0] - p2[0];
        int b = p1[1] - p2[1];
        return Math.sqrt(a*a + b*b);
    }
    public boolean equals(int[] p1,int[] p2){
        return (p1[0] == p2[0] && p1[1] == p2[1]);
    }
}
