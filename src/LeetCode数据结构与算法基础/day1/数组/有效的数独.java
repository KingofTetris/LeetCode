package LeetCode数据结构与算法基础.day1.数组;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author KingofTetris
 * @File 有效的数独
 * @Time 2021/10/1  20:34
 */

/*
36. 有效的数独
        请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。

        数字 1-9 在每一行只能出现一次。
        数字 1-9 在每一列只能出现一次。
        数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
        数独部分空格内已填入了数字，空白格用 '.' 表示。

        注意：

        一个有效的数独（部分已被填充）不一定是可解的。
        只需要根据以上规则，验证已经填入的数字是否有效即可。*/

public class 有效的数独 {

    @Test
    public void test() {
        char[][] sudoku = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        System.out.println(isValidSudoku(sudoku));


    }


    /**
     * 下面那种先遍历列，再遍历行，同时遍历小九宫格的方法太麻烦了。
     * 直接用官解这种一次遍历的方法，同时判断3个容器，只要有一个出现2,返回false
     * 最简单易懂，而且效率最快。
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] columns = new int[9][9];
        int[][][] subboxes = new int[3][3][9];
        //遍历board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {
                    //index就是记录1-9出现的次数
                    //因为下标0-9，所以多减个1。
                    int index = c - '0' - 1;

                    rows[i][index]++; //第i行
                    columns[j][index]++; //第j列
                    subboxes[i / 3][j / 3][index]++; //第 i/3,j/3个小九宫格。

                    if (rows[i][index] > 1
                            || columns[j][index] > 1
                            || subboxes[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /*作者：力扣官方题解
    链接：https://leetcode.cn/problems/valid-sudoku/solutions/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    /**
     * 这个也没什么好说的，还是遍历，处理行处理列。
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku2(char[][] board) {
        ArrayList<Character> row = new ArrayList<>();
        ArrayList<Character> col = new ArrayList<>();

        ArrayList<ArrayList<Character>> boxs = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            boxs.add(new ArrayList<>());//添加9个新的九宫格
        }

        for (int i = 0; i < board.length; i++) {
            //遍历列 0,0 1,0 2,0 ... 8,0
            col.clear();//每列前清空。
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] != '.') { //如果不是. 是数字，就判断列上有没有重复
                    if (col.contains(board[j][i])) {
                        return false;
                    } else {
                        col.add(board[j][i]);  //添加到col
                    }
                }
            }
            //遍历行
            //每个新行情况row
            row.clear();
            //但有个非常大的问题
            // 我们还要遍历每个9宫格。这个也是从左到右。遍历行的顺序。
            //问题是我们怎么确定这个小格子属于哪一个九宫格。
            //其实这个你得画个图看一下，假设格子的坐标是 [x,y]
            //那么他所属的九宫格应该是多少呢？我们假设9宫格的顺序是从左到右，从上到下。
            // 那么比如下标[5,5] 就属于第5个九宫格。
            //[2,8] 属于第7个九宫格。
            //有什么规律呢？？
            //我们再把每个九宫格看作是一个小单元，那么整个就是是一个3*3的矩阵。
            // [0,0] [0,1] [0,2]
            // [1,0] [1,1] [1,2]
            // [2,0] [2,1] [2,2]
            //这就是9个九宫格的位置
            //行决定了上下位置，列决定了左右位置
            // 5 / 3 = 1 ,5 / 3 = 1，就在中间的[1,1]
            // 2 / 3 = 0, 8 / 3 = 2; 就在[0,2]这个位置。
            //把这些九宫格化为0-8的顺序，那么
            // 5,5对应的应该是 5 / 3 * 3 + 5 / 3 = 4
            // 2,8 对应的是 2 / 3 * 3 + 8 / 3 = 2;
            //所以最终一个坐标[x,y]对应的九宫格其实就是 x/3 * 3 + y / 3;
            //那么我们只要把这个坐标放到对应的格子里面，然后判断是否重复就可以了。
            for (int j = 0; j < board[0].length; j++) {
                //行
                char rowTemp = board[i][j];
                if (rowTemp != '.') { //如果不是.
                    if (row.contains(rowTemp)) { //如果row重复
                        return false;
                    } else {
                        row.add(rowTemp);  //添加到row
                    }
                }
                //格子
                int coordination = i / 3 * 3 + j / 3; //本题的最难点，计算坐标对应的格子。
                ArrayList<Character> box = boxs.get(coordination);//对应的九宫格
                if (rowTemp != '.') {
                    if (box.contains(rowTemp)) {
                        return false;//重复
                    } else {
                        box.add(rowTemp);//不重复就加进去
                    }
                }
            }
        }
        //都不重复，返回true
        return true;
    }

}
