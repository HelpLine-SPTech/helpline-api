package com.helpline.helplineapi.strategy;

import com.helpline.helplineapi.entities.BaseEntity;

import java.util.List;

public class QuickSortStrategy<T extends BaseEntity> implements ISortStrategy<T> {

    @Override
    public void sort(List<T> arr) {
        quickSort(arr, 0, arr.size() - 1);
    }

    @Override
    public void sortDescending(List<T> arr) {
        quickSortDescending(arr, 0, arr.size() - 1);
    }

    private void quickSort(List<T> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(List<T> arr, int low, int high) {
        T pivot = arr.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr.get(j).getAddedAt().isBefore(pivot.getAddedAt())) {
                i++;

                T temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }

        T temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);

        return i + 1;
    }

    private void quickSortDescending(List<T> arr, int low, int high) {
        if (low < high) {
            int pi = partitionDescending(arr, low, high);

            quickSortDescending(arr, low, pi - 1);
            quickSortDescending(arr, pi + 1, high);
        }
    }

    private int partitionDescending(List<T> arr, int low, int high) {
        T pivot = arr.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr.get(j).getAddedAt().isAfter(pivot.getAddedAt())) {
                i++;

                T temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }

        T temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);

        return i + 1;
    }
}
