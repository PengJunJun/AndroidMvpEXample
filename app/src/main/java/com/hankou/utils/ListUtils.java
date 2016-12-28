package com.hankou.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bykj003 on 2016/12/13.
 */

public class ListUtils {

    public static ArrayList<String> removeSameItemFromList(ArrayList<String> list) {
        final ArrayList<String> resultList = list;
        String result = "";
        String targetResult = "";
        for (int n = 0; n < resultList.size(); n++) {
            result = resultList.get(n).toString();
            for (int m = (n + 1); m < resultList.size(); m++) {
                targetResult = resultList.get(m).toString();
                if (!StringUtils.isEmpty(result) &&
                        !StringUtils.isEmpty(targetResult) &&
                        targetResult.equals(result)) {
                    resultList.remove(m);
                }
            }
        }
        return resultList;
    }
}
