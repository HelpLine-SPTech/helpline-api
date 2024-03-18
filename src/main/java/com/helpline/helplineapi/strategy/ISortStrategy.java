package com.helpline.helplineapi.strategy;

import com.helpline.helplineapi.entities.BaseEntity;

import java.util.List;

public interface ISortStrategy<T extends BaseEntity> {
    void sort(List<T> arr);

    void sortDescending(List<T> arr);
}
