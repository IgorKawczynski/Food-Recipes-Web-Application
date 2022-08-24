package org.igogrzeg.recipes.user;

import org.igogrzeg.recipes.user.dtos.UserDto;
import org.igogrzeg.recipes.user.valueObjects.EmailValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto fromUserEntityToUserDto(UserEntity userEntity) {
        return UserDto.builder()
                .nickname(setNickname(userEntity.getEmail()))
                .email(userEntity.getEmail().toString())
                .build();
    }

    public List<UserDto> fromUserEntityListToUserDtoList(List<UserEntity> users) {
        return users.stream()
                .map(this::fromUserEntityToUserDto)
                .collect(Collectors.toList());
    }

    public String setNickname(EmailValidator email){
        var nickName = email.toString();
        return nickName.substring(0, nickName.indexOf("@")); // cut string before '@' sign
    }

}
