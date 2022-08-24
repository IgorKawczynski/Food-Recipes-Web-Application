package org.igogrzeg.recipes.user;

import lombok.RequiredArgsConstructor;
import org.igogrzeg.recipes.user.dtos.UserDto;
import org.igogrzeg.recipes.user.exceptions.NoSuchUserExists;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final int PAGE_SIZE = 12;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto getUserById(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow( () -> new NoSuchUserExists(id) );
        return userMapper.fromUserEntityToUserDto(user);
    }

    public List<UserDto> getAllUsers(Integer pageNumber) {
        var users = userRepository.findAll(PageRequest.of(pageNumber, PAGE_SIZE, Sort.by("id"))).stream().toList();
        return userMapper.fromUserEntityListToUserDtoList(users);
    }

}
