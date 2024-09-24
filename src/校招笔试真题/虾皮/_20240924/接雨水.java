package 校招笔试真题.虾皮._20240924;

import org.junit.Test;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author by KingOfTetris
 * @date 2024/9/24
 */
public class 接雨水 {

    @Test
    public void test(){
        int[] height = {1,3,2,2,9,1,4};
        int res = MaxCollectedWater(height);
        System.out.println(res);
    }
    public int MaxCollectedWater(int[] height) {
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
