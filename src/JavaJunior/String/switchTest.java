package JavaJunior.String;

import java.util.Random;

/**
 * @author by KingOfTetris
 * @date 2024/9/21
 */
public class switchTest {

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int a = r.nextInt(2);
        switch(a){
            case 0:
                sb = new StringBuffer("1");
            case 1:
                sb = new StringBuffer("2");
            default:
                sb = new StringBuffer("l");
        }
        sb.append("l");
        sb.append("o");
        System.out.println(sb);
    }
}
