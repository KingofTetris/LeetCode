package 校招笔试真题.金蝶._20231007笔试;

/**
 * @author by KingOfTetris
 * @date 2023/10/7
 */
public class TryCatchFinallyTest {


    public static void main(String[] args) {
        System.out.println("testAA(8,33) = " + testAA(8, 33));
    }


    public static int testAA(int a,int b){
        try {
            return a + b;
        }
        catch (Exception e){

        }
        finally {
            System.out.println("finally代码块");
        }
        //finally外面的代码是不可达的，操阿！
        System.out.println("finally代码块外面");
        return 0;
    }
}
