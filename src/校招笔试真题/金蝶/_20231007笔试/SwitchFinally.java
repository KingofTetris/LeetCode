package 校招笔试真题.金蝶._20231007笔试;

/**
 * @author by KingOfTetris
 * @date 2023/10/7
 */
public class SwitchFinally {

    public static void main(String[] args) {
        System.out.println("testBB(1) = " + testBB(1));
    }

    public static int testBB(int i){
        int result = 0;
        try {
            if (i == 1){
                throw  new RuntimeException();
            }
        }
        catch (Exception e){
            switch (i){
                case 1: result = result + i;
                case 2: result = result + 2 * i;
            }
        }finally {
            result = result + i * 2;
        }
        return result;
    }
}
