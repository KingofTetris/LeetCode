package JDBCTest;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author by KingOfTetris
 * @date 2022/9/5
 */
public class DAT {
    @Test
    public void test(){
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < 16; i++) {
            map.put(i,i + 1);
        }

    }

}
