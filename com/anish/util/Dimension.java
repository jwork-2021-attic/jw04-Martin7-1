package com.anish.util;

import java.lang.reflect.Array;

public class Dimension {

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> T[] changeDimension(Class<T> monster, T[][] elements) {
        // 将二维的矩阵变为一维的向量
        int amount = elements.length * elements[0].length;
        T[] newArray = (T[]) Array.newInstance(monster, amount);
        int curIndex = 0;
        int rowIndex = 0;
        int columnIndex = 0;

        while (curIndex < amount) {
            newArray[curIndex++] = elements[rowIndex][columnIndex];
            if (columnIndex + 1 == elements[0].length) {
                // 重置列
                columnIndex = 0;
                rowIndex++;
            } else {
                columnIndex++;
            }
        }

        return newArray;
    }
    
}
