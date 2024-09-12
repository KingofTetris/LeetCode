package LeetCode周赛._20240811周赛;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/8/11
 */
public class 矩阵中的蛇 {

    public static void main(String[] args) {
        矩阵中的蛇 s = new 矩阵中的蛇();
        List<String> commands = new ArrayList<>();
        commands.add("RIGHT");
        commands.add("LEFT");
        int i = s.finalPositionOfSnake(2, commands);
        System.out.println(i);
    }
    public int finalPositionOfSnake(int n, List<String> commands) {
        int x = 0, y = 0;
        //生成的测评数据确保蛇不会移动到矩阵的边界外。
        //才能这么走 不然还得加判断。
        for (String c : commands) {
            if (c.equals("LEFT")) {
                y -= 1;
            } else if (c.equals("RIGHT")) {
                y += 1;
            } else if (c.equals("UP")) {
                x -= 1;
            } else {
                x += 1;
            }
        }
        //计算方法就是x在第几行，y在第几列
        //最终位置就是 x * n + y
        return x * n + y;
    }
}
