package 校招笔试真题.华为.华为od_0408;

/**
 * @author by KingOfTetris
 * @date 2023/4/13
 */
import java.util.*;

public class 箱子之形摆放{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = sc.nextInt();

        ArrayList<Character>[] res = solution(s,n);

        for(ArrayList<Character> chars : res){
            String str = "";
            for(int i = 0;i<chars.size();i++){
                str += chars.get(i);
            }
            if(str.length() != 0) System.out.println(str);
        }
    }

    public static ArrayList<Character>[] solution(String s,int n){
        //这个只是初始化了一个ArrayList[]数组，但是里面的元素还全部都是null
        //邻接表也是一样的。
        //ArrayList<Integer>[] adj = new ArrayList[n]
        //然后adj[i]每个去初始化
        ArrayList<Character>[] res = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            res[i] = new ArrayList<>();
        }
        //i % n     结果是0->n-1
        //n-1 - (i%n)  结果就是n-1->0
        boolean flag = true;
        for(int i = 0; i < s.length();i++){
            //每一轮取反
            if(i!= 0 && i % n == 0 ) flag = !flag;
            if(flag == true) res[i % n ].add(s.charAt(i));
            else res[(n-1) - (i % n)].add(s.charAt(i));
        }
        return res;
    }
}
