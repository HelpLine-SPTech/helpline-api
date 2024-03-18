package com.helpline.helplineapi.strategy;

import com.helpline.helplineapi.entities.BaseEntity;

import java.util.List;

public class BubbleSortStrategy<T extends BaseEntity> implements ISortStrategy<T> {

    @Override
    public void sort(List<T> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j).getAddedAt().isAfter(arr.get(j + 1).getAddedAt())) {
                    T temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }

    @Override
    public void sortDescending(List<T> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j).getAddedAt().isBefore(arr.get(j + 1).getAddedAt())) {
                    T temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                }
            }
        }
    }
}
