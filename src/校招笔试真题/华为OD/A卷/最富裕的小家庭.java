package 校招笔试真题.华为OD.A卷;

import java.util.Arrays;
import java.util.Scanner;

public class 最富裕的小家庭 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        // 每个元素被输入赋予的值
        long wealth[] = new long[n + 1];
        // 每个小家庭的财富总值 父子关系组成一个小家庭
        long family[] = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            int temp = in.nextInt();
            wealth[i] = temp;
            family[i] = temp;
            // 给每个节点记录自己 也为自己做小家庭的父节点的条件下记录初始值
        }

        for (int i = 1; i <= n - 1; i++) {
            int tempF = in.nextInt();
            int tempS = in.nextInt();
            family[tempF] += wealth[tempS];
            // 更新每个小家庭的值的总和
            // 由于我们只求小家庭的最大值，所以不存在嵌套式的东西，每个小家庭都是父节点自己的值与和它相连的子节点的值的和
        }

        System.out.println(Arrays.stream(family).max().getAsLong());
        // 计算family数组的最大值
    }
}
