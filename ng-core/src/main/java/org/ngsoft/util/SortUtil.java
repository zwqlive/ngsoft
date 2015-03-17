package org.ngsoft.util;

import java.util.List;

/**
 * Created by will on 2015-3-16.
 */
public class SortUtil {


    /**
     * 插入排序
     * @param sortedList 已经排好序的列表
     * @param t
     * @param <T>
     */
    public static <T extends Comparable<T>> void addToSortedList(List<T> sortedList, T t) {
        if (sortedList.isEmpty()) {
            sortedList.add(t);
        } else {
            //二分法查找插入
            int insertIndex = -1;
            int teamSize = sortedList.size();
            int max = teamSize;
            int min = 0;
            while (true) {
                int mode = (max - min) % 2;
                int middle = min + (max - min) / 2 + mode;
                T middleItem = sortedList.get(middle - 1);
                if (middleItem.compareTo(t) == 0) {
                    insertIndex = middle;
                    break;
                }
                if (middleItem.compareTo(t) < 0) {
                    max = middle - mode;
                } else {
                    min = middle;
                }
                if (max - min == 0) {
                    insertIndex = max;
                    break;
                }
            }
            if (insertIndex != -1) {
                sortedList.add(insertIndex, t);
            }
        }
    }
}
