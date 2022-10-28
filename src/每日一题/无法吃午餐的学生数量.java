package 每日一题;

import java.util.LinkedList;

/**
 * @Author KingofTetris
 * @Date 2022/10/19 9:37
 */
public class 无法吃午餐的学生数量 {

    //因为队列是循环的每个元素都会被访问
    // 那么其实学生的顺序并不重要
    // 我们只需要统计喜欢0和1的各自人数
    // 然后依次访问栈顶即可
    public int countStudents2(int[] students, int[] sandwiches) {
        int[] count = new int[2];
        for (int student : students) {
            count[student]++;
        }

        for (int sandwich : sandwiches) {
            if (count[sandwich] > 0) count[sandwich]--;
            else break;//如果已经等于0了，没人喜欢了，就break
        }

        //返回统计人数即可
        return count[0] + count[1];
    }
    /**
     * 简单模拟
     * 当栈顶是0，队列全是1
     * 或者栈顶是1，队列全是0
     * 返回队列的人数即可
     * @param students
     * @param sandwiches
     * @return
     */
    public int countStudents(int[] students, int[] sandwiches) {
        LinkedList<Integer> queue = new LinkedList<>();
        LinkedList<Integer> stack = new LinkedList<>();


        //元素入队，入栈
        for (int student : students) {
            queue.offer(student);
        }

        for (int sandwich : sandwiches) {
            stack.addLast(sandwich);
        }

        while (!queue.isEmpty()){
            if (!queue.contains(stack.peek())) return queue.size(); //如果队列不包含栈顶元素了，那就返回队列长度
            //如果队头和栈顶相等
            //取走三明治，出队
            if (queue.peek() == stack.peek()){
                queue.poll();//出队
                stack.pop();//取走三明治
            }
            else { //如果不同
                //排到队列末尾
                Integer student = queue.poll();
                queue.offer(student);
            }
        }

        return 0;
    }


}
