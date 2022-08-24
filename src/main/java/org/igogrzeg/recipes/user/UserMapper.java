package org.igogrzeg.recipes.user;

import org.igogrzeg.recipes.user.dtos.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto fromUserEntityToUserDto(UserEntity userEntity){
        return UserDto.builder()
                .email(userEntity.getEmail().toString())
                .build();
    }
}
