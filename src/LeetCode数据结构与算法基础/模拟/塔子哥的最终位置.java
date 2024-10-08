package LeetCode数据结构与算法基础.模拟;

import java.util.Scanner;

public class 塔子哥的最终位置 {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String actions = sc.nextLine();

         // 初始位置和方向
        int x = 0, y = 0;
        int dx = 0, dy = 1; // 初始朝向北

        // 模拟行为方式
        for (char action : actions.toCharArray()) {
            if (action == 'W') { //W向前走
                x += dx;
                y += dy;
            } else if (action == 'A') { //'A'转向
                int temp = dx;
                dx = -dy; //A每转一次dx就换个方向
                dy = temp;// dy就变成原来的dx
            } else if (action == 'D') { //'D'转向
                int temp = dx;
                dx = dy; // D每转一次 dx 就变成dy的方向
                dy = -temp; // dy换个方向
            } else if (action == 'S') {
                // 呆在原地，不做任何变化
            }
        }

        // 输出最终位置
        System.out.println(x + " " + y);
        sc.close();
    }
}
