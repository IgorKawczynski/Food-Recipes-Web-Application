package org.igogrzeg.recipes.user;

import lombok.RequiredArgsConstructor;
import org.igogrzeg.recipes.basic.ErrorsMapDto;
import org.igogrzeg.recipes.user.dtos.UserRegistrationDto;
import org.igogrzeg.recipes.user.exceptions.UserWithGivenEmailAlreadyExists;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserRegistrationService {

    private final UserRepository userRepository;

    public ErrorsMapDto registerUser(UserRegistrationDto userDto) {
        var errorsMap = new ErrorsMapDto(new HashMap<>());
        var user = new UserEntity(userDto);

        if ( userExistByEmail(user) ) {
            errorsMap.add( "email", new UserWithGivenEmailAlreadyExists() );
        }
        if ( errorsMap.isEmpty() ) {
            userRepository.save(user);
        }

        return errorsMap;
    }

//    userRepository.existByEmailValidator(user.getEmail());
    private Boolean userExistByEmail(UserEntity user) {
        return true;
    }

}
