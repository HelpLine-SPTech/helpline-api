package com.helpline.helplineapi.strategy;

import com.helpline.helplineapi.entities.BaseEntity;

import java.util.List;

public class SelectionSortStrategy<T extends BaseEntity> implements ISortStrategy<T> {

    @Override
    public void sort(List<T> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j).getAddedAt().isBefore(arr.get(minIndex).getAddedAt())) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                T temp = arr.get(i);
                arr.set(i, arr.get(minIndex));
                arr.set(minIndex, temp);
            }
        }
    }

    @Override
    public void sortDescending(List<T> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j).getAddedAt().isAfter(arr.get(maxIndex).getAddedAt())) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                T temp = arr.get(i);
                arr.set(i, arr.get(maxIndex));
                arr.set(maxIndex, temp);
            }
        }
    }
}