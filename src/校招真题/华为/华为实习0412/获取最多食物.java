package 校招真题.华为.华为实习0412;

/**
 * @author by KingOfTetris
 * @date 2023/4/19
 * 塔子哥设计的这个游戏是一个冒险类游戏，参与者需要在地图上寻找食物并获得尽可能多的食物，
 * 同时需要注意在游戏过程中所处的位置，因为不同的位置可以通过传送门到达其他位置，可能会影响食物获取的数量。
 *
 * 在游戏开始时，参与者从地上选择一个方格作为起点，每个方格上至多 2 个传送门，
 * 通过传送门可将参与者传送至指定的其它方格。每个方格上都标注了三个数字：id 、 parent-id 和 value 。
 * 其中， id 代表方格的编号， parent-id 代表可以通过传送门到达该方格的另外一个方格的编号， value 代表在该方格获取或失去的食物单位数。
 *
 * 参与者需要在地图上行进，到达每个方格并获取或失去对应的食物单位数，
 * 直到满足退出游戏的条件之一。参与者的最终得分是所获取食物单位数的总和，需要尽可能地高。
 *
 * 需要注意的是地图设计时保证了参与者不可能到达相同的方格两次。
 * 因此，参与者当前所处的方格无传送门，游戏将立即结束。另外，参与者在任意方格上都可以宣布退出游戏，同样会结束游戏。
 *
 * 请计算参与者退出游戏后，最多可以获得多少单位的食物。
 *
 * 输入描述
 * 第一行:方块数 N ( N <= 1e4)
 * 接下来N行每行三个整数 id ， parent-id ， value ，具体含义见题面。
 *
 * 0<= id,parent-id < N,-100 <= value <= 100
 *
 * 特殊的 parent-id 可以取 - 1
 * −1 则表示没有任何方格可以通过传送门传送到此方格，这样的方格在地图中有且仅有一个。
 *
 * 输出描述
 * 输出为一个整数，表示参与者退出游戏后最多可以获得多少单位的食物。
 *
 * 样例
 * 样例1
 * 输入
 *
 * 7
 * 0 1 8
 * 1 -1 -2
 * 2 1 9
 * 4 0 -2
 * 5 4 3
 * 3 0 -3
 * 6 2 -3
 * 输出
 *
 * 9
 * 样例解释
 *
 * 参与者从方格0 出发，通过传送门到达方格4 ，再通过传送门到达方格5 。一共获得8+(−2)+3=9 个单位食物，
 * 得到食物最多或者参与者在游戏开始时处于方格2 ，直接主动宣布退出游戏，也可以获得9 个单位食物。
 *
 *
 * 样例2
 * 输入
 *
 * 3
 * 0 -1 3
 * 1 0 1
 * 2 0 2
 * 输出
 *
 * 5
 * 样例解释
 *
 * 参与者从方格0 出发，通过传送门到达方格2 ，一共可以获得3+2=5 个单位食物，此时得到食物最多。
 */
import java.util.*;
public class 获取最多食物 {
    static ArrayList<Integer>[] adj;//有向邻接表
    static int[] weight;
    public static void main(String[] args){
        Scanner sc  = new Scanner(System.in);
        int n = sc.nextInt();
        adj = new ArrayList[n];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
        weight = new int[n];
        for (int i = 0; i < n; i++) {
           int to = sc.nextInt();
           int from = sc.nextInt();
           if (from != -1) adj[from].add(to);//不能往回走，所以只有from -> to 没有to -> from
           weight[to] = sc.nextInt(); //to格子的食物量
        }

        int max = Integer.MIN_VALUE;//因为value的数值有负数，别取0了，-max
        //接下来任选一个格子作为起点，去找最大可获得食物
        for (int i = 0; i < n; i++) {
            int food = dfs(i);
            max = Math.max(food,max);
        }
        System.out.println(max);
    }



    //还要加上dfs另外一条路的时候，foodMaxNumber要还原。
    public static int dfs(int from){
        int foodMaxNumber = weight[from];
        int max = weight[from];
        for (int i = 0; i < adj[from].size(); i++) {
            int to = adj[from].get(i);
            if (i > 0){ //如果已经是另外一条路，就不能共享，要回溯了。
                //回溯前先记录下上一条路的max
                max = Math.max(max,foodMaxNumber);
                foodMaxNumber = weight[from]; //然后还原
            }
            //然后继续往下走
            int nowFood = foodMaxNumber + dfs(to);
            foodMaxNumber = Math.max(nowFood,foodMaxNumber);
        }
        max = Math.max(max,foodMaxNumber);//最后一条路会直接跳出循环，所以还要和foodMax再比一次
        return max;
    }
}
