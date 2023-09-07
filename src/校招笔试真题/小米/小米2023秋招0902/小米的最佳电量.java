package 校招笔试真题.小米.小米2023秋招0902;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/9/2
 */

class Task{
    int consumerPower;
    int needPower;
    public Task(int consumerPower, int needPower) {
        this.consumerPower = consumerPower;
        this.needPower = needPower;
    }
}

/**
 * 1:10,2:12,3:10
 * 上面是1组任务，:号前面是完成该任务需要消耗的电脑，后面是进行该任务至少需要多少电量。
 * 输入一组任务，请你返回最少需要多少电量。
 */
public class 小米的最佳电量 {
    static int MAX_POWER = 4800;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] ss = s.split(",");
        int len = ss.length;//有多少组任务
        Task prev = new Task(0,0);
        for (int i = 0; i < len; i++) {
            String[] tmp = ss[i].split(":");//分开
            Task task = new Task(Integer.parseInt(tmp[0]),Integer.parseInt(tmp[1]));
            if (i > 0){
                prev = mergeTask(task,prev);
            }else {
                prev.consumerPower = task.consumerPower;
                prev.needPower = task.needPower;
            }
        }

        if (prev.needPower <= MAX_POWER) System.out.println(prev.needPower);
        else System.out.println(-1);
    }
    private static Task mergeTask(Task task, Task prev) {
        //合并任务，以 2:45 10:50为例
        //结果会在 12:52 和 12 : 55 之间产生。
        //那么我们当然选择52
        int c = task.consumerPower + prev.consumerPower;
        int n = Math.min(task.needPower + prev.consumerPower,prev.needPower + task.consumerPower);
        return new Task(c,n);
    }
}


