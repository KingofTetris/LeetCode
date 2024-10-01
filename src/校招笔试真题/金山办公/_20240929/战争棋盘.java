package 校招笔试真题.金山办公._20240929;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/29
 */


/**
 * 3 3 2
 * ranko 1 1
 * kotori 2 2
 * 5
 * ranko D
 * ranko W
 * ranko A
 * kotori W
 * kotori W
 */

//写了半天 0% ，真幽默。
//还是因为next和nextLine你没搞清楚，才浪费了这么多时间。
//就算是这个程序也未必就全对，能拿到20%就偷笑了。
    //这题的模拟量真的有点大。
public class 战争棋盘 {

    static String[][] land;
    static HashMap<String, Integer> landMap;
    static HashMap<String, Integer> existMap;
    static HashMap<String, Integer> posX;
    static HashMap<String, Integer> posY;
    static int n;
    static int m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int k = sc.nextInt(); //k方势力
        land = new String[n][m];
        //势力大小map
        landMap = new HashMap<>();
        //存活状态map
        existMap = new HashMap<>();
        //记录势力的当前位置
        posX = new HashMap<>();
        posY = new HashMap<>();
        for (int i = 0; i < k; i++) {
            String name = sc.next();
            int x = Integer.parseInt(sc.next());
            int y = Integer.parseInt(sc.next());

            /**
             * 特殊的，如果有势力一开始就在同一个点，那么一开始就会发生争夺。
             * 唉，根本没想到这点。
             */

            //如果一开始这块地没有势力占领才能直接占领
            if (land[x - 1][y - 1] == null){
                land[x - 1][y - 1] = name;
                existMap.put(name, 1);//一开始都是存活状态
                landMap.put(name, 1);//领土也是1
                //记录势力的当前位置
                posX.put(name, x - 1);
                posY.put(name, y - 1);
            }
            //否则就要打擂台
            else {
                String landName = land[x- 1][y - 1];
                //比较字典序
                if (name.startsWith(landName) || name.compareTo(landName) > 0){
                    land[x - 1][y - 1] = name;
                    existMap.put(name, 1);//一开始都是存活状态
                    //另一个势力被消灭
                    existMap.put(landName,0);
                    landMap.put(name, 1);//领土也是1
                    //记录势力的当前位置
                    posX.put(name, x - 1);
                    posY.put(name, y - 1);
                }
                //否则自己被消灭
                else {
                    existMap.put(name, 0);//一开始都是存活状态
                }
            }
        }


        int q = sc.nextInt();//q次操作
        for (int i = 0; i < q; i++) {
            String curName = sc.next();
            String oper = sc.next(); //为什么读不到最后一个W???
            //如果已经被消灭了就不用再往下走了
            //或者根本就不存在这个势力
            if (existMap.get(curName) == 0 || existMap.get(curName) == null) {
                System.out.println("unexisted empire.");
                continue;
            }
            int curX = posX.get(curName);
            int curY = posY.get(curName);

            //AD 左右动curY WS 上下 动curX
            if ("A".equals(oper)) {
                curY--;
                if (curY < 0){
                    //越界就不用更新了，直接下一步
                    System.out.println("out of bounds!");
                    continue;
                }
                operation(curX,curY,curName,1);
            }
            else if ("D".equals(oper)) {
                curY++;
                if (curY >= m){
                    //越界就不用更新了，直接下一步
                    System.out.println("out of bounds!");
                    continue;
                }
                operation(curX,curY,curName,1);
            }
            else if ("W".equals(oper)) {
                curX--;
                if (curX < 0){
                    //越界就不用更新了，直接下一步
                    System.out.println("out of bounds!");
                    continue;
                }
                operation(curX,curY,curName,0);
            }
            else if ("S".equals(oper)) {
                curX++;
                if (curX >= n){
                    //越界就不用更新了，直接下一步
                    System.out.println("out of bounds!");
                    continue;
                }
                operation(curX,curY,curName,1);
            }

        }
    }

    public static void operation(int curX,int curY,String curName,int mark) {
        //如果这块土地是空的，抢夺成功。
        if (land[curX][curY] == null || existMap.get(land[curX][curY]) == 0) {
            System.out.println("vanquish!");
            land[curX][curY] = curName;
            landMap.put(curName, landMap.get(curName) + 1);
            //更新位置
            if (mark == 0)
            posX.put(curName, curX);
            else
            posY.put(curName,curY);
        }
        //如果是自己的领土
        else if (land[curX][curY].equals(curName)) {
            System.out.println("peaceful.");
            //更新位置
            if (mark == 0)
                posX.put(curName, curX);
            else
                posY.put(curName,curY);
        }
        //如果不是空的，而且是别人的领土，就会发生战斗
        else {
            //先比较势力大小
            String other = land[curX][curY];
            int a = landMap.get(curName);
            int b = landMap.get(other);
            //占领成功
            if (a > b) {
                System.out.println(curName + " wins!");
                land[curX][curY] = curName;
                landMap.put(curName, landMap.get(curName) + 1);
                //更新位置
                if (mark == 0)
                    posX.put(curName, curX);
                else
                    posY.put(curName,curY);
                //失败者被消灭
                existMap.put(other, 0);
            }
            //占领失败
            if (a < b) {
                System.out.println(other + " wins!");
                //失败者被消灭
                existMap.put(curName, 0);
            }
            //如果势力大小一样，比较字典序谁更大
            if (a == b) {
                // b是a的一个前缀则b < a 或者 普通意义上的 a.compareTo(b)
                if (curName.compareTo(other) > 0 || curName.startsWith(other)) {
                    System.out.println(curName + " wins!");
                    land[curX][curY] = curName;
                    landMap.put(curName, landMap.get(curName) + 1);
                    //更新位置
                    if (mark == 0)
                        posX.put(curName, curX);
                    else
                        posY.put(curName,curY);
                    //失败者被消灭
                    existMap.put(other, 0);
                }
                //反过来字典序小的话，就被消灭。
                else {
                    System.out.println(other + " wins!");
                    //失败者被消灭
                    existMap.put(curName, 0);
                }
            }
        }
    }

}
