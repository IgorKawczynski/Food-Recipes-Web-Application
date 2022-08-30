package org.igogrzeg.recipes.user;

import lombok.RequiredArgsConstructor;
import org.igogrzeg.recipes.basic.ErrorsMapDto;
import org.igogrzeg.recipes.user.dtos.UserDto;
import org.igogrzeg.recipes.user.exceptions.NoSuchUserExists;
import org.igogrzeg.recipes.user.valueObjects.EmailValidator;
import org.springframework.stereotype.Service;
import java.util.HashMap;
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

    public UserEntity getUserEntityByEmail(String email) {
        return userRepository.findUserEntityByEmail(new EmailValidator(email))
                .orElseThrow( () -> new NoSuchUserExists(email) );
    }

    public UserDto getUserDtoById(Long id) {
        return userMapper.userEntityToUserDto(getUserEntityById(id));
    }

    public List<UserDto> getAllUsers() {
        var users = userRepository.findAll();
        return userMapper.userEntityListToUserDtoList(users);
    }

    public ErrorsMapDto changeUserByEmail(String email, UserDto changedUser) {

        ErrorsMapDto errors = new ErrorsMapDto( new HashMap<>());
        try{
            UserEntity userEntity = getUserEntityByEmail(email);
            userEntity.changeEmail(email);
        }
        catch(NoSuchUserExists exception) {
            errors.add("email", new NoSuchUserExists(email));
        }
        return errors;
    }

}
