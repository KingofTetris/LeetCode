package 校招面试真题;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author by KingOfTetris
 * @date 2024/10/23
 */
public class TopK_但K等于3 {

    //很多人一上来就说用堆，用堆真的会在这个数量级 10^6 里更快吗？那未必吧
    public static void main(String[] args) {
        for (int t = 0; t < 10; t++) {
            System.out.println("第" + t + "次");
//            int len = (int) Math.pow(10, 6); //10^6明显是3个变量更好
            int len = (int) Math.pow(10, 9); //很显然topK的做法，肯定是K个变量遍历一次的做法是最佳。
            //但问题是如果我们从10亿级别的数据里面取200个数，难道我要写200个变量吗？
            //当然不可能嘛，那样代码要怎么写?。
            //当然求topK，K未知的情况下，直接使用堆排序，也不是真就是最佳解法
            //还有一种基于快速排序的topK求法，见 TopK_但是快排
            int[] nums = new int[len];
            Random random = new Random();
            for (int i = 0; i < nums.length; i++) {
                nums[i] = random.nextInt(1,Integer.MAX_VALUE);
            }
            long startTime1 = System.currentTimeMillis();
            int[] res1 = findMaxThree(nums);
            long endTime1 = System.currentTimeMillis();
            System.out.println(Arrays.toString(res1));
            System.out.println("costTime：" + (endTime1 - startTime1) + "ms");

            long startTime2 = System.currentTimeMillis();
            int[] res2 = findMaxThreeByHeap(nums);
            long endTime2 = System.currentTimeMillis();
            System.out.println(Arrays.toString(res2));
            System.out.println("costTime：" + (endTime2 - startTime2) + "ms");

            System.out.println();
        }

    }

    //如果我们直接排序，快排，归并，堆排序，那么时间复杂度其实是
    //O(nlogn)
    //但我们只需要top3，这个时候其实一种被忽略的排序----冒泡排序就会出现了
    //我们知道冒泡排序每次都是相邻两个数进行比较，谁大谁往后。那么从头开始，3趟以后，最后3个数就最大的。
    //这样的复杂度就是O(3*N) ->O(N) 而且不需要额外的空间消耗。

    //但其实还可以从O(3N)进一步优化，一趟比较就完成三个数字的选择
    private static int[] findMaxThree(int[] nums) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            //要注意更新的顺序
            //注意等于号也要加上去
            if (nums[i] >= first) {
                third = second;
                second = first;
                first = nums[i];
            } else if (nums[i] >= second) {
                third = second;
                second = nums[i];
            } else if (nums[i] >= third) {
                third = nums[i];
            }
        }
        return new int[]{third, second, first};
    }

    //我们再写一个堆排序的来进行对比
    //记住求最大用小根堆，求最小用大根堆
    //面试官肯定会问你，小根堆的性质不是根比左右节点小吗?那你为什么要小根堆呢？大根堆不是能直接取到最大值吗？反过来也是一样
    //这个问题其实就在于我们是求topK 不是求min max
    //求topK的这个过程会牵涉到插入新数字，删除堆顶元素，把堆底元素交换到堆顶以后，
    // 堆调整时进行的上浮和下沉
    //才能动态保证拿到topK
    //而如果你要用大根堆去求topMaxK，那么过了K个值以后，一旦来一个比堆顶大的元素过来，那么需要遍历整个堆，去删除掉最小的值。
    //再插入，再调整，效率太慢，这就是为什么求topMaxK不用大根堆的愿意。
    //反过来也是一样。

    private static int[] findMaxThreeByHeap(int[] nums) {
        //求最大，用小根堆。
        PriorityQueue<Integer> pq = new PriorityQueue<>(3, (o1, o2) -> o1 - o2);
        for (int num : nums) {
            if (pq.size() < 3) {
                pq.offer(num);
            } else {
                //比如小根堆现在是 4 5 8
                //现在再来一个8
                //我们当然是抛弃掉4，把8给放进去。
                //变成 5 8 8
                if (num >= pq.peek()) {
                    pq.poll();
                    pq.offer(num);
                }
            }
        }
        int[] res = new int[3];
        for (int i = 0; i < 3; i++) {
            res[i] = pq.poll();
        }
        return res;
    }
}
