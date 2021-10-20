package com.anish.monsters;

import com.anish.util.Dimension;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T> {
    
    private String plan = "";
    private Class<T> classKind;
    private T[] elements;

    public QuickSorter(Class<T> kind) {
        this.classKind = kind;
    }

    @Override
    public void load(T[][] elements) {
        // change elements to one dimension
        this.elements = Dimension.changeDimension(classKind, elements);
    }

    @Override
    public void load(T[] elements) {
        // nothing to do in this class
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

    @Override
    public void sort() {
        // 选择排序
        // 即每次都选择最小值
        quickSort(0, this.elements.length - 1);
    }

    private void swap(int i, int j) {
        T temp;
        temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
        plan += "" + elements[i] + "<->" + elements[j] + "\n";
    }

    public void quickSort(int low, int high) {
        if(low > high) {
            return;
        }
        int i = low;
        int j = high;
        //temp就是基准位
        T temp = this.elements[low];

        while (i < j) {
            //先看右边，依次往左递减
            while (temp.compareTo(this.elements[j]) <= 0 && i < j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp.compareTo(this.elements[i]) >= 0 && i < j) {
                i++;
            }
            //如果满足条件则交换
            if (i < j) {
                swap(i, j);
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        swap(low, i);
        //递归调用左半数组
        quickSort(low, j-1);
        //递归调用右半数组
        quickSort(j + 1, high);
    }
}
