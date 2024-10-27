package LeetCode数据结构与算法基础.图与并查集;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 除法求值 {

    //并查集的算法看官解，这里用Floyd算法。
    public double[] calcEquation(List<List<String>> equations,
                                 double[] values, List<List<String>> queries) {
        int count = 0;
        //统计出现的所有字符，并赋予对应的index
        Map<String, Integer> map = new HashMap<>();
        for (List<String> list : equations) {
            for (String s : list) {
                if (!map.containsKey(s)) {
                    map.put(s, count++);
                }
            }
        }

        //构建一个矩阵来代替图结构
        double[][] graph = new double[count + 1][count + 1];

        //初始化
        for (String s : map.keySet()) {
            int x = map.get(s);
            graph[x][x] = 1.0;
        }
        int index = 0;
        for (List<String> list : equations) {
            String a = list.get(0);
            String b = list.get(1);
            int aa = map.get(a);
            int bb = map.get(b);
            double value = values[index++];
            graph[aa][bb] = value;
            graph[bb][aa] = 1 / value;
        }

        //通过Floyd算法进行运算
        int n = count + 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (j == k || graph[j][k] != 0) continue;
                    if (graph[j][i] != 0 && graph[i][k] != 0) {
                        graph[j][k] = graph[j][i] * graph[i][k];
                    }
                }
            }
        }

        //直接通过查询矩阵得到答案
        double[] res = new double[queries.size()];
        for (int i = 0; i < res.length; i++) {
            List<String> q = queries.get(i);
            String a = q.get(0);
            String b = q.get(1);
            if (map.containsKey(a) && map.containsKey(b)) {
                double ans = graph[map.get(a)][map.get(b)];
                res[i] = (ans == 0 ? -1.0 : ans);
            } else {
                res[i] = -1.0;
            }
        }
        return res;
    }
}
