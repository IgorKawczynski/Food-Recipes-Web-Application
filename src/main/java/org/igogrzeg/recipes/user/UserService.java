package org.igogrzeg.recipes.user;

import lombok.RequiredArgsConstructor;
import org.igogrzeg.recipes.user.dtos.UserDto;
import org.igogrzeg.recipes.user.exceptions.NoSuchUserExists;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto getUserById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow( () -> new NoSuchUserExists(id) );
        return userMapper.fromUserEntityToUserDto(user);
    }

}
