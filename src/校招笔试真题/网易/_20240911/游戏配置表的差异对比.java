package 校招笔试真题.网易._20240911;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author by KingOfTetris
 * @date 2024/9/11
 */
public class 游戏配置表的差异对比 {
    public ArrayList<ArrayList<Long>> diff (
            ArrayList<Long> leftIds,
            ArrayList<String> leftValues,
            ArrayList<Long> rightIds,
            ArrayList<String> rightValues) {
        // write code here
        //对比新增了几个，修改了几个，删除了几个
        ArrayList<ArrayList<Long>> res = new ArrayList<>();
        LinkedHashMap<Long,String> old = new LinkedHashMap<>();
        LinkedHashMap<Long,String> New = new LinkedHashMap<>();
        for (int i = 0; i < leftIds.size(); i++) {
            old.put(leftIds.get(i),leftValues.get(i));
        }
        for (int i = 0; i < rightIds.size(); i++) {
            New.put(rightIds.get(i),rightValues.get(i));
        }
        //然后对比
        ArrayList<Long> addList = new ArrayList<>();
        ArrayList<Long> changeList = new ArrayList<>();
        ArrayList<Long> removeList = new ArrayList<>();

        for (Map.Entry<Long, String> entry : New.entrySet()) {
            //如果老的没有这个key 说明是新增的
            Long key = entry.getKey();
            if (!old.containsKey(key)){
                addList.add(key);
            }
            //如果老的有这个key但是值不相等，说明修改了
            if (old.containsKey(key) && !old.get(key).equals(New.get(key))){
                changeList.add(key);
            }
        }

        for (Map.Entry<Long, String> entry : old.entrySet()) {
            //如果老的有，但是新的没有说明是删除了。
            Long key = entry.getKey();
            if (!New.containsKey(key)){
                removeList.add(key);
            }
        }

        res.add(addList);
        res.add(changeList);
        res.add(removeList);

        return res;
    }
}
