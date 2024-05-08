package com.helpline.helplineapi.mappers;

import com.helpline.helplineapi.data.contract.user.UserContract;
import com.helpline.helplineapi.entities.user.BaseUserEntity;

import java.util.List;

public abstract class UserMapper {
    public static UserContract toUserResult(BaseUserEntity self) {
        return new UserContract(
                self.getId(),
                self.getName(),
                self.getEmail(),
                self.getDocument()
        );
    }

    public static List<UserContract> toUserResult(List<BaseUserEntity> self) {
        return self.stream().map(UserMapper::toUserResult).toList();
    }
}
