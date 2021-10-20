package com.anish.util;

import java.lang.reflect.Array;

public class Dimension {

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> T[] changeDimension(Class<T> clazz, T[][] elements) {
        // 将二维的矩阵变为一维的向量
        int amount = elements.length * elements[0].length;
        T[] newArray = (T[]) Array.newInstance(clazz, amount);
        int curIndex = 0;

        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[0].length; j++) {
                newArray[curIndex++] = elements[i][j];
            }
        }

        return newArray;
    }
    
}
