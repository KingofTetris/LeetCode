package LeetCode数据结构与算法基础.day4.栈和队列;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author by KingOfTetris
 * @date 2024/8/14
 */
public class 接雨水 {

    @Test
    public void test(){
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int trap = trap(height);
        int trap2 = trapByMonotonousStack(height);
        System.out.println(trap);
        System.out.println(trap2);
    }

    /**
     * 左右最大高度数组进行对比。
     * 这个是用列来求雨水面积
     *
     * 单调栈是反过来 用横来求雨水面积
     * @param height
     * @return
     */
    //计算每一列能够接住的雨水
    //实际上就是 min(lHeight,rHeight) - height[i]
    //左右两边最大的高度的较小值减去当前列的高度，因为宽度都是1 就没必要*1了
    //并且要注意第一个柱子和最后一个柱子不接雨水
    public int trap(int[] height) {
        //如果柱子的数量如果小于2 那没得玩了
        if (height.length < 2) return 0;
        int N = height.length;

        //当前柱子左边比他高的最大值
        int[] maxLeft = new int[N];
        //当前柱子右边比他高的最大值
        int[] maxRight = new int[N];

        //记录当前柱子的左边柱子的最大高度
        maxLeft[0] = height[0];
        for (int i = 1; i < N; i++) {
            //比较当前高度，和左边max的高度
            maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
        }
        //记录当前柱子的右边柱子的最大高度
        maxRight[N - 1] = height[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i + 1]);
        }
        System.out.println(Arrays.toString(maxLeft));
        System.out.println(Arrays.toString(maxRight));
        System.out.println(Arrays.toString(height));
        //求每列柱子的接水面积
        int sum = 0;
        for (int i = 0; i < N; i++) {
            //(左右两根柱子更小的那个 - Height[i])  * 1
            int count = Math.min(maxLeft[i], maxRight[i]) - height[i];
            //如果能接住水，如果是负数是接不了雨水的
            if (count > 0) sum += count;
        }
        return sum;
    }


    public int trapByMonotonousStack(int[] height){
        if (height.length < 2) return 0;
        //用单调栈的思路，横着求接雨水的面积
        //就要找左右第一个比他大的值 - height[i] 就是能储水的高
        //i - stack.poll() - 1 就是储水的宽度
        //右边第一个比他大的元素其实就是peek(),左边第一个比他大的其实就是pop()以后的peek

        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        int sum = 0;
        for (int index = 1; index < height.length; index++) {
            //先取出当前栈顶元素
            int stackTop = stack.peek();
            if (height[index] < height[stackTop]){
                stack.push(index);
            }
//            else if (height[index] == height[stackTop]){
//                // 因为相等的相邻墙，左边一个是不可能存放雨水的，所以pop左边的index, push当前的index
//                stack.pop();
//                stack.push(index);
//            }
            else{
                //pop up all lower value
                int right = height[index];
                while (!stack.isEmpty() && (right > height[stackTop])){
                    int mid = stack.pop(); //pop出来的其实是夹在中间的mid
                    //pop以后操作要注意stack是否为空
                    if (!stack.isEmpty()){
                        int left = stack.peek();
                        int h = Math.min(height[left], right) - height[mid];
                        int w = index - left - 1;
                        int hold = h * w;
                        if (hold > 0) sum += hold;
                        stackTop = stack.peek(); //更新栈顶元素
                    }
                }
                stack.push(index);
            }
        }
       return sum;
    }
}
