package org.igogrzeg.recipes.user;

import lombok.RequiredArgsConstructor;
import org.igogrzeg.recipes.basic.ErrorsMapDto;
import org.igogrzeg.recipes.user.dtos.UserDto;
import org.igogrzeg.recipes.user.dtos.UserRegistrationDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRegistrationService userRegistrationService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable Long id) {
        return userService.getUserDtoById(id);
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping(value = "/{email}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ErrorsMapDto changeUserByEmail(@PathVariable String email, @RequestBody UserDto userDto) {
        return userService.changeUserByEmail(email, userDto);
    }

    @PostMapping("/create-user")
    @ResponseStatus(HttpStatus.CREATED)
    public ErrorsMapDto createUser(@RequestBody UserRegistrationDto userDto) {
        return userRegistrationService.registerUser(userDto);
    }

}
