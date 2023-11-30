package LeetCode数据结构与算法基础.DFS与BFS;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author KingofTetris
 * @File 图像渲染
 * @Time 2021/9/24  23:22
 */

/*733. 图像渲染
        有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。

        给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，
        让你重新上色这幅图像。

        为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，
        接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，
        重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。

        最后返回经过上色渲染后的图像。

        示例 1:

        输入:
        image = [[1,1,1],[1,1,0],[1,0,1]]
        sr = 1, sc = 1, newColor = 2
        输出: [[2,2,2],[2,2,0],[2,0,1]]
        解析:
        在图像的正中间，(坐标(sr,sc)=(1,1)),
        在路径上所有符合条件的像素点的颜色都被更改成2。
        注意，右下角的像素没有更改为2，
        因为它不是在上下左右四个方向上与初始点相连的像素点。
        注意:

        image 和 image[0] 的长度在范围 [1, 50] 内。
        给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。
        image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。*/
public class 图像渲染 {

   /* Queue使用时要尽量避免Collection的add()和remove()方法，add()和remove()方法在失败的时候会抛出异常。

    要使用offer()来加入元素，使用poll()来获取并移出元素。

    它们的优点是通过返回值可以判断成功与否，
     如果要使用前端而不移出该元素，使用element()或者peek()方法。
     值得注意的是LinkedList类实现了Queue接口，因此我们可以把LinkedList当成Queue来用。
*/

    //可以通过两个一维数组实现二维数组的上下左右移动
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, 1, -1, 0};
    // + (1,0) (0,1) (0,-1) (-1,0) 右上下左

//    执行用时：2 ms 在所有 Java 提交中击败了16.91%的用户
    //内存消耗：39.3 MB, 在所有 Java 提交中击败了48.74% 的用户
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        //法一：BFS 需要队列辅助
        //BFS搜索，相同像素值就入队，并更新像素值，防止重复入队
        int currColor = image[sr][sc];
        if(currColor == newColor){
            return image;//一样就不用改
        }
        int n = image.length;//行数
        int m = image[0].length;//列数

        //因为Queue是接口不能实例化，所以要用LinkedList来实例化
        //可用ctrl+alt+u调出类的Diagram 父子结构 实现接口 一目了然
        Queue<int[]> queue = new LinkedList<int[]>();
        //把指定点入队
        queue.offer(new int[]{sr,sc});
        //新像素赋值
        image[sr][sc] = newColor;

        //队列不空时循环
        while(!queue.isEmpty()){
            //因为存的是数组，所以要先赋cell，麻烦一点
            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];

            for (int i = 0; i < 4; i++) {
                //循环4次其实就是
                //上下左右移动
                int mx = x + dx[i];
                int my = y + dy[i];

                //前面4个条件都是限制不出届
                //最后的条件用于判断是否可达，可达就继续延申。
                //同时也避免了重复入队 因为比较的是旧像素值
                if(mx >= 0 && mx < n && my>=0 && my< m && image[mx][my] == currColor){
                    //一样就把可达点的位置入队，并更改可达点的像素值
                    queue.offer(new int[]{mx,my});
                    image[mx][my] = newColor;
                }
            }
        }
        return image;
    }

    //法二 DFS实现
//    我们从给定的起点开始，进行深度优先搜索。每次搜索到一个方格时，
//    如果其与初始位置的方格颜色相同，就将该方格的颜色更新，以防止重复搜索；
//    如果不相同，则进行回溯

/*    执行用时：1 ms, 在所有 Java 提交中击败了65.55%的用户
    内存消耗：39.3 MB, 在所有 Java 提交中击败了51.03% 的用户*/

    //数据对比明显是DFS快很多
    public int[][] floodFill_DFS(int[][] image, int sr, int sc, int newColor) {
        int currColor = image[sr][sc];
        if (currColor == newColor)
            return  image;
        dfs(image,sr,sc,currColor,newColor);
        return  image;
    }

    public void dfs(int[][] image, int x, int y, int color, int newColor) {
        if(image[x][y] == color){
            //同色可达，更新
            image[x][y] = newColor;

            //DFS直接递归就行了，不用在外面加上取队头元素
            //还要用while(!queue.isEmpty())来控制
            for (int i = 0; i < 4; i++) {
                 int mx = x + dx[i];
                 int my = y + dy[i];
                 if( mx>=0 && mx < image.length && my >= 0 && my < image[0].length){
                     //ctrl+alt+enter向上换行
                     //shift+enter 向下换行

                     //只要点还符合边界限制就DFS搜索
                     //递归会自己回溯 完美的特性
                     dfs(image,mx,my,color,newColor);
                 }
            }
        }
    }

}
