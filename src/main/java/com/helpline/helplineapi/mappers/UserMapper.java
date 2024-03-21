package com.helpline.helplineapi.mappers;

import com.helpline.helplineapi.data.contract.user.list.ListUserResult;
import com.helpline.helplineapi.entities.user.UserEntity;

import java.util.List;

public class UserMapper {
    public static ListUserResult toListUserResult(UserEntity self) {
        return new ListUserResult(
                self.getId(),
                self.getName(),
                self.getEmail(),
                self.getDocument()
        );
    }

    public static List<ListUserResult> toListUserResult(List<UserEntity> self) {
        return self.stream().map(UserMapper::toListUserResult).toList();
    }
}
