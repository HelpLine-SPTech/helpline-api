package com.helpline.helplineapi.mappers;

import com.helpline.helplineapi.data.contract.user.UserResult;
import com.helpline.helplineapi.entities.user.UserEntity;

import java.util.List;

public class UserMapper {
    public static UserResult toUserResult(UserEntity self) {
        return new UserResult(
                self.getId(),
                self.getName(),
                self.getEmail(),
                self.getDocument()
        );
    }

    public static List<UserResult> toUserResult(List<UserEntity> self) {
        return self.stream().map(UserMapper::toUserResult).toList();
    }
}
