package LeetCode数据结构与算法基础.day4.栈和队列;

import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author by KingOfTetris
 * @date 2024/10/26
 */
public class 简化路径 {

    //如果你熟悉JavaAPI的话，

    /**
     * 在 Java 中，Path.of(path).normalize().toString()
     * 用于获取规范化后的路径字符串。规范化路径可以消除路径中的冗余部分，
     * 使其更加简洁和标准化。下面是这行代码的解释：
     * <p>
     * Path.of(path): 使用 Path.of() 方法创建一个 Path 对象，表示指定路径字符串。
     * normalize(): normalize() 方法规范化路径，消除路径中的冗余部分，例如多余的分隔符 . 和 .. \等
     * toString(): 将 Path 对象转换为一个字符串表示。
     *
     * @param path
     * @return
     */
    public String simplifyPathUsingAPI(String path) {
        return Path.of(path).normalize().toString();
    }

    //用栈手动实现
    public String simplifyPath(String path) {
        String[] names = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        for (String name : names) {
            if ("..".equals(name)) { // 对于两个点，需要将目录切换到上一级
                if (!stack.isEmpty()) {
                    // 所以只要栈不为空【因为不管如何取上一级，到/根目录也就停了】，就弹出栈顶元素
                    stack.pollLast(); // 弹出栈顶元素
                }
            }
            else if (name.length() > 0 && !".".equals(name)) { // 不是空字符串或者一个点
                stack.offerLast(name); // 否则就是目录名，直接入栈
            }
        }
        // 全部对last进行poll或者offer就是栈的形式，想想一下
        StringBuilder builder = new StringBuilder();
        if (stack.isEmpty()) {
            builder.append('/');
        } else {
            while (!stack.isEmpty()) {
                builder.append('/').append(stack.pollFirst()); // 先进去的先出来，这样结果才对
            }
        }
        return builder.toString();
    }
}
