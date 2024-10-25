package LeetCode数据结构与算法基础.手撕算法;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/10/24
 * <p>
 * 给 n 个进程，每个进程都有一个独一无二的 PID （进程编号）和它的 PPID （父进程编号）。
 * <p>
 * 每一个进程只有一个父进程，但是每个进程可能会有一个或者多个孩子进程。
 * 它们形成的关系就像一个树状结构。只有一个进程的 PPID 是 0 ，意味着这个进程没有父进程。
 * 所有的 PID 都会是唯一的正整数。
 * <p>
 * 我们用两个序列来表示这些进程，第一个序列包含所有进程的 PID ，第二个序列包含所有进程对应的 PPID。
 * <p>
 * 现在给定这两个序列和一个 PID 表示你要杀死的进程，函数返回一个 PID 序列，
 * 表示因为杀这个进程而导致的所有被杀掉的进程的编号。
 * <p>
 * 当一个进程被杀掉的时候，它所有的孩子进程和后代进程都要被杀掉。
 * <p>
 * 你可以以任意顺序排列返回的 PID 序列。
 * <p>
 * <p>
 * 输入:pid=[1,3,10,5]，ppid=[3,0,5,3]，kill=5
 * 输出:[5,10]
 * 解释:涂为红色的进程是应该被杀掉的进程
 * 示例 2:
 * 输入:pid=[1]，ppid=[0]，ki11=1
 * 输出:[1]
 */
public class 杀掉进程 {

    /**
     * 大概说下题意，给出n个进程，每个进程都有一个唯一的进程号，
     * 每个进程只有一个父进程，但一个进程可能有多个子进程，
     * 我们用pid和ppid两个list来保存所有的进程和其父进程。每当杀死一个进程的时候，
     * 其全部子进程都必须被杀死，现在给出一个进程pid，让你找出杀死它时候必须杀死哪些进程？以list返回。
     * 　　我开始尝试了一把直接递归删除子进程的方式，递归的方式简单易懂，仅仅几行。
     */
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        ArrayList<Integer> ans = new ArrayList<Integer>();
        ans.add(kill);
        for (int i = 0; i < ppid.size(); i++) {
            if (ppid.get(i) == kill) {
                ans.addAll(killProcess(pid, ppid, pid.get(i)));
            }
        }
        return ans;
    }


    /**
     * 　代码中我很暴力的通过遍历的方式来查找子进程，然后再递归删除，提交后毫无疑问TLE了。
     * 认真思考下，这个问题的关键点在于如何高效的找出每个进程的所有子进程。本来想把父子进程做成K-V对放到map里，
     * 结果发现jdk并没有提供multimap，不过这也难不到我，我把V做成子进程list放进去，
     * 再通过递归的方式实现kill，最终代码见文末。
     * 　　本题最重要的其实是构建一个高效的查找数据结构，剩下的就简单，我代码最终执行耗时83ms，
     * 超越了70%的人。其实这里我用到了递归，比较耗时，如果改成非递归的方式，性能还能优化不少。
     * 　　其实说白了，这到底就是遍历多叉树，所以存在深度优先遍历(DFS)和广度优先遍历(BFS)两种方式，
     * 我直接用递归写其实是深度优先遍历，有兴趣可以尝试下非递归的DFS和BFS，其实很简单。
     */

    public List<Integer> killall(HashMap<Integer, ArrayList<Integer>> map, int kill) {
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(kill);
        ArrayList<Integer> list = map.get(kill);
        if (null == list)
            return ans;
        for (int i = 0; i < list.size(); i++) {
            ans.addAll(killall(map, list.get(i)));
        }
        return ans;
    }

    public List<Integer> killProcess2(List<Integer> pid, List<Integer> ppid, int kill) {
//        ArrayList<Integer> ans = new ArrayList<>();
//        ans.add(kill);
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < ppid.size(); i++) {
            if (!map.containsKey(ppid.get(i))) {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(pid.get(i));
                map.put(ppid.get(i), list);
            } else {
                map.get(ppid.get(i)).add(pid.get(i));
            }
        }
        return killall(map, kill);
    }
}
