package 校招笔试真题.京东.春招20230408.秋招20230826;

import java.util.Scanner;
// OK，一小时2.75道。京东面试很稳。
public class 塔子哥的训练 {
    // AK
    public static void main(String[] agrs) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        //分配队伍的战斗力
        char[] teams = new char[n + 1];
        int[] lvs = new int[n + 1];
        int[] res = new int[n + 1];//0表示w,1表示l
        for (int i = 1; i <= n; i++) {
            char team = sc.next().toCharArray()[0];
            int lv = sc.nextInt();
            teams[i] = team;
            lvs[i] = lv;
        }

        for (int i = 0; i < m; i++) {
            int p1 = sc.nextInt();
            int p2 = sc.nextInt();
            //是否公开身份
            char openRole1 = sc.next().toCharArray()[0];
            char openRole2 = sc.next().toCharArray()[0];
            //两个有一个已经是loser了不用再比了，下一场。
            if (res[p1] == 1 || res[p2] == 1) {
                continue;//如果有一个是loser 那就不用再比了。
            }
            //正常情况下继续
            //1.两个都隐藏身份，下一场
            if (openRole1 == 'N' && openRole2 == 'N') {
                continue;
            }
            //2.P1隐藏，P2公开
            if (openRole1 == 'N' && openRole2 == 'Y') {
                if (teams[p1] == 'R') {
                    //两个R
                    if (teams[p2] == 'R') {
                        continue;
                    }
                    //P2是blue，p1忍不了直接上去干。
                    if (teams[p2] == 'B') {
                        if (lvs[p2] > lvs[p1]) {
                            res[p1] = 1;//p1负
                        } else if (lvs[p2] < lvs[p1]) {
                            res[p2] = 1;
                        } else { //两败俱伤
                            res[p1] = 1;
                            res[p2] = 1;
                        }
                    }
                }
                //如果p1是'B'
                if (teams[p1] == 'B') {
                    //如果p2也是'B'
                    if (teams[p2] == 'B') {
                        continue;
                    }
                    //如果p2是'R'
                    if (teams[p2] == 'R') {
                        if (lvs[p1] > lvs[p2]) {
                            res[p2] = 1;//p2负
                        } else if (lvs[p1] <= lvs[p2]) {
                            continue;//如果打不过p2,那就忍了。
                        }
                    }
                }
            }
            //3.p1公开，p2隐藏
            if (openRole1 == 'Y' && openRole2 == 'N') {
                //现在去考虑p2
                if (teams[p2] == 'R') {
                    //两个R
                    if (teams[p1] == 'R') {
                        continue;
                    }
                    //P1是blue，p2忍不了直接上去干。
                    if (teams[p1] == 'B') {
                        if (lvs[p1] > lvs[p2]) {
                            res[p2] = 1;//p2负
                        } else if (lvs[p1] < lvs[p2]) {
                            res[p1] = 1;
                        } else { //两败俱伤
                            res[p1] = 1;
                            res[p2] = 1;
                        }
                    }
                }
                //如果p2是'B'
                if (teams[p2] == 'B') {
                    //如果p2也是'B'
                    if (teams[p1] == 'B') {
                        continue;
                    }
                    //如果p2是'R'
                    if (teams[p1] == 'R') {
                        if (lvs[p2] > lvs[p1]) {
                            res[p1] = 1;//p1负
                        } else if (lvs[p2] <= lvs[p1]) {
                            continue;//如果打不过p1,那就忍了。
                        }
                    }
                }
            }
            //4.p1,p2都公开
            if (openRole1 == 'Y' && openRole2 == 'Y') {
                //那就简单了
                if (teams[p1] == teams[p2]) {
                    continue;//大家自己人，过
                }
                //如果不一样，那么红色的一定会去攻击蓝色
                if (teams[p1] == 'R') {
                    if (lvs[p1] > lvs[p2]) {
                        res[p2] = 1;
                    } else if (lvs[p1] < lvs[p2]) {
                        res[p1] = 1;
                    } else {
                        res[p1] = 1;
                        res[p2] = 1;
                    }
                }
                if (teams[p2] == 'R') {
                    if (lvs[p2] > lvs[p1]) {
                        res[p1] = 1;
                    } else if (lvs[p2] < lvs[p1]) {
                        res[p2] = 1;
                    } else {
                        res[p1] = 1;
                        res[p2] = 1;
                    }
                }
            }
        }
        //OK 最后去判断res到底是什么结果
        for (int i = 1; i <= n; i++) {
            if (res[i] == 0) {
                System.out.print("W");
            } else {
                System.out.print("L");
            }
        }
    }
}
