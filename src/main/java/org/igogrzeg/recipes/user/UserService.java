package org.igogrzeg.recipes.user;

import lombok.RequiredArgsConstructor;
import org.igogrzeg.recipes.user.dtos.UserDto;
import org.igogrzeg.recipes.user.exceptions.NoSuchUserExists;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserEntity getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow( () -> new NoSuchUserExists(id) );
    }

    public UserDto getUserDtoById(Long id) {
        return userMapper.userEntityToUserDto(getUserEntityById(id));
    }

    public List<UserDto> getAllUsers() {
        var users = userRepository.findAll();
        return userMapper.userEntityListToUserDtoList(users);
    }

}
