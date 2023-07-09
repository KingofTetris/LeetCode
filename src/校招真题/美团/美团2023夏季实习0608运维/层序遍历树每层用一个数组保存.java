package 校招真题.美团.美团2023夏季实习0608运维;

import LeetCode数据结构入门.day5.树.TreeNode;
import com.sun.source.util.Trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/6/21
 */
public class 层序遍历树每层用一个数组保存 {
    public static void main(String[] args) {
        //假设是一颗满二叉树，给你一个一维数组作为输入，请你层序遍历这棵树然后每层输出为一个数组
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String[] s1 = s.split(" ");
        if (s1.length == 0){
            System.out.println("[]");
            return;
        }
        //计算这棵树有多深 才能确定你数组要用多大
        //满二叉树的树高 h = log2(n+1)
        //java算普通对数就得用换底公式 loga(b) = ln(b)/ln(a)
        //又要用到一个向上取整函数
        int h = (int) Math.ceil(Math.log(s1.length+1) / Math.log(2));
//        System.out.println(h);
        //有了树深就能算最深的节点最多有多少个
        int maxNodeNum = (int) Math.pow(2,h-1);
        String[][] res = new String[h][maxNodeNum];
        Queue<String> queue = new LinkedList<>();
        queue.add(s1[0]);//头节点入队
        int depthNow = 1;
        int sizeNow = 0;
        int maxSizeLayerNow = 1;//第一层就是1
        int index = 1;//记录当前数组位置。
        while (!queue.isEmpty()) {
            String poll = queue.poll();
            res[depthNow - 1][sizeNow] = poll;
            sizeNow++;
            if (sizeNow == (maxSizeLayerNow)){ //一层满了下一层
                depthNow++; //下一层重新计算
                maxSizeLayerNow = (int) Math.pow(2,depthNow-1);
                sizeNow = 0;
            }
            if (index < s1.length) {
                //如果存在的话 当前节点的左孩子入队
                queue.add(s1[index++]);
            }
            if (index < s1.length) {
                //如果存在的话 当前节点的右孩子入队
                queue.add(s1[index++]);
            }
        }

        for (String[] re : res) {
            for (String s2 : re) {
                if (s2 != null) //字符串判断为不为空直接等于号就行了
                System.out.print(s2 + "\t");
            }
            System.out.println();
        }
    }
}
