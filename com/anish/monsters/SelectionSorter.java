package com.anish.monsters;

import com.anish.util.Dimension;

public class SelectionSorter<T extends Comparable<T>> implements Sorter<T> {
    
    private String plan = "";
    private Class<T> classKind;
    private T[] elements;

    public SelectionSorter(Class<T> kind) {
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
        selectionSort(0, this.elements.length - 1);
    }

    private void selectionSort(int startIndex, int endIndex) {
        if (startIndex == endIndex) {
            return;
        }

        T minElement = this.elements[startIndex];
        for (int i = startIndex; i <= endIndex; i++) {
            if (minElement.compareTo(this.elements[i]) > 0) {
                minElement = this.elements[i];
            }
        }

        // 找出最小元素并且与elements[startIndex]来交换
        for (int i = startIndex; i <= endIndex; i++) {
            if (this.elements[i].equals(minElement)) {
                swap(startIndex, i);
            }
        }
    }

    private void swap(int i, int j) {
        T temp;
        temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
        plan += "" + elements[i] + "<->" + elements[j] + "\n";
    }
}
