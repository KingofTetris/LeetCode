package 算法设计与分析和助教课.Stack;

import java.util.Scanner;
import java.util.Stack;
public class Main {
        public static void main(String[] args){
            Scanner scanner = new Scanner(System.in);
            /*输入入栈元素的个数*/
            int n = scanner.nextInt();
            int[] sequence = new int[n];

            /*按顺序输入入栈元素的编号*/
            for (int i=0; i<n; i++){
                sequence[i] = scanner.nextInt();
            }

            /*没有出栈的压栈到stack栈中，输出的时候逆序输出*/
            Stack<Integer> stack = new Stack<Integer>();
            /*出栈的元素压栈到queue栈中，输出的时候正序输出*/
            Stack<Integer> queue = new Stack<Integer>();

            /*
             *1、初始状态有stack、queue两个栈，可以进行两个操作，一个是sequence中的元素入栈stack，对应in函数，一个是
             *   出栈操作，stack中的元素出栈（stack中元素不为空），压到queue栈中，第一个操作执行完了之后执行第二个。
             *2、每次执行读入元素或者元素出栈之后都可以重复刚才所说操作，继续读入数据，然后出栈。
             *3、当读取到sequence最后一个元素的时候，先正序输出queue中的元素，在逆序输出stack中的元素，然后回退。
             *4、回退的时候要把原来的操作撤销，比如读入数据的stack.push(sequence[nextPosition]);in函数执行完了之后要撤销就是
             *   stack.pop();对应得out函数也是：queue.push(stack.pop())对应：if (!queue.isEmpty()) stack.push(queue.pop());
             */
            next(stack,sequence,0,queue);
        }

        public static void in(Stack<Integer> stack,int[] sequence,int nextPosition,Stack<Integer> queue){
            if (nextPosition == sequence.length) {
                String result = "";
                for(int i=0; i< queue.size(); i++){
                    result+=queue.elementAt(i)+" ";
                }
                for (int i=stack.size()-1; i>=0; i--){
                    result+=stack.elementAt(i)+" ";
                }
                System.out.println(result.substring(0,result.length()-1));
                return;
            }
            next(stack, sequence, nextPosition, queue);
        }

        public static void out(Stack<Integer> stack,int[] sequence,int nextPosition,Stack<Integer> queue){
            next(stack,sequence,nextPosition,queue);
        }

        public static void next(Stack<Integer> stack, int[] sequence, int nextPosition, Stack<Integer> queue){
            stack.push(sequence[nextPosition]);
            in(stack, sequence, nextPosition+1, queue);
            stack.pop();

            if (!stack.isEmpty()) {
                queue.push(stack.pop());
                out(stack, sequence, nextPosition, queue);
                if (!queue.isEmpty()) stack.push(queue.pop());
            }
        }
}
