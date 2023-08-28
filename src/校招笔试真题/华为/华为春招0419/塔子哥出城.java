package 校招笔试真题.华为.华为春招0419;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @Author KingofTetris
 * @Date 2023/4/24 10:55
 */
public class 塔子哥出城 {

    static ArrayList<Integer>[] adj;
    static int[] trafficJam;
    static ArrayList<String> paths;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n  = sc.nextInt();
        int m  = sc.nextInt();
        adj = new ArrayList[n + 1];
        trafficJam = new int[n + 1];
        paths = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        while (m-- > 0){
            int from = sc.nextInt();
            int to = sc.nextInt();

            //并且插入的时候要保证小的在前，大的在后

            int flag = 0;
            if (adj[from].size() == 0) adj[from].add(to);
            else{
                for (Integer neighbor : adj[from]) {
                    if (to < neighbor){
                        //如果比neighbor要小，就取代neighbor的位置。neighbor往后移
                        adj[from].add(adj[from].indexOf(neighbor),to);
                        flag = 1;
                        break;//add完记得break，不然就有可能一直添加了
                    }
                }
                if (flag == 0) adj[from].add(to);//如果比里面的所有元素都大，直接添加到末尾
            }
        }

        int jamNum  = sc.nextInt();
        while (jamNum-- > 0){
            trafficJam[sc.nextInt()] = 1;
        }



        DFS(0,"0");

        String res = "";
        int min = Integer.MAX_VALUE;
        if (paths.size() == 0) System.out.println("NULL");
        else {
            for (String path : paths) {
                if (path.length() < min){ //如果长度都是最小，就要取最小的路口号
                    res = path;
                    min = path.length();
                }
            }
        }
        System.out.println(res);
    }

    //从根节点开始DFS
    private static void DFS(int i,String path) {
        int num = 0;
        String pathOld = path;
        for (Integer neighbor : adj[i]) {
            if (num != 0) path = pathOld;//如果开始新的路，就要回溯还原0->...->i的路径
            if (trafficJam[neighbor] == 1) continue;//如果路口堵车，换一条路
            else {
                String p = String.valueOf(neighbor);
                path = path + "->" + p;
                DFS(neighbor,path);//继续往下走
                num++;
            }
        }
        if(adj[i].size() == 0) paths.add(path);//走到出城口加入paths
    }
}
